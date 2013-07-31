/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.LogPane;

import Utils.ResourceLoader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author arturzxc
 */
public class LogPane extends JScrollPane {    
    private JTextArea ta;
    private Image currentImage = ResourceLoader.loadImage("darksquare.png");
    
    public LogPane(){  
        ta=new JTextArea();  
        ta.setText("Welcome to Uppers And Downer  === group G ===");
        ta.setEditable(false);
        ta.setOpaque(false);
        ta.setEnabled(false);
        setViewportView(ta);                           
        //width doesnt matter as it will be set be layout manager       
        
        //this.setOpaque(false);  
        this.setBackground(new Color(0,0,0,100));
        this.getViewport().setOpaque(false);
        this.setBorder(null);
        this.setPreferredSize(new Dimension(0,100));                
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public JTextArea getTextArea() {
        return ta;
    }
    
    
    public void addLog(String newText){
        ta.setText(ta.getText()+"\n"+newText);        
        //keep the scroll bar at the bottom all the time for 100 000 messages
        getViewport().setViewPosition(new Point(0,100000));              
    }
    
        @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;       

            g2d.drawImage(currentImage, 0,0,getWidth(),getHeight(), this);


    }
    
}
