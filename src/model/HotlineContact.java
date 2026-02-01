package model;

/**
 * Model class representing an Emergency Hotline Contact.
 * Follows Encapsulation principle.
 */
public class HotlineContact {
    
    private String name;
    private String description;
    private String number;
    private String link;
    
    // ==================== Constructor ====================
    
    public HotlineContact(String name, String description, String number, String link) {
        this.name = name;
        this.description = description;
        this.number = number;
        this.link = link;
    }
    
    // ==================== Getters and Setters ====================
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    public String getLink() {
        return link;
    }
    
    public void setLink(String link) {
        this.link = link;
    }
    
    @Override
    public String toString() {
        return String.format("%s - %s", name, number);
    }
}
