package Views;

import Utils.ResourceLoader;
import Views.Board.BoardPanel;
import Views.Board.MiniScorePanel;
import Views.LogPane.LogPane;
import Views.SidePanel.SidePanel;
import java.awt.*;
import javax.swing.JPanel;
import uppersanddowners_final.Main;

/**
 *
 * @author arturzxc
 */
public class GameContentPane extends JPanel{
    private GridBagConstraints cons;
    
    private BoardPanel boardPanel;
    private SidePanel sidePanel;
    private LogPane logPane;
    private PlayerInfoDialog playerInfoDialog;
    private MiniScorePanel miniScorePanel;
    
    public GameContentPane() {
        setLayout(new GridBagLayout());        
        setUpConstraints();//initial constrains.
        
        setConstraints(0, 0, 0.55, 0.85, 1, 1);
        setUpBoardPanel();
        
        setConstraints(1, 0, 0.45, 0.85, 3, 1);
        setUpSidePanel();
        
        setConstraints(0, 1, 1, 0.01, 1, 1);
        setUpMiniScorePanel();
        
        setConstraints(0, 2, 1, 0.10, 1, 1);
        setUpLogPane();
        
        this.playerInfoDialog = new PlayerInfoDialog();
    }
    
    /**
     * Returns BoardPanel panel.
     * @return 
     */
    public BoardPanel getBoardPanel() {
        return boardPanel;
    }
    
    /**
     * Returns GridBagConstraints for GameContentPanel.
     * @return 
     */
    public GridBagConstraints getCons() {
        return cons;
    }

    /**
     * Returns Log Panel.
     * @return 
     */
    public LogPane getLogPane() {
        return logPane;
    }

    /**
     * Returns Side Panel.
     * @return 
     */
    public SidePanel getSidePanel() {
        return sidePanel;
    }
    
    /**
     * Returns Scoring Panel.
     * @return 
     */
    public MiniScorePanel getMiniScorePanel(){
        return this.miniScorePanel;
    }
    
    /**
     * Sets up GridBag Constraints for GameContentPanel.
     */
    private void setUpConstraints(){
        cons = new GridBagConstraints();
        cons.anchor=GridBagConstraints.NORTHWEST;
        cons.fill=GridBagConstraints.BOTH;
        cons.weightx=1;
        cons.weighty=1;
        cons.gridheight=1;
        cons.gridwidth=1;        
    }
    
    /**
     * Method is used to modify GridBagConstraints for GameContentPanel.
     * @param gridx
     * @param gridy
     * @param weightx
     * @param weighty
     * @param gridheight
     * @param gridwidth 
     */
    private void setConstraints(int gridx, int gridy,double weightx, double weighty, int gridheight,int gridwidth){
        cons.gridx=gridx;
        cons.gridy=gridy;
        cons.weightx=weightx;
        cons.weighty=weighty; 
        cons.gridheight=gridheight;
        cons.gridwidth=gridwidth;
    }
    
    /*
     * Adds Board Panel to GameContentPane.
     */
    private void setUpBoardPanel(){            
        boardPanel = new BoardPanel();           
        add(boardPanel, cons);
    }
    
    /**
     * Adds Side Panel to GameContentPane.
     */
    private void setUpSidePanel(){
        sidePanel = new SidePanel();
        add(sidePanel, cons);
    }
    
    /**
     * Adds LogPane to GameContentPane.
     */
    private void setUpLogPane(){
        logPane = new LogPane();
        add(logPane, cons);
    }
    
    /**
     * Adds MiniScorePanel to GameContentPane.
     */
    private void setUpMiniScorePanel() {
        this.miniScorePanel = new MiniScorePanel();
        add(miniScorePanel,cons);
    }
    
    /**
     * Makes the player info dialog visible.
     */
    public void showPlayerInfoDialog(){
        this.playerInfoDialog.setVisible(true);
        playerInfoDialog.setLocationRelativeTo(Main.gui.getMainFrame());
    }
   
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        Image img = ResourceLoader.loadImage("bg_image2.png");
        g2d.drawImage(img, 0,0,getWidth(), getHeight(),this);
    }
}
