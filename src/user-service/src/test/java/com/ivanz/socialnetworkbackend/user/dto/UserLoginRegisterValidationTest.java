package com.ivanz.socialnetworkbackend.user.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserLoginRegisterValidationTest {

    private static Validator validator;

    @BeforeAll
    static void init() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    @Test
    void userLoginRequest_recordStoresFields() {
        UserLoginRequest req = new UserLoginRequest("john", "pass");
        assertThat(req.loginOrEmail()).isEqualTo("john");
        assertThat(req.password()).isEqualTo("pass");
    }


    @Test
    void validRegisterRequest_hasNoViolations() {
        UserRegisterRequest ok = new UserRegisterRequest("john", "john@ex.com", "secret6");
        assertThat(validator.validate(ok)).isEmpty();
    }

    @Test
    void blankLogin_failsValidation() {
        UserRegisterRequest bad = new UserRegisterRequest("  ", "john@ex.com", "secret6");
        assertThat(validator.validate(bad)).hasSize(1);
    }

    @Test
    void shortPassword_failsValidation() {
        UserRegisterRequest bad = new UserRegisterRequest("john", "john@ex.com", "123");
        assertThat(validator.validate(bad)).hasSize(1);
    }

    @Test
    void invalidEmail_failsValidation() {
        UserRegisterRequest bad = new UserRegisterRequest("john", "wrong-mail", "secret6");
        assertThat(validator.validate(bad)).hasSize(1);
    }
}
