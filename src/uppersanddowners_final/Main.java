package uppersanddowners_final;

import Controllers.Controller;
import Models.Game;
import Views.GUI;

/**
 *
 * @author arturzxc
 */
public class Main {    
    public static Game model;
    public static GUI gui;    
    public static Controller controller;  
    
    public static void main(String [] args){
        model = new Game(true);                       //MODEL              
        gui = new GUI();                          //VIEW        
        controller = new Controller();   //CONTROLLER 

    }

    
    
    
}
