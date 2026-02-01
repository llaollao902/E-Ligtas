package service;

import model.Incident;
import java.util.List;

/**
 * Repository interface for Incident data operations.
 * Follows Interface Segregation Principle and Dependency Inversion Principle.
 * This abstraction allows for different implementations (JSON, Database, etc.)
 */
public interface IIncidentRepository {
    
    /**
     * Load all incidents from data source.
     * @return List of all incidents
     */
    List<Incident> loadAllIncidents();
    
    /**
     * Save a new incident to data source.
     * @param incident The incident to save
     * @return true if successful, false otherwise
     */
    boolean saveIncident(Incident incident);
    
    /**
     * Get filtered incidents based on criteria.
     * @param typeFilter Type filter or null for all
     * @param statusFilter Status filter or null for all
     * @param locationFilter Location filter or null for all
     * @param searchText Search text or empty for no search
     * @return List of filtered incidents
     */
    List<Incident> getFilteredIncidents(String typeFilter, String statusFilter, 
                                        String locationFilter, String searchText);
}
