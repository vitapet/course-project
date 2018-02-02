package com.gmail.vitaliapetsenak.shop.web.validator;

import com.gmail.vitaliapetsenak.shop.repository.model.UserRole;
import com.gmail.vitaliapetsenak.shop.service.model.UserDTO;
import com.gmail.vitaliapetsenak.shop.web.model.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }


    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "login", "user.login.empty");
        ValidationUtils.rejectIfEmpty(errors, "firstName", "user.firstName.empty");
        ValidationUtils.rejectIfEmpty(errors, "surname", "user.surname.empty");
        ValidationUtils.rejectIfEmpty(errors, "birthDate", "user.birthDate.empty");
        ValidationUtils.rejectIfEmpty(errors, "phone", "user.phone.empty");


        UserDTO user = (UserDTO) target;

        validateLoginPassword(errors, user);
        validateUserInfo(errors, user);
        validateUserAddress(errors, user);

        if (!user.getPasswordCheck().isEmpty() && user.getPasswordCheck() != null &&
                !user.getPassword().isEmpty() && user.getPassword() != null) {
            if (!user.getPassword().equals(user.getPasswordCheck())) {
                errors.rejectValue("passwordCheck", "user.passwordCheck.invalid");
            }
        }
    }

    private void validateLoginPassword(Errors errors, UserDTO user) {
        Pattern pattern;
        if (getPrincipal() != null && getPrincipal().getRole().equals(UserRole.ROOT)) {
            if (user.getPassword().isEmpty() && !user.getPasswordCheck().isEmpty()) {
                ValidationUtils.rejectIfEmpty(errors, "password", "user.password.empty");
            } else if (!user.getPassword().isEmpty() && user.getPasswordCheck().isEmpty()) {
                ValidationUtils.rejectIfEmpty(errors, "passwordCheck", "user.passwordCheck.empty");
            } else {

            }
        } else {
            ValidationUtils.rejectIfEmpty(errors, "password", "user.password.empty");
            ValidationUtils.rejectIfEmpty(errors, "passwordCheck", "user.passwordCheck.empty");
        }

        if (!user.getLogin().isEmpty() && user.getLogin() != null) {
            pattern = Pattern.compile("^[a-zA-ZS\\.0-9_]+@[a-zA-Z0-9\\.]+\\.[a-zA-Z]{2,6}$",
                    Pattern.CASE_INSENSITIVE);
            if (!(pattern.matcher(user.getLogin()).matches())) {
                errors.rejectValue("login", "user.login.invalid");
            }
        }
        if (!user.getPassword().isEmpty() && user.getPassword() != null) {
            pattern = Pattern.compile("^[a-zA-Z0-9_-]{8,20}$",
                    Pattern.CASE_INSENSITIVE);
            if (!(pattern.matcher(user.getPassword()).matches())) {
                errors.rejectValue("password", "user.password.invalid");
            }
        }
    }

    private void validateUserAddress(Errors errors, UserDTO user) {
        Pattern pattern;
        if (!user.getCountry().isEmpty() && user.getCountry() != null) {
            pattern = Pattern.compile("^[a-zA-Z]{1,50}$",
                    Pattern.CASE_INSENSITIVE);
            if (!(pattern.matcher(user.getCountry()).matches())) {
                errors.rejectValue("country", "user.country.invalid");
            }
        }

        if (!user.getCity().isEmpty() && user.getCity() != null) {
            pattern = Pattern.compile("^[a-zA-Z]{1,50}$",
                    Pattern.CASE_INSENSITIVE);
            if (!(pattern.matcher(user.getCity()).matches())) {
                errors.rejectValue("city", "user.city.invalid");
            }
        }

        if (!user.getStreet().isEmpty() && user.getStreet() != null) {
            pattern = Pattern.compile("^[a-zA-Z]{1,50}$",
                    Pattern.CASE_INSENSITIVE);
            if (!(pattern.matcher(user.getStreet()).matches())) {
                errors.rejectValue("street", "user.street.invalid");
            }
        }

        if (!user.getHouse().isEmpty() && user.getHouse() != null) {
            pattern = Pattern.compile("^[1-9]([0-9]){0,50}$",
                    Pattern.CASE_INSENSITIVE);
            if (!(pattern.matcher(user.getHouse()).matches())) {
                errors.rejectValue("house", "user.house.invalid");
            }
        }

        if (!user.getBlock().isEmpty() && user.getBlock() != null) {
            pattern = Pattern.compile("^([1-9]?|[a-z]?)$",
                    Pattern.CASE_INSENSITIVE);
            if (!(pattern.matcher(user.getBlock()).matches())) {
                errors.rejectValue("block", "user.block.invalid");
            }
        }

        if (!user.getApartment().isEmpty() && user.getApartment() != null) {
            pattern = Pattern.compile("^[1-9]([0-9]){0,9}$",
                    Pattern.CASE_INSENSITIVE);
            if (!(pattern.matcher(user.getApartment()).matches())) {
                errors.rejectValue("apartment", "user.apartment.invalid");
            }
        }
    }

    private void validateUserInfo(Errors errors, UserDTO user) {
        Pattern pattern;
        if (!user.getFirstName().isEmpty() && user.getFirstName() != null) {
            pattern = Pattern.compile("^[a-zA-Z]{1,20}$",
                    Pattern.CASE_INSENSITIVE);
            if (!(pattern.matcher(user.getFirstName()).matches())) {
                errors.rejectValue("firstName", "user.firstName.invalid");
            }
        }
        if (!user.getSurname().isEmpty() && user.getSurname() != null) {
            pattern = Pattern.compile("^[a-zA-Z]{1,20}$",
                    Pattern.CASE_INSENSITIVE);
            if (!(pattern.matcher(user.getSurname()).matches())) {
                errors.rejectValue("surname", "user.surname.invalid");
            }
        }
        if (!user.getBirthDate().isEmpty() && user.getBirthDate() != null) {
            pattern = Pattern.compile("^[1-9][0-9]{3}-((0[1-9])|(1[0-2]))-((0[1-9])|([1-2][0-9])|(3[0-1]))$",
                    Pattern.CASE_INSENSITIVE);
            if (!(pattern.matcher(user.getBirthDate()).matches())) {
                errors.rejectValue("birthDate", "user.birthDate.invalid");
            }
        }

        if (!user.getPhone().isEmpty() && user.getPhone() != null) {
            pattern = Pattern.compile("^\\+[1-9][0-9]{11}$",
                    Pattern.CASE_INSENSITIVE);
            if (!(pattern.matcher(user.getPhone()).matches())) {
                errors.rejectValue("phone", "user.phone.invalid");
            }
        }
    }

    private UserPrincipal getPrincipal() {
        UserPrincipal user = null;
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal != null) {
                user = (UserPrincipal) principal;
            }
        }
        return user;
    }
}
