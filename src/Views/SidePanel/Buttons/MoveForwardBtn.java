/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
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
import java.awt.event.MouseEvent;
import uppersanddowners_final.Main;

/**
 *
 * @author arturzxc
 */
public class MoveForwardBtn extends BaseButton{

    private Image img = ResourceLoader.loadImage("mf.png");
    private Image imgHover = ResourceLoader.loadImage("mfhovere.png"); 
    private Image imgDisabled = ResourceLoader.loadImage("mfdisabled.png");

    public MoveForwardBtn(String tooltipImg) {
        super(tooltipImg);                                       
        setEnabled(false);
        addMouseListener(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(isEnabled() && Main.model.getPlayerWhosTurn() instanceof HumanPlayer){//only when enabled and the player is a human
            Game model = Main.model;     
            Die die = model.getBoard().getDie();
            Player p = model.getPlayerWhosTurn();

            if(p.getPosition()+die.getNumberRolled()<=model.getBoard().getNumOfFields()){//can go forward
                model.movePlayer(true);                                      
            }else{//skip
                Main.gui.showMessage(Main.model.getPlayerWhosTurn().getName()+" has skipped his turn");
                if(Main.model.getNextPlayer() instanceof HumanPlayer){
                    Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getSidePanel().getButtonsPanel().getRollBtn().setEnabled(true);
                }
                model.incrementWhosTurn();                                
            }
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
