package Models;

import Utils.ResourceLoader;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Software Engineering 2011-2012 (Group G)
 * Board class which will contain all climbable objects and dice objects.
 */
public final class Board {   
    
    private ArrayList<Square> squares = new ArrayList<Square>();    
    private ArrayList<Image> fieldImages = new ArrayList<Image>();
    private ArrayList<Image> upperImages = new ArrayList<Image>();   
    private ArrayList<Image> downerImages = new ArrayList<Image>();       
    private ArrayList<Climable> uppersAndDowners = new ArrayList<Climable>();    
    private Die die;
    private int numOfFields;
    private int imageSet;
    
    /**
     * constructor for default game
     */
    public Board(){          
        //FIELDS NO are : 100,121,144,169.196
        this.numOfFields=100;//THIS WILL BE A DEFAULT OR LOADED FROM A FILE IF NEEDED.                     
        imageSet=0;
        //add images of fields.
        addFieldImage("squarepurpgrey.png");
        addFieldImage("darksquare.png");      
        addFieldImage("squaregreen_gl.png");      
        addFieldImage("squareredgl.png");  
        addFieldImage("squareyellow_gl.png");  
        
        //add climable images
        addUpperImage("woodenladder.png");
        addDownerImage("greensnake.png");
        //addDownerImage("shortSnake.png");
        
         addSquares();//add square - every second sqare has other colour.
        
        /* Fixed Uppers and Downers*/        
        this.addUpper(80,99);        
        this.addUpper(28,52);     
        this.addUpper(38,42);
        this.addUpper(2,24);        
        this.addUpper(47,84);    
        
        this.addDowner(98,44);
        this.addDowner(56,27);
        this.addDowner(26,14);
        this.addDowner(93,74);
        this.addDowner(72,70);
        
        this.die = new Die();
    }
    
    public Board(int fieldsNo, int imageSet, int upperdownerSet){
                //FIELDS NO are : 100,121,144,169.196
        this.numOfFields=fieldsNo;//THIS WILL BE A DEFAULT OR LOADED FROM A FILE IF NEEDED. 
        this.fieldImages.clear();    
        this.imageSet = imageSet;
        
        addFieldImage("squarepurpgrey.png");
        addFieldImage("darksquare.png");      
        addFieldImage("squaregreen_gl.png");      
        addFieldImage("squareredgl.png");  
        addFieldImage("squareyellow_gl.png");
        
        if(upperdownerSet==0){
            addUpperImage("woodenladder.png");
            addDownerImage("greensnake.png");
        }else if(upperdownerSet==1){
            addUpperImage("metaladder.png");
            addDownerImage("longSnakebrown.png");            
        }else if(upperdownerSet==2){
            addUpperImage("metaladder.png");
            addDownerImage("longSnakeBlue.png");
        }
        
         addSquares();//add square - every second sqare has other colour.

        this.die = new Die();
    }
    
    
    public void addSquares(){
        this.squares.clear();
        Image image = fieldImages.get(0);
        Image image2 = fieldImages.get(1);
        
        if (imageSet==1){
        image = fieldImages.get(1);
        image2 = fieldImages.get(1);
        }
        if (imageSet==2){
        image = fieldImages.get(0);
        image2 = fieldImages.get(0);
        }
        
        
        Image green = fieldImages.get(2);
        Image red  = fieldImages.get(3);
        Image yellow = fieldImages.get(4);
                
        for(int i = 0;i<this.numOfFields;i++){
            if(i%2==0){
                this.squares.add(new Square(i+1, image, yellow, green, red));
            }else{
                this.squares.add(new Square(i+1, image2, yellow, green, red));
            }
        }
    }
    public ArrayList<Square> getSquares() {
        return squares;
    }

    public void setSquares(ArrayList<Square> squares) {
        this.squares = squares;
    }

    public void addUpperImage(String url){        
        upperImages.add(ResourceLoader.loadImage(url));
    }
    
    public void addDownerImage(String url){        
        downerImages.add(ResourceLoader.loadImage(url));
    }
    
    public ArrayList<Image> getFieldImages() {
        return fieldImages;
    }
    
    public void addFieldImage(String url){        
        fieldImages.add(ResourceLoader.loadImage(url));          
    }
    
