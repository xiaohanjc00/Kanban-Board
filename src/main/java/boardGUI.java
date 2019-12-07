/**
 * Represents the Board GUI for the application.
 */

import javax.swing.*;
import javax.swing.JComponent.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.time.*;
import java.util.*;
import java.awt.dnd.*;
import java.awt.Color;

public class boardGUI {
    static int last = -1; // counter to keep track of the last position for lists
    static JPanel col_n; // JPanel to add new column or list
    JPanel col_area;
    JPanel f;
    String b_name;
    int col_name;
    Board board;
    DropPane col_outer;
    ArrayList < JPanel > cols = new ArrayList < > (); //arraylist to store all columns present in the current board.
    private static JScrollPane activityLogPanel;

    public boardGUI(String name, Board b) {
        b_name = name;
        board = b;
    }

    public JPanel generate() {
        // add an input before the creation in the Homepage that would give us the name
        // of the board

        f = new JPanel();
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
        /* Adding button to create list */

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
                JButton edit_col = new JButton("EDIT COLUMN");
                edit_col.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFrame newFrame = new JFrame();
                        JPanel newPanel = new JPanel();
                        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
                        JLabel columnTitleLabel = new JLabel("Please input the new column name:");
                        JTextArea columnTitleText = new JTextArea(column.getName());
                        columnTitleText.setMinimumSize(new Dimension(150, 50));
                        columnTitleText.setMaximumSize(new Dimension(150, 50));
                        columnTitleText.setLineWrap(true);

                        JLabel columnRoleLabel = new JLabel("Please input the new column role:");
                        JTextArea columnRoleText = new JTextArea(column.getRole());
                        columnRoleText.setMinimumSize(new Dimension(150, 50));
                        columnRoleText.setMaximumSize(new Dimension(150, 50));
                        columnRoleText.setLineWrap(true);

                        JButton submitBtn = new JButton("Submit");
                        submitBtn.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                String tempName = column.getName();
                                String tempRole = column.getRole();
                                col_obj.changeName(columnTitleText.getText());
                                col_obj.changeRole(columnRoleText.getText());
                                column.setName(columnTitleText.getText());
                                column.setRole(columnRoleText.getText());

                                JOptionPane.showMessageDialog(newFrame, "Column Details saved!");
                                newFrame.setVisible(false);
                                newFrame.dispose();
                                col_obj.refreshColumn();
                                col_n.remove(0);
                                col_n.add(col_obj.generatePanel(), 0);
                                col_n.revalidate();
                                col_n.repaint();
                                f.revalidate();
                                f.repaint();

                                /*Edit column log */
                                String text = Board.actLog.editColumnLog(columnTitleText.getText(), tempName, columnRoleText.getText(), tempRole);
                                addNewLogLine(text);
                            }
                        });
                        columnTitleLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
                        columnTitleText.setAlignmentX(JComponent.CENTER_ALIGNMENT);
                        columnRoleLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
                        columnRoleText.setAlignmentX(JComponent.CENTER_ALIGNMENT);
                        submitBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
                        newPanel.add(columnTitleLabel);
                        newPanel.add(columnTitleText);
                        newPanel.add(columnRoleLabel);
                        newPanel.add(columnRoleText);
                        newPanel.add(submitBtn);
                        newFrame.add(newPanel);
                        newFrame.setSize(400, 200);
                        newFrame.setVisible(true);
                    }
                });
                JButton delete_col = new JButton("DELETE COLUMN");
                delete_col.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        /*Delete column log */
                        String text = Board.actLog.deleteColumnLog(column.getName(), b_name);
                        addNewLogLine(text);

                        deleteCol(delete_col.getParent().getParent());
                    }
                });
                col_n.add(edit_col);
                col_n.add(delete_col);

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
                f.revalidate();
            }
        });

        // JButton to save a board
        JButton save = new JButton("SAVE");
        // save.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent e){

        // }
        // });

        but.add(save);
        but.add(Box.createHorizontalGlue());
        but.add(add_col);
        work_area.add(but, BorderLayout.PAGE_START);
        work_area.add(col_area, BorderLayout.CENTER);
        f.setLayout(lay);
        f.add(topbar, BorderLayout.PAGE_START);
        f.add(activityLogPanel, BorderLayout.LINE_END);
        f.add(work_area, BorderLayout.CENTER);
        return (f);

    }

    /**
     * function to delete a column
     *
     * @param Container object to be deleted
     */
    public void deleteCol(Container to_delete) {

        col_area.remove(to_delete);
        col_area.revalidate();
        f.revalidate();
        f.repaint();
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
}