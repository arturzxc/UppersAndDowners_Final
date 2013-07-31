package Models;

import java.awt.Image;
import uppersanddowners_final.Main;

/**
 * @author Software Engineering 2011-2012 (Group G)
 */
public class HumanPlayer extends Player {

    private int colourIndex;

    public HumanPlayer(String name, Image img) {
        super(name, img);
        model = Main.model;
        gui = Main.gui;

    }

    /** 
     * Moves player forward and notifies the view.
     */
    public void moveForward() {
        int afterMovePos = position + model.getBoard().getDie().getNumberRolled();
        while (position != afterMovePos) {
            this.position++;
            notifyRepaintAndMove();
        }        
    }

    /** 
     * Moves player backward and notifies the view.
     */
    public void moveBackwards() {
        int afterMovePos = position - model.getBoard().getDie().getNumberRolled();
        while (position != afterMovePos) {
            this.position--;
            notifyRepaintAndMove();
        }
    }

    /** 
     * 
     * @return color index for player's token
     */
    public int getColourIndex() {
        return colourIndex;
    }

    /**
     * Moves player forward or backward.
     * It also tries to bump climb collect scores.
     */
    @Override    
    public void run() {
        if (this.moveForward) {
            moveForward();
        } else {
            moveBackwards();
        }

        bump();//bump before climbing         
        climb();//climb upper or downer
        bump();//bump after climbing                
        addScores();//add scores and update view        
        notifyMoveFinished();        //notify view about move finished and from which to which position.        
        notifyEnableButtons();        //disable buttons when moving enable buttons when moving finished.
        notifyGameOver();   //if player reached last field then notify the view of winning.
        incrementIfNotMax(); //if the roll didnt give and extra roll then naxt player.
        prevPos =position;
    }
    

}
