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
public class RollsPanel extends JLabel{

    
    public RollsPanel() {
        setLayout(new GridLayout(0, 1));
        setOpaque(false);
        setBorder(BorderFactory.createTitledBorder("Start Positions"));
        showRolls();
    }
    
    public void showRolls(){        
        removeAll();//removes old rolls.
        for(int i=0;i<Main.model.getPlayers().size();i++){           
            String position = Integer.toString(Main.model.getPlayers().get(i).getStartPosition());
            if(i==0){
                position+="st";
            }else if(i==1){
                position+="nd";                
            }else if(i==2){
                position+="rd";
            }else if(i==3){
                position+="th";
            }else{}
                
            add(new JLabel(position));
        }  
        revalidate();
        repaint();
    }  

    
}
