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
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.toLowerCase;
import static java.lang.String.format;
import static java.util.Optional.*;
import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.compile;

public final class GivenWhenThenNameGenerator {
    private interface Style {
        default Optional<String> forClass(Class<?> clazz) {
            return empty();
        }

        default Optional<String> forNestedClass(Class<?> clazz) {
            return empty();
        }

        default Optional<String> forMethod(Class<?> clazz, Method method) {
            return empty();
        }
    }

    private static class StyleBase extends DisplayNameGenerator.Standard implements Style {
        @Override
        public String generateDisplayNameForClass(Class<?> testClass) {
            return forClass(testClass).orElseGet(() -> super.generateDisplayNameForClass(testClass));
        }

        @Override
        public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
            return forNestedClass(nestedClass).orElseGet(() -> super.generateDisplayNameForNestedClass(nestedClass));
        }

        @Override
        public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
            return forMethod(testClass, testMethod).orElseGet(() -> super.generateDisplayNameForMethod(testClass, testMethod));
        }
    }

    public static final class Standard extends StyleBase {
        private final Pattern pattern = compile("(?:given(?<given>[a-zA-Z0-9]*))?[_]?when(?<when>[a-zA-Z0-9]*)[_]?then(?<then>[a-zA-Z0-9]*)", CASE_INSENSITIVE);
        private final Pattern given_subpattern = compile("(?<given>[a-zA-Z0-9]*)(?<mod>isNull|areNull|isEmpty|areEmpty)", CASE_INSENSITIVE);
        private final Pattern when_subpattern = compile("(?<mod>instantiating|creating|invoking|calling|building|compareEqual)?(?<when>[a-zA-Z0-9]*)", CASE_INSENSITIVE);
        private final Pattern when_calling_subpattern = compile("(?<method>[a-zA-Z0-9]+)on(?<obj>[a-zA-Z0-9]+)", CASE_INSENSITIVE);
        private final Pattern then_subpattern = compile("(?<mod>return|throw|initialize|invoke|call|forward)?(?<then>[a-zA-Z0-9]*)", CASE_INSENSITIVE);

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
            Matcher matcher = pattern.matcher(name);
            return Optional.ofNullable(matcher.matches() ? format("given %s, when %s, %s",
                    given(matcher.group("given")), when(matcher.group("when")),
                    then(matcher.group("then"))) : name);
        }

        private String given(String substring) {
            Matcher matcher = given_subpattern.matcher(substring);
            if (matcher.matches()) {
                switch (matcher.group("mod").toLowerCase()) {
                    case "isempty":
                    case "areempty":
                        return format("%s == ∅", compat.slc(matcher.group("given")));
                    case "isnull":
                    case "arenull":
                        return format("%s == null", compat.slc(matcher.group("given")));
                    default:
                        return compat.slc(substring);
                }
            } else {
                return compat.slc(substring);
            }
        }

        private String when(String substring) {
            return subpattern().apply(substring, when_subpattern)
                    .apply("mod", "when").apply(this::when);
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
                    Matcher matcher = when_calling_subpattern.matcher(content);
                    if (matcher.matches()) {
                        String method = matcher.group("method");
                        return format("%s.%c%s()", matcher.group("obj"),
                                toLowerCase(method.charAt(0)), method.substring(1));
                    } else {
                        return format("???.%c%s()", toLowerCase(content.charAt(0)),
                                content.substring(1));
                    }
                case "compareequal":
                    return "$0 == $1";
                default:
                    return compat.slc(modifier + content);
            }
        }

        private String then(String substring) {
            return subpattern().apply(substring, then_subpattern)
                    .apply("mod", "then").apply(this::then);
        }

        private String then(String modifier, String content) {
            switch (modifier.toLowerCase()) {
                case "throw":
                    return format("throw %s", content);
                case "forward":
                    return format("forward %s", content);
                case "invoke":
                case "call":
                    Matcher matcher = when_calling_subpattern.matcher(content);
                    if (matcher.matches()) {
                        String method = matcher.group("method");
                        return format("%s.%c%s()", matcher.group("obj"),
                                toLowerCase(method.charAt(0)), method.substring(1));
                    } else {
                        return format("???.%c%s()", toLowerCase(content.charAt(0)),
                                content.substring(1));
                    }
                case "initialize":
                case "return":
                    return compat.slc(modifier + content);
                default:
                    return format("then %s", compat.slc(modifier + content));
            }
        }

        private BiFunction<String, Pattern, BiFunction<String, String, Function<BiFunction<String, String, String>, String>>> subpattern() {
            return (substring, subpattern) -> (modifier, content) -> handler -> {
                Matcher matcher = subpattern.matcher("");
                String[] segments = substring.split("[aA]nd");

                for (int i = 0; i < segments.length; i++) {
                    segments[i] = matcher.reset(segments[i]).matches()
                            && matcher.group(modifier) != null
                            ? handler.apply(matcher.group(modifier), matcher.group(content))
                            : compat.slc(segments[i]);
                }

                return String.join(" and ", segments);
            };
        }
    }

    public static final class Compat extends StyleBase {
        private final Pattern pattern = compile("(?<name>[a-zA-Z0-9]+)(?=Test)");

        @Override
        public Optional<String> forClass(Class<?> clazz) {
            String name = clazz.getName();
            name = name.substring(name.lastIndexOf('.') + 1);
            Matcher matcher = pattern.matcher(name);
            return of(matcher.find() ? matcher.group("name") : name);
        }

        @Override
        public Optional<String> forNestedClass(Class<?> clazz) {
            return of(slc(clazz.getSimpleName()));
        }

        @Override
        public Optional<String> forMethod(Class<?> clazz, Method method) {
            for (Annotation annotation : method.getAnnotations()) {
                if (Objects.equals(annotation.annotationType().getCanonicalName(),
                        "io.carbynestack.testing.command.CommandSource")) {
                    return forCommandSourceMethod(annotation);
                }
            }
            return of(method.getName());
        }

        private Optional<String> forCommandSourceMethod(Annotation annotation) {
            try {
                return of(annotation.annotationType().getDeclaredMethod("args")).flatMap(method -> {
                    try {
                        return ofNullable((String[]) method.invoke(annotation)).map(arr ->
                                format("$ cs %s", String.join(" ", arr)));
                    } catch (IllegalAccessException | InvocationTargetException ignored) {
                        return empty();
                    }
                });
            } catch (NoSuchMethodException ignored) {
                return empty();
            }
        }

        private String slc(String substring) {
            String[] segments = substring.split("(?=\\p{Lu})");

            for (int i = 0; i < segments.length; i++) {
                segments[i] = segments[i].toLowerCase();
            }

            return String.join(" ", segments);
        }
    }
}

