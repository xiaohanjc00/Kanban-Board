import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class ColumnGUI {

    private String name;
    private Column column;
    private JPanel panel;
   
    public ColumnGUI(String name) {
        this.name = name;
        column = new Column(name,name);
        
    }

    public JPanel generatePanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        addTitle();
        loadCards();
        addButton();
        return panel;
    }

   public void addTitle()
   {
       JLabel nameLabel = new JLabel(name);
        panel.add(nameLabel);
   }
    
    /*
     Add Button to create cards
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
     Load all cards into a column
    */
    public void loadCards(){
        JPanel card_in_col;
        
        String members ="";
        ArrayList<Card> arrayOfCards = column.getCards();
        
        for(int i = 0; i<arrayOfCards.size(); i++)
        {
            final Card current_card = arrayOfCards.get(i);
            card_in_col = new JPanel();
            card_in_col.setLayout(new BorderLayout());
            JLabel card_title = new JLabel(arrayOfCards.get(i).getTitle());
            
             
            for(String member:arrayOfCards.get(i).getMembers() )
            {
                members  = members + member + " ";
            }
            
            JLabel card_members = new JLabel(members);
            JButton view_card = new JButton("View");
            view_card.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
             CardGUI open_card = new CardGUI(current_card);
              }
            });
            card_in_col.add(card_title, BorderLayout.PAGE_START);
            card_in_col.add(card_members, BorderLayout.CENTER);
            card_in_col.add(view_card, BorderLayout.LINE_END);
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
    public void deleteCard(String cardName){
     
    }

   
}