package test.model;

import org.junit.Test;

import model.User;

import static org.junit.Assert.*;

/**
 * JUnit test class for User model.
 * Tests the immutable user data object (POJO).
 */
public class UserTest {
    
    // ==================== Constructor Tests ====================
    
    /**
     * Tests that constructor properly initializes all user fields.
     */
    @Test
    public void testConstructor() {
        User user = new User("johndoe", "password123", "John", "Doe");
        
        assertEquals("johndoe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
    }
    
    /**
     * Tests constructor with empty strings doesn't throw exceptions.
     */
    @Test
    public void testConstructorWithEmptyStrings() {
        User user = new User("", "", "", "");
        
        assertEquals("", user.getUsername());
        assertEquals("", user.getPassword());
        assertEquals("", user.getFirstName());
        assertEquals("", user.getLastName());
    }
    
    // ==================== Getter Tests ====================
    
    /**
     * Tests getUsername returns correct username.
     */
    @Test
    public void testGetUsername() {
        User user = new User("testuser", "pass", "Test", "User");
        assertEquals("testuser", user.getUsername());
    }
    
    /**
     * Tests getPassword returns correct password.
     */
    @Test
    public void testGetPassword() {
        User user = new User("user", "securepass", "First", "Last");
        assertEquals("securepass", user.getPassword());
    }
    
    /**
     * Tests getFirstName returns correct first name.
     */
    @Test
    public void testGetFirstName() {
        User user = new User("user", "pass", "Alice", "Smith");
        assertEquals("Alice", user.getFirstName());
    }
    
    /**
     * Tests getLastName returns correct last name.
     */
    @Test
    public void testGetLastName() {
        User user = new User("user", "pass", "Bob", "Johnson");
        assertEquals("Johnson", user.getLastName());
    }
    
    // ==================== Immutability Tests ====================
    
    /**
     * Tests that User object is immutable (fields are final).
     * Once created, values cannot be changed.
     */
    @Test
    public void testUserImmutability() {
        User user = new User("original", "pass123", "Original", "Name");
        
        // Get values
        String username = user.getUsername();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        
        // Values should remain the same
        assertEquals("original", user.getUsername());
        assertEquals("pass123", user.getPassword());
        assertEquals("Original", user.getFirstName());
        assertEquals("Name", user.getLastName());
    }
    
    /**
     * Tests creating multiple user instances with different data.
     */
    @Test
    public void testMultipleUserInstances() {
        User user1 = new User("user1", "pass1", "First1", "Last1");
        User user2 = new User("user2", "pass2", "First2", "Last2");
        
        assertNotEquals(user1.getUsername(), user2.getUsername());
        assertNotEquals(user1.getPassword(), user2.getPassword());
        assertNotEquals(user1.getFirstName(), user2.getFirstName());
        assertNotEquals(user1.getLastName(), user2.getLastName());
    }
    
    /**
     * Tests user with special characters in fields.
     */
    @Test
    public void testUserWithSpecialCharacters() {
        User user = new User("user@123", "p@ss!word", "O'Brien", "Smith-Jones");
        
        assertEquals("user@123", user.getUsername());
        assertEquals("p@ss!word", user.getPassword());
        assertEquals("O'Brien", user.getFirstName());
        assertEquals("Smith-Jones", user.getLastName());
    }
}