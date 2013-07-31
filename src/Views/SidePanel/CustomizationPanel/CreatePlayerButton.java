package Views.SidePanel.CustomizationPanel;

import Utils.ResourceLoader;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import uppersanddowners_final.Main;

/**
 *
 * @author Mathan
 */
public class CreatePlayerButton extends JLabel implements MouseListener{
    
    private Image img = ResourceLoader.loadImage("createplayer.png");
    private Image imgHover = ResourceLoader.loadImage("createplayerhover.png");
    private Image currentImage;
    private CustomPlayerPanel customPlayerPanel;
    
    public CreatePlayerButton(CustomPlayerPanel cpp){
        this.customPlayerPanel = cpp;
        currentImage=img;
        addMouseListener(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
      //nothing
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
        if(customPlayerPanel.getNumberPlayerPanel().getRadioInfo(2).isSelected() && customPlayerPanel.getPlayerPanel().checkInfo(2)){
            Main.model.addPlayerCustom(customPlayerPanel.getPlayerPanel().getPlayerType(1), 
                                       customPlayerPanel.getPlayerPanel().getPlayerName(1),
                                       customPlayerPanel.getPlayerPanel().getPlayerTokenColour(1));
            
            Main.model.addPlayerCustom(customPlayerPanel.getPlayerPanel().getPlayerType(2), 
                                       customPlayerPanel.getPlayerPanel().getPlayerName(2),
                                       customPlayerPanel.getPlayerPanel().getPlayerTokenColour(2));
            
            Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getSidePanel().switchToFirstRoll();
            Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getBoardPanel().getBoardSquarePanel().setActivePopupMenus(true);
            Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getMiniScorePanel().refresh();
        }
        
        else if(customPlayerPanel.getNumberPlayerPanel().getRadioInfo(3).isSelected() && customPlayerPanel.getPlayerPanel().checkInfo(3)){
            Main.model.addPlayerCustom(customPlayerPanel.getPlayerPanel().getPlayerType(1), 
                                       customPlayerPanel.getPlayerPanel().getPlayerName(1),
                                       customPlayerPanel.getPlayerPanel().getPlayerTokenColour(1));
            
            Main.model.addPlayerCustom(customPlayerPanel.getPlayerPanel().getPlayerType(2), 
                                       customPlayerPanel.getPlayerPanel().getPlayerName(2),
                                       customPlayerPanel.getPlayerPanel().getPlayerTokenColour(2));
            
            Main.model.addPlayerCustom(customPlayerPanel.getPlayerPanel().getPlayerType(3), 
                                       customPlayerPanel.getPlayerPanel().getPlayerName(3),
                                       customPlayerPanel.getPlayerPanel().getPlayerTokenColour(3));
            
            Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getSidePanel().switchToFirstRoll();
            Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getBoardPanel().getBoardSquarePanel().setActivePopupMenus(true);
            Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getMiniScorePanel().refresh();
        }
        
        else if(customPlayerPanel.getNumberPlayerPanel().getRadioInfo(4).isSelected() && customPlayerPanel.getPlayerPanel().checkInfo(4)){
            Main.model.addPlayerCustom(customPlayerPanel.getPlayerPanel().getPlayerType(1), 
                                       customPlayerPanel.getPlayerPanel().getPlayerName(1),
                                       customPlayerPanel.getPlayerPanel().getPlayerTokenColour(1));
            
            Main.model.addPlayerCustom(customPlayerPanel.getPlayerPanel().getPlayerType(2), 
                                       customPlayerPanel.getPlayerPanel().getPlayerName(2),
                                       customPlayerPanel.getPlayerPanel().getPlayerTokenColour(2));
            
            Main.model.addPlayerCustom(customPlayerPanel.getPlayerPanel().getPlayerType(3), 
                                       customPlayerPanel.getPlayerPanel().getPlayerName(3),
                                       customPlayerPanel.getPlayerPanel().getPlayerTokenColour(3));
            
            Main.model.addPlayerCustom(customPlayerPanel.getPlayerPanel().getPlayerType(4), 
                                       customPlayerPanel.getPlayerPanel().getPlayerName(4),
                                       customPlayerPanel.getPlayerPanel().getPlayerTokenColour(4));
            
            Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getSidePanel().switchToFirstRoll();
            Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getBoardPanel().getBoardSquarePanel().setActivePopupMenus(true);
            Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getMiniScorePanel().refresh();
        }
        
        else{
            Main.gui.showMessage("not all info has been entered!");
        }
        
        
        
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
    public void mouseReleased(MouseEvent e) {
        //nothing
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;       

            g2d.drawImage(currentImage, 0,0,getWidth(),getHeight(), this);
    }
    
}
