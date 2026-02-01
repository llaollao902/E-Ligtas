package service;

import model.HotlineContact;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing emergency hotlines.
 * Follows Single Responsibility Principle.
 */
public class HotlineService {
    
    /**
     * Get all emergency hotline contacts for the Philippines.
     * @return List of hotline contacts
     */
    public List<HotlineContact> getAllHotlines() {
        List<HotlineContact> hotlines = new ArrayList<>();
        
        hotlines.add(new HotlineContact(
            "National Emergency Hotline",
            "For all emergencies",
            "911",
            "https://bettergov.ph/philippines/hotlines"
        ));
        
        hotlines.add(new HotlineContact(
            "Bureau of Fire Protection",
            "Fire emergencies",
            "160 / (02) 8426-0219",
            "https://www.foi.gov.ph/agencies/bfp/"
        ));
        
        hotlines.add(new HotlineContact(
            "Philippine National Police",
            "Crime and security emergencies",
            "117/ (02) 8722-0650",
            "https://www.foi.gov.ph/agencies/pnp/"
        ));
        
        hotlines.add(new HotlineContact(
            "Philippine Red Cross",
            "Medical emergencies & disasters",
            "143 / (02) 8527-0000",
            "https://redcross.org.ph/contact-us/"
        ));
        
        hotlines.add(new HotlineContact(
            "NDRRMC",
            "Disaster risk reduction & management",
            "(02) 8911-5061",
            "https://monitoring-dashboard.ndrrmc.gov.ph/"
        ));
        
        hotlines.add(new HotlineContact(
            "DOH Health Emergency",
            "Health-related emergencies",
            "1555",
            "https://doh.gov.ph/"
        ));
        
        return hotlines;
    }
}
