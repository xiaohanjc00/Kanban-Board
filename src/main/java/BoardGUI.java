
/** 
 * Represents the Board GUI for the application. 
 * The Board is a Kanban board that gives its users different features of a Kanban Board.
 * It implements Serializable class to implement certain features of the board.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JComponent.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.awt.dnd.*;
import java.awt.Color;
import java.io.*;
import java.awt.image.BufferedImage;

public class BoardGUI implements Serializable {
    static int last = -1; // counter to keep track of the last position for lists
    static JPanel col_n; // JPanel to add new column or list
    JPanel col_area;
    JPanel board_panel;
    JPanel build_board;
    String b_name;
    int col_name;
    Board board;
    DropPane col_outer;
    ArrayList <JPanel> cols = new ArrayList <> ();      // arraylist to store all columns present in the current board.
    private static JScrollPane activityLogPanel;

    public BoardGUI(String name, Board b) {
        b_name = name;
        board = b;
    }
    
   /**
    * This generates a new board GUI instance in the form of a JPanel. 
    */
    
    public JPanel generate() {
        
        board_panel = new JPanel();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        BorderLayout lay = new BorderLayout();

        // JPanel for toolbar showing board name and members
        JPanel topbar = new JPanel();

        topbar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        BoxLayout box_ly = new BoxLayout(topbar, BoxLayout.X_AXIS);
        topbar.setLayout(box_ly);

        JLabel board_name = new JLabel("BOARD NAME:  " + board.getName());
        topbar.add(board_name);
        topbar.add(Box.createHorizontalGlue());
        JLabel members = new JLabel(" MEMBERS:  ");
        topbar.add(members);

        for (String mem : board.getMembers()) {
            JLabel thismember = new JLabel(mem);
            topbar.add(thismember);
        }

        /* JPanel showing the Activity Log for Current Board */

        JPanel subPanel = new JPanel();
        activityLogPanel = new JScrollPane(subPanel);
        activityLogPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        BoxLayout g1 = new BoxLayout(subPanel, BoxLayout.Y_AXIS);
        subPanel.setLayout(g1);

        JLabel head = new JLabel("   ACTIVITY LOG    ");
        ((JPanel) activityLogPanel.getViewport().getView()).add(head);

        for (String line : board.getLog()) {
            JLabel newLabel = new JLabel(line);
            ((JPanel) activityLogPanel.getViewport().getView()).add(newLabel);
        }

        /* JPanel for Columns */

        JPanel work_area = new JPanel();
        work_area.setLayout(new BorderLayout());

        col_area = new JPanel();

        GridBagLayout g2 = new GridBagLayout();

        GridBagConstraints c3 = new GridBagConstraints();
        col_area.setLayout(g2);
        col_area.setBorder(BorderFactory.createLineBorder(Color.black));

        JPanel but = new JPanel();
        but.setBorder(BorderFactory.createLineBorder(Color.black));
        but.setLayout(new BoxLayout(but, BoxLayout.X_AXIS));
        
        /* Adding button to create a column. The column informstion is taken by the user in a new JOPtionPane. */

        JButton add_col = new JButton(" CREATE A NEW COLUMN: ");
        add_col.addActionListener(l -> {
            last++;
            JPanel col_input = new JPanel();
            col_input.setLayout(new BoxLayout(col_input, BoxLayout.Y_AXIS));
            col_input.add(new JLabel("ENTER COLUMN NAME: "));
            JTextField name = new JTextField(5);
            col_input.add(name);
            col_input.add(new JPanel());
            col_input.add(new JLabel("ENTER COLUMN ROLE: "));
            JTextField role = new JTextField(5);
            col_input.add(role);
            int col_name = JOptionPane.showConfirmDialog(Main.first_frame, col_input, "ENTER COLUMN DETAILS:",
                    JOptionPane.OK_CANCEL_OPTION);
            if (col_name == JOptionPane.OK_OPTION) {
                ColumnGUI col_obj = new ColumnGUI(name.getText(), role.getText(), this);
                Column column = col_obj.getColumn();
                board.addColumn(column);

                /*New Column log */
                String text = Board.actLog.createColumnLog(name.getText(), board.getName());
                addNewLogLine(text);

                col_n = new JPanel();
                col_outer = new DropPane(column);
                col_n.setLayout(new BoxLayout(col_n, BoxLayout.Y_AXIS));
                col_n.add(col_obj.generatePanel());
                col_n.add(deleteBut(column));
                col_n.setBorder(BorderFactory.createLineBorder(Color.black));
                col_outer.setBorder(BorderFactory.createLineBorder(Color.black));
                col_outer.setBackground(Color.BLACK);
                col_outer.add(col_n);
                c3.anchor = GridBagConstraints.NORTHWEST;
                c3.gridx = last;
                c3.gridy = 0;
                c3.weightx = 0.01;
                c3.weighty = 0.01;
                col_area.add(col_outer, c3);
                cols.add(col_n);
                board_panel.revalidate();
            }
        });

        /* JButton to save a board and all the information it stores. */
        
        JButton save = new JButton("SAVE");
         save.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e){
            String fileName = board.getName() ;
            SaveData saveData = new SaveData(fileName, board);
            takeSnapShot(board_panel, board.getName());

            /*Save board log */
             
            String text = Board.actLog.saveButtonLog(board.getName());
            addNewLogLine(text);
            board_panel.revalidate();
            board_panel.repaint();
         }
         });

        but.add(save);
        but.add(Box.createHorizontalGlue());
        but.add(add_col);
        work_area.add(but, BorderLayout.PAGE_START);
        work_area.add(col_area, BorderLayout.CENTER);
        board_panel.setLayout(lay);
        board_panel.add(topbar, BorderLayout.PAGE_START);
        board_panel.add(activityLogPanel, BorderLayout.LINE_END);
        board_panel.add(work_area, BorderLayout.CENTER);
        return (board_panel);

    }
    
    /*
    * Method to build a perviously saved board.
    * @param: LoadData object that reads all information from a CSV file.
    * It returns the board built in the form of a JPanel.
    */
    
    public JPanel build(LoadData load_data)
    {
     build_board = new JPanel();    
     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     BorderLayout lay = new BorderLayout();   
     //board.clearColumns();
        try {
            board = new Board(load_data.getBoardName());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        /* topbar to show board name */
        
     JPanel topbar = new JPanel();
        topbar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        BoxLayout box_ly = new BoxLayout(topbar, BoxLayout.X_AXIS);
        topbar.setLayout(box_ly);
        JLabel board_name = new JLabel("BOARD NAME: " + board.getName());
        topbar.add(board_name);
        topbar.add(Box.createHorizontalGlue());
        JLabel members = new JLabel(" MEMBERS:  ");
        topbar.add(members);
        for (String mem : board.getMembers()) {
            JLabel thismember = new JLabel(mem);
            topbar.add(thismember);
        }
        
    /* JPanel showing the Activity Log for Current Board */
        
        JPanel subPanel = new JPanel();
        activityLogPanel = new JScrollPane(subPanel);
        activityLogPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        BoxLayout g1 = new BoxLayout(subPanel, BoxLayout.Y_AXIS);
        subPanel.setLayout(g1);

        JLabel head = new JLabel("   ACTIVITY LOG    ");
        ((JPanel) activityLogPanel.getViewport().getView()).add(head);

        for (String line : board.getLog()) {
            JLabel newLabel = new JLabel(line);
            ((JPanel) activityLogPanel.getViewport().getView()).add(newLabel);
        }

    /* JPanel for Columns */
        
    JPanel work_area = new JPanel();
    work_area.setLayout(new BorderLayout());

    col_area = new JPanel();
    GridBagLayout g2 = new GridBagLayout();

        GridBagConstraints c3 = new GridBagConstraints();
        col_area.setLayout(g2);
        col_area.setBorder(BorderFactory.createLineBorder(Color.black));

        JPanel but = new JPanel();
        but.setBorder(BorderFactory.createLineBorder(Color.black));
        but.setLayout(new BoxLayout(but, BoxLayout.X_AXIS));
        
        /* Adding button to create list. The column informstion is taken by the user in a new JOPtionPane. */

        JButton add_col = new JButton(" CREATE A NEW COLUMN: ");
        add_col.addActionListener(l -> {
        last++;
        JPanel col_input = new JPanel();
            col_input.setLayout(new BoxLayout(col_input, BoxLayout.Y_AXIS));
            col_input.add(new JLabel("ENTER COLUMN NAME: "));
            JTextField name = new JTextField(5);
            col_input.add(name);
            col_input.add(new JPanel());
            col_input.add(new JLabel("ENTER COLUMN ROLE: "));
            JTextField role = new JTextField(5);
            col_input.add(role);
            int col_name = JOptionPane.showConfirmDialog(Main.first_frame, col_input, "ENTER COLUMN DETAILS:",
                    JOptionPane.OK_CANCEL_OPTION);
            if (col_name == JOptionPane.OK_OPTION) {
                ColumnGUI col_obj = new ColumnGUI(name.getText(), role.getText(), this);
                Column column = col_obj.getColumn();
                board.addColumn(column);   
    
           /*New Column log */
                
                String text = board.actLog.createColumnLog(name.getText(), board.getName());
                addNewLogLine(text);
                
                col_n = new JPanel();
                col_outer = new DropPane(column);
                col_n.setLayout(new BoxLayout(col_n, BoxLayout.Y_AXIS));
                col_n.add(col_obj.generatePanel());
                col_n.add(deleteBut(column));

                col_n.setBorder(BorderFactory.createLineBorder(Color.black));
                col_outer.setBorder(BorderFactory.createLineBorder(Color.black));
                col_outer.setBackground(Color.BLACK);
                col_outer.add(col_n);
                c3.anchor = GridBagConstraints.NORTHWEST;
                c3.gridx = last;
                c3.gridy = 0;
                c3.weightx = 0.01;
                c3.weighty = 0.01;
                col_area.add(col_outer, c3);

                cols.add(col_n);
                build_board.revalidate();
            }
        });
        
        /* JButton to save a board. All information about the data is saved in a CSV file. */
        
        JButton save = new JButton("SAVE");
         save.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e){
            String fileName = board.getName();
            SaveData saveData = new SaveData(fileName, board);
            takeSnapShot(build_board, board.getName());

            /*Save board log */
             
            String text = Board.actLog.saveButtonLog(board.getName());
            addNewLogLine(text);
            build_board.revalidate();
            build_board.repaint();
         }
          });

        but.add(save);
        but.add(Box.createHorizontalGlue());
        but.add(add_col);
        
        /* Load up the components of the board (e.g. Columns, Cards in Columns). The LoadData object holds all information of the CSV file. */
        
        ArrayList<ArrayList<Object>> board_info = new ArrayList<ArrayList<Object>>(); //redefine this arraylist
        try {
            board_info = load_data.getInformation();
        } catch (IOException e) {
            e.printStackTrace();
        }
        last = last + 1;
        for(ArrayList<Object> column: board_info)                             //This loop goes through every ArrayList<Object> element.
        {
        ArrayList<ArrayList<String>> load_cards_string = new ArrayList<ArrayList<String>>();  //add all card objects in this arraylist
        load_cards_string = load_data.getCardDetails(column);
        ArrayList<Card> load_cards = new ArrayList<Card>();
        for(ArrayList<String> cardString : load_cards_string){
            Card newCard = new Card("creator", load_data.getCardName(cardString), load_data.getCardID(cardString));
            newCard.setDescription(load_data.getCardDescription(cardString));
            newCard.setStoryPoint(load_data.getCardStoryPoints(cardString));
            load_cards.add(newCard);
            
        }
        
        ColumnGUI load_col= new ColumnGUI(load_data.getColumnName(column), load_data.getColumnRole(column),this, load_cards); //Here, you add the column name and column role parameter.
        Column loadColumn = load_col.getColumn();
        board.addColumn(loadColumn);
        //System.out.println("-----");



        DropPane load_col_outer = new DropPane(load_col.getColumn());
        load_col_outer.setBorder(BorderFactory.createLineBorder(Color.black));
        load_col_outer.setBackground(Color.BLACK);
        
        JPanel load_col_n = new JPanel();
        load_col_n.setLayout(new BoxLayout(load_col_n, BoxLayout.Y_AXIS));
        load_col_n.add(load_col.buildCol());
        //System.out.println("deleting " + load_col.getColumn());
        //System.out.println("size before: " + );
        load_col_n.add(deleteBut(load_col.getColumn()));
        //System.out.println("deleting " + load_col.getColumn());
        load_col_outer.add(load_col_n);

        
        
        GridBagConstraints c4 = new GridBagConstraints();
        c4.anchor = GridBagConstraints.NORTHWEST;
                c4.gridx = last;
                c4.gridy = 0;
                c4.weightx = 0.01;
                c4.weighty = 0.01;
        col_area.add(load_col_outer,c4);
        last ++;
        }
        
        
        work_area.add(but, BorderLayout.PAGE_START);
        work_area.add(col_area, BorderLayout.CENTER);
        
        build_board.setLayout(lay);
        build_board.add(topbar, BorderLayout.PAGE_START);
        build_board.add(activityLogPanel, BorderLayout.LINE_END);
        build_board.add(work_area, BorderLayout.CENTER);
        return build_board;
        
        
    }
    /**
     * function to delete a column
     *
     * @param Container object to be deleted
     */
    public void deleteCol(Container to_delete) {

        col_area.remove(to_delete);
        col_area.revalidate();
        if(board_panel != null)
        {
        board_panel.revalidate();
        board_panel.repaint();
        }
        if(build_board != null)
        {
         build_board.revalidate();
         build_board.repaint();
        }
    }
    /**
     * function to create a delete button
     *
     * @param Column object associated with the parent column
     */
    public JButton deleteBut(Column column){
        JButton delete_col = new JButton("DELETE COLUMN");
        delete_col.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                        
        /*Delete column log */
        String text = board.actLog.deleteColumnLog(column.getName(), b_name);
        addNewLogLine(text);

        board.removeColumn(column);
        deleteCol(delete_col.getParent().getParent());
                    }
                });
        return delete_col;
    }
    public static void addNewLogLine(String text) {
        if(!text.equals("")){
            JLabel newLabel = new JLabel(text);
            ((JPanel) activityLogPanel.getViewport().getView()).add(newLabel);
        }
    }

    public Board getBoard() {
        return board;
    }

    void takeSnapShot(JPanel panel , String boardName){
        BufferedImage bufImage = new BufferedImage(panel.getSize().width, panel.getSize().height,BufferedImage.TYPE_INT_RGB);
        panel.paint(bufImage.createGraphics());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_hh.mm.ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String time = localDateTime.format(formatter);

        File newFolder = new File("src/main/resources/Screenshots/" + boardName + "/" + boardName + "_" + time + ".jpeg");
        if(!newFolder.exists()){
            newFolder.mkdirs();
        }

        try{
            //newFolder.createNewFile();
            ImageIO.write(bufImage, "jpeg", newFolder);
        }
        catch(Exception ex){
            ex.getStackTrace();
        }
 }
}
