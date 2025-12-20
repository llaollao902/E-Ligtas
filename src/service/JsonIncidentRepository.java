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
 * Follows Single Responsibility Principle - only handles JSON file operations.
 * Follows Open/Closed Principle - can be extended without modifying interface.
 */
public class JsonIncidentRepository implements IIncidentRepository {
    
    private static final String FILE_PATH = "src/resources/incident_report.json";
    
    // ==================== Interface Implementation ====================
    
    @Override
    public List<Incident> loadAllIncidents() {
        List<Incident> incidents = new ArrayList<>();
        
        try {
            String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            incidents = parseJsonToIncidents(json);
        } catch (Exception e) {
            System.err.println("Error loading incidents: " + e.getMessage());
            e.printStackTrace();
        }
        
        return incidents;
    }
    
    @Override
    public boolean saveIncident(Incident incident) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(incident.toJson() + ",\n");
            return true;
        } catch (IOException e) {
            System.err.println("Error saving incident: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public List<Incident> getFilteredIncidents(String typeFilter, String statusFilter, 
                                               String locationFilter, String searchText) {
        List<Incident> allIncidents = loadAllIncidents();
        
        return allIncidents.stream()
            .filter(incident -> matchesTypeFilter(incident, typeFilter))
            .filter(incident -> matchesStatusFilter(incident, statusFilter))
            .filter(incident -> matchesLocationFilter(incident, locationFilter))
            .filter(incident -> matchesSearchText(incident, searchText))
            .collect(Collectors.toList());
    }
    
    // ==================== Private Helper Methods ====================
    
    private List<Incident> parseJsonToIncidents(String json) {
        List<Incident> incidents = new ArrayList<>();
        
        json = json.trim();
        if (json.startsWith("[")) json = json.substring(1);
        if (json.endsWith("]")) json = json.substring(0, json.length() - 1);
        
        String[] objects = json.split("\\},\\s*\\{");
        
        for (String obj : objects) {
            obj = obj.replace("{", "").replace("}", "");
            
            String type = "", location = "", description = "",
                   reporter = "", contact = "", date = "", status = "";
            
            String[] fields = obj.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            
            for (String field : fields) {
                String[] kv = field.split(":", 2);
                if (kv.length < 2) continue;
                
                String key = kv[0].replace("\"", "").trim();
                String value = kv[1].replace("\"", "").trim();
                
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
            
            Incident incident = new Incident(type, location, description, 
                                            reporter, contact, status, date);
            incidents.add(incident);
        }
        
        return incidents;
    }
    
    private boolean matchesTypeFilter(Incident incident, String typeFilter) {
        if (typeFilter == null || typeFilter.equals("[None]")) {
            return true;
        }
        return incident.getType().equalsIgnoreCase(typeFilter);
    }
    
    private boolean matchesStatusFilter(Incident incident, String statusFilter) {
        if (statusFilter == null || statusFilter.equals("[None]")) {
            return true;
        }
        return incident.getStatus().equalsIgnoreCase(statusFilter);
    }
    
    private boolean matchesLocationFilter(Incident incident, String locationFilter) {
        if (locationFilter == null || locationFilter.equals("[None]")) {
            return true;
        }
        return incident.getLocation().equalsIgnoreCase(locationFilter);
    }
    
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
