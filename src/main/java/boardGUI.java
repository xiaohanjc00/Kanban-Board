import javax.swing.*;
import javax.swing.JComponent.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.util.HashMap;
import java.io.IOException;

public  class boardGUI
{
  static ActivityLog actLog;
   static int last = -1; // counter to keep track of the last position for lists
   static JPanel col_n; // JPanel to add new column or list
    String b_name ;
    String col_name;
    Board board; //
    public boardGUI(String name, Board b)
    {
        b_name = name;
        board = b;

        /*Board Log*/
        try {
            actLog = new ActivityLog(b_name + "ActivityLog.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  JPanel generate()
{
    //add an input before the creation in the Homepage that would give us the name of the board
    //board = new Board(b_name);
    //BoardList boardlist = new BoardList();
    //boardlist.addBoard(board);
    JPanel f = new JPanel();
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
    // topbar.add(members);

    for(String mem : board.getMembers()){
      JLabel thismember = new JLabel(mem);
      topbar.add(thismember);
    }
    //topbar.setAlignmentY(Component.TOP_ALIGNMENT);

    /*  JPanel showing the Activity Log for Current Board */
    JPanel activity= new JPanel();
    activity.setBorder(BorderFactory.createLineBorder(Color.black));
    GridBagLayout g1 = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    activity.setLayout(g1);

    JLabel head = new JLabel("   ACTIVITY LOG    ");
      head.setText(actLog.getActivityLogAsString());

     c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor =  GridBagConstraints.NORTHWEST;
     c.gridx = 1;
     c.gridy = 0;
    c.gridwidth = 10;
    c.gridheight = 2;
    c.weightx = 0.01;
    c.weighty = 0.01;
    activity.add(head,c);


    int i=1;
    for(Object key : board.getLog().keySet()){
      c.gridy = i;
      JLabel thing = new JLabel(board.getLog().get(key));
      activity.add(thing, c);
      i++;
    }

    /* JPanel for Columns */    JPanel work_area = new JPanel();
    work_area.setLayout(new BorderLayout());

    JPanel col_area = new JPanel();

    GridBagLayout g2 = new GridBagLayout();

    GridBagConstraints c3 = new GridBagConstraints();
    col_area.setLayout(g2);
    col_area.setBorder(BorderFactory.createLineBorder(Color.black));

    JPanel but = new JPanel();
    but.setBorder(BorderFactory.createLineBorder(Color.black));

    // Adding button to create list

     JButton add_col = new JButton(" Create a new List ");
    add_col.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent e) {
        last++;
        col_name = JOptionPane.showInputDialog(Main.first_frame,
                        "ENTER COLUMN NAME:", null);
    Column column = new Column(col_name, "TBD Column role");
    Main.log.createColumnLog(col_name, board.getName());
    board.addColumn(column);
    ColumnGUI col_obj= new ColumnGUI(col_name);
    col_n = col_obj.generatePanel();
    col_n.setBorder(BorderFactory.createLineBorder(Color.black));
    c3.anchor =  GridBagConstraints.NORTHWEST;
    c3.gridx = last;
    c3.gridy = 0;
     c3.weightx = 0.01;
    c3.weighty = 0.01;
    col_area.add(col_n,c3);
    f.revalidate();
    }
  });

    but.add(add_col);
    work_area.add(but,BorderLayout.PAGE_START);
    work_area.add(col_area, BorderLayout.CENTER);



    f.setLayout(lay);
    f.add(topbar, BorderLayout.PAGE_START);
    f.add(activity, BorderLayout.LINE_END);
    f.add(work_area, BorderLayout.CENTER);
    //f.setSize(screenSize.width, screenSize.height);
    //f.setVisible(true);

    return (f);
}





}
