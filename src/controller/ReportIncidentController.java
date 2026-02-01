package controller;

import model.Incident;
import service.IncidentService;
import service.IIncidentRepository;
import service.JsonIncidentRepository;
import util.IValidator;
import util.NameValidator;
import util.PhoneNumberValidator;

/**
 * Controller for Report Incident operations.
 * Handles report submission logic.
 * Follows Single Responsibility Principle.
 * Follows Dependency Inversion Principle.
 */
public class ReportIncidentController {
    
    private final IncidentService incidentService;
    private final IValidator nameValidator;
    private final IValidator phoneValidator;
    
    // ==================== Constructor ====================
    
    public ReportIncidentController() {
        IIncidentRepository repository = new JsonIncidentRepository();
        this.incidentService = new IncidentService(repository);
        this.nameValidator = new NameValidator();
        this.phoneValidator = new PhoneNumberValidator();
    }
    
    /**
     * Constructor for testing with custom dependencies.
     * @param repository Custom repository
     * @param nameValidator Custom name validator
     * @param phoneValidator Custom phone validator
     */
    public ReportIncidentController(IIncidentRepository repository, 
                                   IValidator nameValidator, 
                                   IValidator phoneValidator) {
        this.incidentService = new IncidentService(repository);
        this.nameValidator = nameValidator;
        this.phoneValidator = phoneValidator;
    }
    
    // ==================== Public Controller Methods ====================
    
    /**
     * Submit a new incident report.
     * @param incidentType Type of incident
     * @param location Location of incident
     * @param description Description
     * @param name Reporter name
     * @param contact Reporter contact
     * @return ValidationResult containing success status and message
     */
    public ValidationResult submitReport(String incidentType, String location, 
                                        String description, String name, String contact) {
        
        // Validate description
        if (description == null || description.trim().isEmpty() || 
            description.equals("Provide detailed information...")) {
            return new ValidationResult(false, "Please provide an incident description.");
        }
        
        // Validate name
        if (name == null || name.trim().isEmpty() || name.equals("Juan Dela Cruz")) {
            return new ValidationResult(false, "Please enter your name.");
        }
        
        if (!nameValidator.isValid(name)) {
            return new ValidationResult(false, nameValidator.getErrorMessage());
        }
        
        // Validate contact
        if (contact == null || contact.trim().isEmpty() || contact.equals("09XX XXX XXXX")) {
            return new ValidationResult(false, "Please enter your contact number.");
        }
        
        if (!phoneValidator.isValid(contact)) {
            return new ValidationResult(false, phoneValidator.getErrorMessage());
        }
        
        // Normalize incident type
        String normalizedType = incidentService.normalizeIncidentType(incidentType);
        
        // Create and save incident
        Incident incident = new Incident(normalizedType, location, description, name, contact);
        boolean success = incidentService.saveIncident(incident);
        
        if (success) {
            return new ValidationResult(true, "Report submitted successfully!");
        } else {
            return new ValidationResult(false, "Failed to save report. Please try again.");
        }
    }
    
    // ==================== Inner Class for Validation Results ====================
    
    /**
     * Encapsulates validation result with success status and message.
     */
    public static class ValidationResult {
        private final boolean success;
        private final String message;
        
        public ValidationResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
        
        public boolean isSuccess() {
            return success;
        }
        
        public String getMessage() {
            return message;
        }
    }
}
