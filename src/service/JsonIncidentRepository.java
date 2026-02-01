package service;

import model.Incident;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JSON implementation of IIncidentRepository.
 * Handles loading, saving, and filtering incident data stored in a JSON file.
 */
public class JsonIncidentRepository implements IIncidentRepository {
    
    /** Path to the JSON file storing incident reports */
    private static final String FILE_PATH = "src/resources/incident_report.json";
    
    // ==================== Interface Implementation ====================
    
    /**
     * Loads all incidents from the JSON file.
     *
     * @return list of all incidents
     */
    @Override
    public List<Incident> loadAllIncidents() {
        List<Incident> incidents = new ArrayList<>();
        
        try {
            // Read entire JSON file as a string
            String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            
            // Convert JSON string into Incident objects
            incidents = parseJsonToIncidents(json);
        } catch (Exception e) {
            // Handle file read or parsing errors
            System.err.println("Error loading incidents: " + e.getMessage());
            e.printStackTrace();
        }
        
        return incidents;
    }
    
    /**
     * Saves a single incident by appending it to the JSON file.
     *
     * @param incident the incident to save
     * @return true if saved successfully, false otherwise
     */
    @Override
    public boolean saveIncident(Incident incident) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            // Append incident JSON representation to file
            writer.write(incident.toJson() + ",\n");
            return true;
        } catch (IOException e) {
            // Handle file write errors
            System.err.println("Error saving incident: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Retrieves incidents filtered by type, status, location, and search text.
     *
     * @return filtered list of incidents
     */
    @Override
    public List<Incident> getFilteredIncidents(String typeFilter, String statusFilter, 
                                               String locationFilter, String searchText) {
        // Load all incidents first
        List<Incident> allIncidents = loadAllIncidents();
        
        // Apply filters using Java Streams
        return allIncidents.stream()
            .filter(incident -> matchesTypeFilter(incident, typeFilter))
            .filter(incident -> matchesStatusFilter(incident, statusFilter))
            .filter(incident -> matchesLocationFilter(incident, locationFilter))
            .filter(incident -> matchesSearchText(incident, searchText))
            .collect(Collectors.toList());
    }
    
    // ==================== Private Helper Methods ====================
    
    /**
     * Converts raw JSON text into a list of Incident objects.
     */
    private List<Incident> parseJsonToIncidents(String json) {
        List<Incident> incidents = new ArrayList<>();
        
        // Remove surrounding JSON array brackets
        json = json.trim();
        if (json.startsWith("[")) json = json.substring(1);
        if (json.endsWith("]")) json = json.substring(0, json.length() - 1);
        
        // Split JSON into individual objects
        String[] objects = json.split("\\},\\s*\\{");
        
        for (String obj : objects) {
            // Remove remaining braces
            obj = obj.replace("{", "").replace("}", "");
            
            // Initialize incident fields
            String type = "", location = "", description = "",
                   reporter = "", contact = "", date = "", status = "";
            
            // Split fields while preserving quoted text
            String[] fields = obj.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            
            for (String field : fields) {
                String[] kv = field.split(":", 2);
                if (kv.length < 2) continue;
                
                String key = kv[0].replace("\"", "").trim();
                String value = kv[1].replace("\"", "").trim();
                
                // Assign values based on JSON keys
                switch (key) {
                    case "type" -> type = value;
                    case "location" -> location = value;
                    case "description" -> description = value;
                    case "reporter" -> reporter = value;
                    case "contact" -> contact = value;
                    case "date" -> date = value;
                    case "status" -> status = value;
                }
            }
            
            // Create Incident object and add to list
            Incident incident = new Incident(type, location, description, 
                                            reporter, contact, status, date);
            incidents.add(incident);
        }
        
        return incidents;
    }
    
    /** Checks if incident matches selected type filter */
    private boolean matchesTypeFilter(Incident incident, String typeFilter) {
        if (typeFilter == null || typeFilter.equals("[None]")) {
            return true;
        }
        return incident.getType().equalsIgnoreCase(typeFilter);
    }
    
    /** Checks if incident matches selected status filter */
    private boolean matchesStatusFilter(Incident incident, String statusFilter) {
        if (statusFilter == null || statusFilter.equals("[None]")) {
            return true;
        }
        return incident.getStatus().equalsIgnoreCase(statusFilter);
    }
    
    /** Checks if incident matches selected location filter */
    private boolean matchesLocationFilter(Incident incident, String locationFilter) {
        if (locationFilter == null || locationFilter.equals("[None]")) {
            return true;
        }
        return incident.getLocation().equalsIgnoreCase(locationFilter);
    }
    
    /** Checks if incident contains the search text */
    private boolean matchesSearchText(Incident incident, String searchText) {
        if (searchText == null || searchText.trim().isEmpty()) {
            return true;
        }
        
        String searchLower = searchText.toLowerCase();
        String combinedText = (
            incident.getType() + 
            incident.getLocation() + 
            incident.getDescription() + 
            incident.getContact() + 
            incident.getReporter() + 
            incident.getStatus()
        ).toLowerCase();
        
        return combinedText.contains(searchLower);
    }
}
