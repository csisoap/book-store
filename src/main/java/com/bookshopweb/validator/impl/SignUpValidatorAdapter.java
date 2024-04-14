package com.bookshopweb.validator.impl;

import com.bookshopweb.dto.SignUpRequest;
import com.bookshopweb.validator.Validator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// adapter user common validate
public class SignUpValidatorAdapter implements Validator<SignUpRequest> {
    private final EmailValidator emailValidator;
    private final RegexValidator regexPhoneValidator;

    public SignUpValidatorAdapter() {
        this.emailValidator = EmailValidator.getInstance();
        this.regexPhoneValidator = new RegexValidator("^\\d{10,11}$");
    }

    @Override
    public void validate(SignUpRequest data) throws Exception {
        Map<String, List<String>> violations = new HashMap<>();

        violations.put("usernameViolations", com.bookshopweb.utils.Validator.of(data.getUsername())
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .isAtMostOfLength(25)
                .toList());

        violations.put("passwordViolations", com.bookshopweb.utils.Validator.of(data.getPassword())
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .isAtMostOfLength(32)
                .toList());
        violations.put("fullnameViolations", com.bookshopweb.utils.Validator.of(data.getFullname())
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .toList());

        if(!emailValidator.isValid(data.getEmail())) {
            violations.put("emailViolations", Collections.singletonList("Invalid email!"));
        }


        if(regexPhoneValidator.isValid(data.getPhoneNumber())) {
            violations.put("emailViolations", Collections.singletonList("Invalid phone!"));
        }

        violations.put("genderViolations", com.bookshopweb.utils.Validator.of(data.getGender())
                .isNotNull()
                .toList());
        violations.put("addressViolations", com.bookshopweb.utils.Validator.of(data.getAddress())
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .toList());
        violations.put("policyViolations", com.bookshopweb.utils.Validator.of(data.getPolicy())
                .isNotNull()
                .toList());

        if(violations.isEmpty()) {
            throw new Exception("Validate failed! error=" + violations.values().stream().collect(Collectors.toList()));
        }
    }
}
