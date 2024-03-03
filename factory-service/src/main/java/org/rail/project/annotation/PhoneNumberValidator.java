package org.rail.project.annotation;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static com.google.i18n.phonenumbers.PhoneNumberUtil.getInstance;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        PhoneNumberUtil numberUtil = getInstance();
        Phonenumber.PhoneNumber parsedPhoneNumber;
        try {
            parsedPhoneNumber = numberUtil.parse(phoneNumber, "US");
        } catch (NumberParseException e) {
            throw new RuntimeException("phone Number: " + e);
        }
        return numberUtil.isValidNumber(parsedPhoneNumber);
    }
}