    /**
     * Will add an Upper object to the game.
     * @param headPosition
     * @param tailPosition 
     */        
    public void addUpper(int headPosition, int tailPosition){
        Collections.shuffle(upperImages);//shiffle so every time upper is added it random image
        this.uppersAndDowners.add(new Upper(headPosition, tailPosition, upperImages.get(0)));
        this.squares.get(headPosition-1).setOccupied(true);
        this.squares.get(tailPosition-1).setOccupied(true);
        this.squares.get(headPosition-1).setOccupiedByUpper(true);
        this.squares.get(tailPosition-1).setOccupiedByUpper(true);
    }
    
    /**
     * Will add a Downer object to the game.
     * @param headPosition
     * @param tailPosition 
     */
    public void addDowner(int headPosition, int tailPosition){
        Collections.shuffle(downerImages);//shiffle so every time upper is added it random image
        this.uppersAndDowners.add(new Downer(headPosition, tailPosition, downerImages.get(0)));
        this.squares.get(headPosition-1).setOccupied(true);
        this.squares.get(tailPosition-1).setOccupied(true);
        this.squares.get(headPosition-1).setOccupiedByUpper(false);
        this.squares.get(tailPosition-1).setOccupiedByUpper(false);
    }        
    
    /**
     * Will return an Array list containing all the climbable objects.
     * @return 
     */
    public ArrayList<Climable> getClaimables() {
        return uppersAndDowners;
    }
   
    /**
     * Will return the die.
     * @return 
     */
    public Die getDie(){
        return this.die;
    }
    
    /**
     * Will return size of board.
     * @return 
     */
    public int getNumOfFields(){
        return this.numOfFields;
    }   
 
     public void saveBoard(String saveName) {
        String boardName = saveName;
	  try {
 
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("BOARD");
		doc.appendChild(rootElement);
 
		
		// number of fields element
		Element noOfFields = doc.createElement("NOOFFIELDS");
                String nFields = ""+this.numOfFields;
		noOfFields.appendChild(doc.createTextNode(nFields));
		rootElement.appendChild(noOfFields);
 
                
                
                // fields colour element
		Element roll = doc.createElement("FIELDSSET");
                String getRoll = ""+this.imageSet;
		roll.appendChild(doc.createTextNode(getRoll));
		rootElement.appendChild(roll);
                
                 // climable set elements
                Element udSet = doc.createElement("UDSET");
                rootElement.appendChild(udSet);
                
                Element squaresList = doc.createElement("SQUARESLIST");
                rootElement.appendChild(squaresList);
                
                //square elements
                for (int i=0; i<squares.size(); i++){
                Element square = doc.createElement("SQUARE");
                                
                Attr squareNo = doc.createAttribute("SQUARENO");
                squareNo.setValue(""+i);		
                square.setAttributeNode(squareNo);
                
                square.appendChild(doc.createTextNode(""+squares.get(i).getScore()));
                squaresList.appendChild(square);
                }
              
                Element climableList = doc.createElement("CLIMABLES");
		rootElement.appendChild(climableList);
                //climables elements
                for (int i=0; i<uppersAndDowners.size(); i++){
                Element climable = doc.createElement("CLIMABLE");
		climableList.appendChild(climableList);
                
                // set attribute to climable element
		Attr udType = doc.createAttribute("CLIMBTYPE");
                if (uppersAndDowners.get(i) instanceof Upper)
                    udType.setValue("upper");
                else
                    udType.setValue("downer");
                   
		climable.setAttributeNode(udType);
                
 
		//head position elements
		Element head = doc.createElement("HEAD");
                String headPos = ""+uppersAndDowners.get(i).getHead();
		head.appendChild(doc.createTextNode(headPos));
		climable.appendChild(head);
 
		//tail position elements
		Element tail = doc.createElement("TAIL");
                String tailPos =""+uppersAndDowners.get(i).getTail();
		tail.appendChild(doc.createTextNode(tailPos));
		climable.appendChild(tail);
                
                
                
                }
 
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(boardName+"board.xml"));
 
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
 
		transformer.transform(source, result);
 
		//System.out.println("File saved!");
 
	  } catch (ParserConfigurationException pce) {
		//System.out.println("no!");
	  } catch (TransformerException tfe) {
             // System.out.println("no2!");
	  }
    
    
     }
    
}
