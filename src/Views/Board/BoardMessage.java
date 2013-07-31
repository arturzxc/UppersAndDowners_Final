
package Views.Board;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Timer;
import java.util.TimerTask;
import uppersanddowners_final.Main;

/**
 *
 * @author Mathan
 */
public class BoardMessage {
    
    private BoardPanel boardPanel;
    private String message;
    private int r,g,b;
    private int alpha;
    private Color fontColor;
    private Color msgContainerColor;
    private boolean complete;
    
    private Timer timer;
    private final int TRANSITION_DELAY = 100;
    
    /**
     * Default Constructor
     * @param msg
     * @param bp 
     */
    public BoardMessage(String msg){
        
        this.message = msg;
        this.r = 26;
        this.g = 26;
        this.b = 26;
        this.alpha=200;
        this.fontColor = new Color(255,255,255,alpha);
        this.msgContainerColor = new Color(r,g,b,alpha);
        this.complete = false;
        runTransition();
    }
    
    public void renderMessage(Graphics2D g, int xPos, int yPos){
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(msgContainerColor);
        g.fillRoundRect(xPos, yPos, g.getFontMetrics().stringWidth(message) + 30, 30, 10, 10);
        g.setColor(new Color(41,41,41));
        g.setStroke(new BasicStroke(2));
        g.setColor(fontColor);
        g.drawString(message, xPos + 10, yPos + 20);
    }
    
    public boolean isComplete(){
        return this.complete;
    }

    public synchronized  void runTransition() {
        this.timer = new Timer();
        TimerTask task = new TimerTask(){

            @Override
            public void run() {
                alpha = alpha-5;
                if(alpha > 0){
                    msgContainerColor = new Color(r, g, b, alpha);
                    fontColor = new Color(255,255,255,alpha);
                }
                else{
                    timer.cancel();
                    complete = true;
                }
                Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getBoardPanel().repaint();
            }
            
        };
        
        timer.schedule(task, 0, TRANSITION_DELAY);
    }
    
}
