package test.model;

import org.junit.Test;

import model.Incident;

import static org.junit.Assert.*;

/**
 * JUnit test class for Incident model.
 * Tests the model's data encapsulation, constructors, and business logic methods.
 */
public class IncidentTest {
    
    // ==================== Constructor Tests ====================
    
    /**
     * Tests the default constructor initializes status as "Pending" 
     * and sets the current date.
     */
    @Test
    public void testDefaultConstructor() {
        Incident incident = new Incident();
        
        assertEquals("Pending", incident.getStatus());
        assertNotNull(incident.getDate());
    }
    
    /**
     * Tests the parameterized constructor correctly initializes all fields
     * and sets default status and date.
     */
    @Test
    public void testParameterizedConstructor() {
        Incident incident = new Incident(
            "Fire", 
            "Downtown", 
            "Building fire", 
            "John Doe", 
            "09123456789"
        );
        
        assertEquals("Fire", incident.getType());
        assertEquals("Downtown", incident.getLocation());
        assertEquals("Building fire", incident.getDescription());
        assertEquals("John Doe", incident.getReporter());
        assertEquals("09123456789", incident.getContact());
        assertEquals("Pending", incident.getStatus());
        assertNotNull(incident.getDate());
    }
    
    /**
     * Tests the full constructor with all parameters including status and date.
     */
    @Test
    public void testFullConstructor() {
        Incident incident = new Incident(
            "Flood", 
            "City Center", 
            "Street flooding", 
            "Jane Smith", 
            "09987654321",
            "Responding",
            "December 20, 2025 10:30 AM"
        );
        
        assertEquals("Flood", incident.getType());
        assertEquals("City Center", incident.getLocation());
        assertEquals("Street flooding", incident.getDescription());
        assertEquals("Jane Smith", incident.getReporter());
        assertEquals("09987654321", incident.getContact());
        assertEquals("Responding", incident.getStatus());
        assertEquals("December 20, 2025 10:30 AM", incident.getDate());
    }
    
    // ==================== Getter and Setter Tests ====================
    
    /**
     * Tests all getter and setter methods for proper encapsulation.
     */
    @Test
    public void testGettersAndSetters() {
        Incident incident = new Incident();
        
        incident.setType("Crime");
        assertEquals("Crime", incident.getType());
        
        incident.setLocation("Park");
        assertEquals("Park", incident.getLocation());
        
        incident.setDescription("Theft reported");
        assertEquals("Theft reported", incident.getDescription());
        
        incident.setReporter("Bob Johnson");
        assertEquals("Bob Johnson", incident.getReporter());
        
        incident.setContact("09111222333");
        assertEquals("09111222333", incident.getContact());
        
        incident.setStatus("Resolved");
        assertEquals("Resolved", incident.getStatus());
        
        incident.setDate("December 19, 2025 03:00 PM");
        assertEquals("December 19, 2025 03:00 PM", incident.getDate());
    }
    
    // ==================== Business Logic Tests ====================
    
    /**
     * Tests toTableRow method returns correct Object array 
     * with all incident data in proper order.
     */
    @Test
    public void testToTableRow() {
        Incident incident = new Incident(
            "Medical", 
            "Hospital Area", 
            "Emergency", 
            "Dr. Smith", 
            "09555666777",
            "Responding",
            "December 20, 2025 11:00 AM"
        );
        
        Object[] row = incident.toTableRow();
        
        assertEquals(7, row.length);
        assertEquals("Medical", row[0]);
        assertEquals("Hospital Area", row[1]);
        assertEquals("Emergency", row[2]);
        assertEquals("09555666777", row[3]);
        assertEquals("Dr. Smith", row[4]);
        assertEquals("December 20, 2025 11:00 AM", row[5]);
        assertEquals("Responding", row[6]);
    }
    
    /**
     * Tests toJson method generates valid JSON string representation.
     */
    @Test
    public void testToJson() {
        Incident incident = new Incident(
            "Accident", 
            "Highway", 
            "Car collision", 
            "Officer Jones", 
            "09444333222",
            "Pending",
            "December 20, 2025 09:15 AM"
        );
        
        String json = incident.toJson();
        
        assertTrue(json.contains("\"type\": \"Accident\""));
        assertTrue(json.contains("\"location\": \"Highway\""));
        assertTrue(json.contains("\"description\": \"Car collision\""));
        assertTrue(json.contains("\"reporter\": \"Officer Jones\""));
        assertTrue(json.contains("\"contact\": \"09444333222\""));
        assertTrue(json.contains("\"status\": \"Pending\""));
        assertTrue(json.contains("\"date\": \"December 20, 2025 09:15 AM\""));
    }
    
    /**
     * Tests toJson method properly escapes special characters.
     */
    @Test
    public void testToJsonWithSpecialCharacters() {
        Incident incident = new Incident(
            "Fire", 
            "Building \"A\"", 
            "Fire with \"smoke\"", 
            "John\\Doe", 
            "09123456789"
        );
        
        String json = incident.toJson();
        
        assertTrue(json.contains("Building \\\"A\\\""));
        assertTrue(json.contains("Fire with \\\"smoke\\\""));
        assertTrue(json.contains("John\\\\Doe"));
    }
    
    /**
     * Tests toString method returns formatted string representation.
     */
    @Test
    public void testToString() {
        Incident incident = new Incident(
            "Fire", 
            "Downtown", 
            "Building fire", 
            "John Doe", 
            "09123456789",
            "Responding",
            "December 20, 2025 10:30 AM"
        );
        
        String str = incident.toString();
        
        assertTrue(str.contains("Fire"));
        assertTrue(str.contains("Downtown"));
        assertTrue(str.contains("Responding"));
    }
    
    /**
     * Tests incident with null values doesn't throw exceptions.
     */
    @Test
    public void testNullValuesHandling() {
        Incident incident = new Incident(null, null, null, null, null, null, null);
        
        assertNull(incident.getType());
        assertNull(incident.getLocation());
        assertNull(incident.getDescription());
        
        // Should not throw exception
        String json = incident.toJson();
        assertNotNull(json);
    }
}