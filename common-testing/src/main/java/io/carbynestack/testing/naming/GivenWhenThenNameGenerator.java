/*
 * Copyright (c) 2022 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/carbynestack/common.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package io.carbynestack.testing.naming;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.lang.Character.toLowerCase;
import static java.lang.String.format;
import static java.lang.String.join;
import static java.util.Collections.emptySet;
import static java.util.Optional.*;
import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.compile;

public final class GivenWhenThenNameGenerator {
    public static final class Standard extends StyleBase {
        private static final String NAME = "(?:given(?<given>[a-zA-Z0-9]*))?[_]?when(?<when>[a-zA-Z0-9]*)[_]?then(?<then>[a-zA-Z0-9]*)";
        private static final String GIVEN = "(?<given>[a-zA-Z0-9]*)(?<mod>isNull|areNull|isEmpty|areEmpty)";
        private static final String WHEN = "(?<mod>instantiating|creating|invoking|calling|building)?(?<when>[a-zA-Z0-9]*)";
        private static final String CALL = "(?<method>[a-zA-Z0-9]+)on(?<obj>[a-zA-Z0-9]+)";
        private static final String THEN = "(?<mod>return|throw|initialize|invoke|call|forward)?(?<then>[a-zA-Z0-9]*)";
        private static final String AND = "[aA]nd";

        private static final Pattern P_NAME = compile(NAME, CASE_INSENSITIVE);
        private static final Pattern P_GIVEN = compile(GIVEN, CASE_INSENSITIVE);
        private static final Pattern P_WHEN = compile(WHEN, CASE_INSENSITIVE);
        private static final Pattern P_CALL = compile(CALL, CASE_INSENSITIVE);
        private static final Pattern P_THEN = compile(THEN, CASE_INSENSITIVE);
        private static final Pattern P_AND = compile(AND);

        private final Compat compat = new Compat();

        @Override
        public Optional<String> forClass(Class<?> clazz) {
            return compat.forClass(clazz);
        }

        @Override
        public Optional<String> forNestedClass(Class<?> clazz) {
            return compat.forNestedClass(clazz);
        }

        @Override
        public Optional<String> forMethod(Class<?> clazz, Method method) {
            return compat.forMethod(clazz, method).flatMap(this::forMethod);
        }

        private Optional<String> forMethod(String name) {
            Matcher m = P_NAME.matcher(name);
            return ofNullable(m.matches() ? format("given %s, when %s, %s",
                    given(m.group("given")), when(m.group("when")),
                    then(m.group("then"))) : name);
        }

        private String given(String substring) {
            Matcher m = P_GIVEN.matcher(substring);
            if (!m.matches()) {
                return compat.space(substring);
            } else {
                switch (m.group("mod").toLowerCase()) {
                    case "isempty":
                    case "areempty":
                        return format("%s == ∅", compat.space(m.group("given")));
                    case "isnull":
                    case "arenull":
                        return format("%s == null", compat.space(m.group("given")));
                    default:
                        return compat.space(substring);
                }
            }
        }

        private String toMixedCase(String content) {
            return format("%c%s", toLowerCase(content.charAt(0)), content.substring(1));
        }

        private String call(String content) {
            Matcher m = P_CALL.matcher(content);
            if (m.matches()) {
                return format("%s.%s()", m.group("obj"),
                        toMixedCase(m.group("method")));
            } else {
                return format("???.%s()", toMixedCase(content));
            }
        }

        private String when(String substring) {
            return subpattern(substring, P_WHEN, "when", this::when);
        }

        private String when(String modifier, String content) {
            switch (modifier.toLowerCase()) {
                case "instantiating":
                case "creating":
                    return format("new %s()", content);
                case "building":
                    return format("%s.builder().???()", content);
                case "invoking":
                case "calling":
                    return call(content);
                default:
                    return compat.space(modifier + content);
            }
        }

        private String then(String substring) {
            return subpattern(substring, P_THEN, "then", this::then);
        }

        private String then(String modifier, String content) {
            switch (modifier.toLowerCase()) {
                case "throw":
                    return format("throw %s", content);
                case "forward":
                    return format("forward %s", content);
                case "invoke":
                case "call":
                    return call(content);
                case "initialize":
                case "return":
                    return compat.space(modifier + content);
                default:
                    return format("then %s", compat.space(modifier + content));
            }
        }

        private String subpattern(String substring, Pattern subpattern, String content, BiFunction<String, String, String> handler) {
            Matcher m = subpattern.matcher("");
            String[] segments = P_AND.split(substring);

            for (int i = 0; i < segments.length; i++) {
                segments[i] = m.reset(segments[i]).matches()
                        && m.group("mod") != null
                        ? handler.apply(m.group("mod"), m.group(content))
                        : compat.space(segments[i]);
            }

            return join(" and ", segments);
        }
    }

    public static final class Compat extends StyleBase {
        private static final String COMMAND_SOURCE = "io.carbynestack.testing.command.CommandSource";
        private static final String NAME = "(?<name>[a-zA-Z0-9]+)(?=Test)";
        private static final String UPPER_CASE = "(?=\\p{Lu})";
        private static final Pattern P_NAME = compile(NAME);
        private static final Pattern P_UPPER_CASE = compile(UPPER_CASE);

        @Override
        public Optional<String> forClass(Class<?> clazz) {
            String name = clazz.getName();
            name = name.substring(name.lastIndexOf('.') + 1);
            Matcher m = P_NAME.matcher(name);
            return of(m.find() ? m.group("name") : name);
        }

        @Override
        public Optional<String> forNestedClass(Class<?> clazz) {
            return of(space(clazz.getSimpleName()));
        }

        @Override
        public Optional<String> forMethod(Class<?> clazz, Method method) {
            for (Annotation annot : method.getAnnotations()) {
                if (Objects.equals(annot.annotationType().getCanonicalName(), COMMAND_SOURCE)) {
                    return forCommandSourceMethod(annot);
                }
            }
            return of(method.getName());
        }

        private Optional<String> forCommandSourceMethod(Annotation annot) {
            return of(annot.annotationType()).flatMap(type -> {
                try {
                    return of(type.getDeclaredMethod("args"));
                } catch (NoSuchMethodException ignored) {
                    return empty();
                }
            }).flatMap(method -> {
                try {
                    return ofNullable((String[]) method.invoke(annot));
                } catch (IllegalAccessException | InvocationTargetException ignored) {
                    return empty();
                }
            }).map(arr -> format("$ cs %s", join(" ", arr)));
        }

        private String space(String substring) {
            class SpaceJoinCollector implements Collector<String, List<String>, String> {
                private final StringBuilder temp = new StringBuilder();

                @Override
                public Supplier<List<String>> supplier() {
                    return ArrayList::new;
                }

                @Override
                public BiConsumer<List<String>, String> accumulator() {
                    return (l, e) -> {
                        if (e.length() == 1) {
                            temp.append(e);
                        } else {
                            String t = temp.toString();
                            if (!t.isEmpty()) {
                                l.add(t.toLowerCase());
                                temp.setLength(0);
                            }
                            l.add(e.toLowerCase());
                        }
                    };
                }

                @Override
                public BinaryOperator<List<String>> combiner() {
                    return (l, o) -> l;
                }

                @Override
                public Function<List<String>, String> finisher() {
                    return l -> join(" ", l);
                }

                @Override
                public Set<Characteristics> characteristics() {
                    return emptySet();
                }
            }

            return Stream.of(P_UPPER_CASE.split(substring))
                    .collect(new SpaceJoinCollector());
        }
    }

    private static class StyleBase extends DisplayNameGenerator.Standard {
        public Optional<String> forClass(Class<?> clazz) {
            return empty();
        }

        @Override
        public String generateDisplayNameForClass(Class<?> testClass) {
            return forClass(testClass).orElseGet(() ->
                    super.generateDisplayNameForClass(testClass));
        }

        public Optional<String> forNestedClass(Class<?> clazz) {
            return empty();
        }

        @Override
        public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
            return forNestedClass(nestedClass).orElseGet(() ->
                    super.generateDisplayNameForNestedClass(nestedClass));
        }

        public Optional<String> forMethod(Class<?> clazz, Method method) {
            return empty();
        }

        @Override
        public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
            return forMethod(testClass, testMethod).orElseGet(() ->
                    super.generateDisplayNameForMethod(testClass, testMethod));
        }
    }
}

