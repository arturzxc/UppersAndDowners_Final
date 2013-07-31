/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;

import javax.swing.text.html.parser.AttributeList;
 
public class Load {
    
        
        
	public static Game loadGame(String name) {
          Game loadedGame=new Game(true);
	  try {
// Needs to set afolder to save
		File fXmlFile = new File(name+".xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
 
                loadedGame = new Game(false);
                
		//checks the type of saved game and loads a board
                System.out.println(doc.getDocumentElement().getAttribute("TYPE"));
                if (doc.getDocumentElement().getAttribute("TYPE").equalsIgnoreCase("custom")){
                   // loadedGame = new Game(false);
                    loadedGame.setBoard(LoadBoard(name));
                }
                if (doc.getDocumentElement().getAttribute("TYPE").equalsIgnoreCase("default")){
                    //loadedGame = new Game(true);
                    loadedGame.setBoard(new Board());
                }
                    
                
                NodeList nListRoot = doc.getElementsByTagName("GAME");
               
                for (int i = 0; i < nListRoot.getLength(); i++) {
                    Node child =  nListRoot.item(i);
		   if (child.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element)child;
                  
                   //sets the number rolled
                    loadedGame.getBoard().getDie().setNumberRolled(Integer.parseInt(getTagValue("ROLL", el))); 
                    
                    //sets if it was rolled
                    if (getTagValue("ISROLLED", el).equals("true"))
                        loadedGame.setAlreadyRolled(true);
                    else
                        loadedGame.setAlreadyRolled(false);
                    
                    //sets who's turn
                    loadedGame.setWhosTurn(Integer.parseInt(getTagValue("TURN", el))); 
                    
                    } 
                   
                }
                
                //loads players
                NodeList nList = doc.getElementsByTagName("PLAYER");
		                  
		for (int temp = 0; temp < nList.getLength(); temp++) {
 
		   Node nNode = nList.item(temp);
		   if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
		      Element eElement = (Element) nNode;
                      
                       //System.out.println("First Name : " + getTagValue("NAME", eElement));
                      String playerName = getTagValue("NAME", eElement);
                      //System.out.println("Type: "+eElement.getAttribute("PTYPE"));
                      if (eElement.getAttribute("PTYPE").equals("computer"))
                          loadedGame.addComputerPlayer(playerName);
                      else
                          loadedGame.addHumanPlayer(playerName);
		     
                      ArrayList<Player> plL = loadedGame.getPlayers();
                      plL.get(plL.size()-1).setPosition(Integer.parseInt(getTagValue("POS", eElement)));
                      plL.get(plL.size()-1).setCurrentScore(Integer.parseInt(getTagValue("SCORE", eElement)));
                    
		   }
                   
                   
		}
	  } catch (Exception e) {
		//e.printStackTrace();
	  }
          return loadedGame;
  }
        public static Board LoadBoard(String name){
       
        Board loadedBoard = new Board();
        try {
            
		File fXmlFile = new File(name+"board.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
 
                NodeList boardList = doc.getElementsByTagName("BOARD");
                int numberOfFields=100;
                int imgSet=0;
                int udSet=0;
                for (int i = 0; i < boardList.getLength(); i++) {
                   Node child =  boardList.item(i);
		   if (child.getNodeType() == Node.ELEMENT_NODE) {
                        Element el = (Element)child;
                        numberOfFields = Integer.parseInt(getTagValue("NOOFFIELDS", el));
                        imgSet = Integer.parseInt(getTagValue("FIELDSET", el));
                        udSet = Integer.parseInt(getTagValue("UDSET", el));
                    }
                     
                }
                loadedBoard = new Board(numberOfFields, imgSet, udSet);
                loadedBoard.addSquares();
                
                NodeList fieldsList = doc.getElementsByTagName("SQUARELIST");	             
		for (int i = 0; i < fieldsList.getLength(); i++) {
 
		   Node nNode =fieldsList.item(i);
		   if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 		      
                      Element eElement = (Element) nNode;              
		      loadedBoard.getSquares().get(i).setScore(Integer.parseInt(getTagValue("SQUARE", eElement)));
		    
		   }
		}
                
                NodeList climablesList = doc.getElementsByTagName("CLIMABLES");	             
		for (int j = 0; j < climablesList.getLength(); j++) {
                        
                   int tail=0;
                   int head=0;
                    
		   Node nNode = climablesList.item(j);
		   if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 		      
                      Element el = (Element) nNode;        
                      
                      head = Integer.parseInt(getTagValue("HEAD", el));
                      tail = Integer.parseInt(getTagValue("TAIL", el));
                      
                      if (el.getAttribute("CLIMBTYPE").equals("upper"))
                          loadedBoard.addUpper(head, tail);
                      if (el.getAttribute("CLIMBTYPE").equals("downer"))
                          loadedBoard.addDowner(head, tail);
		   }
		}
                
                
                
	  } catch (Exception e) {
		//e.printStackTrace();
	  }
        
       // loadedBoard = new Board(int fieldsNo, int imageSet, int upperdownerSet);
        return loadedBoard;
  }
 
  private static String getTagValue(String sTag, Element eElement) {
	NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
 
        Node nValue = (Node) nlList.item(0);
 
	return nValue.getNodeValue();
  }
 
}
