package controller;

import model.Incident;
import model.IncidentStatistics;
import service.IncidentService;
import service.IIncidentRepository;
import service.JsonIncidentRepository;

import java.util.List;

// controller for dashboard operations
public class DashboardController {
    
    private final IncidentService incidentService;
    
    // constructor    
    public DashboardController() {
        IIncidentRepository repository = new JsonIncidentRepository();
        this.incidentService = new IncidentService(repository);
    }
    
    /**
     * constructor for testing with custom repository.
     * @param repository custom repository implementation
     */
    public DashboardController(IIncidentRepository repository) {
        this.incidentService = new IncidentService(repository);
    }
        
    /**
     * load all incidents.
     * @return List of all incidents
     */
    public List<Incident> loadAllIncidents() {
        return incidentService.getAllIncidents();
    }
    
    /**
     * get filtered incidents based on type.
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
     * calculate statistics from a list of incidents.
     * @param incidents list of incidents
     * @return IncidentStatistics object
     */
    public IncidentStatistics calculateStatistics(List<Incident> incidents) {
        return incidentService.calculateStatistics(incidents);
    }
    
    /**
     * convert incidents to table rows.
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
