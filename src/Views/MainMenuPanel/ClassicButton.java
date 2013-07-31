package Views.MainMenuPanel;

import Models.Game;
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
 * @author Mathan
 */
public class ClassicButton extends JLabel implements MouseListener{
    
    private Image img = ResourceLoader.loadImage("classicmenubutt.png");
    private Image imgHover = ResourceLoader.loadImage("classicmenuhover.png");
    private Image currentImage;
    
    public ClassicButton(){
        currentImage=img;
        addMouseListener(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
      //nothing
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Main.model=null;//clean first.
        Main.model=new Game(true);
        Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getBoardPanel().initComponents();
        Main.gui.getMainFrame().getTotalContentPane().switchToGameContentPane();
        Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getSidePanel().switchToFirstRoll();
        Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().showPlayerInfoDialog();
        //SHOULD ALSO INITIALISE MODEL OBJECT WITH CONSTRUCTORS FOR CLASSIC GAME
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
