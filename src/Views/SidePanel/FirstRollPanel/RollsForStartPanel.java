package Views.SidePanel.FirstRollPanel;


import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import uppersanddowners_final.Main;

/**
 *
 * @author arturzxc
 */
public class RollsForStartPanel extends JLabel {

    private FirstRollBtn rollBtn;
    private StartButton startGame;
    private GridBagConstraints cons;
    private PlayersPanel playersPnl;
    private RollsPanel rollsPnl;
    
    
    public RollsForStartPanel() {                 
        setLayout(new GridBagLayout());                
        setBorder(BorderFactory.createTitledBorder("Roll for positions"));
        initComponents();
        
        cons.anchor=GridBagConstraints.NORTHEAST;
        cons.fill=GridBagConstraints.BOTH;      
        cons.weightx=1;
        cons.weighty=1;
        
        addElements();

    }

    public PlayersPanel getPlayersPnl() {
        return playersPnl;
    }

    public RollsPanel getRollsPnl() {
        return rollsPnl;
    }
    
    
    
    private void initComponents(){
        rollBtn = new FirstRollBtn("none");
        playersPnl = new PlayersPanel();
        rollsPnl=new RollsPanel();
        startGame = new StartButton();
        cons = new GridBagConstraints();    
        
    }        
    
    private void addElements(){
        cons.gridx=0;
        cons.gridy=0;        
        cons.weighty=0.25;
        cons.gridwidth=2;
        add(rollBtn,cons);
        
        cons.gridx=0;
        cons.gridy=1;        
        cons.weighty=0.5;
        cons.gridwidth=1;
        add(playersPnl,cons);
        
        cons.gridx=1;
        cons.gridy=1;        
        cons.weighty=0.5;
        cons.gridwidth=1;
        add(rollsPnl,cons);
        
        cons.gridx=0;
        cons.gridy=2;        
        cons.weighty=0.25;
        cons.gridwidth=2;
        add(startGame,cons);
                
    }

    public StartButton getStartGame() {
        return startGame;
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        Image img = Main.model.getBoard().getFieldImages().get(0);                
        g2d.drawImage(img, 0,0,getWidth(),getHeight(),this);        
        
    }
}
