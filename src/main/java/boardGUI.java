/**
 * Represents the Board GUI for the application.
 */

import javax.swing.*;
import javax.swing.JComponent.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.util.*;
import java.awt.dnd.*;
import java.awt.Color;

public class boardGUI {
    static int last = -1; // counter to keep track of the last position for lists
    static DragPane col_n; // JPanel to add new column or list
    JPanel col_area;
    JPanel f;
    String b_name;
    int col_name;
    Board board;
    DropPane col_outer;
    ArrayList < JPanel > cols = new ArrayList < > (); //arraylist to store all columns present in the current board.
    public boardGUI(String name, Board b) {
        b_name = name;
        board = b;
    }
    public JPanel generate() {
        //add an input before the creation in the Homepage that would give us the name of the board
        
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

        for (String mem: board.getMembers()) {
            JLabel thismember = new JLabel(mem);
            topbar.add(thismember);
        }
        
        /*  JPanel showing the Activity Log for Current Board */
        
        JPanel activity = new JPanel();
        activity.setBorder(BorderFactory.createLineBorder(Color.black));
        GridBagLayout g1 = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        activity.setLayout(g1);

        JLabel head = new JLabel("   ACTIVITY LOG    ");


        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 10;
        c.gridheight = 2;
        c.weightx = 0.01;
        c.weighty = 0.01;
        activity.add(head, c);


        int i = 1;
        for (Object key: board.getLog().keySet()) {
            c.gridy = i;
            JLabel thing = new JLabel(board.getLog().get(key));
            activity.add(thing, c);
            i++;
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

        /* Adding button to create list */

        JButton add_col = new JButton(" CREATE A NEW COLUMN: ");
        add_col.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
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
                int col_name = JOptionPane.showConfirmDialog(Main.first_frame, col_input,
                    "ENTER COLUMN DETAILS:", JOptionPane.OK_CANCEL_OPTION);
                if (col_name == JOptionPane.OK_OPTION) {
                    Column column = new Column(name.getText(), role.getText());
                    board.addColumn(column);
                    ColumnGUI col_obj = new ColumnGUI(name.getText(), role.getText());
                    col_n = new DragPane(last);
                    col_outer = new DropPane();;
                    col_n.setLayout(new BoxLayout(col_n, BoxLayout.Y_AXIS));
                    col_n.add(col_obj.generatePanel());
                     JButton delete_col = new JButton("DELETE COLUMN");
                    delete_col.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            deleteCol(delete_col.getParent().getParent());
                        }
                    });
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
            }
        });

        but.add(add_col);
        work_area.add(but, BorderLayout.PAGE_START);
        work_area.add(col_area, BorderLayout.CENTER);
        f.setLayout(lay);
        f.add(topbar, BorderLayout.PAGE_START);
        f.add(activity, BorderLayout.LINE_END);
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

}