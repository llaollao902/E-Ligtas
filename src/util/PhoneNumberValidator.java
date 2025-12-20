package util;

import java.util.regex.Pattern;

/**
 * Validator for Philippine mobile phone numbers.
 * Follows Single Responsibility Principle - only validates phone numbers.
 */
public class PhoneNumberValidator implements IValidator {
    
    private static final Pattern PHONE_PATTERN = Pattern.compile("^09\\d{9}$");
    private static final String ERROR_MESSAGE = "Please enter a valid Philippine mobile number (e.g., 09XX XXX XXXX).";
    
    @Override
    public boolean isValid(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        String cleanPhone = input.replaceAll("[\\s-]", "");
        return PHONE_PATTERN.matcher(cleanPhone).matches();
    }
    
    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
