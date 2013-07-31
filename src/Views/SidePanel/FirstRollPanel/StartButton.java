/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.SidePanel.FirstRollPanel;

import Models.ComputerPlayer;
import Utils.ResourceLoader;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import uppersanddowners_final.Main;

/**
 *
 * @author arturzxc
 */
public class StartButton extends JLabel implements MouseListener{
    
    private Image img = ResourceLoader.loadImage("startgame.png");
    private Image imgHover = ResourceLoader.loadImage("startgamehover.png");
    private Image currentImage = img;
    
    public StartButton() {                
        addMouseListener(this);
        setEnabled(false);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        //nothing
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(isEnabled()){
            Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getSidePanel().switchToGamePlay();            
            
            //deactivate popup menu
            Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getBoardPanel().getBoardSquarePanel().setActivePopupMenus(false);
            Main.controller.addObservers();//refresh observers as there might be new players to be ovserved.
            
            if(Main.model.getPlayerWhosTurn() instanceof ComputerPlayer){
                Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getSidePanel().getButtonsPanel().getRollBtn().setEnabled(false);//disable roll button for computer.
                Main.model.movePlayer(true);
            }
        }else{
            Main.gui.showMessage("You must roll first!");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        currentImage=imgHover;
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        currentImage=img;
        repaint();
    }

        
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;        
        g2d.drawImage(currentImage, 0,0,getWidth(),getHeight(),this);        
        
    }
}
