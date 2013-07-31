package Views.SidePanel.CustomizationPanel;

import Models.Game;
import Utils.ResourceLoader;
import Views.GUI;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
import uppersanddowners_final.Main;

/**
 *
 * @author Mathan
 */
public class CustomPanel extends JTabbedPane {
    
    private GUI gui;
    private CustomBoardPanel customBoardPanel;
    private CustomPlayerPanel customPlayerPanel;
    private Game model;
    
    public CustomPanel(){
        this.gui = Main.gui;
        this.model=Main.model;
        
        this.customBoardPanel = new CustomBoardPanel();
        this.customPlayerPanel = new CustomPlayerPanel();
        setUpTabbedPanel();
    }

    private void setUpTabbedPanel() {
        this.addTab("Board", customBoardPanel);
        this.addTab("Players", customPlayerPanel);
        this.enableTab(1, false);
    }
    
    public void enableTab(int index, boolean enabled){
        this.setEnabledAt(index, enabled);
    }
    
    public CustomPlayerPanel getCustomPlayerPanel(){
        return this.customPlayerPanel;
    }
    
    public CustomBoardPanel getCustomBoardPanel(){
        return this.customBoardPanel;
    }
    
    
}
