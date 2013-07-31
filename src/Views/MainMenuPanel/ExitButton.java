package Views.MainMenuPanel;

import Utils.ResourceLoader;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

/**
 *
 * @author Mathan
 */
public class ExitButton extends JLabel implements MouseListener{
    
    private Image img = ResourceLoader.loadImage("menuexit.png");
    private Image imgHover = ResourceLoader.loadImage("menuexithover.png");
    private Image currentImage;
    
    public ExitButton(){
        currentImage=img;
        addMouseListener(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
      //nothing
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.exit(0);
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
