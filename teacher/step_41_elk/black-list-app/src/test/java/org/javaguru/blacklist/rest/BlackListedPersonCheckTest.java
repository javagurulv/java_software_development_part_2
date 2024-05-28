package org.javaguru.blacklist.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackListedPersonCheckTest extends BlackListedPersonCheckControllerTestCase {

    @Test
    @DisplayName("ERROR_CODE_1 personFirstName is NULL")
    public void check_ERROR_CODE_1_NULL() throws Exception {
        executeAndCompare("/ERROR_CODE_1_personFirstName_is_null");
    }

    @Test
    @DisplayName("ERROR_CODE_1 personFirstName is EMPTY")
    public void check_ERROR_CODE_1_EMPTY() throws Exception {
        executeAndCompare("/ERROR_CODE_1_personFirstName_is_empty");
    }

    @Test
    @DisplayName("ERROR_CODE_2 personLastName is NULL")
    public void check_ERROR_CODE_2_NULL() throws Exception {
        executeAndCompare("/ERROR_CODE_2_personLastName_is_null");
    }

    @Test
    @DisplayName("ERROR_CODE_2 personLastName is EMPTY")
    public void check_ERROR_CODE_2_EMPTY() throws Exception {
        executeAndCompare("/ERROR_CODE_2_personLastName_is_empty");
    }

    @Test
    @DisplayName("ERROR_CODE_3 personCode is NULL")
    public void check_ERROR_CODE_3_NULL() throws Exception {
        executeAndCompare("/ERROR_CODE_3_personCode_is_null");
    }

    @Test
    @DisplayName("ERROR_CODE_3 personCode is EMPTY")
    public void check_ERROR_CODE_3_EMPTY() throws Exception {
        executeAndCompare("/ERROR_CODE_3_personCode_is_empty");
    }

    @Test
    @DisplayName("Person blacklisted")
    public void check_Person_blacklisted() throws Exception {
        executeAndCompare("/Person_blacklisted");
    }

    @Test
    @DisplayName("Person not blacklisted")
    public void check_Person_NOT_blacklisted() throws Exception {
        executeAndCompare("/Person_not_blacklisted");
    }

}
