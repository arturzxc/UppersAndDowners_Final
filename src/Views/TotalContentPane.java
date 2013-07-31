/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Views.MainMenuPanel.IntroAnimation.IntroAnimationPane;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLayeredPane;
import javax.swing.OverlayLayout;

/**
 *
 * @author arturzxc
 */
public class TotalContentPane extends JLayeredPane{
    private GameContentPane gameContentPane;
    private MenuContentPane menuContentPane;    
    private ScoreContentPane scoreContentPane;
    private IntroAnimationPane introAnimationPane;

    public TotalContentPane() {
        setLayout(new OverlayLayout(this));
        setUpAllContentPanes();                
        
    }

    public ScoreContentPane getScoreContentPane() {
        return scoreContentPane;
    }
                  
    
    public void switchToGameContentPane(){
        removeAll();      
        introAnimationPane.stop();
        add(gameContentPane,1);
        revalidate();
        repaint();
    }
    
    public void switchToMenuContentPane(){        
        removeAll();
        add(introAnimationPane = new IntroAnimationPane(),1);
        introAnimationPane.play();
        add(menuContentPane,0);
        revalidate();
        repaint();
    }
    
    public void switchToScoreContentPane(){
        removeAll();
        introAnimationPane.stop();
        add(scoreContentPane,0);
        revalidate();
        repaint();
    }
    
    private void setUpAllContentPanes(){
        gameContentPane = new GameContentPane();
        try {
            menuContentPane = new MenuContentPane();
        } catch (URISyntaxException ex) {
            Logger.getLogger(TotalContentPane.class.getName()).log(Level.SEVERE, null, ex);
        }
        scoreContentPane= new ScoreContentPane();
    }

    public GameContentPane getGameContentPane() {
        return gameContentPane;
    }
}
