/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.MainMenuPanel.IntroAnimation;

import Utils.ResourceLoader;
import java.awt.*;
import javax.swing.JPanel;

/**
 *
 * @author arturzxc
 */
public class IntroAnimationPane extends JPanel implements Runnable{    
    private Font textFont =  new Font(Font.DIALOG, Font.BOLD, 25);
    private Font textFont2 =  new Font(Font.DIALOG, Font.BOLD, 35);
    
    private boolean stop;
    private int alpha = 0;    
    private int delay = 18;
    private int timeLineCounter = 0;
    private int timeLineTotal =3300;
    private Thread runner;
    
    public IntroAnimationPane() {
        setLayout(new GridLayout());        
    }
    public void play(){
        runner = new Thread(this);
        runner.start();            
    }
    
    public void stop(){
        stop=true;
    }
    
    private void appearImage(Graphics2D g2d,String imgPath, int paddTop, int paddLeft){    
        g2d.drawImage(ResourceLoader.loadAnimationIntroImage(imgPath), 0, 0, getWidth(), getHeight(), this);
    }   
    
    private void appearText(Graphics2D g2d,String text,int paddTop, int paddLeft){        
        if(alpha<255){
            alpha++;            
        }           
        g2d.setFont(textFont2);
        
        g2d.setColor(new Color(0,0,0, alpha/2));
        g2d.fillRect(paddLeft, paddTop-30, 1000, 50);
        
        g2d.setFont(textFont);
        g2d.setColor(new Color(255, 200, 50, alpha));   
        g2d.drawString(text, paddLeft, paddTop);  
        
  
    }
    
    private void fadeText(Graphics2D g2d,String text,int paddTop, int paddLeft){        
        if(alpha!=0){
            alpha--;
        }        
        g2d.setFont(textFont2);
        
        g2d.setColor(new Color(0,0,0, alpha/2));
        g2d.fillRect(paddLeft, paddTop-30, 1000, 50);
        
        g2d.setFont(textFont);
        g2d.setColor(new Color(255, 200, 50, alpha));   
        g2d.drawString(text, paddLeft, paddTop);         
    }
    
    private void showText(Graphics2D g2d,String text, int start, int stop, int paddLeft, int paddTop){
        int mid = (int)(start+stop)/2;           
        if(timeLineCounter>=start && timeLineCounter< mid){
            appearText(g2d, text,paddTop,paddLeft);
        }else if(timeLineCounter>=mid&& timeLineCounter<stop){
            fadeText(g2d, text, paddTop,paddLeft);
        }        
    }
    
    private void showImage(Graphics2D g2d, String imgPath, int start, int stop, int paddLeft, int paddTop, boolean fade){     
        if(timeLineCounter>=start && timeLineCounter<=stop){
            appearImage(g2d, imgPath,paddTop,paddLeft);
            if(fade){
                g2d.setColor(new Color(0,0,0,255-alpha));
               // g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        }  
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(new Color(0,0,0));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        showImage(g2d, "whole_1.jpg", 0, 200, 0, 0,true);
        showText(g2d, "Group G",0, 200, 500, 150);        
        
        showImage(g2d, "whole_2.jpg", 200, 400, 0, 0,true);
        showText(g2d, "Presents...",200,400, 50, 500);
        
        showImage(g2d, "upper.jpg", 500, 700, 0, 0,false);
        showImage(g2d, "downer.jpg", 700, 900, 0, 0,false);
        showImage(g2d, "downer2.jpg", 900, 1100, 0, 0,true);
        showText(g2d, "Uppers"  ,500, 1100, 510, 70);                
        showText(g2d, "And"  ,500, 1100, 550, 120);  
        showText(g2d, "Downers"  ,500, 1100, 500, 170);  
        
        showImage(g2d, "tokens.jpg",1100, 1300, 0, 0,true);
        showText(g2d, "Pick your token"  ,1100, 1300,10, 450);   
        
        showImage(g2d, "roll_1.jpg",1300, 1500, 0, 0,false);
        showImage(g2d, "roll_2.jpg",1500, 1700, 0, 0,false);
        showImage(g2d, "roll_3.jpg",1700, 1900, 0, 0,true);
        showText(g2d, "Roll the die"  ,1300, 1900, 50, 450);  
        
        showImage(g2d, "bump_1.jpg",1900, 2100, 0, 0,false);
        showText(g2d, "Move forward or backward"  ,1900, 2100, 100, 50);  
        showImage(g2d, "bump_2.jpg",2100, 2300, 0, 0,false);
        showText(g2d, "but be aware..."  ,2100, 2300, 100, 50); 
        showImage(g2d, "bump_3.jpg",2300, 2500, 0, 0,true);
        showText(g2d, "BUMPING ALLOWED!"  ,2300, 2500, 100, 50); 
        
        showImage(g2d, "winner.jpg",2700, 3500, 0, 0,true);
        showText(g2d, "And remember..."  ,2500, 2700, 100, 50);         
        showText(g2d, "There can only be  one winner!"  ,2700, 4000, 20, 450);          
    }
    
    @Override
    public void run() {
        while(!stop){
            timeLineCounter=0;//reset counter:D
            for (int i = 0; i < timeLineTotal; i++) {
                if(stop){
                    return;
                }                                
                try{                                  
                    revalidate();
                    getParent().repaint();
                    //repaint();
                    timeLineCounter++;                    
                    Thread.sleep(delay);
                }catch(Exception e ){                  
                }
            }
        }        
    }
    
}
