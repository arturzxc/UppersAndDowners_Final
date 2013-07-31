/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.ScorePanelElements;

import Utils.ResourceLoader;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;

/**
 *
 * @author arturzxc
 */
public class TopCell extends JLabel{
    private String txt;

    public TopCell(String txt) {
        this.txt = txt;
    }
       
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(ResourceLoader.loadImage("squareyellow.png"), 0, 0,getWidth(),getHeight(),this);
        g2d.drawString(txt, getWidth()/3, getHeight()/2 );
    }
    
}
