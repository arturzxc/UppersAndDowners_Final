package Views.MenuBar;

import Models.Game;
import Models.Load;
import Utils.ResourceLoader;
import Views.GUI;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import uppersanddowners_final.Main;

/**
 *
 * @author Mathan
 */
public class MenuBar extends JMenuBar {

    private GUI gui;
    private JMenu file, edit, help;
    private JMenuItem exit, viewHelp, save, load;

    public MenuBar() throws URISyntaxException {
        this.gui = Main.gui;
        constructMenuBar();
    }

    private void constructMenuBar() throws URISyntaxException {
        //create Menu
        this.file = new JMenu("File");
        //this.edit = new JMenu("Edit"); currently no use for
        this.help = new JMenu("Help");

        //create Menu Items
        this.save = new JMenuItem("Save");
        this.save.addActionListener(new SaveListener());
        this.file.add(save);

        this.load = new JMenuItem("Load");
        this.load.addActionListener(new LoadListener());
        this.file.add(load);

        this.exit = new JMenuItem("Exit");
        this.exit.addActionListener(new ExitListener());
        this.file.add(exit);



        this.viewHelp = new JMenuItem("View Help");
        this.viewHelp.addActionListener(new viewHelpListener());
        this.help.add(viewHelp);

        //add Menu to Menu Bar
        this.add(file);
        //this.add(edit);
        this.add(help);
    }

    public class SaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try{                
                String title=JOptionPane.showInputDialog(
                        Main.gui.getMainFrame(),
                        "Please name your game vefore saving",
                        "Save Game",
                        JOptionPane.INFORMATION_MESSAGE);                        
                Main.model.saveGame(title);
            }catch(Exception e){
                Main.gui.showMessage("Save cancelled");                
            }
        }
    }
    
    public class LoadListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Main.gui.getMainFrame().dispose();
            Main.model=null;
            //needs to call filechooser and start GamePlay without calling a custom board. Don't have ideea how to do that
            Main.model = Load.loadGame("11");
            Main.gui=new GUI();
            Main.gui.getMainFrame().getTotalContentPane().switchToGameContentPane();
            
        }
    }
    
    
    
    public class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            System.exit(0);
        }
    }

    public class viewHelpListener implements ActionListener {
        final URI uri;

        public viewHelpListener() throws URISyntaxException {
            uri = new URI("http://webprojects.eecs.qmul.ac.uk/ec09002/engWEBPAGE/customgame.html");
        }
        
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            open(uri);
            // Main.gui.showHelpContentPanel();
        }
        
        private void open(URI uri) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(uri);
            } catch (IOException e) { /* TODO: error handling */ }
        } else { /* TODO: error handling */ }
    }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        Image img = ResourceLoader.loadImage("menuBar.png");
        g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
}
