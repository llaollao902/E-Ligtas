package controller;

import model.HotlineContact;
import service.HotlineService;

import java.util.List;

/**
 * Controller for Hotlines operations.
 */
public class HotlinesController {
    
    private final HotlineService hotlineService;
    
    // ==================== Constructor ====================
    
    public HotlinesController() {
        this.hotlineService = new HotlineService();
    }
    
    /**
     * Constructor for testing with custom service.
     * @param hotlineService Custom hotline service
     */
    public HotlinesController(HotlineService hotlineService) {
        this.hotlineService = hotlineService;
    }
    
    // ==================== Public Controller Methods ====================
    
    /**
     * Get all emergency hotline contacts.
     * @return List of hotline contacts
     */
    public List<HotlineContact> getAllHotlines() {
        return hotlineService.getAllHotlines();
    }
}
