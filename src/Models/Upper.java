package Models;

import java.awt.Image;

/**
 * 
 * @author Software Engineering 2011-2012 (Group G)
 */
public class Upper implements Climable {

    private int head, tail;
    private Image image;
    
    public Upper(int headPos, int tailPos, Image img) {
       this.head = headPos;
       this.tail = tailPos;
       this.image = img;
       
    }
    
    @Override
    public int getHead() {
        return this.head;   
    }

    @Override
    public int getTail() {
        return this.tail;
    }

    @Override
    public Image getImg() {
        return this.image;
    }

    
}
