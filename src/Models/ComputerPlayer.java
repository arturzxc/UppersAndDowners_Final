package Models;

import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import uppersanddowners_final.Main;

/**
 * @author Software Engineering 2011-2012 (Group G)
 * This is the computer class which is inherited from the Player class and is
 * responsible for the computer AI.
 * 
 */
public class ComputerPlayer extends Player {

    /**
     * Constructor for player class, takes parameters from the player class
     * and also creates a game object
     * @param Group G
     * @param img
     */
    public ComputerPlayer(String name, Image img) {
        super(name, img);
        model = Main.model;
        gui = Main.gui;
    }
    /**
     * This method runs the computer player in order of what's needed.
     * It invokes the AI and also notifies the observer of the next move.
     */
    @Override
    public void run() {
        model = Main.model;
        gui = Main.gui;
        do {
            notifyRoll();
            decideMove();//IA
            bump();//bump before climbing         
            climb();//climb upper or downer
            bump();//bump after climbing                
            addScores();//add scores and update view        
            notifyMoveFinished();        //notify view about move finished and from which to which position.        
            notifyEnableButtons();        //disable buttons when moving enable buttons when moving finished.
            notifyGameOver();   //if player reached last field then notify the view of winning.
            incrementIfNotMax(); //if the roll didnt give and extra roll then naxt player.
            prevPos = position;
        } while (model.getBoard().getDie().getNumberRolled() == 6);
    }

    /**
     * This method invokes the computer player to  move forward
     * and notifies the observer to repaint
     */
    public void moveForward() {
        int afterMovePos = position + model.getBoard().getDie().getNumberRolled();
        while (position != afterMovePos) {
            this.position++;
            notifyRepaintAndMove();
        }
    }

    /**
     * This method invokes the computer player to  move backward
     * and notifies the observer to repaint
     */
    public void moveBackwards() {
        int afterMovePos = position - model.getBoard().getDie().getNumberRolled();
        while (position != afterMovePos) {
            this.position--;
            notifyRepaintAndMove();
        }
    }
    /**
     * This is the computer AI function.
     * It makes sensible moves either to go forward or
     * backward depending on either a bump, a downer or an upper. Also skips if
     * there's no more fields to go too.
     */

    private void decideMove() {
        boolean scanForbump = false;
        boolean scanForBumpForward = false;
        boolean scanForUpper = false;
        boolean scanForDowner = false;
        boolean ScanIfFinalPosition = false;
        boolean exceedFinalPosition = false;
        for (int j = 0; j < model.getPlayers().size(); j++) {
            if (this.position > model.getBoard().getNumOfFields()//scans for winning square
                    && position - model.getBoard().getDie().getNumberRolled() == model.getPlayers().get(j).getPosition()) {
                ScanIfFinalPosition = true;

                break;
            } else if (position + model.getBoard().getDie().getNumberRolled() > model.getBoard().getNumOfFields()
                    && position - model.getBoard().getDie().getNumberRolled() != model.getPlayers().get(j).getPosition()) {
                //skips play if the move exceeds winning square
                exceedFinalPosition = true;
                break;
            }
            break;
        }

        //scanning for downer
        for (int j = 0; j < model.getBoard().getClaimables().size(); j++) {
            if (position + model.getBoard().getDie().getNumberRolled() == model.getBoard().getClaimables().get(j).getHead()
                    && model.getBoard().getClaimables().get(j).getTail() < model.getBoard().getClaimables().get(j).getHead()) {
                scanForDowner = true;
                break;
            }
        }
        //for scanning for possible upper if moved backwards
        for (int j = 0; j < model.getBoard().getClaimables().size(); j++) {
            if (position - model.getBoard().getDie().getNumberRolled() == model.getBoard().getClaimables().get(j).getHead()
                    && model.getBoard().getClaimables().get(j).getTail() > model.getBoard().getClaimables().get(j).getHead()
                    && model.getBoard().getClaimables().get(j).getTail() > position + model.getBoard().getDie().getNumberRolled()) {
                scanForUpper = true;
                break;
            }
        }

        //for bumping a player if possible by moving backwards
        for (int j = 0; j < model.getPlayers().size(); j++) {
            if (position + model.getBoard().getDie().getNumberRolled() == model.getPlayers().get(j).getPosition()) {
                scanForBumpForward = true;
                break;
            } else if (position - model.getBoard().getDie().getNumberRolled() == model.getPlayers().get(j).getPosition()) {
                scanForbump = true;
                break;
            }
        }

        //SKIP
        if (exceedFinalPosition == true) {
            //model.incrementWhosTurn();
            //setChanged();
            //notifyObservers("move_" + name + "_" + position + "_" + prevPos);
            notifyRepaintAndMove();
            notifySkip();
            //MOVE FORWARD
        } else if (scanForDowner == false && scanForbump == false && ScanIfFinalPosition == false
                && scanForUpper == false || scanForBumpForward == true) {
            moveForward();

            //MOVE BACKWARD
        } else if (position - model.getBoard().getDie().getNumberRolled() >= 2) {
            moveBackwards();
        } else {
            moveForward();
        }
    }
    /**
     * This is the method that notifies the observer of the internal moves that is
     * being carried out by the computer player. Except for skip
     */
    private void notifyRoll() {
        model.getBoard().getDie().roll();
        try {
            setChanged();
            notifyObservers("computerRolled_" + name + "_" + model.getBoard().getDie().getNumberRolled());// lock down all the buttons while moving.
            Thread.sleep(2000);//computer thining time so it doesnt move straight away as that would be too quick.            
            setChanged();
            notifyObservers("computerThinking_" + name);
            Thread.sleep(2000);//computer thining time so it doesnt move straight away as that would be too quick.            
        } catch (InterruptedException ex) {
            Logger.getLogger(ComputerPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
     * This is the method that notifies the observer of the internal moves that is
     * being carried out by the computer player if it skips a move
     */
    
    private void notifySkip(){
        setChanged();
        notifyObservers("computerSkip_"+name);
    }
}
