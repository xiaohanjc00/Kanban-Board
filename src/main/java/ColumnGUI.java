/**
* Represents the Column GUI for every column.
*/

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class ColumnGUI {

    private String name;
    private Column column;
    private JPanel panel;
    private JPanel card_in_col;
    private String role;
    
    public ColumnGUI(String name, String role) {
        this.name = name;
        this.role = role;
        column = new Column(name, role);   
    }

    /**
    * method to generate a JPanel that represents a column in a board.
    */
    
    public JPanel generatePanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        addTitle();
        loadCards();
        addButton();
        return panel;
    }

    /**
    * method to add the name, and role to a column.
    */
    
   public void addTitle()
   {
       JPanel top = new JPanel();
       top.setLayout(new BoxLayout(top,BoxLayout.X_AXIS));
       JLabel nameLabel = new JLabel("NAME: " + name);
        top.add(nameLabel);
       top.add(Box.createHorizontalGlue());
       JLabel roleLabel = new JLabel("ROLE: "+ role);
       top.add(roleLabel);
       panel.add(top);
   }
    
    /**
    * Add Button to create cards
    */

    public void addButton() {
         JButton add_card = new JButton("ADD NEW CARD");
        add_card.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
               String card_name =  JOptionPane.showInputDialog(Main.first_frame,
                        "ENTER CARD TITLE:", null);
                addCard("", card_name);
            }
        });
        panel.add(add_card);
    }
    
    /*
    * Load all cards into a column
    */
    public void loadCards(){
        
        String members ="";
        ArrayList<Card> arrayOfCards = column.getCards();
        
        for(int i = 0; i<arrayOfCards.size(); i++)
        {
            final Card current_card = arrayOfCards.get(i);
            card_in_col = new JPanel();
            card_in_col.setLayout(new BorderLayout());
            JLabel card_title = new JLabel(arrayOfCards.get(i).getTitle());
            
             
            for(String member : arrayOfCards.get(i).getMembers())
            {
                members  = members + member + " ";
            }
            
            JLabel card_members = new JLabel(members);
            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
            JButton delete_card = new JButton("Delete");
            delete_card.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete card " + current_card.getTitle() + "?", "Confirm Deletion",
				    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                    System.out.println(response);
                    if (response == 0) {
                        column.removeCard(current_card);
                        deleteCard(delete_card.getParent());
                    }
                }
            });
            JButton view_card = new JButton("View");
            view_card.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
             CardGUI open_card = new CardGUI(current_card);
              }
            });
            buttonsPanel.add(delete_card);
            buttonsPanel.add(view_card);
            card_in_col.add(card_title, BorderLayout.PAGE_START);
            card_in_col.add(card_members, BorderLayout.CENTER);
            card_in_col.add(buttonsPanel, BorderLayout.LINE_END);
            card_in_col.setBorder(BorderFactory.createLineBorder(Color.black));
            panel.add(card_in_col);
        }
    }
    /*
    Create new cards for the column
    */
    public void addCard(String creator, String cardName){
        column.addCard(creator, cardName);
        panel.removeAll();
        addTitle();
        loadCards();
        addButton();
        panel.revalidate();
    }
    /*
    Delete cards from the column
    */
    public void deleteCard(Container toDelete){
        panel.removeAll();
        addTitle();
        loadCards();
        addButton();
        panel.revalidate();
    }
}