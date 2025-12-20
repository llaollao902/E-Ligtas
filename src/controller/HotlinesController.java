package controller;

import model.HotlineContact;
import service.HotlineService;

import java.util.List;

// controller for hotlines operation
public class HotlinesController {
    
    private final HotlineService hotlineService;
    
    // constructor    
    public HotlinesController() {
        this.hotlineService = new HotlineService();
    }
    
    /**
     * constructor for testing with custom service.
     * @param hotlineService Custom hotline service
     */
    public HotlinesController(HotlineService hotlineService) {
        this.hotlineService = hotlineService;
    }
    
    
    /**
     * Get all emergency hotline contacts.
     * @return List of hotline contacts
     */
    public List<HotlineContact> getAllHotlines() {
        return hotlineService.getAllHotlines();
    }
}
