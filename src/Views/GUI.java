package Views;

import Views.SidePanel.AnimationDiePanel.AnimationPanel;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arturzxc
 */
public class GUI implements Observer{ // Manages Updates
    private MainFrame mainFrame;

    public GUI() {
        setUpMainFrame();
    }
    
    /**
     * Returns the Main Frame.
     * @return 
     */
    public MainFrame getMainFrame() {
        return mainFrame;
    }
        
    /**
     * Sets up the MainFrame and makes it visible.
     */
    private void setUpMainFrame(){
        mainFrame=new MainFrame();
        mainFrame.setVisible(true);
    }
    
    /**
     * To show fading message on top left hand corner of board.
     * @param msg 
     */
    public void showMessage(String msg){
        mainFrame.getTotalContentPane().getGameContentPane().getBoardPanel().getBoardMessagePanel().showMessage(msg);
        mainFrame.getTotalContentPane().getGameContentPane().getLogPane().addLog(msg);
    }
    
    /**
     * To show fading image at the center of the board.
     * @param imgName 
     */
    public void showImage(String imgName){
        mainFrame.getTotalContentPane().getGameContentPane().getBoardPanel().getbBoardImagePanel().showPicture(imgName);
    }
    
    /**
     * All updates needing to be done to the view are done via this method.
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable o, Object arg) {
        String args = (String)arg;
        String[] argArr = args.split("_");
        
        //System.out.println("OBSERVER UPDATE MSG: "+args);//Debugging help.
        
        
        //MOVEMENT////////////////////////////////////////////////////////////////////////////
        if(argArr[0].equalsIgnoreCase("repaint")){            
            mainFrame.getTotalContentPane().getGameContentPane().repaint();
        }        
        
        else if(argArr[0].equalsIgnoreCase("moveFinished")){
            showMessage(argArr[1]+" moved from position "+argArr[3]+" to position "+argArr[2]);
            mainFrame.getTotalContentPane().getGameContentPane().getMiniScorePanel().refresh();
        } 
        //////////////////////////////////////////////////////////////////////////////////////////
        
        
        //BUMP AND CLIMB ///////////////////////////////////////////////////////////////////////
        else if(argArr[0].equalsIgnoreCase("bump")){
            showMessage(argArr[1]+" has bumped "+argArr[2]+" at position "+argArr[3]);
            showImage("bumped.png");
        }
        
        else if(argArr[0].equalsIgnoreCase("climb")){
            String name = argArr[1];
            int pos = Integer.parseInt(argArr[2]);
            int prevPos = Integer.parseInt(argArr[3]);            
            if(pos>prevPos){//upper
                showMessage(name+" has climbed from "+prevPos+ " to "+pos);
                showImage("movedup.png");
            }else{
                showMessage(name+" has fallen from "+prevPos+ " to "+pos);
                showImage("moveddown.png");
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////
       
                
        //BUTTONS OPERATIONS///////////////////////////////////////////////////////////////////
        else if(argArr[0].equalsIgnoreCase("enableButtons")){            
            mainFrame.getTotalContentPane().getGameContentPane().getSidePanel().getButtonsPanel().getRollBtn().setEnabled(true);            
        }
        
        else if(argArr[0].equalsIgnoreCase("disableButtons")){                        
            mainFrame.getTotalContentPane().getGameContentPane().getSidePanel().getButtonsPanel().disableAll();
        }
        ///////////////////////////////////////////////////////////////////////////////////////
        
        
        
        //GAME SPECIFIC///////////////////////////////////////////////////////////////////////
        else if(argArr[0].equalsIgnoreCase("gameOver")){
            showMessage(argArr[1]+" has won the game! The game will end in 5 sec..." );
            try {
                Thread.sleep(5000);            
                mainFrame.getTotalContentPane().getGameContentPane().getSidePanel().getButtonsPanel().disableAll();
                mainFrame.getTotalContentPane().getScoreContentPane().initComponents();//gets newest score and positions.
                mainFrame.getTotalContentPane().getScoreContentPane().composeComponents();
                mainFrame.getTotalContentPane().switchToScoreContentPane();
            } catch (InterruptedException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////
        
        
        
        //COMPUTER SPECIFIC////////////////////////////////////////////////////////////////
        
        else if(argArr[0].equalsIgnoreCase("computerSkip")){
            showMessage(argArr[1]+" [Computer] skipped turn");
        }
        
        else if(argArr[0].equalsIgnoreCase("computerThinking")){
            showMessage(argArr[1]+" [Computer] is thinking...");
        }
        
        else if(argArr[0].equalsIgnoreCase("computerRolled")){
            showMessage(argArr[1]+" [Computer] rolled "+argArr[2]);
            AnimationPanel ap = mainFrame.getTotalContentPane().getGameContentPane().getSidePanel().getAnimationPanel();
            ap.setFirstRoll(false);
            Thread t  = new Thread(ap);
            t.start();
        } 
        ///////////////////////////////////////////////////////////////////////////////////////////
    }
    
}
