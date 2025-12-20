package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Model class representing an Incident Report.
 * Follows Encapsulation principle - all fields are private with public getters/setters.
 */
public class Incident {
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMMM dd, yyyy hh:mm a");
    
    private String type;
    private String location;
    private String description;
    private String reporter;
    private String contact;
    private String status;
    private String date;
    
    // ==================== Constructors ====================
    
    public Incident() {
        this.status = "Pending";
        this.date = LocalDateTime.now().format(DATE_FORMATTER);
    }
    
    public Incident(String type, String location, String description, 
                    String reporter, String contact) {
        this();
        this.type = type;
        this.location = location;
        this.description = description;
        this.reporter = reporter;
        this.contact = contact;
    }
    
    public Incident(String type, String location, String description, 
                    String reporter, String contact, String status, String date) {
        this.type = type;
        this.location = location;
        this.description = description;
        this.reporter = reporter;
        this.contact = contact;
        this.status = status;
        this.date = date;
    }
    
    // ==================== Getters and Setters (Encapsulation) ====================
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getReporter() {
        return reporter;
    }
    
    public void setReporter(String reporter) {
        this.reporter = reporter;
    }
    
    public String getContact() {
        return contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    // ==================== Business Logic ====================
    
    /**
     * Converts the incident to a table row representation.
     * @return Object array containing all incident data
     */
    public Object[] toTableRow() {
        return new Object[]{type, location, description, contact, reporter, date, status};
    }
    
    /**
     * Converts the incident to JSON format.
     * @return JSON string representation
     */
    public String toJson() {
        return String.format(
            "{\n" +
            "  \"type\": \"%s\",\n" +
            "  \"location\": \"%s\",\n" +
            "  \"description\": \"%s\",\n" +
            "  \"reporter\": \"%s\",\n" +
            "  \"contact\": \"%s\",\n" +
            "  \"status\": \"%s\",\n" +
            "  \"date\": \"%s\"\n" +
            "}",
            escapeJson(type),
            escapeJson(location),
            escapeJson(description),
            escapeJson(reporter),
            escapeJson(contact),
            escapeJson(status),
            escapeJson(date)
        );
    }
    
    private String escapeJson(String value) {
        if (value == null) return "";
        return value.replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r");
    }
    
    @Override
    public String toString() {
        return String.format("Incident{type='%s', location='%s', status='%s'}", 
                           type, location, status);
    }
}
