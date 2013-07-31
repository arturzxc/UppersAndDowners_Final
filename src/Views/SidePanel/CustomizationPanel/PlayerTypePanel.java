package Views.SidePanel.CustomizationPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Mathan
 */

public class PlayerTypePanel extends JLabel {
    
    private CustomPlayerPanel customPlayerPanel;
    private GridBagConstraints cons;
    private JLabel playersLabel, playerNameLabel, tokenColourLabel;
    private JTextField player1,player2,player3,player4;
    private JComboBox playerType1,playerType2,playerType3,playerType4,tokenColour1,tokenColour2,tokenColour3,tokenColour4;
    
    public PlayerTypePanel(CustomPlayerPanel cpp){
        this.customPlayerPanel = cpp;
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createTitledBorder("Player Customization"));
        setUpConstraints();
        setUpPlayerTypePanel();
    }
    
    private void setUpConstraints(){
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

    private void setUpPlayerTypePanel() {        
        
        //Labels
        this.playersLabel = new JLabel("Player");
        this.setConstraints(0, 0, 1, 0.1, 1, 1);
        add(playersLabel, cons);
        
        this.playerNameLabel = new JLabel("Name");
        this.setConstraints(1, 0, 1, 0.1, 1, 1);
        add(playerNameLabel, cons);
        
        this.tokenColourLabel = new JLabel("Token");
        this.setConstraints(2, 0, 1, 0.1, 1, 1);
        add(tokenColourLabel, cons);
        
        //Player 1
        String [] types = {"Human", "Computer"};
        this.playerType1 = new JComboBox(types);
        playerType1.setEnabled(false);
        this.setConstraints(0, 1, 1, 0.1, 1, 1);
        add(playerType1,cons);
        
        this.player1 = new JTextField();
        player1.setEnabled(false);
        this.setConstraints(1, 1, 1, 0.1, 1, 1);
        add(player1,cons);
        
        String[] tokenColour = {"Red", "Blue", "Purple", "Green"};
        this.tokenColour1 = new JComboBox(tokenColour);
        tokenColour1.setEnabled(false);
        this.setConstraints(2, 1, 1, 0.1, 1, 1);
        add(tokenColour1, cons);
        
        //player2
        this.playerType2 = new JComboBox(types);
        playerType2.setSelectedIndex(1);
        playerType2.setEnabled(false);
        this.setConstraints(0, 2, 1, 0.1, 1, 1);
        add(playerType2,cons);
        
        this.player2 = new JTextField();
        player2.setEnabled(false);
        this.setConstraints(1, 2, 1, 0.1, 1, 1);
        add(player2,cons);
        
        this.tokenColour2 = new JComboBox(tokenColour);
        tokenColour2.setSelectedIndex(1);
        tokenColour2.setEnabled(false);
        this.setConstraints(2, 2, 1, 0.1, 1, 1);
        add(tokenColour2, cons);
        
        //player 3
        this.playerType3 = new JComboBox(types);
        playerType3.setSelectedIndex(1);
        playerType3.setEnabled(false);
        this.setConstraints(0, 3, 1, 0.1, 1, 1);
        add(playerType3,cons);
        
        this.player3 = new JTextField();
        player3.setEnabled(false);
        this.setConstraints(1, 3, 1, 0.1, 1, 1);
        add(player3,cons);
        
        this.tokenColour3 = new JComboBox(tokenColour);
        tokenColour3.setSelectedIndex(2);
        tokenColour3.setEnabled(false);
        this.setConstraints(2, 3, 1, 0.1, 1, 1);
        add(tokenColour3, cons);
        
        // player 4
        this.playerType4 = new JComboBox(types);
        playerType4.setSelectedIndex(1);
        playerType4.setEnabled(false);
        this.setConstraints(0, 4, 1, 0.1, 1, 1);
        add(playerType4,cons);
        
        this.player4 = new JTextField();
        player4.setEnabled(false);
        this.setConstraints(1, 4, 1, 0.1, 1, 1);
        add(player4,cons);
        
        this.tokenColour4 = new JComboBox(tokenColour);
        tokenColour4.setSelectedIndex(3);
        tokenColour4.setEnabled(false);
        this.setConstraints(2, 4, 1, 0.1, 1, 1);
        add(tokenColour4, cons);
    }
    
    public void enableRows(int rows){
        //disable all first
        this.disableAll();
        
        if(rows==2){
            this.playerType1.setEnabled(true);
            this.player1.setEnabled(true);
            this.tokenColour1.setEnabled(true);
            
            this.playerType2.setEnabled(true);
            this.player2.setEnabled(true);
            this.tokenColour2.setEnabled(true);
            
        }
        
        else if(rows ==3){
            this.playerType1.setEnabled(true);
            this.player1.setEnabled(true);
            this.tokenColour1.setEnabled(true);
            
            this.playerType2.setEnabled(true);
            this.player2.setEnabled(true);
            this.tokenColour2.setEnabled(true);
            
            this.playerType3.setEnabled(true);
            this.player3.setEnabled(true);
            this.tokenColour3.setEnabled(true);
             
        }
        
        else if (rows==4){
            this.playerType1.setEnabled(true);
            this.player1.setEnabled(true);
            this.tokenColour1.setEnabled(true);
            
            this.playerType2.setEnabled(true);
            this.player2.setEnabled(true);
            this.tokenColour2.setEnabled(true);
            
            this.playerType3.setEnabled(true);
            this.player3.setEnabled(true);
            this.tokenColour3.setEnabled(true);
            
            this.playerType4.setEnabled(true);
            this.player4.setEnabled(true);
            this.tokenColour4.setEnabled(true);
            
        }
    }
    
    public void disableAll(){
        this.playerType1.setEnabled(false);
        this.player1.setEnabled(false);
        this.tokenColour1.setEnabled(false);
            
        this.playerType2.setEnabled(false);
        this.player2.setEnabled(false);
        this.tokenColour2.setEnabled(false);

        this.playerType3.setEnabled(false);
        this.player3.setEnabled(false);
        this.tokenColour3.setEnabled(false);

        this.playerType4.setEnabled(false);
        this.player4.setEnabled(false);
        this.tokenColour4.setEnabled(false);
    }
    
    public String getPlayerName(int player){
        if(player==1 && player1.isEnabled() && !player1.getText().isEmpty()){
            return player1.getText();
        }
        
        else if(player==2 && player2.isEnabled() && !player2.getText().isEmpty()){
            return player2.getText();
        }
        else if(player==3 && player3.isEnabled() && !player3.getText().isEmpty()){
            return player3.getText();
        }
        else if(player ==4 && player4.isEnabled() && !player4.getText().isEmpty()){
            return player4.getText();
        }
        
        else{
            return null;
        }
    }
    
    public String getPlayerType(int player){
        if(player==1 && player1.isEnabled() ){
            return playerType1.getSelectedItem().toString();
        }
        
        else if(player==2 && player2.isEnabled()){
            return playerType2.getSelectedItem().toString();
        }
        else if(player==3 && player3.isEnabled()){
            return playerType3.getSelectedItem().toString();
        }
        else if(player ==4 && player4.isEnabled()){
            return playerType4.getSelectedItem().toString();
        }
        
        else{
            return null;
        }
    }
    
    public String getPlayerTokenColour(int player){
        if(player==1 && player1.isEnabled() ){
            return tokenColour1.getSelectedItem().toString();
        }
        
        else if(player==2 && player2.isEnabled()){
            return tokenColour2.getSelectedItem().toString();
        }
        else if(player==3 && player3.isEnabled()){
            return tokenColour3.getSelectedItem().toString();
        }
        else if(player ==4 && player4.isEnabled()){
            return tokenColour4.getSelectedItem().toString();
        }
        
        else{
            return null;
        }
    }
    
    public boolean checkInfo(int players){
        if (players==2){
            if(player1.getText().isEmpty() || player2.getText().isEmpty()){
                return false;
            }
            else {
                return true;
            }
        }
        
        else if(players ==3){
            if(player1.getText().isEmpty() || player2.getText().isEmpty() ||player3.getText().isEmpty() ){
                return false;
            }
            else {
                return true;
            }
        }
        
        else if(players ==4){
            if(player1.getText().isEmpty() || player2.getText().isEmpty() ||player3.getText().isEmpty()|| player4.getText().isEmpty()){
                return false;
            }
            else {
                return true;
            }
        }
        
        else {
            return false;
        }
    }
}
