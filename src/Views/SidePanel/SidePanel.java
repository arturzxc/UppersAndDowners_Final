package Views.SidePanel;


import Utils.ResourceLoader;
import Views.SidePanel.AnimationDiePanel.AnimationPanel;
import Views.SidePanel.Buttons.ButtonsPanel;
import Views.SidePanel.CustomizationPanel.CustomPanel;
import Views.SidePanel.FirstRollPanel.RollsForStartPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 *
 * @author arturzxc
 */
public class SidePanel extends JPanel{
    
    private CustomPanel customPanel;
    private AnimationPanel animationPanel;
    private ButtonsPanel buttonsPanel;
    private RollsForStartPanel firstRollPanel;
    
    private GridBagConstraints cons;
    
    public SidePanel() {
        setLayout(new GridBagLayout());
        setOpaque(false);        
        setUpAllPanels();
        setUpConstraints();
        switchToCustomization();        
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

    public AnimationPanel getAnimationPanel() {
        return animationPanel;
    }

    public ButtonsPanel getButtonsPanel() {
        return buttonsPanel;
    }

    public CustomPanel getCustomPanel() {
        return customPanel;
    }
  

    public RollsForStartPanel getFirstRollPanel() {
        return firstRollPanel;
    }
    
    public void switchToCustomization(){
        removeAll();
        setConstraints(0, 0, 1, 1, 2, 1);
        add(customPanel,cons);
        revalidate();
        repaint();
    }
    public void switchToFirstRoll(){
        removeAll();
        setConstraints(0, 0, 1, 0.45, 1, 1);
        add(animationPanel,cons);
        setConstraints(0, 1, 1, 0.55, 1, 1); 
        add(firstRollPanel,cons);
        revalidate();
        repaint();
    }
    public void switchToGamePlay(){
        removeAll();
        setConstraints(0, 0, 1, 0.45, 1, 1);
        add(animationPanel,cons);
        setConstraints(0, 1, 1, 0.55, 1, 1); 
        add(buttonsPanel,cons);
        revalidate();
        repaint();
    }
    
    private void setUpAllPanels(){
        animationPanel = new AnimationPanel("Die_","jpg", 1, 1, 48, 20);
        buttonsPanel = new ButtonsPanel();
        firstRollPanel = new RollsForStartPanel();
        customPanel = new CustomPanel();         
    }  
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(ResourceLoader.loadImage("darksquare.png"), 0, 0,getWidth(),getHeight(),this);
    }
    
}
