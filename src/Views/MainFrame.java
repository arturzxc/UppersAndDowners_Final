package Views;

import Utils.ResourceLoader;
import Views.MenuBar.MenuBar;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class MainFrame extends JFrame{ 

    private TotalContentPane totalContentPane;    
    private MenuBar menuBar;
    
    public MainFrame(){        
        setTitle("Uppers And Downers - GUI version group G");                           
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                          
        setIconImage(ResourceLoader.loadImage("bah.png"));

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int)size.getWidth()/4, (int)size.getHeight()/4);
        setSize(800,600);
                
        setUpMenuBar();          
        
        setUpTotalContentPane();
        
    }
    
    /**
     * Returns the Content Panel (TotalContentPane)
     * @return 
     */
    public TotalContentPane getTotalContentPane() {
        return totalContentPane;
    }
    
    /**
     * Sets up the Content Panel to hold all elements
     */
    private void setUpTotalContentPane(){
        totalContentPane = new TotalContentPane();
        setContentPane(totalContentPane);
        totalContentPane.switchToMenuContentPane();
        
    }
    
    /**
     * Sets up the Menu Bar.
     */
    private void setUpMenuBar(){
        try {
            menuBar = new MenuBar();
        } catch (URISyntaxException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        setJMenuBar(menuBar);
    }
    
    
    
}

