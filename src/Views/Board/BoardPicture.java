package Views.Board;

import Utils.ResourceLoader;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.Timer;
import java.util.TimerTask;
import uppersanddowners_final.Main;

/**
 *
 * @author Mathan
 */
public class BoardPicture {

    private Image img;
    private float alpha;
    private boolean complete;
    private Graphics2D g;
    
    private Timer timer;
    private final int TRANSITION_DELAY = 100;

    public BoardPicture(String img){
        this.img = ResourceLoader.loadImage(img);
        this.complete = false;
        this.alpha = 1.0f;
        runTransition();
    }
    
    public void renderImage(Graphics2D g, int xPos, int yPos){
        this.g = g;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g.drawImage(img, xPos, yPos, Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getBoardPanel());
    }
    
    public boolean isComplete(){
        return this.complete;
    }

    public synchronized  void runTransition() {
        this.timer = new Timer();
        TimerTask task = new TimerTask(){

            @Override
            public void run() {
                alpha = alpha - 0.05f;
                if(alpha <=0){
                    timer.cancel();
                    complete = true;
                }
                
                Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getBoardPanel().repaint();
            }
            
        };
        
        timer.schedule(task, 0, TRANSITION_DELAY);
    }    
}
