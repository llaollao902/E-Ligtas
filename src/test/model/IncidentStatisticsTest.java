package test.model;

import org.junit.Test;

import model.IncidentStatistics;

import org.junit.Before;
import static org.junit.Assert.*;

/**
 * JUnit test class for IncidentStatistics model.
 * Tests statistics calculation and counting functionality.
 */
public class IncidentStatisticsTest {
    
    private IncidentStatistics stats;
    
    /**
     * Sets up a fresh IncidentStatistics instance before each test.
     */
    @Before
    public void setUp() {
        stats = new IncidentStatistics();
    }
    
    // ==================== Constructor Tests ====================
    
    /**
     * Tests that constructor initializes all counts to zero.
     */
    @Test
    public void testDefaultConstructorInitializesToZero() {
        assertEquals(0, stats.getTotalIncidents());
        assertEquals(0, stats.getPendingCount());
        assertEquals(0, stats.getRespondingCount());
        assertEquals(0, stats.getResolvedCount());
        assertEquals(0, stats.getFireCount());
        assertEquals(0, stats.getFloodCount());
        assertEquals(0, stats.getAccidentCount());
        assertEquals(0, stats.getCrimeCount());
        assertEquals(0, stats.getMedicalCount());
    }
    
    // ==================== Getter and Setter Tests ====================
    
    /**
     * Tests setters and getters for total incidents count.
     */
    @Test
    public void testTotalIncidentsSetterAndGetter() {
        stats.setTotalIncidents(50);
        assertEquals(50, stats.getTotalIncidents());
    }
    
    /**
     * Tests setters and getters for status counts.
     */
    @Test
    public void testStatusCountsSettersAndGetters() {
        stats.setPendingCount(10);
        stats.setRespondingCount(20);
        stats.setResolvedCount(30);
        
        assertEquals(10, stats.getPendingCount());
        assertEquals(20, stats.getRespondingCount());
        assertEquals(30, stats.getResolvedCount());
    }
    
    /**
     * Tests setters and getters for incident type counts.
     */
    @Test
    public void testTypeCountsSettersAndGetters() {
        stats.setFireCount(5);
        stats.setFloodCount(8);
        stats.setAccidentCount(12);
        stats.setCrimeCount(15);
        stats.setMedicalCount(10);
        
        assertEquals(5, stats.getFireCount());
        assertEquals(8, stats.getFloodCount());
        assertEquals(12, stats.getAccidentCount());
        assertEquals(15, stats.getCrimeCount());
        assertEquals(10, stats.getMedicalCount());
    }
    
    // ==================== Business Logic Tests ====================
    
    /**
     * Tests incrementTotal method increases total incident count.
     */
    @Test
    public void testIncrementTotal() {
        assertEquals(0, stats.getTotalIncidents());
        
        stats.incrementTotal();
        assertEquals(1, stats.getTotalIncidents());
        
        stats.incrementTotal();
        stats.incrementTotal();
        assertEquals(3, stats.getTotalIncidents());
    }
    
    /**
     * Tests incrementTypeCount method correctly increments fire count.
     */
    @Test
    public void testIncrementTypeCountFire() {
        stats.incrementTypeCount("Fire");
        assertEquals(1, stats.getFireCount());
        
        stats.incrementTypeCount("fire"); // Test case insensitivity
        assertEquals(2, stats.getFireCount());
    }
    
    /**
     * Tests incrementTypeCount method correctly increments flood count.
     */
    @Test
    public void testIncrementTypeCountFlood() {
        stats.incrementTypeCount("Flood");
        stats.incrementTypeCount("FLOOD");
        assertEquals(2, stats.getFloodCount());
    }
    
    /**
     * Tests incrementTypeCount method correctly increments accident count.
     */
    @Test
    public void testIncrementTypeCountAccident() {
        stats.incrementTypeCount("Accident");
        stats.incrementTypeCount("accident");
        stats.incrementTypeCount("ACCIDENT");
        assertEquals(3, stats.getAccidentCount());
    }
    
    /**
     * Tests incrementTypeCount method correctly increments crime count.
     */
    @Test
    public void testIncrementTypeCountCrime() {
        stats.incrementTypeCount("Crime");
        assertEquals(1, stats.getCrimeCount());
    }
    
    /**
     * Tests incrementTypeCount method correctly increments medical count.
     */
    @Test
    public void testIncrementTypeCountMedical() {
        stats.incrementTypeCount("Medical");
        stats.incrementTypeCount("medical");
        assertEquals(2, stats.getMedicalCount());
    }
    
    /**
     * Tests incrementTypeCount with invalid type doesn't crash 
     * and doesn't affect any counters.
     */
    @Test
    public void testIncrementTypeCountWithInvalidType() {
        stats.incrementTypeCount("InvalidType");
        
        assertEquals(0, stats.getFireCount());
        assertEquals(0, stats.getFloodCount());
        assertEquals(0, stats.getAccidentCount());
        assertEquals(0, stats.getCrimeCount());
        assertEquals(0, stats.getMedicalCount());
    }
    
    /**
     * Tests incrementStatusCount method correctly increments pending count.
     */
    @Test
    public void testIncrementStatusCountPending() {
        stats.incrementStatusCount("Pending");
        stats.incrementStatusCount("pending");
        stats.incrementStatusCount("PENDING");
        assertEquals(3, stats.getPendingCount());
    }
    
    /**
     * Tests incrementStatusCount method correctly increments responding count.
     */
    @Test
    public void testIncrementStatusCountResponding() {
        stats.incrementStatusCount("Responding");
        stats.incrementStatusCount("responding");
        assertEquals(2, stats.getRespondingCount());
    }
    
    /**
     * Tests incrementStatusCount method correctly increments resolved count.
     */
    @Test
    public void testIncrementStatusCountResolved() {
        stats.incrementStatusCount("Resolved");
        assertEquals(1, stats.getResolvedCount());
    }
    
    /**
     * Tests incrementStatusCount with invalid status doesn't crash
     * and doesn't affect any counters.
     */
    @Test
    public void testIncrementStatusCountWithInvalidStatus() {
        stats.incrementStatusCount("InvalidStatus");
        
        assertEquals(0, stats.getPendingCount());
        assertEquals(0, stats.getRespondingCount());
        assertEquals(0, stats.getResolvedCount());
    }
    
    /**
     * Tests multiple increment operations work together correctly
     * to simulate real-world usage.
     */
    @Test
    public void testMultipleIncrements() {
        // Simulate processing multiple incidents
        stats.incrementTotal();
        stats.incrementTypeCount("Fire");
        stats.incrementStatusCount("Pending");
        
        stats.incrementTotal();
        stats.incrementTypeCount("Medical");
        stats.incrementStatusCount("Responding");
        
        stats.incrementTotal();
        stats.incrementTypeCount("Fire");
        stats.incrementStatusCount("Resolved");
        
        assertEquals(3, stats.getTotalIncidents());
        assertEquals(2, stats.getFireCount());
        assertEquals(1, stats.getMedicalCount());
        assertEquals(1, stats.getPendingCount());
        assertEquals(1, stats.getRespondingCount());
        assertEquals(1, stats.getResolvedCount());
    }
}