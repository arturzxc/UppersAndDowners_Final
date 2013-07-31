package Views.Board;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author arturzxc
 */
public class BoardMessagePanel extends JPanel{
    
    private BoardMessage bm;
    
    public BoardMessagePanel() {        
    }
    
    public void showMessage(String msg){
        this.bm = new BoardMessage(msg);
    }

    private void paintMessage(Graphics2D g2d) {
        if(bm != null){
            if(bm.isComplete()){
                this.bm = null;
            }
            else{
                bm.renderMessage(g2d, 20, 20);
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;        
        paintMessage(g2d);
    }
    
}
