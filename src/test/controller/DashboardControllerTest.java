package test.controller;

import model.Incident;
import model.IncidentStatistics;
import org.junit.Test;

import controller.DashboardController;

import org.junit.Before;
import service.IIncidentRepository;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JUnit test class for DashboardController.
 * Tests dashboard operations including loading, filtering, and statistics.
 */
public class DashboardControllerTest {
    
    private DashboardController controller;
    private MockIncidentRepository mockRepository;
    
    /**
     * Sets up controller with mock repository before each test.
     */
    @Before
    public void setUp() {
        mockRepository = new MockIncidentRepository();
        controller = new DashboardController(mockRepository);
    }
    
    // ==================== loadAllIncidents Tests ====================
    
    /**
     * Tests loadAllIncidents returns all incidents from repository.
     */
    @Test
    public void testLoadAllIncidents() {
        List<Incident> incidents = controller.loadAllIncidents();
        
        assertNotNull(incidents);
        assertEquals(4, incidents.size());
    }
    
    /**
     * Tests loadAllIncidents returns empty list when no incidents exist.
     */
    @Test
    public void testLoadAllIncidentsWhenEmpty() {
        mockRepository.clearIncidents();
        List<Incident> incidents = controller.loadAllIncidents();
        
        assertNotNull(incidents);
        assertEquals(0, incidents.size());
    }
    
    // ==================== getFilteredIncidents Tests ====================
    
    /**
     * Tests getFilteredIncidents with no filters returns all incidents.
     */
    @Test
    public void testGetFilteredIncidentsWithNoFilters() {
        List<Incident> filtered = controller.getFilteredIncidents(
            "[None]", "[None]", "[None]", ""
        );
        
        assertEquals(4, filtered.size());
    }
    
    /**
     * Tests getFilteredIncidents by type filter.
     */
    @Test
    public void testGetFilteredIncidentsByType() {
        List<Incident> filtered = controller.getFilteredIncidents(
            "Fire", "[None]", "[None]", ""
        );
        
        assertEquals(1, filtered.size());
        assertEquals("Fire", filtered.get(0).getType());
    }
    
    /**
     * Tests getFilteredIncidents by status filter.
     */
    @Test
    public void testGetFilteredIncidentsByStatus() {
        List<Incident> filtered = controller.getFilteredIncidents(
            "[None]", "Pending", "[None]", ""
        );
        
        assertEquals(2, filtered.size());
        for (Incident incident : filtered) {
            assertEquals("Pending", incident.getStatus());
        }
    }
    
    /**
     * Tests getFilteredIncidents by location filter.
     */
    @Test
    public void testGetFilteredIncidentsByLocation() {
        List<Incident> filtered = controller.getFilteredIncidents(
            "[None]", "[None]", "Downtown", ""
        );
        
        assertEquals(1, filtered.size());
        assertEquals("Downtown", filtered.get(0).getLocation());
    }
    
    /**
     * Tests getFilteredIncidents by search text.
     */
    @Test
    public void testGetFilteredIncidentsBySearchText() {
        List<Incident> filtered = controller.getFilteredIncidents(
            "[None]", "[None]", "[None]", "flood"
        );
        
        assertTrue(filtered.size() > 0);
        for (Incident incident : filtered) {
            String combinedText = (incident.getType() + incident.getLocation() + 
                                 incident.getDescription()).toLowerCase();
            assertTrue(combinedText.contains("flood"));
        }
    }
    
    /**
     * Tests getFilteredIncidents with multiple filters combined.
     */
    @Test
    public void testGetFilteredIncidentsWithMultipleFilters() {
        List<Incident> filtered = controller.getFilteredIncidents(
            "Fire", "Pending", "[None]", ""
        );
        
        assertEquals(1, filtered.size());
        assertEquals("Fire", filtered.get(0).getType());
        assertEquals("Pending", filtered.get(0).getStatus());
    }
    
    /**
     * Tests getFilteredIncidents with filters that match nothing.
     */
    @Test
    public void testGetFilteredIncidentsWithNoMatches() {
        List<Incident> filtered = controller.getFilteredIncidents(
            "Fire", "Resolved", "[None]", ""
        );
        
        assertEquals(0, filtered.size());
    }
    
    // ==================== calculateStatistics Tests ====================
    
    /**
     * Tests calculateStatistics computes correct total count.
     */
    @Test
    public void testCalculateStatisticsTotal() {
        List<Incident> incidents = controller.loadAllIncidents();
        IncidentStatistics stats = controller.calculateStatistics(incidents);
        
        assertEquals(4, stats.getTotalIncidents());
    }
    
    /**
     * Tests calculateStatistics computes correct status counts.
     */
    @Test
    public void testCalculateStatisticsStatusCounts() {
        List<Incident> incidents = controller.loadAllIncidents();
        IncidentStatistics stats = controller.calculateStatistics(incidents);
        
        assertEquals(2, stats.getPendingCount());
        assertEquals(1, stats.getRespondingCount());
        assertEquals(1, stats.getResolvedCount());
    }
    
    /**
     * Tests calculateStatistics computes correct type counts.
     */
    @Test
    public void testCalculateStatisticsTypeCounts() {
        List<Incident> incidents = controller.loadAllIncidents();
        IncidentStatistics stats = controller.calculateStatistics(incidents);
        
        assertEquals(1, stats.getFireCount());
        assertEquals(1, stats.getFloodCount());
        assertEquals(1, stats.getMedicalCount());
        assertEquals(1, stats.getAccidentCount());
        assertEquals(0, stats.getCrimeCount());
    }
    
