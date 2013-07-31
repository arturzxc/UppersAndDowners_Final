/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.SidePanel.FirstRollPanel;

import Views.SidePanel.Buttons.*;
import Models.Die;
import Models.Player;
import Utils.ResourceLoader;
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
public class FirstRollBtn extends BaseButton {
    
    private Image img = ResourceLoader.loadImage("roll.png");
    private Image imgHover = ResourceLoader.loadImage("rollhover.png");
    private Image imgDisabled = ResourceLoader.loadImage("rolldisabled.png");        
    
    public FirstRollBtn(String tooltipImg) {
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
            setEnabled(false);  
            
            //ROLL
            AnimationPanel ap = Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getSidePanel().getAnimationPanel();
            ap.setFirstRoll(true);
            Thread t = new Thread((Runnable) ap);                
            t.start();    //start animation panel
            Collections.shuffle(Main.model.getPlayers());//shuffles the players order.
            
            //HANDLE BUTTONS AND UPDATES OF PLAYERS POSITIONS AND NAMES
            for (int i = 0; i < Main.model.getPlayers().size(); i++) {
                Main.model.getPlayers().get(i).setStartPosition(i + 1);//sets new start position for each player in the game
            }                
            Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getSidePanel().getFirstRollPanel().getPlayersPnl().addNames();
            Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getSidePanel().getFirstRollPanel().getRollsPnl().showRolls();                
            Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getSidePanel().getFirstRollPanel().getStartGame().setEnabled(true);
            Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().repaint();//so that the view reflects new order of players afer shuffling

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
