
/**
 * 
 * Represents the Homepage of the application. The homepage of the application gives a brief description of the application, and allows users to open a new * * board or an existing board.
 * It acts as the starting point of the application.
 */

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;

public class Main {
    static String board_name = "";
    static JFrame first_frame;
    public static Board activeBoard;
    final static File folder = new File("src/main/resources/Data/");
    static BoardList boardlist;

    public static void main(String[] args) {
        setColors();
        boardlist = new BoardList();
        JPanel first_panel = new JPanel();
        String app_name = "KANBAN BOARD";
        first_frame = new JFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screen_width = (int) screenSize.getWidth();
        BorderLayout base = new BorderLayout(10, 10);
        activeBoard = null;

        /*
         * Header of home page that that acts as the navbar and holds the Home Button,
         * Close button, and title
         */

        JPanel head = new JPanel();
        head.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.decode("#0277BD")));
        head.setLayout(new BoxLayout(head, BoxLayout.X_AXIS));
        JLabel head_lb = new JLabel(app_name);

        /* Home Button: Clicking this loads the homepage of the application. */

        JButton home_b = new JButton(" HOME ");
        home_b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                first_frame.getContentPane().removeAll();
                first_panel.removeAll();
                first_panel.setLayout(base);
                first_panel.add(head, BorderLayout.PAGE_START);
                first_panel.add(loadBody(boardlist, head), BorderLayout.CENTER);
                first_frame.add(first_panel);
                first_frame.revalidate();
                first_frame.repaint();
            }
        });

        head.add(home_b);
        head.add(Box.createHorizontalGlue());
        head.add(head_lb);
        head.add(Box.createHorizontalGlue());

        /* Close Button: Clicking this shuts the application. */

        JButton close_b = new JButton("X Close");
        close_b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String fileName = activeBoard.getName();
                    SaveData saveData = new SaveData(fileName, activeBoard);
                } catch (Exception e2) {
                    System.out.println("no such file");
                }
                System.exit(0);
            }
        });
        head.add(close_b);

        /* Setting up the homepage of the application. */

        first_panel.setLayout(base);
        first_panel.add(head, BorderLayout.PAGE_START);
        first_panel.add(loadBody(boardlist, head), BorderLayout.CENTER);
        first_frame.add(first_panel);
        first_frame.setSize(screenSize.width, screenSize.height);
        first_frame.setVisible(true);
    }

    /**
     * Loads the body of the homepage that stores the description of the
     * application, and features to add or load a board.
     */

    public static JPanel loadBody(BoardList boardlist, JPanel head) {
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));

        /* Adding a basic description of the application */

        JPanel description = new JPanel();
        description.setBackground(Color.decode("#0277BD"));
        description.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.GRAY));
        JLabel description_label = new JLabel(" Welcome to our kanban board! ");
        description.add(description_label);
        description.setMaximumSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 400));
        body.add(description);

        /* Adding button to create a new Kanban Board */

        JPanel add_board = new JPanel();
        add_board.setBackground(Color.decode("#0277BD"));
        add_board.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, Color.decode("#0277BD")));
        JButton add_board_btn = new JButton(" ADD NEW BOARD ");
        add_board_btn.setBackground(Color.decode("#ECEFF1"));
        add_board_btn.setPreferredSize(new Dimension(600, 100));
        add_board_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board_name = JOptionPane.showInputDialog(first_frame, "ENTER BOARD NAME:", null);
                if (board_name != null) {
                    Board board_added = new Board(board_name);
                    boardlist.addBoard(board_added);
                    BoardGUI new_board = new BoardGUI(board_name, board_added); // Board Object also passed as parameter
                    activeBoard = board_added;

                    first_frame.getContentPane().removeAll();
                    first_frame.add(head, BorderLayout.PAGE_START);
                    first_frame.add(new_board.generate(), BorderLayout.CENTER);
                    // LoadData load_data = new LoadData("aaa.csv");
                    // first_frame.add(new_board.build(load_data), BorderLayout.CENTER);
                    first_frame.revalidate();
                    first_frame.repaint();
                }
            }
        });
        add_board.add(add_board_btn);
        add_board.setMaximumSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 100));
        body.add(add_board);

        /* Displaying a list of current saved boards in the application */
        listFileFromFolder(folder);
        JPanel list_boards = new JPanel();
        list_boards.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.decode("#0277BD")));
        JLabel list_l = new JLabel("CURRENT BOARDS: ");
        list_boards.add(list_l);
        JPanel board_panel;
        for (Board curr_board : boardlist.getAllBoards()) {
            JButton curr_board_name = new JButton(curr_board.getName());
            curr_board_name.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    activeBoard = curr_board;
                    // create the LoadData object here
                    LoadData load_data = new LoadData(curr_board.getName());
                    BoardGUI load_board;
                    load_board = new BoardGUI(curr_board.getName(), curr_board);
                    first_frame.getContentPane().removeAll();
                    first_frame.add(head, BorderLayout.PAGE_START);
                    first_frame.add(load_board.build(load_data), BorderLayout.CENTER);
                    first_frame.revalidate();
                    first_frame.repaint();
                }

            });
            board_panel = new JPanel();
            board_panel.add(curr_board_name);
            list_boards.add(board_panel);
        }

        list_boards.setMaximumSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 300));
        body.add(list_boards);
        return body;
    }

    public static void listFileFromFolder(final File folder) {
        boardlist.clearList();
        if (folder.listFiles() != null) {
            for (final File fileEntry : folder.listFiles()) {
                String result = fileEntry.getName();
                result = result.substring(0, result.length() - 8);
                Board board_added = new Board(result);
                boardlist.addBoard(board_added);
            }
        }
    }

    public static void setColors() {
        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch(Exception e4){}
        
        UIManager.put("Panel.background", Color.decode("#0277BD"));
        UIManager.put("Label.foreground", Color.decode("#ECEFF1"));
        UIManager.put("Button.background", Color.decode("#ECEFF1"));
        UIManager.put("OptionPane.background", Color.decode("#0277BD"));
        UIManager.put("OptionPane.foreground", Color.decode("#ECEFF1"));
    }
}