package service;

import model.Incident;
import model.IncidentStatistics;
import java.util.List;

/**
 * Service class handling business logic for incidents.
 * Follows Single Responsibility Principle - handles business logic only.
 * Follows Dependency Inversion Principle - depends on IIncidentRepository abstraction.
 */
public class IncidentService {
    
    private final IIncidentRepository repository;
    
    // ==================== Constructor (Dependency Injection) ====================
    
    public IncidentService(IIncidentRepository repository) {
        this.repository = repository;
    }
    
    // ==================== Business Logic Methods ====================
    
    /**
     * Get all incidents from repository.
     * @return List of all incidents
     */
    public List<Incident> getAllIncidents() {
        return repository.loadAllIncidents();
    }
    
    /**
     * Save a new incident.
     * @param incident The incident to save
     * @return true if successful
     */
    public boolean saveIncident(Incident incident) {
        return repository.saveIncident(incident);
    }
    
    /**
     * Get filtered incidents.
     * @param typeFilter Type filter
     * @param statusFilter Status filter
     * @param locationFilter Location filter
     * @param searchText Search text
     * @return List of filtered incidents
     */
    public List<Incident> getFilteredIncidents(String typeFilter, String statusFilter,
                                               String locationFilter, String searchText) {
        return repository.getFilteredIncidents(typeFilter, statusFilter, 
                                              locationFilter, searchText);
    }
    
    /**
     * Calculate statistics from a list of incidents.
     * @param incidents List of incidents to analyze
     * @return IncidentStatistics object with computed statistics
     */
    public IncidentStatistics calculateStatistics(List<Incident> incidents) {
        IncidentStatistics stats = new IncidentStatistics();
        
        for (Incident incident : incidents) {
            stats.incrementTotal();
            stats.incrementStatusCount(incident.getStatus());
            stats.incrementTypeCount(incident.getType());
        }
        
        return stats;
    }
    
    /**
     * Normalize incident type for consistency.
     * Maps "Medical Emergency" to "Medical" for data consistency.
     * @param incidentType The original incident type
     * @return Normalized incident type
     */
    public String normalizeIncidentType(String incidentType) {
        if ("Medical Emergency".equals(incidentType)) {
            return "Medical";
        }
        return incidentType;
    }
}
