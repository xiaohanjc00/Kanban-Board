import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ColumnGui {

    private String name;
    private Column column;
    private JPanel panel;
   
    public ColumnGui(String name) {
        this.name = name;
        column = new Column(name,name);
        generatePanel();
    }

    public JPanel generatePanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel nameLabel = new JLabel(name);
        panel.add(nameLabel);
        
        createCards(panel);

        return panel;
    }

    /*
    Create Buttons for each card
    */
    public void createCards(JPanel panel){
        ArrayList<CardGui> arrayOfCards = column.getCards();
        for (int i = 0; i < column.getCards().size(); i++) {
            CardGui newCard = arrayOfCards.get(i);
            panel.add(newCard);
        }
    }
    /*
    Create new cards for the column
    */
    public void addCard(CardGui newCard){
        column.addCard(newCard);
        panel.add(newCard);
    }

    /*
    Delete cards from the column
    */
    public void deleteCard(String cardName){
    }

    /*
    Edit cards from the column
    */
    public void editCard(String cardName){
    }
}