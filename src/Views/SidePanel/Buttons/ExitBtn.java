package Views.SidePanel.Buttons;

import Models.Game;
import Utils.ResourceLoader;
import Views.GUI;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import uppersanddowners_final.Main;

/**
 *
 * @author arturzxc
 */
public class ExitBtn extends BaseButton{
    
    private Image img = ResourceLoader.loadImage("backmenu.png");
    private Image imgHover = ResourceLoader.loadImage("menubackhover.png");  
    
    
    public ExitBtn(String tooltipImg) {
        super(tooltipImg);        
        currentImage=img;
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //nothing
    }

    @Override
    public void mousePressed(MouseEvent e) {        
        Main.gui.getMainFrame().dispose();
        Main.model=null;
        Main.model=new Game(true);
        Main.gui=new GUI();
        Main.gui.getMainFrame().getTotalContentPane().switchToMenuContentPane();
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

            g2d.drawImage(currentImage, 0,0,getWidth(),getHeight(), this);


    }

}
