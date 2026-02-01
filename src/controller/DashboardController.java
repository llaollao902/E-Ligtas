package controller;

import model.Incident;
import model.IncidentStatistics;
import service.IncidentService;
import service.IIncidentRepository;
import service.JsonIncidentRepository;

import java.util.List;

/**
 * Controller for Dashboard operations.
 * Mediates between View and Model/Service layers.
 * Follows Single Responsibility Principle - handles dashboard logic only.
 * Follows Dependency Inversion Principle - depends on IIncidentRepository abstraction.
 */
public class DashboardController {
    
    private final IncidentService incidentService;
    
    // ==================== Constructor (Dependency Injection) ====================
    
    public DashboardController() {
        IIncidentRepository repository = new JsonIncidentRepository();
        this.incidentService = new IncidentService(repository);
    }
    
    /**
     * Constructor for testing with custom repository.
     * @param repository Custom repository implementation
     */
    public DashboardController(IIncidentRepository repository) {
        this.incidentService = new IncidentService(repository);
    }
    
    // ==================== Public Controller Methods ====================
    
    /**
     * Load all incidents.
     * @return List of all incidents
     */
    public List<Incident> loadAllIncidents() {
        return incidentService.getAllIncidents();
    }
    
    /**
     * Get filtered incidents based on criteria.
     * @param typeFilter Type filter
     * @param statusFilter Status filter
     * @param locationFilter Location filter
     * @param searchText Search text
     * @return List of filtered incidents
     */
    public List<Incident> getFilteredIncidents(String typeFilter, String statusFilter,
                                               String locationFilter, String searchText) {
        return incidentService.getFilteredIncidents(typeFilter, statusFilter, 
                                                    locationFilter, searchText);
    }
    
    /**
     * Calculate statistics from a list of incidents.
     * @param incidents List of incidents
     * @return IncidentStatistics object
     */
    public IncidentStatistics calculateStatistics(List<Incident> incidents) {
        return incidentService.calculateStatistics(incidents);
    }
    
    /**
     * Convert incidents to table rows.
     * @param incidents List of incidents
     * @return 2D array for table model
     */
    public Object[][] convertToTableData(List<Incident> incidents) {
        Object[][] data = new Object[incidents.size()][];
        for (int i = 0; i < incidents.size(); i++) {
            data[i] = incidents.get(i).toTableRow();
        }
        return data;
    }
}
