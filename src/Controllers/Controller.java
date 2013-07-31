package Controllers;

import Views.GUI;
import Models.Game;
import java.util.Observer;
import uppersanddowners_final.Main;
/**
 *
 * @author ak303
 */
public class Controller{
    private Game model;
    private GUI gui;
    
    public Controller() {        

    }
    /**
     * this is triggered when the players are added as we don't know how many
     * players will be in the game and if it was triggered 
     * here we would have to trigger it again as new 
     * players are added in the custom game.        
     */
    public void addObservers(){
        model = Main.model;
        gui=Main.gui;//if the game is custome then need to refresh gui reference.
        
        model.addObserver(gui);
        
        for(int i=0;i<model.getPlayers().size();i++){            
            model.getPlayers().get(i).addObserver((Observer)gui);
        }
    }
   
}
