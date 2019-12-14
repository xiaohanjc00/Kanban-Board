
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
    static JPanel board_panel;
    static JPanel build_board;
    String b_name;
    int col_name;
    Board board;
    DropPane col_outer;
    ArrayList<JPanel> cols = new ArrayList<>(); // arraylist to store all columns present in the current board.
    private static JScrollPane activityLogPanel;
    private JScrollPane scroll_pane;

    public BoardGUI(String name, Board b) {
        b_name = name;
        board = b;
    }

    /**
     * Generate Board GUI when creating a new board
     * 
     * @return Main Board panel
     */
    public JPanel generate() {
        /* Main panel */
        board_panel = new JPanel();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        BorderLayout lay = new BorderLayout();

        /* Create top panel */
        JPanel topbar = new JPanel();
        topbar = buildTopBar();

        /* Create ActivityLog panel */
        buildActivityLogPanel();

        /* Create main working area panel */
        JPanel work_area = new JPanel();
        work_area.setLayout(new BorderLayout());

        /* Create column panel */
        col_area = new JPanel();
        col_area = buildNewColumn(work_area, col_area);

        /* Joining everything together */
        scroll_pane = new JScrollPane(work_area);
        work_area.add(col_area, BorderLayout.CENTER);
        board_panel.setLayout(lay);
        board_panel.add(topbar, BorderLayout.PAGE_START);
        board_panel.add(activityLogPanel, BorderLayout.LINE_END);
        activityLogPanel.setPreferredSize(new Dimension(400, Integer.MAX_VALUE));
        board_panel.add(scroll_pane, BorderLayout.CENTER);
        return (board_panel);

    }

    /**
     * Generate Board GUI when loading a previously created board
     * 
     * @param load_data where previous data is stored
     * @return Main board panel
     */
    public JPanel build(LoadData load_data) {
        /* Main panel */
        build_board = new JPanel();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        BorderLayout lay = new BorderLayout();

        /* Create new empty board */
        try {
            board = new Board(load_data.getBoardName());
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        /* Create topBar panel */
        JPanel topbar = new JPanel();
        topbar = buildTopBar();

        /* Create ActivityLog panel */
        buildActivityLogPanel();

        /* Create main working area panel */
        JPanel work_area = new JPanel();
        work_area.setLayout(new BorderLayout());

        /* Create column panel */
        col_area = new JPanel();
        col_area = buildNewColumn(work_area, col_area);

        /* Retrieve previous stored boards */
        getPreviousBoards(load_data);

        /* Join everything together */
        scroll_pane = new JScrollPane(work_area);
        work_area.add(col_area, BorderLayout.CENTER);
        build_board.setLayout(lay);
        build_board.add(topbar, BorderLayout.PAGE_START);
        build_board.add(activityLogPanel, BorderLayout.LINE_END);
        activityLogPanel.setPreferredSize(new Dimension(400, Integer.MAX_VALUE));
        build_board.add(scroll_pane, BorderLayout.CENTER);
        return build_board;
    }

    /**
     * Build the top bar of the main Board panel
     * 
     * @return topBar JPanel
     */
    public JPanel buildTopBar() {
        /* Create topBar panel */
        JPanel topbar = new JPanel();

        /* Set topBar layout */
        topbar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        BoxLayout box_ly = new BoxLayout(topbar, BoxLayout.X_AXIS);
        topbar.setLayout(box_ly);

        /* Create board name label */
        JLabel board_name = new JLabel("BOARD NAME:  " + board.getName());
        topbar.add(board_name);
        topbar.add(Box.createHorizontalGlue());

        return topbar;
    }

    /**
     * Build the activityLog panel
     */
    public void buildActivityLogPanel() {

        /* Create the activityLog panel */
        JPanel subPanel = new JPanel();
        activityLogPanel = new JScrollPane(subPanel);
        activityLogPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        BoxLayout g1 = new BoxLayout(subPanel, BoxLayout.Y_AXIS);
        subPanel.setLayout(g1);

        /* Create activityLog label */
        JLabel head = new JLabel("   ACTIVITY LOG    ");
        ((JPanel) activityLogPanel.getViewport().getView()).add(head);

        /* Load previously saved activity log of the board */
        for (String line : board.getLog()) {
            JLabel newLabel = new JLabel(line);
            ((JPanel) activityLogPanel.getViewport().getView()).add(newLabel);
        }
    }

    /**
     * Build the main working panel of the Board (where column is showed)
     * 
     * @param work_area Main panel of the Board
     * @param col_area  Column panel
     * @return JPanel of the main working panel
     */
    public JPanel buildNewColumn(JPanel work_area, JPanel col_area) {

        /* Set the layout */
        GridBagLayout g2 = new GridBagLayout();
        GridBagConstraints c3 = new GridBagConstraints();
        col_area.setLayout(g2);
        col_area.setBorder(BorderFactory.createLineBorder(Color.black));

        /* Create button panel */
        JPanel but = new JPanel();

        /* Set button panel layout */
        but.setBorder(BorderFactory.createLineBorder(Color.black));
        but.setLayout(new BoxLayout(but, BoxLayout.X_AXIS));

        /* Create add column button */
        JButton add_col = new JButton(" CREATE A NEW COLUMN: ");
        add_col.addActionListener(l -> {
            last++;

            /* Create new column window */
            JPanel col_input = new JPanel();
            col_input.setLayout(new BoxLayout(col_input, BoxLayout.Y_AXIS));

            /* Add labels and textfields to the window */
            col_input.add(new JLabel("ENTER COLUMN NAME: "));
            JTextField name = new JTextField(5);
            col_input.add(name);
            col_input.add(new JPanel());
            col_input.add(new JLabel("ENTER COLUMN ROLE: "));
            JTextField role = new JTextField(5);
            col_input.add(role);

            /* Create confirmation dialog */
            int col_name = JOptionPane.showConfirmDialog(Main.first_frame, col_input, "ENTER COLUMN DETAILS:",
                    JOptionPane.OK_CANCEL_OPTION);

            /* Action after pressing OK */
            if (col_name == JOptionPane.OK_OPTION) {
                ColumnGUI col_obj = new ColumnGUI(name.getText(), role.getText(), this);

                /* Create the new column */
                Column column = col_obj.getColumn();
                board.addColumn(column);

                /* New Column log */
                String text = board.actLog.createColumnLog(name.getText(), board.getName());
                addNewLogLine(text);

                /* Create new DropPane for the column */
                col_n = new JPanel();
                col_outer = new DropPane(column);
                col_n.setLayout(new BoxLayout(col_n, BoxLayout.Y_AXIS));
                col_n.add(col_obj.generatePanel());
                col_n.add(deleteBut(column));

                /* Set the layout */
                col_n.setBorder(BorderFactory.createLineBorder(Color.black));
                col_outer.setBorder(BorderFactory.createLineBorder(Color.black));
                col_outer.setBackground(Color.BLACK);
                c3.anchor = GridBagConstraints.NORTHWEST;
                c3.gridx = last;
                c3.gridy = 0;
                c3.weightx = 0.01;
                c3.weighty = 0.01;

                /* Join everything together to form the column */
                col_outer.add(col_n);
                col_area.add(col_outer, c3);
                cols.add(col_n);

                /* Revalidating the board */
                try {
                    build_board.revalidate();
                } catch (Exception e) {
                }
                try {
                    board_panel.revalidate();
                } catch (Exception e) {
                }
            }
        });

        /* Join all the buttons to a same panel */
        but.add(createSaveButton());
        but.add(Box.createHorizontalGlue());
        but.add(add_col);

        /* Add button panel to working area panel */
        work_area.add(but, BorderLayout.PAGE_START);

        return col_area;
    }

    /**
     * Create the save button
     * 
     * @return Button for the save function
     */
    public JButton createSaveButton() {
        /* Create the save button */
        JButton save = new JButton("SAVE");

        /* Action for the save button */
        save.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                /* Save the data board into its csv */
                String fileName = board.getName();
                SaveData saveData = new SaveData(fileName, board);

                /* Save screenshot of the current board state */
                try {
                    takeSnapShot(build_board, board.getName());
                } catch (Exception e3) {
                }
                try {
                    takeSnapShot(board_panel, board.getName());
                } catch (Exception e3) {
                }

                /* Save board log */
                String text = Board.actLog.saveButtonLog(board.getName());
                addNewLogLine(text);

                /* Revalidate board */
                try {
                    board_panel.revalidate();
                    board_panel.repaint();
                } catch (Exception e2) {
                }
                ;
                try {
                    build_board.revalidate();
                    build_board.repaint();
                } catch (Exception e2) {
                }
            }

        });
        return save;
    }

    /**
     * Get the board data of previously saved data
     * 
     * @param load_data where the data is loaded
     */
    public void getPreviousBoards(LoadData load_data) {

        /* Get the information form the load data file */
        ArrayList<ArrayList<Object>> board_info = new ArrayList<ArrayList<Object>>();
        try {
            board_info = load_data.getInformation();
        } catch (IOException e) {
            e.printStackTrace();
        }

        last = last + 1;

        /* Build the board using the given information of each column */
        for (ArrayList<Object> column : board_info) {

            /* Data structure for the cards in each column */
            ArrayList<ArrayList<String>> load_cards_string = new ArrayList<ArrayList<String>>();
            load_cards_string = load_data.getCardDetails(column);
            ArrayList<Card> load_cards = new ArrayList<Card>();

            /* For each card information given create a card */
            for (ArrayList<String> cardString : load_cards_string) {
                Card newCard = new Card("creator", load_data.getCardName(cardString), load_data.getCardID(cardString));
                newCard.setDescription(load_data.getCardDescription(cardString));
                newCard.setStoryPoint(load_data.getCardStoryPoints(cardString));
                load_cards.add(newCard);

            }

            /* Create the column GUI */
            ColumnGUI load_col = new ColumnGUI(load_data.getColumnName(column), load_data.getColumnRole(column), this,
                    load_cards); // Here, you add the column name and column role parameter.
            Column loadColumn = load_col.getColumn();
            board.addColumn(loadColumn);

            /* Set the new board as the current active board */
            Main.activeBoard = board;

            /** Create the structure of the column */
            DropPane load_col_outer = new DropPane(load_col.getColumn());
            load_col_outer.setBorder(BorderFactory.createLineBorder(Color.black));
            load_col_outer.setBackground(Color.BLACK);

            JPanel load_col_n = new JPanel();
            load_col_n.setLayout(new BoxLayout(load_col_n, BoxLayout.Y_AXIS));
            load_col_n.add(load_col.buildCol());
            load_col_n.add(deleteBut(load_col.getColumn()));
            load_col_outer.add(load_col_n);

            GridBagConstraints c4 = new GridBagConstraints();
            c4.anchor = GridBagConstraints.NORTHWEST;
            c4.gridx = last;
            c4.gridy = 0;
            c4.weightx = 0.01;
            c4.weighty = 0.01;
            col_area.add(load_col_outer, c4);
            last++;
        }
    }

    /**
     * FUnction for deleting a column
     * 
     * @param Container object to be deleted
     */
    public void deleteCol(Container to_delete) {

        /* Remove the column */
        col_area.remove(to_delete);
        col_area.revalidate();

        /* Revalidate the board */
        if (board_panel != null) {
            board_panel.revalidate();
            board_panel.repaint();
        }
        if (build_board != null) {
            build_board.revalidate();
            build_board.repaint();
        }
    }

    /**
     * Create a delete button
     * 
     * @param Column object associated with the parent column
     */
    public JButton deleteBut(Column column) {

        /* Create the button */
        JButton delete_col = new JButton("DELETE COLUMN");

        /* Action for the button */
        delete_col.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                /* Delete column log */
                String text = board.actLog.deleteColumnLog(column.getName(), b_name);
                addNewLogLine(text);

                /** Remove from column */
                board.removeColumn(column);
                deleteCol(delete_col.getParent().getParent());
            }

        });

        return delete_col;
    }

    /**
     * Add a new line to the ActivityLog panel
     * 
     * @param text text to be added to the ActivityLog panel
     */
    public static void addNewLogLine(String text) {
        /* Add the text if it is not null */
        if (!text.equals("")) {
            JLabel newLabel = new JLabel(text);
            ((JPanel) activityLogPanel.getViewport().getView()).add(newLabel);
        }

        /* Revalidate board */
        try {
            board_panel.revalidate();
            board_panel.repaint();
        } catch (Exception e2) {
        }
        ;
        try {
            build_board.revalidate();
            build_board.repaint();
        }
        catch(Exception e2){}
    }

    /**
     * Get the current board
     * @return the current board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Take a snapshot of the current state of the board
     * @param panel Main panel of the board
     * @param boardName Name of the current board
     */
    public void takeSnapShot(JPanel panel , String boardName){

        /* Get the bufered image */
        BufferedImage bufImage = new BufferedImage(panel.getSize().width, panel.getSize().height,BufferedImage.TYPE_INT_RGB);
        panel.paint(bufImage.createGraphics());

        /* Get the current time */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_hh.mm.ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String time = localDateTime.format(formatter);

        /* Create the folder to store the image if does not exist */
        File newFolder = new File("src/main/resources/Screenshots/" + boardName + "/" + boardName + "_" + time + ".jpeg");
        if(!newFolder.exists()){
            newFolder.mkdirs();
        }
        /* Save the new image to the corresponding folder */
        try{
            ImageIO.write(bufImage, "jpeg", newFolder);
        }
        catch(Exception ex){
            ex.getStackTrace();
        }
    }
}
