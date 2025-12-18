package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
	//list that stores all users in memory
    private List<User> users;
    //path to JSON file where user data is stored
    private final String filePath = "UserDatabase.json";

    //initializes the user list and loads users from file
    public UserManager() {
        users = new ArrayList<>();
        loadUsersFromFile();
        System.out.println("User file path: " + new File(filePath).getAbsolutePath());
    }

    // login
    //checks if a username and password match any stored
    public boolean login(String username, String password) {
        return users.stream()
                .anyMatch(u -> 
                		u.getUsername().equals(username) && 
                		u.getPassword().equals(password)
                	);
    }

    // checks if a username and password match any stored user
    public boolean usernameExists(String username) {
        return users.stream().anyMatch(u -> u.getUsername().equals(username));
    }

    // signup
    //creates a new user, adds it to the list, and saves to file
    public void signup(String username, String password, String firstName, String lastName) {
        User user = new User(username, password, firstName, lastName);
        users.add(user);
        saveUsersToFile();
    }

    // save to file
    // writes all users into JSON file in a readable format
    private void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("{\n  \"users\": [\n");
            //loop thru all users and write each one
            for (int i = 0; i < users.size(); i++) {
                User u = users.get(i);
                writer.write("    {\n");
                writer.write("      \"username\": \"" + u.getUsername() + "\",\n");
                writer.write("      \"password\": \"" + u.getPassword() + "\",\n");
                writer.write("      \"firstName\": \"" + u.getFirstName() + "\",\n");
                writer.write("      \"lastName\": \"" + u.getLastName() + "\"\n");
                writer.write("    }");
                //add a comma if this is not the last user
                if (i < users.size() - 1) {
                    writer.write(",");
                }
                writer.write("\n");
            }
            //end of JSON structure
            writer.write("  ]\n}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // load from file
    //loads users from the JSON file and converts them into User objects
    private void loadUsersFromFile() {
        File file = new File(filePath);

        //if file does not exist or is exist, start with an empty list
        if (!file.exists() || file.length() == 0) {
            users = new ArrayList<>();
            return;
        }

        users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            //read entire file into one string
        	StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line.trim());
            }

            String json = jsonBuilder.toString();

            // check if JSON contain the users array
            if (json.contains("\"users\"")) {
            	//extract the content inside the brackets
                String usersArray = json.split("\\[")[1].split("]")[0];
                //split each user object
                String[] userObjects = usersArray.split("\\},\\{");
                
                for (String obj : userObjects) {
                    //remove braces and clean string
                	obj = obj.replace("{", "").replace("}", "").trim();
                   
                	//split key-value pairs
                	String[] fields = obj.split(",");
                	
                    String username = "", password = "", firstName = "", lastName = "";
                    
                    //extract values from each field
                    for (String field : fields) {
                        String[] kv = field.split(":");
                        String key = kv[0].trim().replace("\"", "");
                        String value = kv[1].trim().replace("\"", "");
                        switch (key) {
                            case "username" -> username = value;
                            case "password" -> password = value;
                            case "firstName" -> firstName = value;
                            case "lastName" -> lastName = value;
                        }
                    }
                    //create User object and add to list
                    users.add(new User(username, password, firstName, lastName));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            users = new ArrayList<>();
        }
    }

    //returns a copy of the user list to prevent external modification
    public List<User> getUsers() {
        return new ArrayList<>(users);
    }
}
