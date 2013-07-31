/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.SidePanel.CustomizationPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 *
 * @author arturzxc
 */
public class PlayersPanel extends JPanel{
    
    private GridBagConstraints cons;
    
    private JPanel players,board; //main panels
    private JPanel noOfPlayers, playerNames; //player panels
    private JRadioButton two,three,four;
    private JComboBox playerType1,playerType2,playerType3,playerType4,tokenColour1,tokenColour2,tokenColour3,tokenColour4;
    private JTextField player1,player2,player3,player4;
    private JLabel playersLabel, playerNameLabel, tokenColourLabel;
    private TitledBorder playerNumTitle,playerSetUp;
    private JButton createPlayers,clearPlayers;
    private ButtonGroup noOfPlayerRadio;

    public PlayersPanel() {
        setLayout(new GridBagLayout());
        setUpCconsonstraints();
        
        
        
        
    }
    
    private void setUpCconsonstraints(){
        cons = new GridBagConstraints();
        cons.anchor=GridBagConstraints.NORTHWEST;
        cons.fill=GridBagConstraints.BOTH;
        cons.weightx=1;
        cons.weighty=1;
        cons.gridheight=1;
        cons.gridwidth=1;        
    }
    
    private void setConstraints(int gridx, int gridy,double weightx, double weighty, int gridheight,int gridwidth){
        cons.gridx=gridx;
        cons.gridy=gridy;
        cons.weightx=weightx;
        cons.weighty=weighty; 
        cons.gridheight=gridheight;
        cons.gridwidth=gridwidth;
    }
    
    private void setUpNoOfPlayersPanel(){
        add(noOfPlayers,cons);
    }
    
    private void setUpPlayerNamesPanel(){
        add(playerNames,cons);
    }
    
}
