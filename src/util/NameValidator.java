package util;

import java.util.regex.Pattern;

/**
 * Validator for person names.
 * Follows Single Responsibility Principle - only validates names.
 */
public class NameValidator implements IValidator {
    
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-ZÀ-ÿ\\s'-]{2,}$");
    private static final String ERROR_MESSAGE = "Please enter a valid name (letters and spaces only).";
    
    @Override
    public boolean isValid(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        return NAME_PATTERN.matcher(input).matches();
    }
    
    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
