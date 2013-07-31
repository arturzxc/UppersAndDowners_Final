package Views;

import Views.MainMenuPanel.ClassicButton;
import Views.MainMenuPanel.CustomButton;
import Views.MainMenuPanel.ExitButton;
import Views.MainMenuPanel.HelpButton;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Mathan
 */
public class MenuContentPane extends JPanel {

    private GridBagConstraints cons;
    private ClassicButton classicButton;
    private ExitButton exitButton;
    private HelpButton helpButton;
    private CustomButton customButton;

    public MenuContentPane() throws URISyntaxException{
        setLayout(new GridBagLayout());
        setOpaque(false);
        setUpCconsonstraints();
            setUpAllButtons();

        setConstraints(0, 0, 0.5, 0.25, 1, 1);
        add(classicButton, cons);

        setConstraints(0, 1, 1, 0.25, 1, 1);
        add(customButton, cons);

        setConstraints(0, 2, 1, 0.25, 1, 1);
        add(helpButton, cons);

        setConstraints(0, 3, 1, 0.25, 1, 1);
        add(exitButton, cons);

    }
    
    /**
     * Sets up main menu buttons.
     * @throws URISyntaxException 
     */
    private void setUpAllButtons() throws URISyntaxException {
        classicButton = new ClassicButton();
        customButton = new CustomButton();
        helpButton = new HelpButton();
        exitButton = new ExitButton();
    }
    /**
     * Sets up GridBag constraints for MenuContentPane.
     */
    private void setUpCconsonstraints() {
        cons = new GridBagConstraints();
        cons.anchor = GridBagConstraints.NORTHWEST;
        cons.fill = GridBagConstraints.BOTH;
        cons.weightx = 1;
        cons.weighty = 1;
        cons.gridheight = 1;
        cons.gridwidth = 1;
    }
    
    /**
     * This method is used to modify GridBagConstraints for MenuContentPane.
     * @param gridx
     * @param gridy
     * @param weightx
     * @param weighty
     * @param gridheight
     * @param gridwidth 
     */
    private void setConstraints(int gridx, int gridy, double weightx, double weighty, int gridheight, int gridwidth) {
        cons.gridx = gridx;
        cons.gridy = gridy;
        cons.weightx = weightx;
        cons.weighty = weighty;
        cons.gridheight = gridheight;
        cons.gridwidth = gridwidth;
    }

    @Override
    public void paintComponent(Graphics g) {
        setBorder(new EmptyBorder(getHeight() / 2, getWidth() / 2, getHeight() / 10, getWidth() / 10));
    }
}
