/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.SidePanel.CustomizationPanel;

import Utils.ResourceLoader;
import Views.SidePanel.Buttons.BaseButton;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import uppersanddowners_final.Main;

/**
 *
 * @author Mathan
 */
public class CreateBoardButton extends BaseButton implements MouseListener{
    
    private Image img = ResourceLoader.loadImage("createboard.png");
    private Image imgHover = ResourceLoader.loadImage("createboardhover.png");
    private Image currentImage;
    
    public CreateBoardButton(String tooltip){
        super(tooltip);
        currentImage=img;
        addMouseListener(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
      //nothing
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Main.model.createBoard(Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getSidePanel().getCustomPanel().getCustomBoardPanel().getChosenSize(), //no of fields.
                               Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getSidePanel().getCustomPanel().getCustomBoardPanel().getChosenBoardStyle(),
                               Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getSidePanel().getCustomPanel().getCustomBoardPanel().getUpDownStyle()
                                ); // coloue of board background
        Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getBoardPanel().initComponents();
        Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getSidePanel().switchToCustomization();
        Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getSidePanel().getCustomPanel().enableTab(1, true);
        Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getSidePanel().getCustomPanel().setSelectedIndex(1);
        
        //activate popup menus of the squares.
        Main.gui.showMessage("Right click on any square to add upper, downer or score");
        Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getBoardPanel().getBoardSquarePanel().setActivePopupMenus(true);
        
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

