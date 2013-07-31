package Views.SidePanel.CustomizationPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

/**
 *
 * @author Mathan
 */

public class CustomPlayerPanel extends JLabel {

    private GridBagConstraints cons;
    private JLabel createPlayer;
    private PlayerTypePanel playerPanel;
    private NumberPlayerPanel noPanel;
    
    public CustomPlayerPanel() {
        this.setEnabled(false);
        this.setLayout(new GridBagLayout());
        this.setUpConstraints();
        this.setUpNoOfPlayers();
        this.setUpPlayerCustomization();
        this.build();
    }

    private void setUpConstraints() {
        cons = new GridBagConstraints();
        cons.anchor = GridBagConstraints.NORTHWEST;
        cons.fill = GridBagConstraints.BOTH;
        cons.weightx = 1;
        cons.weighty = 1;
        cons.gridheight = 1;
        cons.gridwidth = 1;
    }

    private void setConstraints(int gridx, int gridy, double weightx, double weighty, int gridheight, int gridwidth) {
        cons.gridx = gridx;
        cons.gridy = gridy;
        cons.weightx = weightx;
        cons.weighty = weighty;
        cons.gridheight = gridheight;
        cons.gridwidth = gridwidth;
    }

    private void setUpNoOfPlayers() {
        this.noPanel = new NumberPlayerPanel(this);
    }

    private void setUpPlayerCustomization() {
        this.playerPanel = new PlayerTypePanel(this);
        this.createPlayer = new CreatePlayerButton(this);
    }

    private void build() {
        setConstraints(0, 0, 1, 0.4, 1, 1);
        add(noPanel, cons);
        setConstraints(0, 1, 1, 0.4, 1, 1);
        add(playerPanel, cons);
        setConstraints(0,2,1,0.2,1,1);
        add(createPlayer,cons);
    }
    
    public PlayerTypePanel getPlayerPanel(){
        return this.playerPanel;
    }
    
    public NumberPlayerPanel getNumberPlayerPanel(){
        return this.noPanel;
    }

}
