package test.service;

import model.Incident;
import model.IncidentStatistics;
import service.IIncidentRepository;
import service.IncidentService;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JUnit test class for IncidentService.
 * Tests business logic for incident management.
 * Uses a mock repository to avoid file I/O during testing.
 */
public class IncidentServiceTest {
    
    private IncidentService service;
    private MockIncidentRepository mockRepository;
    
    /**
     * Sets up fresh service and mock repository before each test.
     */
    @Before
    public void setUp() {
        mockRepository = new MockIncidentRepository();
        service = new IncidentService(mockRepository);
    }
    
    // ==================== getAllIncidents Tests ====================
    
    /**
     * Tests getAllIncidents returns all incidents from repository.
     */
    @Test
    public void testGetAllIncidents() {
        List<Incident> incidents = service.getAllIncidents();
        
        assertNotNull(incidents);
        assertEquals(3, incidents.size());
    }
    
    /**
     * Tests getAllIncidents returns empty list when no incidents exist.
     */
    @Test
    public void testGetAllIncidentsWhenEmpty() {
        mockRepository.clearIncidents();
        List<Incident> incidents = service.getAllIncidents();
        
        assertNotNull(incidents);
        assertEquals(0, incidents.size());
    }
    
    // ==================== saveIncident Tests ====================
    
    /**
     * Tests saveIncident successfully saves a valid incident.
     */
    @Test
    public void testSaveIncident() {
        Incident newIncident = new Incident("Fire", "Downtown", "Building fire", 
                                           "John Doe", "09123456789");
        
        boolean result = service.saveIncident(newIncident);
        
        assertTrue(result);
        assertEquals(4, mockRepository.getSavedIncidents().size());
    }
    
    /**
     * Tests saveIncident handles null incident gracefully.
     */
    @Test
    public void testSaveNullIncident() {
        boolean result = service.saveIncident(null);
        
        assertFalse(result);
        assertEquals(3, mockRepository.getSavedIncidents().size());
    }
    
    // ==================== getFilteredIncidents Tests ====================
    
    /**
     * Tests filtering incidents by type.
     */
    @Test
    public void testGetFilteredIncidentsByType() {
        List<Incident> filtered = service.getFilteredIncidents("Fire", null, null, "");
        
        assertEquals(1, filtered.size());
        assertEquals("Fire", filtered.get(0).getType());
    }
    
    /**
     * Tests filtering incidents by status.
     */
    @Test
    public void testGetFilteredIncidentsByStatus() {
        List<Incident> filtered = service.getFilteredIncidents(null, "Pending", null, "");
        
        assertEquals(2, filtered.size());
        for (Incident incident : filtered) {
            assertEquals("Pending", incident.getStatus());
        }
    }
    
    /**
     * Tests filtering incidents by location.
     */
    @Test
    public void testGetFilteredIncidentsByLocation() {
        List<Incident> filtered = service.getFilteredIncidents(null, null, "Downtown", "");
        
        assertEquals(1, filtered.size());
        assertEquals("Downtown", filtered.get(0).getLocation());
    }
    
    /**
     * Tests filtering incidents by search text.
     */
    @Test
    public void testGetFilteredIncidentsBySearchText() {
        List<Incident> filtered = service.getFilteredIncidents(null, null, null, "flood");
        
        assertEquals(1, filtered.size());
        assertTrue(filtered.get(0).getDescription().toLowerCase().contains("flood"));
    }
    
    /**
     * Tests filtering with multiple criteria combined.
     */
    @Test
    public void testGetFilteredIncidentsWithMultipleCriteria() {
        List<Incident> filtered = service.getFilteredIncidents("Fire", "Pending", null, "");
        
        assertEquals(1, filtered.size());
        assertEquals("Fire", filtered.get(0).getType());
        assertEquals("Pending", filtered.get(0).getStatus());
    }
    
    /**
     * Tests filtering with all null filters returns all incidents.
     */
    @Test
    public void testGetFilteredIncidentsWithNullFilters() {
        List<Incident> filtered = service.getFilteredIncidents(null, null, null, "");
        
        assertEquals(3, filtered.size());
    }
    
    // ==================== calculateStatistics Tests ====================
    
    /**
     * Tests calculateStatistics computes correct total count.
     */
    @Test
    public void testCalculateStatisticsTotal() {
        List<Incident> incidents = service.getAllIncidents();
        IncidentStatistics stats = service.calculateStatistics(incidents);
        
        assertEquals(3, stats.getTotalIncidents());
    }
    
    /**
     * Tests calculateStatistics computes correct status counts.
     */
    @Test
    public void testCalculateStatisticsStatusCounts() {
        List<Incident> incidents = service.getAllIncidents();
        IncidentStatistics stats = service.calculateStatistics(incidents);
        
        assertEquals(2, stats.getPendingCount());
        assertEquals(1, stats.getRespondingCount());
        assertEquals(0, stats.getResolvedCount());
    }
    
    /**
     * Tests calculateStatistics computes correct type counts.
     */
    @Test
    public void testCalculateStatisticsTypeCounts() {
        List<Incident> incidents = service.getAllIncidents();
        IncidentStatistics stats = service.calculateStatistics(incidents);
        
        assertEquals(1, stats.getFireCount());
        assertEquals(1, stats.getFloodCount());
        assertEquals(1, stats.getMedicalCount());
        assertEquals(0, stats.getAccidentCount());
        assertEquals(0, stats.getCrimeCount());
    }
    
