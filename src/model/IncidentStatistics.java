package model;

/**
 * Model class for holding incident statistics.
 * Follows Encapsulation principle.
 */
public class IncidentStatistics {
    
    private int totalIncidents;
    private int pendingCount;
    private int respondingCount;
    private int resolvedCount;
    
    private int fireCount;
    private int floodCount;
    private int accidentCount;
    private int crimeCount;
    private int medicalCount;
    
    // ==================== Constructors ====================
    
    public IncidentStatistics() {
        // Initialize all counts to 0
    }
    
    // ==================== Getters and Setters ====================
    
    public int getTotalIncidents() {
        return totalIncidents;
    }
    
    public void setTotalIncidents(int totalIncidents) {
        this.totalIncidents = totalIncidents;
    }
    
    public int getPendingCount() {
        return pendingCount;
    }
    
    public void setPendingCount(int pendingCount) {
        this.pendingCount = pendingCount;
    }
    
    public int getRespondingCount() {
        return respondingCount;
    }
    
    public void setRespondingCount(int respondingCount) {
        this.respondingCount = respondingCount;
    }
    
    public int getResolvedCount() {
        return resolvedCount;
    }
    
    public void setResolvedCount(int resolvedCount) {
        this.resolvedCount = resolvedCount;
    }
    
    public int getFireCount() {
        return fireCount;
    }
    
    public void setFireCount(int fireCount) {
        this.fireCount = fireCount;
    }
    
    public int getFloodCount() {
        return floodCount;
    }
    
    public void setFloodCount(int floodCount) {
        this.floodCount = floodCount;
    }
    
    public int getAccidentCount() {
        return accidentCount;
    }
    
    public void setAccidentCount(int accidentCount) {
        this.accidentCount = accidentCount;
    }
    
    public int getCrimeCount() {
        return crimeCount;
    }
    
    public void setCrimeCount(int crimeCount) {
        this.crimeCount = crimeCount;
    }
    
    public int getMedicalCount() {
        return medicalCount;
    }
    
    public void setMedicalCount(int medicalCount) {
        this.medicalCount = medicalCount;
    }
    
    // ==================== Business Logic ====================
    
    /**
     * Increment the count for a specific incident type.
     * @param type The type of incident
     */
    public void incrementTypeCount(String type) {
        switch (type.toLowerCase()) {
            case "fire" -> fireCount++;
            case "flood" -> floodCount++;
            case "accident" -> accidentCount++;
            case "crime" -> crimeCount++;
            case "medical" -> medicalCount++;
        }
    }
    
    /**
     * Increment the count for a specific status.
     * @param status The status of incident
     */
    public void incrementStatusCount(String status) {
        switch (status.toLowerCase()) {
            case "pending" -> pendingCount++;
            case "responding" -> respondingCount++;
            case "resolved" -> resolvedCount++;
        }
    }
    
    /**
     * Increment total incidents count.
     */
    public void incrementTotal() {
        totalIncidents++;
    }
}
