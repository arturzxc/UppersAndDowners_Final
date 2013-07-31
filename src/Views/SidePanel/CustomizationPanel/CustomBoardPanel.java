package Views.SidePanel.CustomizationPanel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JButton;

/**
 *
 * @author Nyx
 */
public class CustomBoardPanel extends JLabel implements ActionListener
{

    private JButton ok;
    private JLabel createBoardTitle;
    private JLabel createColourTitle;
    private JLabel createUpDownTitle;
    private int chosenSize;
    private int chosenBoardStyle;
    private int chosenUpDownStyle;
    private int[] boardSize;
    private String[] squareTypes, up_DownersType;
    private ArrayList<String> sqTypePics = new ArrayList<String>();
    private ArrayList<String> upDownTypePics = new ArrayList<String>();
    private JComboBox sizeList;
    private JComboBox typeList;
    private JComboBox upDownList;
    JLabel boardStylePics;
    JLabel upDownPics;
    JLabel createBoardButton;
    //private CreateBoardButton createBButton;
    private JLabel createBButton;
    private GridBagConstraints cons;

    public CustomBoardPanel() {
        //super(new GridBagLayout());
        setSize(291,500);
        setLayout(new GridBagLayout());
        setUpCconsonstraints();
        setUpAll();
        setConstraints(0, 0, 1, 0.1, 1, 1);
        add(createBoardTitle, cons);
        setConstraints(0, 1, 1, 0.1, 1, 1);
        add(sizeList, cons);
        setConstraints(0, 2, 1, 0.1, 1, 1);
        add(createColourTitle, cons);
        setConstraints(0, 3, 1, 0.4, 1, 1);
        add(typeList, cons);
        setConstraints(0, 4, 1, 0.1, 1, 1);
        add(boardStylePics, cons);
        //newly added
        setConstraints(0, 5, 1, 0.4, 1, 1);
        add(createUpDownTitle, cons);
        setConstraints(0, 6, 1, 0.4, 1, 1);
        add(upDownList, cons);
        setConstraints(0, 7, 1, 0.1, 1, 1);
        add(upDownPics, cons);
        setConstraints(0, 8, 1, 1, 1, 1);
        add(createBButton,cons);

        this.chosenSize = 10;//default fields
        this.chosenBoardStyle = 0;//default style

    }
    private void setUpAll(){
          //Create combobox for board size
        createBoardTitle = new JLabel("Set Board Size:");

        this.boardSize = new int[]{ 8, 9, 10, 11, 12, 13, 14};
        String[] boardSizes = new String[boardSize.length];
        for (int i=0; i<boardSize.length; i++)
        {
            boardSizes[i]=boardSize[i]+"x"+boardSize[i];
        }
        sizeList = new JComboBox(boardSizes);
        sizeList.setSelectedIndex(2);
        sizeList.addActionListener(this);


        createColourTitle = new JLabel("Set Board Colour:");


         //Create combobox for boardtype CREATE SEPERATELY
        squareTypes = new String[] {"Checkered", "Dark", "Light"};
        //List of squeres for the board
        typeList = new JComboBox(squareTypes);
        typeList.setSelectedIndex(0);
        typeList.addActionListener(this);



        boardStylePics = new JLabel();
        boardStylePics.setFont(boardStylePics.getFont().deriveFont(Font.ITALIC));
        boardStylePics.setHorizontalAlignment(JLabel.CENTER);
        updateLabel(typeList.getSelectedIndex());
        boardStylePics.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));


        ///////////////createBButton = new CreateBoardButton("none");

        createUpDownTitle = new JLabel("Set Uppers and Downers Appearance:");/*\n(Note: Uppers and Downers can be placed"
                + "on the board after its creation by right clicking on the square representing the start position "
                + "of an upper/downer and entering the square number for the final position of the upper/downer)");
         //Create combobox for uppers/downers CREATE SEPERATELY*/
        up_DownersType = new String[] {"Forest Green", "Industrial Grey", "Cool Blue"};
        //List of squeres for the board
        upDownList = new JComboBox(up_DownersType);
        upDownList.setSelectedIndex(0);
        upDownList.addActionListener(this);



        upDownPics = new JLabel();
        upDownPics.setFont(upDownPics.getFont().deriveFont(Font.ITALIC));
        upDownPics.setHorizontalAlignment(JLabel.CENTER);
        updateUpDownLabel(upDownList.getSelectedIndex());
        upDownPics.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));


        createBButton = new CreateBoardButton("none");

    }

    private void setUpCconsonstraints(){
        cons = new GridBagConstraints();
        cons.anchor=GridBagConstraints.NORTHWEST;
        cons.fill=GridBagConstraints.BOTH;
        cons.weightx=1;
        cons.weighty=1;
        cons.gridheight=1;
        cons.gridwidth=1;
    }

    private void setConstraints(int gridx, int gridy,double weightx, double weighty, int gridheight,int gridwidth){
        cons.gridx=gridx;
        cons.gridy=gridy;
        cons.weightx=weightx;
        cons.weighty=weighty;
        cons.gridheight=gridheight;
        cons.gridwidth=gridwidth;
    }

    public int getStyle(){
        return this.chosenBoardStyle;
    }

    public int getUpDownStyle(){
        return this.chosenUpDownStyle;
    }

    /** Listens to the combo box. */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==sizeList)
        {
            JComboBox cb = (JComboBox)e.getSource();
            chosenSize = (Integer)boardSize[cb.getSelectedIndex()];
            System.out.println(chosenSize);
        }
        else if (e.getSource()==typeList)
        {
            JComboBox cb = (JComboBox)e.getSource();
            chosenBoardStyle = cb.getSelectedIndex();
            System.out.println(chosenBoardStyle);
            updateLabel(chosenBoardStyle);
        }
        else if (e.getSource()==upDownList)
        {
            JComboBox cb = (JComboBox)e.getSource();
            chosenUpDownStyle = cb.getSelectedIndex();
            System.out.println(chosenUpDownStyle);
            updateUpDownLabel(chosenUpDownStyle);
        }

    }

    protected void updateUpDownLabel(int choice) {
          //Set up the picture.

        upDownTypePics.add("src/resources/images/forestGreenPreview.png");
        upDownTypePics.add("src/resources/images/industrialGreyPreview.png");
        upDownTypePics.add("src/resources/images/coolBluePreview.png");

        ImageIcon icon = new ImageIcon(upDownTypePics.get(choice));
        upDownPics.setIcon(icon);
        upDownPics.setToolTipText("");
        upDownPics.repaint();
        if (icon != null) {
            upDownPics.setText(null);
        } else {
            upDownPics.setText("Image not found");
        }


    }




    protected void updateLabel(int choice) {
          //Set up the picture.

        sqTypePics.add("src/resources/images/chessboard.png");
        sqTypePics.add("src/resources/images/darkboard.png");
        sqTypePics.add("src/resources/images/lgreyboard.png");

        ImageIcon icon = new ImageIcon(sqTypePics.get(choice));
        boardStylePics.setIcon(icon);
        boardStylePics.setToolTipText("");
        boardStylePics.repaint();
        if (icon != null) {
            boardStylePics.setText(null);
        } else {
            boardStylePics.setText("Image not found");
        }


    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = CustomBoardPanel.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public int getChosenBoardStyle() {
        return chosenBoardStyle;
    }

    public int getChosenSize() {
        int fields = chosenSize*chosenSize;
        return fields;
    }


    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Custom Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new CustomBoardPanel();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setSize(291,350);
        //frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}