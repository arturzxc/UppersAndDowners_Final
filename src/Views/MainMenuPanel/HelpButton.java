package Views.MainMenuPanel;

import Utils.ResourceLoader;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JLabel;

/**
 *
 * @author Mathan
 */
public class HelpButton extends JLabel implements MouseListener {

    private Image img = ResourceLoader.loadImage("menuhelp.png");
    private Image imgHover = ResourceLoader.loadImage("menuhelphover.png");
    private Image currentImage;
    final URI uri;

    public HelpButton() throws URISyntaxException {
        uri = new URI("http://webprojects.eecs.qmul.ac.uk/ec09002/engWEBPAGE/customgame.html");
        currentImage = img;
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //nothing
    }

    @Override
    public void mousePressed(MouseEvent e)  {
        open(uri);
        //this like action perdormed here. use instead of mouse clicked for much better reaction time :D
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        currentImage = imgHover;
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        currentImage = img;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //nothing
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(currentImage, 0, 0, getWidth(), getHeight(), this);

    }

    private static void open(URI uri) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(uri);
            } catch (IOException e) { /* TODO: error handling */ }
        } else { /* TODO: error handling */ }
    }
}
