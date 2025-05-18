package com.ivanz.socialnetworkbackend.user.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ProfileUpdateRequestValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    private static ProfileUpdateRequest buildValid() {
        return new ProfileUpdateRequest(
                "John", "Doe",
                LocalDate.of(1990, 1, 1),
                "john@ex.com",
                "+12345678901"
        );
    }

    @Test
    void validRequest_hasNoViolations() {
        assertThat(validator.validate(buildValid())).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void blankFirstName_failsValidation(String firstName) {
        ProfileUpdateRequest req = new ProfileUpdateRequest(
                firstName, "Doe",
                LocalDate.of(1990, 1, 1),
                "john@ex.com",
                "+12345678901"
        );
        assertSingleViolation(req, "firstName");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void blankLastName_failsValidation(String lastName) {
        ProfileUpdateRequest req = new ProfileUpdateRequest(
                "John", lastName,
                LocalDate.of(1990, 1, 1),
                "john@ex.com",
                "+12345678901"
        );
        assertSingleViolation(req, "lastName");
    }

    @Test
    void futureBirthDate_failsValidation() {
        ProfileUpdateRequest req = new ProfileUpdateRequest(
                "John", "Doe",
                LocalDate.now().plusDays(1),
                "john@ex.com",
                "+12345678901"
        );
        assertSingleViolation(req, "birthDate");
    }

    @Test
    void invalidEmail_failsValidation() {
        ProfileUpdateRequest req = new ProfileUpdateRequest(
                "John", "Doe",
                LocalDate.of(1990, 1, 1),
                "wrong-mail",
                "+12345678901"
        );
        assertSingleViolation(req, "email");
    }

    @Test
    void invalidPhone_failsValidation() {
        ProfileUpdateRequest req = new ProfileUpdateRequest(
                "John", "Doe",
                LocalDate.of(1990, 1, 1),
                "john@ex.com",
                "1234"
        );
        assertSingleViolation(req, "phone");
    }

    /* ---------- helper ---------- */

    private void assertSingleViolation(ProfileUpdateRequest req, String property) {
        Set<ConstraintViolation<ProfileUpdateRequest>> v = validator.validate(req);
        assertThat(v).hasSize(1)
                .first()
                .extracting(cv -> cv.getPropertyPath().toString())
                .isEqualTo(property);
    }
}
