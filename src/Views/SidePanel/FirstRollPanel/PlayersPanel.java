/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.SidePanel.FirstRollPanel;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import uppersanddowners_final.Main;

/**
 *
 * @author arturzxc
 */
public class PlayersPanel extends JLabel{

    public PlayersPanel() {
        setLayout(new GridLayout(0, 1));   
        setOpaque(false);
        setBorder(BorderFactory.createTitledBorder("Players"));
        addNames();
        
    }
    
    public void addNames(){
        removeAll();
        for(int i=0;i<Main.model.getPlayers().size();i++){            
            add(new JLabel(Main.model.getPlayers().get(i).getName()));
        }  
        revalidate();
        repaint();
    }    
    
}