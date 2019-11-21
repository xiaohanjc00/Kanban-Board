import javax.swing.*;
import javax.swing.JComponent.*;
import java.awt.*;
import java.awt.event.*;

public  class boardGUI  
{
   static int last = -1; // counter to keep track of the last position for lists
   static JPanel col_n; // JPanel to add new column or list
    public  JPanel generate()
{ 
    JPanel f = new JPanel();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    BorderLayout lay = new BorderLayout();
   
    // JPanel for toolbar showing board name and members
    JPanel topbar = new JPanel();
    topbar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    BoxLayout box_ly = new BoxLayout(topbar, BoxLayout.X_AXIS);
    topbar.setLayout(box_ly);
    
    JLabel board_name = new JLabel("BOARD NAME:  "); 
    topbar.add(board_name);
    topbar.add(Box.createHorizontalGlue());
    JLabel members = new JLabel(" MEMBERS:  ");
    topbar.add(members);
    //topbar.setAlignmentY(Component.TOP_ALIGNMENT);
    
    /*  JPanel showing the Activity Log for Current Board */
    JPanel activity= new JPanel();
    activity.setBorder(BorderFactory.createLineBorder(Color.black));
    GridBagLayout g1 = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    activity.setLayout(g1);
    
    JLabel head = new JLabel("   ACTIVITY LOG    ");
    
     c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor =  GridBagConstraints.NORTHWEST;
     c.gridx = 5;
     c.gridy = 0;
    c.gridwidth = 10;
    c.gridheight = 2;
    c.weightx = 0.01;
    c.weighty = 0.01;   
    activity.add(head,c);

    /* JPanel for Columns */
    
    JPanel work_area = new JPanel();
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
    // column JPanel to be added 
    col_n = new JPanel();
      
    col_n.setBorder(BorderFactory.createLineBorder(Color.black));
    JLabel tt = new JLabel("LIST 1 ");
    col_n.add(tt);
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
