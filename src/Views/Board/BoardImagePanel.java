package Views.Board;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Mathan
 */
public class BoardImagePanel extends JPanel {
    
    private BoardPicture bp;

    public BoardImagePanel() {        
    }

    public void showPicture(String img){
        this.bp = new BoardPicture(img);
    }

    private void paintPicture(Graphics2D g2d) {
        if(bp != null){
            if(bp.isComplete()){
                this.bp = null;
            }
            else{
                bp.renderImage(g2d, 150, 100);
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;        
        paintPicture(g2d);
    }
    
}
