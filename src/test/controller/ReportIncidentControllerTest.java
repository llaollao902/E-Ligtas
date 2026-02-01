package test.controller;

import model.Incident;
import org.junit.Test;

import controller.ReportIncidentController;

import org.junit.Before;
import service.IIncidentRepository;
import util.IValidator;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JUnit test class for ReportIncidentController.
 * Tests incident report submission logic and validation.
 */
public class ReportIncidentControllerTest {
    
    private ReportIncidentController controller;
    private MockIncidentRepository mockRepository;
    private MockValidator mockNameValidator;
    private MockValidator mockPhoneValidator;
    
    /**
     * Sets up controller with mock dependencies before each test.
     */
    @Before
    public void setUp() {
        mockRepository = new MockIncidentRepository();
        mockNameValidator = new MockValidator(true, "");
        mockPhoneValidator = new MockValidator(true, "");
        
        controller = new ReportIncidentController(
            mockRepository, 
            mockNameValidator, 
            mockPhoneValidator
        );
    }
    
    // ==================== Successful Submission Tests ====================
    
    /**
     * Tests submitReport with valid data succeeds.
     */
    @Test
    public void testSubmitReportWithValidData() {
        ReportIncidentController.ValidationResult result = controller.submitReport(
            "Fire", 
            "Downtown", 
            "Building fire on 3rd floor", 
            "John Doe", 
            "09123456789"
        );
        
        assertTrue(result.isSuccess());
        assertEquals("Report submitted successfully!", result.getMessage());
        assertEquals(1, mockRepository.getSavedIncidents().size());
    }
    
    /**
     * Tests submitReport saves incident with correct data.
     */
    @Test
    public void testSubmitReportSavesCorrectData() {
        controller.submitReport(
            "Medical", 
            "Hospital Area", 
            "Heart attack patient", 
            "Jane Smith", 
            "09987654321"
        );
        
        List<Incident> saved = mockRepository.getSavedIncidents();
        assertEquals(1, saved.size());
        
        Incident incident = saved.get(0);
        assertEquals("Medical", incident.getType());
        assertEquals("Hospital Area", incident.getLocation());
        assertEquals("Heart attack patient", incident.getDescription());
        assertEquals("Jane Smith", incident.getReporter());
        assertEquals("09987654321", incident.getContact());
    }
    
    /**
     * Tests submitReport normalizes "Medical Emergency" to "Medical".
     */
    @Test
    public void testSubmitReportNormalizesMedicalEmergency() {
        controller.submitReport(
            "Medical Emergency", 
            "Clinic", 
            "Emergency case", 
            "Dr. Brown", 
            "09111222333"
        );
        
        Incident incident = mockRepository.getSavedIncidents().get(0);
        assertEquals("Medical", incident.getType());
    }
    
    // ==================== Description Validation Tests ====================
    
    /**
     * Tests submitReport fails when description is null.
     */
    @Test
    public void testSubmitReportFailsWithNullDescription() {
        ReportIncidentController.ValidationResult result = controller.submitReport(
            "Fire", 
            "Location", 
            null, 
            "John Doe", 
            "09123456789"
        );
        
        assertFalse(result.isSuccess());
        assertEquals("Please provide an incident description.", result.getMessage());
    }
    
    /**
     * Tests submitReport fails when description is empty.
     */
    @Test
    public void testSubmitReportFailsWithEmptyDescription() {
        ReportIncidentController.ValidationResult result = controller.submitReport(
            "Fire", 
            "Location", 
            "", 
            "John Doe", 
            "09123456789"
        );
        
        assertFalse(result.isSuccess());
        assertEquals("Please provide an incident description.", result.getMessage());
    }
    
    /**
     * Tests submitReport fails when description is only whitespace.
     */
    @Test
    public void testSubmitReportFailsWithWhitespaceDescription() {
        ReportIncidentController.ValidationResult result = controller.submitReport(
            "Fire", 
            "Location", 
            "   ", 
            "John Doe", 
            "09123456789"
        );
        
        assertFalse(result.isSuccess());
        assertEquals("Please provide an incident description.", result.getMessage());
    }
    
    /**
     * Tests submitReport fails when description is placeholder text.
     */
    @Test
    public void testSubmitReportFailsWithPlaceholderDescription() {
        ReportIncidentController.ValidationResult result = controller.submitReport(
            "Fire", 
            "Location", 
            "Provide detailed information...", 
            "John Doe", 
            "09123456789"
        );
        
        assertFalse(result.isSuccess());
        assertEquals("Please provide an incident description.", result.getMessage());
    }
    
    // ==================== Name Validation Tests ====================
    
    /**
     * Tests submitReport fails when name is null.
     */
    @Test
    public void testSubmitReportFailsWithNullName() {
        ReportIncidentController.ValidationResult result = controller.submitReport(
            "Fire", 
            "Location", 
            "Description", 
            null, 
            "09123456789"
        );
        
        assertFalse(result.isSuccess());
        assertEquals("Please enter your name.", result.getMessage());
    }
    
    /**
     * Tests submitReport fails when name is empty.
     */
    @Test
    public void testSubmitReportFailsWithEmptyName() {
        ReportIncidentController.ValidationResult result = controller.submitReport(
            "Fire", 
            "Location", 
            "Description", 
            "", 
            "09123456789"
        );
        
        assertFalse(result.isSuccess());
        assertEquals("Please enter your name.", result.getMessage());
    }
    
