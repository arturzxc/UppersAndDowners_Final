package Utils;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author arturzxc
 */
public class ResourceLoader {
    
    private static final String IMAGE_PATH = "/resources/images/";
    private static final String ANIMATION_PATH_ROLLDIE = "/resources/animation/rolldie/";
    private static final String ANIMATION_PATH_INTRO= "/resources/animation/intro/";
    
    /**
     * Loads an image given its name and is returned.
     * @param str
     * @return 
     */
    public static Image loadImage(String str){        
        Image img = new ImageIcon(Object.class.getResource(IMAGE_PATH+str)).getImage();
        return img;
    }
    
    /**
     * Loads the animation images for rolling and is returned.
     * @param str
     * @return 
     */
    public static Image loadAnimationRollImage(String str){             
        Image img = new ImageIcon(Object.class.getResource(ANIMATION_PATH_ROLLDIE+str)).getImage();
        return img;
    }
    
    /**
     * Loads the animation images for the Intro and is returned.
     * @param str
     * @return 
     */
    public static Image loadAnimationIntroImage(String str){             
        Image img = new ImageIcon(Object.class.getResource(ANIMATION_PATH_INTRO+str)).getImage();
        return img;
    }
    
    /**
     * Returns the image URL of any image.
     * @param tooltipUrl
     * @return 
     */
    public static URL getImageURL(String tooltipUrl){
        URL url = Object.class.getResource(IMAGE_PATH+tooltipUrl);
        return url;
    }
    
}
