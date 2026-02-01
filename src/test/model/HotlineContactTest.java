package test.model;

import org.junit.Test;

import model.HotlineContact;

import static org.junit.Assert.*;

/**
 * JUnit test class for HotlineContact model.
 * Tests emergency hotline contact data encapsulation.
 */
public class HotlineContactTest {
    
    // ==================== Constructor Tests ====================
    
    /**
     * Tests that constructor properly initializes all hotline contact fields.
     */
    @Test
    public void testConstructor() {
        HotlineContact contact = new HotlineContact(
            "Emergency Hotline",
            "For all emergencies",
            "911",
            "https://example.com"
        );
        
        assertEquals("Emergency Hotline", contact.getName());
        assertEquals("For all emergencies", contact.getDescription());
        assertEquals("911", contact.getNumber());
        assertEquals("https://example.com", contact.getLink());
    }
    
    // ==================== Getter and Setter Tests ====================
    
    /**
     * Tests getName and setName methods.
     */
    @Test
    public void testGetAndSetName() {
        HotlineContact contact = new HotlineContact("Test", "Desc", "123", "link");
        
        contact.setName("Fire Department");
        assertEquals("Fire Department", contact.getName());
    }
    
    /**
     * Tests getDescription and setDescription methods.
     */
    @Test
    public void testGetAndSetDescription() {
        HotlineContact contact = new HotlineContact("Test", "Desc", "123", "link");
        
        contact.setDescription("Fire emergencies only");
        assertEquals("Fire emergencies only", contact.getDescription());
    }
    
    /**
     * Tests getNumber and setNumber methods.
     */
    @Test
    public void testGetAndSetNumber() {
        HotlineContact contact = new HotlineContact("Test", "Desc", "123", "link");
        
        contact.setNumber("(02) 8426-0219");
        assertEquals("(02) 8426-0219", contact.getNumber());
    }
    
    /**
     * Tests getLink and setLink methods.
     */
    @Test
    public void testGetAndSetLink() {
        HotlineContact contact = new HotlineContact("Test", "Desc", "123", "link");
        
        contact.setLink("https://foi.gov.ph");
        assertEquals("https://foi.gov.ph", contact.getLink());
    }
    
    // ==================== Business Logic Tests ====================
    
    /**
     * Tests toString method returns formatted name and number.
     */
    @Test
    public void testToString() {
        HotlineContact contact = new HotlineContact(
            "Police",
            "Emergency police line",
            "117",
            "https://pnp.gov.ph"
        );
        
        String result = contact.toString();
        assertEquals("Police - 117", result);
    }
    
    /**
     * Tests toString with multiple contact numbers.
     */
    @Test
    public void testToStringWithMultipleNumbers() {
        HotlineContact contact = new HotlineContact(
            "Red Cross",
            "Medical emergencies",
            "143 / (02) 8527-0000",
            "https://redcross.org.ph"
        );
        
        String result = contact.toString();
        assertEquals("Red Cross - 143 / (02) 8527-0000", result);
    }
    
    /**
     * Tests setting empty values doesn't throw exceptions.
     */
    @Test
    public void testEmptyValues() {
        HotlineContact contact = new HotlineContact("", "", "", "");
        
        assertEquals("", contact.getName());
        assertEquals("", contact.getDescription());
        assertEquals("", contact.getNumber());
        assertEquals("", contact.getLink());
        assertEquals(" - ", contact.toString());
    }
    
    /**
     * Tests modifying contact information after creation.
     */
    @Test
    public void testModifyContactInformation() {
        HotlineContact contact = new HotlineContact(
            "Initial Name",
            "Initial Desc",
            "000",
            "http://old.com"
        );
        
        // Modify all fields
        contact.setName("Updated Name");
        contact.setDescription("Updated Description");
        contact.setNumber("999");
        contact.setLink("http://new.com");
        
        assertEquals("Updated Name", contact.getName());
        assertEquals("Updated Description", contact.getDescription());
        assertEquals("999", contact.getNumber());
        assertEquals("http://new.com", contact.getLink());
        assertEquals("Updated Name - 999", contact.toString());
    }
}