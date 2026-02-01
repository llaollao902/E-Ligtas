package test.controller;

import model.HotlineContact;
import service.HotlineService;
import org.junit.Test;

import controller.HotlinesController;

import org.junit.Before;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JUnit test class for HotlinesController.
 * Tests hotline retrieval and controller-service integration.
 */
public class HotlinesControllerTest {
    
    private HotlinesController controller;
    private MockHotlineService mockService;
    
    /**
     * Sets up controller with mock service before each test.
     */
    @Before
    public void setUp() {
        mockService = new MockHotlineService();
        controller = new HotlinesController(mockService);
    }
    
    // ==================== Constructor Tests ====================
    
    /**
     * Tests default constructor creates controller successfully.
     */
    @Test
    public void testDefaultConstructor() {
        HotlinesController defaultController = new HotlinesController();
        assertNotNull(defaultController);
    }
    
    /**
     * Tests parameterized constructor with custom service.
     */
    @Test
    public void testParameterizedConstructor() {
        HotlineService customService = new MockHotlineService();
        HotlinesController customController = new HotlinesController(customService);
        
        assertNotNull(customController);
    }
    
    // ==================== getAllHotlines Tests ====================
    
    /**
     * Tests getAllHotlines returns non-null list.
     */
    @Test
    public void testGetAllHotlinesReturnsNonNull() {
        List<HotlineContact> hotlines = controller.getAllHotlines();
        
        assertNotNull(hotlines);
    }
    
    /**
     * Tests getAllHotlines returns correct number of hotlines.
     */
    @Test
    public void testGetAllHotlinesReturnsCorrectCount() {
        List<HotlineContact> hotlines = controller.getAllHotlines();
        
        assertEquals(3, hotlines.size());
    }
    
    /**
     * Tests getAllHotlines returns hotlines from service.
     */
    @Test
    public void testGetAllHotlinesReturnsDataFromService() {
        List<HotlineContact> hotlines = controller.getAllHotlines();
        
        // Verify first hotline
        assertEquals("Emergency Hotline", hotlines.get(0).getName());
        assertEquals("911", hotlines.get(0).getNumber());
        
        // Verify second hotline
        assertEquals("Fire Department", hotlines.get(1).getName());
        assertEquals("160", hotlines.get(1).getNumber());
        
        // Verify third hotline
        assertEquals("Police", hotlines.get(2).getName());
        assertEquals("117", hotlines.get(2).getNumber());
    }
    
    /**
     * Tests getAllHotlines delegates to service correctly.
     */
    @Test
    public void testGetAllHotlinesDelegatesToService() {
        controller.getAllHotlines();
        
        assertTrue("Service method should have been called", 
                  mockService.wasGetAllHotlinesCalled());
    }
    
    /**
     * Tests getAllHotlines with empty service returns empty list.
     */
    @Test
    public void testGetAllHotlinesWhenServiceReturnsEmpty() {
        mockService.clearHotlines();
        List<HotlineContact> hotlines = controller.getAllHotlines();
        
        assertNotNull(hotlines);
        assertEquals(0, hotlines.size());
    }
    
    /**
     * Tests getAllHotlines returns all required hotline fields.
     */
    @Test
    public void testGetAllHotlinesReturnsCompleteData() {
        List<HotlineContact> hotlines = controller.getAllHotlines();
        
        for (HotlineContact contact : hotlines) {
            assertNotNull("Name should not be null", contact.getName());
            assertNotNull("Description should not be null", contact.getDescription());
            assertNotNull("Number should not be null", contact.getNumber());
            assertNotNull("Link should not be null", contact.getLink());
            
            assertFalse("Name should not be empty", contact.getName().isEmpty());
            assertFalse("Number should not be empty", contact.getNumber().isEmpty());
        }
    }
    
    /**
     * Tests multiple calls to getAllHotlines work correctly.
     */
    @Test
    public void testMultipleCallsToGetAllHotlines() {
        List<HotlineContact> hotlines1 = controller.getAllHotlines();
        List<HotlineContact> hotlines2 = controller.getAllHotlines();
        
        assertEquals(hotlines1.size(), hotlines2.size());
        assertEquals(2, mockService.getCallCount());
    }
    
    // ==================== Mock Service for Testing ====================
    
    /**
     * Mock implementation of HotlineService for testing.
     * Provides controlled test data without external dependencies.
     */
    private static class MockHotlineService extends HotlineService {
        
        private List<HotlineContact> hotlines;
        private boolean getAllHotlinesCalled = false;
        private int callCount = 0;
        
        public MockHotlineService() {
            hotlines = new ArrayList<>();
            // Add sample test data
            hotlines.add(new HotlineContact(
                "Emergency Hotline",
                "For all emergencies",
                "911",
                "https://emergency.gov.ph"
            ));
            hotlines.add(new HotlineContact(
                "Fire Department",
                "Fire emergencies",
                "160",
                "https://fire.gov.ph"
            ));
            hotlines.add(new HotlineContact(
                "Police",
                "Crime and security",
                "117",
                "https://police.gov.ph"
            ));
        }
        
        @Override
        public List<HotlineContact> getAllHotlines() {
            getAllHotlinesCalled = true;
            callCount++;
            return new ArrayList<>(hotlines);
        }
        
        public boolean wasGetAllHotlinesCalled() {
            return getAllHotlinesCalled;
        }
        
        public int getCallCount() {
            return callCount;
        }
        
        public void clearHotlines() {
            hotlines.clear();
        }
    }
}