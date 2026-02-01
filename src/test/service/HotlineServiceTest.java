package test.service;

import model.HotlineContact;
import service.HotlineService;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.List;

/**
 * JUnit test class for HotlineService.
 * Tests emergency hotline management functionality.
 */
public class HotlineServiceTest {
    
    private HotlineService service;
    
    /**
     * Sets up fresh HotlineService instance before each test.
     */
    @Before
    public void setUp() {
        service = new HotlineService();
    }
    
    // ==================== getAllHotlines Tests ====================
    
    /**
     * Tests getAllHotlines returns a non-null list.
     */
    @Test
    public void testGetAllHotlinesReturnsNonNull() {
        List<HotlineContact> hotlines = service.getAllHotlines();
        
        assertNotNull(hotlines);
    }
    
    /**
     * Tests getAllHotlines returns expected number of hotlines.
     */
    @Test
    public void testGetAllHotlinesReturnsCorrectCount() {
        List<HotlineContact> hotlines = service.getAllHotlines();
        
        assertEquals(6, hotlines.size());
    }
    
    /**
     * Tests getAllHotlines includes National Emergency Hotline (911).
     */
    @Test
    public void testGetAllHotlinesIncludesNationalEmergency() {
        List<HotlineContact> hotlines = service.getAllHotlines();
        
        boolean found = false;
        for (HotlineContact contact : hotlines) {
            if (contact.getName().equals("National Emergency Hotline") && 
                contact.getNumber().equals("911")) {
                found = true;
                break;
            }
        }
        
        assertTrue("National Emergency Hotline (911) should be in the list", found);
    }
    
    /**
     * Tests getAllHotlines includes Bureau of Fire Protection.
     */
    @Test
    public void testGetAllHotlinesIncludesBFP() {
        List<HotlineContact> hotlines = service.getAllHotlines();
        
        boolean found = false;
        for (HotlineContact contact : hotlines) {
            if (contact.getName().equals("Bureau of Fire Protection")) {
                found = true;
                assertEquals("Fire emergencies", contact.getDescription());
                assertNotNull(contact.getNumber());
                assertNotNull(contact.getLink());
                break;
            }
        }
        
        assertTrue("Bureau of Fire Protection should be in the list", found);
    }
    
    /**
     * Tests getAllHotlines includes Philippine National Police.
     */
    @Test
    public void testGetAllHotlinesIncludesPNP() {
        List<HotlineContact> hotlines = service.getAllHotlines();
        
        boolean found = false;
        for (HotlineContact contact : hotlines) {
            if (contact.getName().equals("Philippine National Police")) {
                found = true;
                assertTrue(contact.getDescription().toLowerCase().contains("crime"));
                break;
            }
        }
        
        assertTrue("Philippine National Police should be in the list", found);
    }
    
    /**
     * Tests getAllHotlines includes Philippine Red Cross.
     */
    @Test
    public void testGetAllHotlinesIncludesRedCross() {
        List<HotlineContact> hotlines = service.getAllHotlines();
        
        boolean found = false;
        for (HotlineContact contact : hotlines) {
            if (contact.getName().equals("Philippine Red Cross")) {
                found = true;
                assertTrue(contact.getDescription().toLowerCase().contains("medical"));
                break;
            }
        }
        
        assertTrue("Philippine Red Cross should be in the list", found);
    }
    
    /**
     * Tests getAllHotlines includes NDRRMC.
     */
    @Test
    public void testGetAllHotlinesIncludesNDRRMC() {
        List<HotlineContact> hotlines = service.getAllHotlines();
        
        boolean found = false;
        for (HotlineContact contact : hotlines) {
            if (contact.getName().equals("NDRRMC")) {
                found = true;
                assertTrue(contact.getDescription().toLowerCase().contains("disaster"));
                break;
            }
        }
        
        assertTrue("NDRRMC should be in the list", found);
    }
    
    /**
     * Tests getAllHotlines includes DOH Health Emergency.
     */
    @Test
    public void testGetAllHotlinesIncludesDOH() {
        List<HotlineContact> hotlines = service.getAllHotlines();
        
        boolean found = false;
        for (HotlineContact contact : hotlines) {
            if (contact.getName().equals("DOH Health Emergency")) {
                found = true;
                assertTrue(contact.getDescription().toLowerCase().contains("health"));
                assertEquals("1555", contact.getNumber());
                break;
            }
        }
        
        assertTrue("DOH Health Emergency should be in the list", found);
    }
    
    /**
     * Tests all hotlines have non-null and non-empty required fields.
     */
    @Test
    public void testAllHotlinesHaveRequiredFields() {
        List<HotlineContact> hotlines = service.getAllHotlines();
        
        for (HotlineContact contact : hotlines) {
            assertNotNull("Name should not be null", contact.getName());
            assertNotNull("Description should not be null", contact.getDescription());
            assertNotNull("Number should not be null", contact.getNumber());
            assertNotNull("Link should not be null", contact.getLink());
            
            assertFalse("Name should not be empty", contact.getName().isEmpty());
            assertFalse("Description should not be empty", contact.getDescription().isEmpty());
            assertFalse("Number should not be empty", contact.getNumber().isEmpty());
            assertFalse("Link should not be empty", contact.getLink().isEmpty());
        }
    }
    
    /**
     * Tests all hotlines have valid link format (starts with http/https).
     */
    @Test
    public void testAllHotlinesHaveValidLinks() {
        List<HotlineContact> hotlines = service.getAllHotlines();
        
        for (HotlineContact contact : hotlines) {
            String link = contact.getLink();
            assertTrue("Link should start with http:// or https://", 
                      link.startsWith("http://") || link.startsWith("https://"));
        }
    }
    
    /**
     * Tests getAllHotlines returns a new list each time (not cached).
     */
    @Test
    public void testGetAllHotlinesReturnsNewList() {
        List<HotlineContact> hotlines1 = service.getAllHotlines();
        List<HotlineContact> hotlines2 = service.getAllHotlines();
        
        // Should be different list instances
        assertNotSame(hotlines1, hotlines2);
        
        // But should have the same content
        assertEquals(hotlines1.size(), hotlines2.size());
    }
}