    /**
     * Tests calculateStatistics with filtered list.
     */
    @Test
    public void testCalculateStatisticsWithFilteredList() {
        List<Incident> filtered = controller.getFilteredIncidents(
            "[None]", "Pending", "[None]", ""
        );
        IncidentStatistics stats = controller.calculateStatistics(filtered);
        
        assertEquals(2, stats.getTotalIncidents());
        assertEquals(2, stats.getPendingCount());
        assertEquals(0, stats.getRespondingCount());
        assertEquals(0, stats.getResolvedCount());
    }
    
    /**
     * Tests calculateStatistics with empty list.
     */
    @Test
    public void testCalculateStatisticsWithEmptyList() {
        List<Incident> emptyList = new ArrayList<>();
        IncidentStatistics stats = controller.calculateStatistics(emptyList);
        
        assertEquals(0, stats.getTotalIncidents());
        assertEquals(0, stats.getPendingCount());
        assertEquals(0, stats.getFireCount());
    }
    
    // ==================== convertToTableData Tests ====================
    
    /**
     * Tests convertToTableData creates correct 2D array structure.
     */
    @Test
    public void testConvertToTableData() {
        List<Incident> incidents = controller.loadAllIncidents();
        Object[][] tableData = controller.convertToTableData(incidents);
        
        assertNotNull(tableData);
        assertEquals(4, tableData.length);
    }
    
    /**
     * Tests convertToTableData with each row having correct number of columns.
     */
    @Test
    public void testConvertToTableDataRowStructure() {
        List<Incident> incidents = controller.loadAllIncidents();
        Object[][] tableData = controller.convertToTableData(incidents);
        
        for (Object[] row : tableData) {
            assertEquals(7, row.length); // 7 columns per incident
        }
    }
    
    /**
     * Tests convertToTableData with correct data mapping.
     */
    @Test
    public void testConvertToTableDataContent() {
        List<Incident> incidents = controller.loadAllIncidents();
        Object[][] tableData = controller.convertToTableData(incidents);
        
        // First incident should be Fire at Downtown
        assertEquals("Fire", tableData[0][0]);
        assertEquals("Downtown", tableData[0][1]);
    }
    
    /**
     * Tests convertToTableData with empty list.
     */
    @Test
    public void testConvertToTableDataWithEmptyList() {
        List<Incident> emptyList = new ArrayList<>();
        Object[][] tableData = controller.convertToTableData(emptyList);
        
        assertNotNull(tableData);
        assertEquals(0, tableData.length);
    }
    
    /**
     * Tests convertToTableData with single incident.
     */
    @Test
    public void testConvertToTableDataWithSingleIncident() {
        List<Incident> singleList = new ArrayList<>();
        singleList.add(new Incident("Fire", "Location", "Desc", "Name", "Contact"));
        
        Object[][] tableData = controller.convertToTableData(singleList);
        
        assertEquals(1, tableData.length);
        assertEquals(7, tableData[0].length);
    }
    
    // ==================== Mock Repository for Testing ====================
    
    /**
     * Mock implementation of IIncidentRepository for testing.
     * Stores incidents in memory instead of files.
     */
    private static class MockIncidentRepository implements IIncidentRepository {
        
        private List<Incident> incidents;
        
        public MockIncidentRepository() {
            incidents = new ArrayList<>();
            // Add sample test data
            incidents.add(new Incident("Fire", "Downtown", "Building fire", 
                                      "John Doe", "09123456789", "Pending", "Dec 20, 2025"));
            incidents.add(new Incident("Flood", "City Center", "Street flooding", 
                                      "Jane Smith", "09987654321", "Responding", "Dec 20, 2025"));
            incidents.add(new Incident("Medical", "Hospital", "Emergency case", 
                                      "Dr. Smith", "09555666777", "Pending", "Dec 20, 2025"));
            incidents.add(new Incident("Accident", "Highway", "Car collision", 
                                      "Officer Jones", "09444333222", "Resolved", "Dec 19, 2025"));
        }
        
        @Override
        public List<Incident> loadAllIncidents() {
            return new ArrayList<>(incidents);
        }
        
        @Override
        public boolean saveIncident(Incident incident) {
            if (incident == null) {
                return false;
            }
            incidents.add(incident);
            return true;
        }
        
        @Override
        public List<Incident> getFilteredIncidents(String typeFilter, String statusFilter, 
                                                   String locationFilter, String searchText) {
            List<Incident> filtered = new ArrayList<>();
            
            for (Incident incident : incidents) {
                if (matchesFilters(incident, typeFilter, statusFilter, locationFilter, searchText)) {
                    filtered.add(incident);
                }
            }
            
            return filtered;
        }
        
        private boolean matchesFilters(Incident incident, String typeFilter, 
                                      String statusFilter, String locationFilter, String searchText) {
            if (typeFilter != null && !typeFilter.equals("[None]") && 
                !incident.getType().equalsIgnoreCase(typeFilter)) {
                return false;
            }
            if (statusFilter != null && !statusFilter.equals("[None]") && 
                !incident.getStatus().equalsIgnoreCase(statusFilter)) {
                return false;
            }
            if (locationFilter != null && !locationFilter.equals("[None]") && 
                !incident.getLocation().equalsIgnoreCase(locationFilter)) {
                return false;
            }
            if (searchText != null && !searchText.isEmpty()) {
                String combinedText = (incident.getType() + incident.getLocation() + 
                                      incident.getDescription()).toLowerCase();
                if (!combinedText.contains(searchText.toLowerCase())) {
                    return false;
                }
            }
            return true;
        }
        
        public void clearIncidents() {
            incidents.clear();
        }
    }
}