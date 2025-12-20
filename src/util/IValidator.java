package util;

/**
 * Interface for validation operations.
 * Follows Interface Segregation Principle - focused interface for validation.
 */
public interface IValidator {
    
    /**
     * Validate if the input is valid.
     * @param input The input to validate
     * @return true if valid, false otherwise
     */
    boolean isValid(String input);
    
    /**
     * Get the error message for invalid input.
     * @return Error message
     */
    String getErrorMessage();
}
