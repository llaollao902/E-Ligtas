package util;

import java.util.regex.Pattern;

/**
 * Validator for person names.
 * Ensures that names contain only letters, spaces, apostrophes, and hyphens.
 * Follows Single Responsibility Principle - only validates names.
 */
public class NameValidator implements IValidator {
    
    /** Regex pattern allowing letters (including accented), spaces, apostrophes, and hyphens */
    private static final Pattern NAME_PATTERN =
            Pattern.compile("^[a-zA-ZÀ-ÿ\\s'-]{2,}$");

    /** Error message returned when validation fails */
    private static final String ERROR_MESSAGE =
            "Please enter a valid name (letters and spaces only).";
    
    /**
     * Validates the given input as a person name.
     *
     * @param input the name to validate
     * @return true if valid, false otherwise
     */
    @Override
    public boolean isValid(String input) {
        // Reject null or empty input
        if (input == null || input.trim().isEmpty()) {
            return false;
        }

        // Match input against name pattern
        return NAME_PATTERN.matcher(input).matches();
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
