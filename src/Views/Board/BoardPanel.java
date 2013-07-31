package Views.Board;

import Utils.BoardDrawingAlgorithms;
import Views.GUI;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLayeredPane;
import javax.swing.OverlayLayout;
import uppersanddowners_final.Main;

/**
 *
 * @author arturzxc
 */
public class BoardPanel extends JLayeredPane implements Observer {

    private GUI gui;
    private BoardMessagePanel boardMessagePanel;
    private BoardTokensPanel boardTokensPanel;
    private BoardUpAndDownPanel boardUpAndDownPanel;
    private BoardDecorationPanel boardDecoationPanel;
    private BoardSquarePanel boardSquarePanel;
    private BoardImagePanel boardImagePanel;

    public BoardPanel() {
        this.gui = Main.gui;
        this.setLayout(new OverlayLayout(this));
    }

    public void initComponents() {

        BoardDrawingAlgorithms.setPositions(800, 800, Main.model.getBoard().getNumOfFields());
        
        
        add(boardImagePanel = new BoardImagePanel(),0);
        add(boardMessagePanel = new BoardMessagePanel(), 1);
        add(boardTokensPanel = new BoardTokensPanel(), 2);
        add(boardUpAndDownPanel = new BoardUpAndDownPanel(), 3);        
        add(boardDecoationPanel = new BoardDecorationPanel(), 4);
        add(boardSquarePanel = new BoardSquarePanel(), 5);

        
    }

    public BoardDecorationPanel getBoardDecoationPanel() {
        return boardDecoationPanel;
    }

    public BoardMessagePanel getBoardMessagePanel() {
        return boardMessagePanel;
    }

    public BoardSquarePanel getBoardSquarePanel() {
        return boardSquarePanel;
    }

    public BoardTokensPanel getBoardTokensPanel() {
        return boardTokensPanel;
    }

    public BoardUpAndDownPanel getBoardUpAndDownPanel() {
        return boardUpAndDownPanel;
    }
    
    public BoardImagePanel getbBoardImagePanel(){
        return boardImagePanel;
    }

    @Override
    public void update(Observable o, Object arg) {
        revalidate();
        repaint();
    }
}
