package util;

import java.util.regex.Pattern;

/**
 * Validator for Philippine mobile phone numbers.
 * Accepts numbers starting with "09" followed by 9 digits.
 * Follows Single Responsibility Principle - only validates phone numbers.
 */
public class PhoneNumberValidator implements IValidator {
    
    /** Regex pattern for PH mobile numbers (09XXXXXXXXX) */
    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^09\\d{9}$");

    /** Error message returned when validation fails */
    private static final String ERROR_MESSAGE =
            "Please enter a valid Philippine mobile number (e.g., 09XX XXX XXXX).";
    
    /**
     * Validates the given phone number input.
     *
     * @param input the phone number to validate
     * @return true if valid Philippine mobile number, false otherwise
     */
    @Override
    public boolean isValid(String input) {
        // Reject null or empty input
        if (input == null || input.trim().isEmpty()) {
            return false;
        }

        // Remove spaces and dashes before validation
        String cleanPhone = input.replaceAll("[\\s-]", "");

        // Validate against PH phone number pattern
        return PHONE_PATTERN.matcher(cleanPhone).matches();
    }
    
    /**
     * Returns the validation error message.
     *
     * @return error message string
     */
    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
