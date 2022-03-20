/*
 * Copyright (c) 2022 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/carbynestack/common.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package io.carbynestack.testing.naming;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.assertj.core.api.Assertions.assertThat;

public class GivenWhenThenStandardNameGeneratorCsTest {
    @Test
    public void givenNullAsServiceAddress_whenCreatingAmphoraServiceUri_thenThrowIllegalArgumentException(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given null as service address, when new "
                + "AmphoraServiceUri(), throw IllegalArgumentException");
    }

    @Test
    public void givenNullAsServiceAddressWhenCreatingAmphoraServiceUriThenThrowIllegalArgumentException(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given null as service address, when new "
                + "AmphoraServiceUri(), throw IllegalArgumentException");
    }

    @Test
    public void givenEmptyStringAsServiceAddress_whenCreatingAmphoraServiceUri_thenThrowIllegalArgumentException(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given empty string as service address, when "
                + "new AmphoraServiceUri(), throw IllegalArgumentException");
    }

    @Test
    public void givenEmptyStringAsServiceAddressWhenCreatingAmphoraServiceUriThenThrowIllegalArgumentException(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given empty string as service address, when "
                + "new AmphoraServiceUri(), throw IllegalArgumentException");
    }

    @Test
    public void givenNoSchemeDefined_whenCreatingAmphoraServiceUri_thenThrowIllegalArgumentException(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given no scheme defined, when new "
                + "AmphoraServiceUri(), throw IllegalArgumentException");
    }

    @Test
    public void givenNoSchemeDefinedWhenCreatingAmphoraServiceUriThenThrowIllegalArgumentException(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given no scheme defined, when new "
                + "AmphoraServiceUri(), throw IllegalArgumentException");
    }

    @Test
    public void givenInvalidUriString_whenCreatingAmphoraServiceUri_thenThrowIllegalArgumentException(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given invalid uri string, when new "
                + "AmphoraServiceUri(), throw IllegalArgumentException");
    }

    @Test
    public void givenInvalidUriStringWhenCreatingAmphoraServiceUriThenThrowIllegalArgumentException(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given invalid uri string, when new "
                + "AmphoraServiceUri(), throw IllegalArgumentException");
    }

    @Test
    public void givenUriStringWithDomain_whenCreatingAmphoraServiceUri_thenCreateExpectedAmphoraServiceUri(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given uri string with domain, when new "
                + "AmphoraServiceUri(), create expected amphora service uri");
    }

    @Test
    public void givenUriStringWithDomainWhenCreatingAmphoraServiceUriThenCreateExpectedAmphoraServiceUri(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given uri string with domain, when new "
                + "AmphoraServiceUri(), create expected amphora service uri");
    }

    @Test
    public void givenUriStringWithIpAndPort_whenCreatingAmphoraServiceUri_thenCreateExpectedAmphoraServiceUri(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given uri string with ip and port, when "
                + "new AmphoraServiceUri(), create expected amphora service uri");
    }

    @Test
    public void givenUriStringWithIpAndPortWhenCreatingAmphoraServiceUriThenCreateExpectedAmphoraServiceUri(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given uri string with ip and port, when "
                + "new AmphoraServiceUri(), create expected amphora service uri");
    }

    @Test
    public void givenUriStringWithoutPort_whenCreatingAmphoraServiceUri_thenCreateExpectedAmphoraServiceUri(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given uri string without port, when new "
                + "AmphoraServiceUri(), create expected amphora service uri");
    }

    @Test
    public void givenUriStringWithoutPortWhenCreatingAmphoraServiceUriThenCreateExpectedAmphoraServiceUri(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given uri string without port, when new "
                + "AmphoraServiceUri(), create expected amphora service uri");
    }

    @Test
    public void givenUriStringWithPath_whenCreatingAmphoraServiceUri_thenCreateExpectedAmphoraServiceUri(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given uri string with path, when new "
                + "AmphoraServiceUri(), create expected amphora service uri");
    }

    @Test
    public void givenUriStringWithPathWhenCreatingAmphoraServiceUriThenCreateExpectedAmphoraServiceUri(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given uri string with path, when new "
                + "AmphoraServiceUri(), create expected amphora service uri");
    }

    @Test
    public void givenUriStringWithPortAndPath_whenCreatingAmphoraServiceUri_thenCreateExpectedAmphoraServiceUri(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given uri string with port and path, when "
                + "new AmphoraServiceUri(), create expected amphora service uri");
    }

    @Test
    public void givenUriStringWithPortAndPathWhenCreatingAmphoraServiceUriThenCreateExpectedAmphoraServiceUri(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given uri string with port and path, when "
                + "new AmphoraServiceUri(), create expected amphora service uri");
    }

    @Test
    public void givenAmphoraServiceUri_whenGetSecretShareUri_thenReturnExpectedUri(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given amphora service uri, when get secret "
                + "share uri, return expected uri");
    }

    @Test
    public void givenAmphoraServiceUriWhenGetSecretShareUriThenReturnExpectedUri(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given amphora service uri, when get secret "
                + "share uri, return expected uri");
    }

    @Test
    public void givenAmphoraServiceUri_whenGetMaskedInputUri_thenReturnExpectedUri(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given amphora service uri, when get masked "
                + "input uri, return expected uri");
    }

    @Test
    public void givenAmphoraServiceUriWhenGetMaskedInputUriThenReturnExpectedUri(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given amphora service uri, when get masked "
                + "input uri, return expected uri");
    }

    @Test
    public void givenAmphoraServiceUri_whenGetInputMaskUri_thenReturnExpectedUri(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given amphora service uri, when get input "
                + "mask uri, return expected uri");
    }

    @Test
    public void givenAmphoraServiceUriWhenGetInputMaskUriThenReturnExpectedUri(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given amphora service uri, when get input "
                + "mask uri, return expected uri");
    }

    @Test
    public void givenUriStringWithTrailingSlash_whenCreateAmphoraServiceUri_thenReturnExpectedAmphoraServiceUri(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given uri string with trailing slash, when "
                + "create amphora service uri, return expected amphora service uri");
    }

    @Test
    public void givenUriStringWithTrailingSlashWhenCreateAmphoraServiceUriThenReturnExpectedAmphoraServiceUri(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given uri string with trailing slash, when "
                + "create amphora service uri, return expected amphora service uri");
    }

    @Test
    public void givenValidPathSegments_whenBuildingResourceUri_thenReturnExpectedUri(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given valid path segments, when "
                + "ResourceUri.builder().???(), return expected uri");
    }

    @Test
    public void givenValidPathSegmentsWhenBuildingResourceUriThenReturnExpectedUri(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given valid path segments, when "
                + "ResourceUri.builder().???(), return expected uri");
    }

    @Test
    public void givenIdIsNull_whenCreatingNewSecret_thenThrowException(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given id == null, when new NewSecret(), "
                + "throw Exception");
    }

    @Test
    public void givenIdIsNullWhenCreatingNewSecretThenThrowException(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given id == null, when new NewSecret(), "
                + "throw Exception");
    }

    @Test
    public void givenTagsAreNull_whenCreatingMaskedInput_thenUseEmptyListInstead(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given tags == null, when new MaskedInput(), "
                + "use empty list instead");
    }

    @Test
    public void givenTagsAreNullWhenCreatingMaskedInputThenUseEmptyListInstead(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given tags == null, when new MaskedInput(), "
                + "use empty list instead");
    }

    @Test
    public void givenIdIsNull_whenCallingBuildOnBuilder_thenThrowException(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given id == null, when Builder.build(), "
                + "throw Exception");
    }

    @Test
    public void givenIdIsNullWhenCallingBuildOnBuilderThenThrowException(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given id == null, when Builder.build(), "
                + "throw Exception");
    }

    @Test
    public void givenListWithNullTags_whenSettingTagsOnBuilder_thenRemoveNullTagsFromList(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given list with null tags, when setting tags "
                + "on builder, remove null tags from list");
    }

    @Test
    public void givenListWithNullTagsWhenSettingTagsOnBuilderThenRemoveNullTagsFromList(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given list with null tags, when setting tags "
                + "on builder, remove null tags from list");
    }

    @Test
    public void givenURLSharesOfDifferentLengthThanSecretShares_whenCallingBuildOnBuilder_thenThrowException(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given url shares of different length than "
                + "secret shares, when Builder.build(), throw Exception");
    }

    @Test
    public void givenURLSharesOfDifferentLengthThanSecretSharesWhenCallingBuildOnBuilderThenThrowException(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given url shares of different length than "
                + "secret shares, when Builder.build(), throw Exception");
    }

    @Test
    public void givenUSharesOfDifferentLengthThanSecretShares_whenCallingBuildOnBuilder_thenThrowException(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given u shares of different length than secret "
                + "shares, when Builder.build(), throw Exception");
    }

    @Test
    public void givenUSharesOfDifferentLengthThanSecretSharesWhenCallingBuildOnBuilderThenThrowException(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given u shares of different length than secret "
                + "shares, when Builder.build(), throw Exception");
    }

    @Test
    public void givenVSharesOfDifferentLengthThanSecretShares_whenCallingBuildOnBuilder_thenThrowException(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given v shares of different length than secret "
                + "shares, when Builder.build(), throw Exception");
    }

    @Test
    public void givenVSharesOfDifferentLengthThanSecretSharesWhenCallingBuildOnBuilderThenThrowException(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given v shares of different length than secret "
                + "shares, when Builder.build(), throw Exception");
    }

    @Test
    public void givenValidBuilderConfiguration_whenCallingBuildOnBuilder_thenReturnObject(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given valid builder configuration, when "
                + "Builder.build(), return object");
    }

    @Test
    public void givenValidBuilderConfigurationWhenCallingBuildOnBuilderThenReturnObject(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given valid builder configuration, when "
                + "Builder.build(), return object");
    }

    @Test
    public void givenDataOfInvalidLength_whenBuildSecretShare_thenThrowIllegalArgumentException(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given data of invalid length, when build "
                + "secret share, throw IllegalArgumentException");
    }

    @Test
    public void givenDataOfInvalidLengthWhenBuildSecretShareThenThrowIllegalArgumentException(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given data of invalid length, when build "
                + "secret share, throw IllegalArgumentException");
    }

    @Test
    public void givenDataOfVariableButValidLength_whenBuildSecretShare_thenSucceed(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given data of variable but valid length, "
                + "when build secret share, succeed");
    }

    @Test
    public void givenDataOfVariableButValidLengthWhenBuildSecretShareThenSucceed(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given data of variable but valid length, "
                + "when build secret share, succeed");
    }

    @Test
    public void givenKeyWithTooManyCharacters_whenBuildingTag_thenThrowIllegalArgumentException(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given key with too many characters, when "
                + "Tag.builder().???(), throw IllegalArgumentException");
    }

    @Test
    public void givenKeyWithTooManyCharactersWhenBuildingTagThenThrowIllegalArgumentException(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given key with too many characters, when "
                + "Tag.builder().???(), throw IllegalArgumentException");
    }

    @Test
    public void givenHttpClientThrowsException_whenFetchingDataFromOnePlayer_thenForwardException(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given http client throws exception, when "
                + "fetching data from one player, forward Exception");
    }

    @Test
    public void givenHttpClientThrowsExceptionWhenFetchingDataFromOnePlayerThenForwardException(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given http client throws exception, when "
                + "fetching data from one player, forward Exception");
    }

    @Test
    public void givenSuccessfulRequest_whenListingObjectsWithFilterAndEmptyValue_thenConstructExpectedRequestAndReturnResult(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given successful request, when listing objects "
                + "with filter and empty value, construct expected request and return result");
    }

    @Test
    public void givenSuccessfulRequestWhenListingObjectsWithFilterAndEmptyValueThenConstructExpectedRequestAndReturnResult(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given successful request, when listing objects "
                + "with filter and empty value, construct expected request and return result");
    }
}
