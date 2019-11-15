import javax.swing.*;
import java.awt.*;
import java.util.*;

public class columnGui {

    private ArrayList<Card> cards;
    private String name;

    public columnGui(ArrayList<Cards> cards, String name) {
        this.cards = cards;
        this.name = name;
    }

    public JPanel generatePanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel nameLabel = new JLabel(name);
        panel.add(nameLabel);
        
        for (int i = 0; i < cards.size(); i++) {
            JButton btn = new JButton(cards[i].getTitle());
            panel.add(btn);

        }

        return panel;
    }
}