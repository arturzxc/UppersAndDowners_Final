package Models;

import Views.GUI;

import java.awt.Image;
import java.util.Observable;
import uppersanddowners_final.Main;

/**
 *
 * @author Software Engineering 2011-2012 (Group G)
 * 
 * This is an abstract class player which has functions common functions
 * shared by HumanPlayer and ComputerPlaer such as bump climb and notifications
 * of observers.
 */
abstract public class Player extends Observable implements Runnable {
    protected Game model;
    protected GUI gui;
    protected int position;
    protected int prevPos;
    protected String name;
    protected Image image;
    protected int startPosition;
    protected int currentScore;    
    protected boolean moveForward;
    protected int delay=200;
    
    
    /**
     * Default constructor
     * @param name player name
     * @param img image of token
     */
    public Player(String name, Image img) {
        model=Main.model;
        gui=Main.gui;
        
        this.position = 1; // formerly 0
        this.prevPos = position;
        this.name = name;
        this.image = img;
        this.currentScore = 0;
        this.moveForward = true;
        
        
    }

    public int getPrevPos() {
        return prevPos;
    }

    public void setMoveForward(boolean moveForward) {
        this.moveForward = moveForward;
    }

    /**
     * Tries to bump players that are on the landing position of this player
     */
    protected void bump() {

        for (int i = 0; i < model.getPlayers().size(); i++) {
            if (!model.getPlayers().get(i).getName().equals(name)
                    && position == model.getPlayers().get(i).getPosition()
                    && position != 1) {
                model.setPlayerToBump(model.getPlayers().get(i));//model holds player to bump
                model.getPlayerToBump().reset(); //if current player's position matches another player's position, reset position to 0.
                setChanged();
                notifyObservers("bump_" + name + "_" + model.getPlayerToBump().getName() + "_" + position);
            }
        }
    }//will bump anyyone on the spot it lands

    /**
     * This function tries to climb any upper or downer on the landing spot.
     */
    protected void climb() {
        //gets the position of the player        
        for (int i = 0; i < model.getBoard().getClaimables().size(); i++) {
            if (position == model.getBoard().getClaimables().get(i).getHead()) {
                position = model.getBoard().getClaimables().get(i).getTail();
                setChanged();
                notifyObservers("climb_" + name + "_" + position + "_" + prevPos);
            }
        }
    }//if there is a ladder or snake on the field it lands then it will climb
    
    
    /**
     * Notifies the view that it should repaint the board of players
     * as player has moved.
     */    
    protected void notifyRepaintAndMove(){
            try {
                Thread.sleep(delay);
                setChanged();
                notifyObservers("repaint");
            } catch (InterruptedException ex) {
                //could not repaint
            }
    }
    
    
    /**
     * If the player did not roll the max number the increment the turn counter 
     * in the Game to let other players have their turn.
     */
    protected void incrementIfNotMax(){                
        if (model.getBoard().getDie().getNumberRolled() != model.getBoard().getDie().getNumberOfSides()) {            
            model.incrementWhosTurn();
        }
    }
    
    /**
     * The player notifies the game that he has won the game and that it should end.
     */
    protected void notifyGameOver(){
        if (this.position == model.getBoard().getNumOfFields()) {
            Main.model.setWinner(this);          
            setChanged();
            notifyObservers("gameOver_" + name);
        }
    }
    
    
    /**
     * Enable buttons for HumanPlayers and disable for ComputerPlayer.
     * In addition it disables the roll and movement buttons when someone is
     * moving.
     */
    protected void notifyEnableButtons(){
        setChanged();
        if(Main.model.getBoard().getDie().getNumberRolled()!=6){//Next player will go
            if (Main.model.getNextPlayer() instanceof HumanPlayer) {
                notifyObservers("enableButtons");
            }
            else{
                notifyObservers("disablePlayer");
            } 
        }else{
            if (Main.model.getPlayerWhosTurn() instanceof HumanPlayer) {
                notifyObservers("enableButtons");
            }
            else{
                notifyObservers("disablePlayer");
            } 
            
        }
    }
        
    /**
     * Player notifies the view that the move was complete and for it to display
     * appropriate message.
     */
    protected void notifyMoveFinished(){
        setChanged();
        notifyObservers("moveFinished_" + name + "_" + position + "_" + prevPos);
    }
    
    /**
     * Scans the landing square for any
     * points it holds and add them to the players score
     */
    protected void addScores(){
        //adding scores
        if(Main.model.getBoard().getSquares().get(position-1).getScore() != 0){
            this.addToScore(Main.model.getBoard().getSquares().get(position-1).getScore());
        }
    }
    
    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public void addToScore(int points) {
        this.currentScore += points;
    }

    public void takeAwayFromScore(int points) {
        this.currentScore -= points;
    }

    protected void setPosition(int position) {
        this.position = position;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    
    public int getCurrentScore() {
        return currentScore;
    }

    public Image getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void reset() {
        this.position = 1; // formerly 0        
    }

    public void compareTo() {
    }
}
