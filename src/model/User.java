package model;

/**
 * Represents a system user. 
 * This is a Data Object located in the model package.
 */
public class User {
    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    
    public User(String username, String password, String firstName, String lastName){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}