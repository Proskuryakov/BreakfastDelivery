package ru.relex.delivery.services.model.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Objects;
import java.util.stream.Stream;


class UserInfoTest {

    @ParameterizedTest(name = "Check full name for: {arguments} ")
    @MethodSource("supplyFullNameExamples")
    void makeFullNameTests(FullNameBundle fullNameBundle) {
        final var pi = getUserInfo(fullNameBundle.firstName, fullNameBundle.lastName);

        Assertions.assertEquals(fullNameBundle.fullName, pi.getFullName());
    }

    static class FullNameBundle {
        String firstName;
        String lastName;
        String fullName;

        public FullNameBundle(String firstName, String lastName, String fullName) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.fullName = fullName;
        }

        @Override
        public String toString() {
            return
                    Objects.requireNonNullElse(firstName, "null") +
                            Objects.requireNonNullElse(lastName, "null") +
                            fullName;
        }
    }

    static Stream<FullNameBundle> supplyFullNameExamples() {
        return Stream.of(
                new FullNameBundle("John", "Doe", "John Doe"),
                new FullNameBundle(null, "Doe", "Doe"),
                new FullNameBundle("  john", null, "John"),
                new FullNameBundle(null, null, ""),
                new FullNameBundle("", "", "")
        );
    }

    @Test
    @DisplayName("Check full name when first name are present")
    void getFullName() {
        String firstName = "john ";
        String lastName = "doe ";
        UserInfo pi = getUserInfo(firstName, lastName);

        Assertions.assertEquals("John Doe", pi.getFullName());

    }



    private static UserInfo getUserInfo(String firstName, String lastName) {
        return new UserInfo() {
            @Override
            public String getFirstName() {
                return firstName;
            }

            @Override
            public String getLastName() {
                return lastName;
            }

            @Override
            public String getPhone() {
                return "";
            }
        };
    }
}