package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    // path to the file where users are stored
    private final String filePath = "UserDatabase.json";

    public List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        File file = new File(filePath);

        // return empty list if file doesn't exist or is empty
        if (!file.exists() || file.length() == 0) return users;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            // read file content line by line
            while ((line = reader.readLine()) != null) jsonBuilder.append(line.trim());

            String json = jsonBuilder.toString();
            // simple check to find the users array in the string
            if (json.contains("\"users\"")) {
                String usersArray = json.split("\\[")[1].split("]")[0];
                if (usersArray.trim().isEmpty()) return users;

                // split the string into individual user objects
                String[] userObjects = usersArray.split("\\},\\{");
                for (String obj : userObjects) {
                    users.add(parseUserJson(obj));
                }
            }
        } catch (Exception e) {
            System.err.println("error loading users: " + e.getMessage());
        }
        return users;
    }

    public void saveUsers(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // manually write the json file structure
            writer.write("{\n  \"users\": [\n");
            for (int i = 0; i < users.size(); i++) {
                User u = users.get(i);
                // format and write each user object
                writer.write(String.format(
                    "    {\n      \"username\": \"%s\",\n      \"password\": \"%s\",\n      \"firstName\": \"%s\",\n      \"lastName\": \"%s\"\n    }%s\n",
                    u.getUsername(), u.getPassword(), u.getFirstName(), u.getLastName(),
                    (i < users.size() - 1 ? "," : "")
                ));
            }
            writer.write("  ]\n}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private User parseUserJson(String obj) {
        // clean up json characters and split into fields
        obj = obj.replace("{", "").replace("}", "").trim();
        String[] fields = obj.split(",");
        String user = "", pass = "", first = "", last = "";
        
        for (String field : fields) {
            // split into key-value pairs and remove quotes
            String[] kv = field.split(":");
            String key = kv[0].trim().replace("\"", "");
            String val = kv[1].trim().replace("\"", "");
            
            // assign values to variables based on key
            switch (key) {
                case "username" -> user = val;
                case "password" -> pass = val;
                case "firstName" -> first = val;
                case "lastName" -> last = val;
            }
        }
        return new User(user, pass, first, last);
    }
}