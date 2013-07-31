/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Board;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author arturzxc
 */
public class BoardDecorationPanel extends JPanel{
    
    private Image panelFrame;

    public BoardDecorationPanel() {
        this.panelFrame = new ImageIcon(this.getClass().getResource("/resources/images/boardframe.png")).getImage();
        
    }
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;        
        g2d.drawImage(panelFrame, 0,0,getWidth(),getHeight(), this);        
    }
    
    
}
