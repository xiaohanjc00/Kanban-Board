
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Main  {
    static String board_name = "";
    static JFrame first_frame;
public static void main(String[] args)
{
   JPanel first_panel = new JPanel();
    String app_name = "APPLICATION_NAME";
     first_frame = new JFrame();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screen_width = (int)screenSize.getWidth();
    BorderLayout base = new BorderLayout(10,10);
    
    /*header of home page */
    
    JPanel head = new JPanel();
    head.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.black));
    head.setLayout(new BoxLayout(head, BoxLayout.X_AXIS));
    JLabel head_lb = new JLabel(app_name);
    
    /*Home Button*/
    JButton home_b = new JButton(" HOME ");
    home_b.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e){
             first_frame.getContentPane().removeAll();
             first_panel.setLayout(base);
             first_panel.add(head, BorderLayout.PAGE_START);
             first_frame.add(first_panel);
             first_frame.revalidate();
             first_frame.repaint();
             }                
        });
    
    head.add(home_b);
    head.add(Box.createHorizontalGlue());
    head.add(head_lb);
    head.add(Box.createHorizontalGlue());
    
    /*Close Button*/
    
    JButton close_b = new JButton("X Close");
        close_b.addActionListener(new ActionListener()
            {  
             public void actionPerformed(ActionEvent e){  
            System.exit(0);
             }  
    });
    head.add(close_b);
    
    /* Setting the body of the homepage */
     JPanel body = new JPanel();
    body.setLayout(new BoxLayout(body,BoxLayout.Y_AXIS));
    
    /* Adding a basic description of the application */
    JPanel description = new JPanel();
    description.setBorder(BorderFactory.createMatteBorder(0,5,5,5, Color.black));
    JLabel description_label = new JLabel(" ADD BASIC DESCRIPTION ABOUT THE APPLICATION ");
    description.add(description_label);
    description.setMaximumSize(new Dimension(screen_width,400));
    body.add(description);
                
    /* Adding button to create a new Kanban Board */
                          
    JPanel add_board = new JPanel();
    add_board.setBorder(BorderFactory.createMatteBorder(0,5,0,5, Color.black));                  
    JButton add_board_btn = new JButton(" ADD NEW BOARD ");
    add_board_btn.setPreferredSize(new Dimension(600,100));
    add_board_btn.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent e){
             board_name = JOptionPane.showInputDialog(first_frame,
                        "ENTER BOARD NAME:", null);
            if(board_name != null)
            {
             boardGUI new_board = new boardGUI(board_name);
                first_frame.getContentPane().removeAll();
                first_frame.add(head, BorderLayout.PAGE_START);
                first_frame.add(new_board.generate(), BorderLayout.CENTER);
                first_frame.revalidate();
                first_frame.repaint();
            }
        }
    });
    add_board.add(add_board_btn);  
    add_board.setMaximumSize(new Dimension(screen_width, 100));
    body.add(add_board);       
    
    /* Adding a list of current booards already created */
    JPanel list_boards = new JPanel();
    list_boards.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.black));  
    JLabel list_l = new JLabel("CURRENT BOARDS: "+ board_name) ;
    
    list_boards.add(list_l);
    list_boards.setMaximumSize(new Dimension(screen_width,300));
    body.add(list_boards);
    
    //Setting up tHe first page
    
    first_panel.setLayout(base);
    first_panel.add(head, BorderLayout.PAGE_START);
    first_panel.add(body, BorderLayout.CENTER);
    first_frame.add(first_panel);
    first_frame.setSize(screenSize.width, screenSize.height);
    
    first_frame.setVisible(true);
    
}
}

