/**
 * Game class
 */
package Models;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.ImageIcon;
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
 *:)
 * @author Artur, Mathan, Mike.
 */
public final class Game extends Observable {

    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Image> tokenImages = new ArrayList<Image>();
    private boolean basic;
    private Board board;
    private int whosTurn;
    private boolean alreadyRolled;
    private Player playerToBump, winner; // TESTING FOR CREATING AND PRINTING BUMP MESSAGE    

    /**
     * Basic Constructor of Game.
     */
    public Game(boolean isBasic) {
        basic = isBasic;
        alreadyRolled = false;
        whosTurn = 0; //initiate turn to 0.  
        //set token images
        this.addTokenImage("/resources/images/tokenpurp.png");
        this.addTokenImage("/resources/images/tokenblue.png");
        this.addTokenImage("/resources/images/tokengreen.png");
        this.addTokenImage("/resources/images/tokenred.png");
            
        if (isBasic) {
            board = new Board();//Board initiated with default settings. 
            addComputerPlayer("Comp");
        }  
        //Board initiated with custom fields.
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void createBoard(int fieldsNo, int boardStyle, int upperdownersSet) {
        board = null;
        board = new Board(fieldsNo, boardStyle, upperdownersSet);
    }

    public boolean isBasic() {
        return basic;
    }

    public void setPlayerToBump(Player playerToBump) {
        this.playerToBump = playerToBump;
    }

    public boolean isAlreadyRolled() {
        return alreadyRolled;
    }

    public void setAlreadyRolled(boolean alreadyRolled) {
        this.alreadyRolled = alreadyRolled;
    }

    public void setWhosTurn(int whosTurn) {
        this.whosTurn = whosTurn;
    }
    
    

    /**
     * Gets the board.
     * @return 
     */
    public Board getBoard() {
        return board;
    }
    
    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getPlayerToBump() {
        return playerToBump;
    }

    /**     
     * @return next player in the queue.
     */
    public synchronized Player getPlayerWhosTurn() {
        return players.get(whosTurn);
    }

    public void addTokenImage(String url) {
        Image img = new ImageIcon(this.getClass().getResource(url)).getImage();
        tokenImages.add(img);
    }

    /**
     * 
     * Add a Human player to the list of Players.
     * @param name
     * @param imgURL 
     */
    public void addHumanPlayer(String name) {
        this.players.add(new HumanPlayer(name, tokenImages.get(players.size())));
    }

    public void addPlayerCustom(String Player, String name, String colour) {
        int index = 0;

        if (colour.equalsIgnoreCase("red")) {
            index = 3;
        } else if (colour.equalsIgnoreCase("blue")) {
            index = 1;
        } else if (colour.equalsIgnoreCase("green")) {
            index = 2;
        }

        if (Player.equalsIgnoreCase("Human")) {
            this.players.add(new HumanPlayer(name, tokenImages.get(index)));
        } else if (Player.equalsIgnoreCase("Computer")) {
            this.players.add(new ComputerPlayer(name, tokenImages.get(index)));
        }
    }

    /**
     * Add a Computer player to the list of players.
     * @param name
     * @param imgURL 
     */
    public void addComputerPlayer(String name) {
        this.players.add(new ComputerPlayer(name, tokenImages.get(players.size())));
    }

    /**
     * Returns the list of players.
     * @return player list.
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * This increments the index of turn by 1 every time next player moves.
     * It is like a internal counter that keeps track of who's turn it is.
     */
    public void incrementWhosTurn() {
        if (winner != null) {//no point of playing more when there is a winner.
            return;
        }

        int numOfPlayers = this.players.size() - 1;

        if (this.whosTurn == numOfPlayers) {
            this.whosTurn = 0;
        } else {
            this.whosTurn++;
        }
        if (getPlayerWhosTurn() instanceof ComputerPlayer) {
            movePlayer(basic);
        }
    }

    public Player getNextPlayer() {
        try {
            return players.get(whosTurn + 1);
        } catch (Exception e) {
            return players.get(0);
        }
    }

    public boolean scanForWinner() {
        int winningSquare = this.getBoard().getNumOfFields();
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getPosition() == winningSquare) {
                this.winner = players.get(i);
                return true;
            }
        }
        return false;
    }

    public Player getWinner() {
        return this.winner;
    }

    /**
     * The method checks which player has his 
     * turn and then moves him by starting a thread.          
     * @param moveForward matters only for HumanPlayer as computer player determines it himself.
     */
    public void movePlayer(boolean moveForward) {
        /* Human Player */
        Thread t = null;

        if (this.players.get(this.whosTurn) instanceof HumanPlayer) {
            HumanPlayer hp = (HumanPlayer) (this.players.get(this.whosTurn));
            hp.setMoveForward(moveForward);
            t = new Thread((Runnable) hp);
            t.start(); //this will trigger run() inside player which triggers move climb and bump.                    
        } /* Computer Player */ else {
            ComputerPlayer cp = (ComputerPlayer) (this.getPlayers().get(this.whosTurn));
            cp.setMoveForward(moveForward);
            t = new Thread((Runnable) cp);
            t.start(); //this will trigger run() inside player which triggers move climb and bump.  

        }
    }


    /**
     * returns the positions of players in order they were added into ArrayList Players
     * @return positions of players in order they were created
     */
    public ArrayList<Integer> getPositionsOfPlayers() {
        try {
            ArrayList<Integer> posOfPlayers = new ArrayList<Integer>();
            for (int i = 0; i < players.size(); i++) {
                posOfPlayers.add(this.players.get(i).getPosition());
            }
            return posOfPlayers;
        } catch (NullPointerException ex) {
            throw new Error("There are no player in the list to call getPositionsOfPlayers");
        }
    }

    /**
     * Saves the game in an XML file.
     * @param saveName name of the saved game.
     */
    public void saveGame(String saveName) {
        String gameName = saveName;
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("GAME");
            doc.appendChild(rootElement);

            // Game name element
            Element gName = doc.createElement("GNAME");
            gName.appendChild(doc.createTextNode(gameName));
            rootElement.appendChild(gName);

            // set attribute to game element
            Attr attrType = doc.createAttribute("TYPE");
            if (this.basic) {
                attrType.setValue("default");
            } else {
                attrType.setValue("custom");
                board.saveBoard(gameName);
            }
            rootElement.setAttributeNode(attrType);


            // turn element
            Element turn = doc.createElement("TURN");
            String wTurn = "" + this.whosTurn;
            turn.appendChild(doc.createTextNode(wTurn));
            rootElement.appendChild(turn);


            //if is already rolled element
            Element isRolled = doc.createElement("ISROLLED");
            String rolled;
            if (this.isAlreadyRolled()) {
                rolled = "true";
            } else {
                rolled = "false";
            }
            isRolled.appendChild(doc.createTextNode(rolled));
            rootElement.appendChild(isRolled);

            // roll element
            Element roll = doc.createElement("ROLL");
            String getRoll = "" + this.board.getDie().getNumberRolled();
            roll.appendChild(doc.createTextNode(getRoll));
            rootElement.appendChild(roll);


            for (int i = 0; i < players.size(); i++) {

                // players elements
                Element player = doc.createElement("PLAYER");
                rootElement.appendChild(player);

                // set attribute to game element
                Attr playerType = doc.createAttribute("PTYPE");
                if (players.get(i) instanceof HumanPlayer) {
                    playerType.setValue("human");
                } else {
                    playerType.setValue("computer");
                }

                player.setAttributeNode(playerType);


                // name elements
                Element name = doc.createElement("NAME");
                name.appendChild(doc.createTextNode(players.get(i).getName()));
                player.appendChild(name);

                //position elements
                Element pos = doc.createElement("POS");
                String position = "" + players.get(i).getPosition();
                pos.appendChild(doc.createTextNode(position));
                player.appendChild(pos);

                //score elements
                Element score = doc.createElement("SCORE");
                String getScore = "" + players.get(i).getCurrentScore();
                score.appendChild(doc.createTextNode(getScore));
                player.appendChild(score);

            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(gameName + ".xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            //System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            //System.out.println("no!");
        } catch (TransformerException tfe) {
            //System.out.println("no2!");
        }
    }
}
