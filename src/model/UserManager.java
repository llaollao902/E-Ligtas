package model;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users;
    private final UserDAO userDAO;

    public UserManager() {
        // setup the data handler and load saved users
        this.userDAO = new UserDAO();
        this.users = userDAO.loadUsers();
    }

    public boolean login(String username, String password) {
        // check if username and password match any user in the list
        return users.stream()
                .anyMatch(u -> u.getUsername().equals(username) && 
                               u.getPassword().equals(password));
    }

    public boolean usernameExists(String username) {
        // check if the username is already taken
        return users.stream().anyMatch(u -> u.getUsername().equals(username));
    }

    public void signup(String username, String password, String firstName, String lastName) {
        // create new user, add to list, and save to storage
        User user = new User(username, password, firstName, lastName);
        users.add(user);
        userDAO.saveUsers(users);
    }

    public List<User> getUsers() {
        // return a copy of the user list
        return new ArrayList<>(users);
    }
}