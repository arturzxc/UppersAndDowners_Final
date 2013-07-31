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
public class CustomButton extends JLabel implements MouseListener{
    
    private Image img = ResourceLoader.loadImage("custom.png");
    private Image imgHover = ResourceLoader.loadImage("customhover.png");
    private Image currentImage;
    
    public CustomButton(){
        currentImage=img;
        addMouseListener(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
      //nothing
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Main.model = null;
        Main.model=new Game(false);//game is not basic - it is custom.
        Main.gui.getMainFrame().getTotalContentPane().switchToGameContentPane();
        Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getSidePanel().switchToCustomization();
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
