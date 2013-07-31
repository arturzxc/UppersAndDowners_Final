/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.SidePanel.AnimationDiePanel;

import Models.Die;
import Utils.ResourceLoader;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import uppersanddowners_final.Main;

/**
 *
 * @author arturzxc
 */
public class AnimationPanel extends JPanel implements Runnable{
 
    private Image animationFrame;
    private Image panelFrame;
    private String type;    
    private final int start;
    private final int end;
    private final int delayBetweenFrames;
    private Die die;
    private int frameIterator;   
    private String base;
    private boolean firstRoll;
    
    private String name;
    
    public AnimationPanel(String base, String type, int startFrame, int start, int end, int delayBetweenFrames) {
        
        this.base=base;
        this.type=type;
        this.animationFrame=ResourceLoader.loadAnimationRollImage(this.base+startFrame+"."+type);       
        this.start=start;
        this.end=end;
        this.delayBetweenFrames=delayBetweenFrames;
        this.panelFrame = ResourceLoader.loadImage("animationframe.png");
        this.frameIterator=start;
        
    }

    public boolean isFirstRoll() {
        return firstRoll;
    }

    public void setFirstRoll(boolean firstRoll) {
        this.firstRoll = firstRoll;
    }
    
    @Override
    public void run(){
        die=Main.model.getBoard().getDie();
        name=Main.model.getPlayerWhosTurn().getName();

        for(frameIterator=start; frameIterator<end;frameIterator++){
            animationFrame=ResourceLoader.loadAnimationRollImage(base+frameIterator+"."+type);
            try{
                Thread.sleep(delayBetweenFrames);
                repaint();                                                 
            } catch (InterruptedException ex) {
                Logger.getLogger(AnimationPanel.class.getName()).log(Level.SEVERE, null, ex);
            }                        
        }
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(animationFrame, 0, 0, getWidth(), getHeight(), this);        
        
        if(frameIterator>=end-30){//Display in the last frame of the animation            
            int no = die.getNumberRolled();
            int multi=4;
            int alfa=0;
            
            if(multi*frameIterator>200){
                alfa=200;
            }else{
                alfa=multi*frameIterator;
            }

           
            

            
            
            Color bg = new Color(26,26,26, alfa);
            g2d.setColor(bg);
            g2d.fillRect(0,getHeight()/4, getWidth(), getHeight()/4+getHeight()/8);
            
            
            Color txt = new Color(255,255,255, alfa);
            g2d.setColor(txt);
            g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
            String str=Integer.toString(no);       
            String nameAndRoll;
            if(firstRoll){
                nameAndRoll="Starting positions assigned!";
            }else{
                nameAndRoll=name+" rolls " +str;
            }
            
            g2d.drawString(nameAndRoll, getWidth()/8, getHeight()/2);
        }
        
        g2d.drawImage(panelFrame, 0, 0, getWidth(), getHeight(), this);
    }
}
