/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.SidePanel.Buttons;

import Models.ComputerPlayer;
import Models.Die;
import Models.Game;
import Models.HumanPlayer;
import Models.Player;
import Utils.ResourceLoader;
import Views.GUI;
import Views.SidePanel.AnimationDiePanel.AnimationPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.Collections;
import uppersanddowners_final.Main;

/**
 *
 * @author arturzxc
 */
public class RollBtn extends BaseButton {
    
    private Image img = ResourceLoader.loadImage("roll.png");
    private Image imgHover = ResourceLoader.loadImage("rollhover.png");
    private Image imgDisabled = ResourceLoader.loadImage("rolldisabled.png");    


    
    public RollBtn(String tooltipImg) {
        super(tooltipImg);        
        addMouseListener(this);
        currentImage = img;
    }
    

    
    @Override
    public void mouseClicked(MouseEvent e) {
        //do nothing
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (isEnabled()) {//only when enabled and the player is a human
            
            
            //ROLL////////////////////////////////////////////////////////
            Main.model.getBoard().getDie().roll();//roll the die    
            Main.gui.showMessage(Main.model.getPlayerWhosTurn().getName() + " rolled " + Main.model.getBoard().getDie().getNumberRolled());
            AnimationPanel ap = Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getSidePanel().getAnimationPanel();
            ap.setFirstRoll(false);
            Thread t = new Thread((Runnable) ap);                
            t.start();    //start animation panel              
            if (Main.model.getBoard().getDie().getNumberRolled() == 6) {
                Main.gui.showMessage(Main.model.getPlayerWhosTurn().getName() + " has earned an extra move!");
            }             
            //////////////////////////////////////////////////////////////           
     
            
            //HANDLE BUTTONS ////////////////////////////////////////////
            Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getSidePanel().getButtonsPanel().getMoveForwardBtn().setEnabled(true);
            if (Main.model.getPlayerWhosTurn().getPosition() - Main.model.getBoard().getDie().getNumberRolled() > 1) {//prevent going backwards further than 1
                Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getSidePanel().getButtonsPanel().getMoveBackBtn().setEnabled(true);                    
            }                                                              
            setEnabled(false);  
            /////////////////////////////////////////////////////////////
        }        
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        //do nothing
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        currentImage = imgHover;
        repaint();
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        currentImage = img;
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;        
        if (isEnabled()) {
            g2d.drawImage(currentImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            g2d.drawImage(imgDisabled, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
