package app;

import view.HomeScreen;
import controller.HomeController;
import model.UserManager;

public class Main {
    public static void main(String[] args) {
    	//initializes the model
    	//handles user logic
        UserManager model = new UserManager();
        
        //initializes view
        //creates gui
        HomeScreen homeView = new HomeScreen();
        
        // initializes controller
        //listen for UI events and updates data
        new HomeController(homeView, model);
        
        //makes window visible
        homeView.setVisible(true);
    }
}