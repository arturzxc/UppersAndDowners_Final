package Models;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * @author Software Engineering 2011-2012 (Group G)
 */
public class Downer implements Climable {
    
    private int head, tail;
    private Image image;
    
    public Downer(int headPos, int tailPos, Image img) {
        this.head = headPos;
        this.tail = tailPos;
        this.image = img;
    }

    @Override
    /**
     * Will return the head of the object.
     */
    public int getHead() {
       return this.head;
    }

    @Override
    /**
     * Will return the tail of the object.
     */
    public int getTail() {
        return this.tail;
    }

    @Override
    /**
     * Will return the object image.
     */
    public Image getImg() {
        return this.image;
    }
    
}