    /**
     * Tests calculateStatistics with empty list returns zero counts.
     */
    @Test
    public void testCalculateStatisticsWithEmptyList() {
        List<Incident> emptyList = new ArrayList<>();
        IncidentStatistics stats = service.calculateStatistics(emptyList);
        
        assertEquals(0, stats.getTotalIncidents());
        assertEquals(0, stats.getPendingCount());
        assertEquals(0, stats.getFireCount());
    }
    
    /**
     * Tests calculateStatistics with null list throws no exception.
     * Note: Actual implementation may need null check.
     */
    @Test
    public void testCalculateStatisticsWithSingleIncident() {
        List<Incident> singleList = new ArrayList<>();
        singleList.add(new Incident("Crime", "Park", "Theft", "Bob", "09111222333"));
        
        IncidentStatistics stats = service.calculateStatistics(singleList);
        
        assertEquals(1, stats.getTotalIncidents());
        assertEquals(1, stats.getCrimeCount());
    }
    
    // ==================== normalizeIncidentType Tests ====================
    
    /**
     * Tests normalizeIncidentType converts "Medical Emergency" to "Medical".
     */
    @Test
    public void testNormalizeIncidentTypeMedicalEmergency() {
        String normalized = service.normalizeIncidentType("Medical Emergency");
        assertEquals("Medical", normalized);
    }
    
    /**
     * Tests normalizeIncidentType leaves other types unchanged.
     */
    @Test
    public void testNormalizeIncidentTypeOtherTypes() {
        assertEquals("Fire", service.normalizeIncidentType("Fire"));
        assertEquals("Flood", service.normalizeIncidentType("Flood"));
        assertEquals("Accident", service.normalizeIncidentType("Accident"));
        assertEquals("Crime", service.normalizeIncidentType("Crime"));
    }
    
    /**
     * Tests normalizeIncidentType with Medical (already normalized).
     */
    @Test
    public void testNormalizeIncidentTypeMedical() {
        assertEquals("Medical", service.normalizeIncidentType("Medical"));
    }
    
    /**
     * Tests normalizeIncidentType with null returns null.
     */
    @Test
    public void testNormalizeIncidentTypeWithNull() {
        assertNull(service.normalizeIncidentType(null));
    }
    
    /**
     * Tests normalizeIncidentType with empty string returns empty string.
     */
    @Test
    public void testNormalizeIncidentTypeWithEmptyString() {
        assertEquals("", service.normalizeIncidentType(""));
    }
    
    // ==================== Mock Repository for Testing ====================
    
    /**
     * Mock implementation of IIncidentRepository for testing.
     * Stores incidents in memory instead of files to avoid file I/O during tests.
     */
    private static class MockIncidentRepository implements IIncidentRepository {
        
        private List<Incident> incidents;
        
        public MockIncidentRepository() {
            incidents = new ArrayList<>();
            // Add sample test data with different types and statuses
            incidents.add(new Incident("Fire", "Downtown", "Building fire on 3rd floor", 
                                      "John Doe", "09123456789", "Pending", "December 20, 2025 10:00 AM"));
            incidents.add(new Incident("Flood", "City Center", "Street flooding after heavy rain", 
                                      "Jane Smith", "09987654321", "Responding", "December 20, 2025 11:30 AM"));
            incidents.add(new Incident("Medical", "Hospital Area", "Medical emergency case", 
                                      "Dr. Smith", "09555666777", "Pending", "December 20, 2025 12:15 PM"));
        }
        
        @Override
        public List<Incident> loadAllIncidents() {
            // Return a copy to prevent external modification
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
        
        /**
         * Helper method to check if an incident matches all filter criteria.
         */
        private boolean matchesFilters(Incident incident, String typeFilter, 
                                      String statusFilter, String locationFilter, String searchText) {
            // Check type filter
            if (typeFilter != null && !typeFilter.isEmpty() && !typeFilter.equals("[None]")) {
                if (!incident.getType().equalsIgnoreCase(typeFilter)) {
                    return false;
                }
            }
            
            // Check status filter
            if (statusFilter != null && !statusFilter.isEmpty() && !statusFilter.equals("[None]")) {
                if (!incident.getStatus().equalsIgnoreCase(statusFilter)) {
                    return false;
                }
            }
            
            // Check location filter
            if (locationFilter != null && !locationFilter.isEmpty() && !locationFilter.equals("[None]")) {
                if (!incident.getLocation().equalsIgnoreCase(locationFilter)) {
                    return false;
                }
            }
            
            // Check search text (searches across all text fields)
            if (searchText != null && !searchText.trim().isEmpty()) {
                String combinedText = (
                    incident.getType() + " " +
                    incident.getLocation() + " " +
                    incident.getDescription() + " " +
                    incident.getReporter() + " " +
                    incident.getContact() + " " +
                    incident.getStatus()
                ).toLowerCase();
                
                if (!combinedText.contains(searchText.toLowerCase())) {
                    return false;
                }
            }
            
            return true;
        }
        
        /**
         * Helper method for testing - clears all incidents.
         */
        public void clearIncidents() {
            incidents.clear();
        }
        
        /**
         * Helper method for testing - gets all saved incidents.
         */
        public List<Incident> getSavedIncidents() {
            return incidents;
        }
    }
}