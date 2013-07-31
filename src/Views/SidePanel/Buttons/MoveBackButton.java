/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.SidePanel.Buttons;

import Models.Die;
import Models.Game;
import Models.HumanPlayer;
import Models.Player;
import Utils.ResourceLoader;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import uppersanddowners_final.Main;

/**
 *
 * @author arturzxc
 */
public class MoveBackButton extends BaseButton{

    private Image img = ResourceLoader.loadImage("mb.png");
    private Image imgHover = ResourceLoader.loadImage("mbhover.png");
    private Image imgDisabled = ResourceLoader.loadImage("mbdisabled.png");

    public MoveBackButton(String tooltipImg) {
        super(tooltipImg);                             
        setEnabled(false);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //nothing
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(isEnabled() && Main.model.getPlayerWhosTurn() instanceof HumanPlayer){//only when enabled and the player is a human
            Game g = Main.model;   
            Die die = g.getBoard().getDie();
            Player p = g.getPlayerWhosTurn();

            if(p.getPosition()-die.getNumberRolled()<=g.getBoard().getNumOfFields()){//can go backward
                g.movePlayer(false);                                      
            }
          //  g.movePlayer(false);                    
            Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getSidePanel().getButtonsPanel().getMoveBackBtn().setEnabled(false);
            Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getSidePanel().getButtonsPanel().getMoveForwardBtn().setEnabled(false);       
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       //do nothing
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
        if(isEnabled()){
            g2d.drawImage(currentImage, 0,0,getWidth(),getHeight(), this);
        }else{
            g2d.drawImage(imgDisabled, 0,0,getWidth(),getHeight(), this);
        }
    }
    
}