    /**
     * Tests submitReport fails when name is placeholder text.
     */
    @Test
    public void testSubmitReportFailsWithPlaceholderName() {
        ReportIncidentController.ValidationResult result = controller.submitReport(
            "Fire", 
            "Location", 
            "Description", 
            "Juan Dela Cruz", 
            "09123456789"
        );
        
        assertFalse(result.isSuccess());
        assertEquals("Please enter your name.", result.getMessage());
    }
    
    /**
     * Tests submitReport fails when name validation fails.
     */
    @Test
    public void testSubmitReportFailsWithInvalidName() {
        mockNameValidator.setValid(false);
        mockNameValidator.setErrorMessage("Name must contain only letters");
        
        ReportIncidentController.ValidationResult result = controller.submitReport(
            "Fire", 
            "Location", 
            "Description", 
            "John123", 
            "09123456789"
        );
        
        assertFalse(result.isSuccess());
        assertEquals("Name must contain only letters", result.getMessage());
    }
    
    // ==================== Contact Validation Tests ====================
    
    /**
     * Tests submitReport fails when contact is null.
     */
    @Test
    public void testSubmitReportFailsWithNullContact() {
        ReportIncidentController.ValidationResult result = controller.submitReport(
            "Fire", 
            "Location", 
            "Description", 
            "John Doe", 
            null
        );
        
        assertFalse(result.isSuccess());
        assertEquals("Please enter your contact number.", result.getMessage());
    }
    
    /**
     * Tests submitReport fails when contact is empty.
     */
    @Test
    public void testSubmitReportFailsWithEmptyContact() {
        ReportIncidentController.ValidationResult result = controller.submitReport(
            "Fire", 
            "Location", 
            "Description", 
            "John Doe", 
            ""
        );
        
        assertFalse(result.isSuccess());
        assertEquals("Please enter your contact number.", result.getMessage());
    }
    
    /**
     * Tests submitReport fails when contact is placeholder text.
     */
    @Test
    public void testSubmitReportFailsWithPlaceholderContact() {
        ReportIncidentController.ValidationResult result = controller.submitReport(
            "Fire", 
            "Location", 
            "Description", 
            "John Doe", 
            "09XX XXX XXXX"
        );
        
        assertFalse(result.isSuccess());
        assertEquals("Please enter your contact number.", result.getMessage());
    }
    
    /**
     * Tests submitReport fails when phone number validation fails.
     */
    @Test
    public void testSubmitReportFailsWithInvalidPhoneNumber() {
        mockPhoneValidator.setValid(false);
        mockPhoneValidator.setErrorMessage("Invalid phone format");
        
        ReportIncidentController.ValidationResult result = controller.submitReport(
            "Fire", 
            "Location", 
            "Description", 
            "John Doe", 
            "123"
        );
        
        assertFalse(result.isSuccess());
        assertEquals("Invalid phone format", result.getMessage());
    }
    
    // ==================== Repository Failure Tests ====================
    
    /**
     * Tests submitReport handles repository save failure.
     */
    @Test
    public void testSubmitReportHandlesRepositoryFailure() {
        mockRepository.setShouldFailSave(true);
        
        ReportIncidentController.ValidationResult result = controller.submitReport(
            "Fire", 
            "Location", 
            "Description", 
            "John Doe", 
            "09123456789"
        );
        
        assertFalse(result.isSuccess());
        assertEquals("Failed to save report. Please try again.", result.getMessage());
    }
    
    // ==================== ValidationResult Tests ====================
    
    /**
     * Tests ValidationResult encapsulates success status and message.
     */
    @Test
    public void testValidationResultSuccess() {
        ReportIncidentController.ValidationResult result = 
            new ReportIncidentController.ValidationResult(true, "Success message");
        
        assertTrue(result.isSuccess());
        assertEquals("Success message", result.getMessage());
    }
    
    /**
     * Tests ValidationResult encapsulates failure status and message.
     */
    @Test
    public void testValidationResultFailure() {
        ReportIncidentController.ValidationResult result = 
            new ReportIncidentController.ValidationResult(false, "Error message");
        
        assertFalse(result.isSuccess());
        assertEquals("Error message", result.getMessage());
    }
    
    // ==================== Mock Classes for Testing ====================
    
    /**
     * Mock repository for testing without file I/O.
     */
    private static class MockIncidentRepository implements IIncidentRepository {
        private List<Incident> savedIncidents = new ArrayList<>();
        private boolean shouldFailSave = false;
        
        @Override
        public List<Incident> loadAllIncidents() {
            return new ArrayList<>(savedIncidents);
        }
        
        @Override
        public boolean saveIncident(Incident incident) {
            if (shouldFailSave) {
                return false;
            }
            savedIncidents.add(incident);
            return true;
        }
        
        @Override
        public List<Incident> getFilteredIncidents(String typeFilter, String statusFilter, 
                                                   String locationFilter, String searchText) {
            return new ArrayList<>();
        }
        
        public List<Incident> getSavedIncidents() {
            return savedIncidents;
        }
        
        public void setShouldFailSave(boolean shouldFail) {
            this.shouldFailSave = shouldFail;
        }
    }
    
    /**
     * Mock validator for testing validation logic.
     */
    private static class MockValidator implements IValidator {
        private boolean isValid;
        private String errorMessage;
        
        public MockValidator(boolean isValid, String errorMessage) {
            this.isValid = isValid;
            this.errorMessage = errorMessage;
        }
        
        @Override
        public boolean isValid(String input) {
            return isValid;
        }
        
        @Override
        public String getErrorMessage() {
            return errorMessage;
        }
        
        public void setValid(boolean valid) {
            this.isValid = valid;
        }
        
        public void setErrorMessage(String message) {
            this.errorMessage = message;
        }
    }
}