import java.util.ArrayList;
public class Column {
    private String name;
    private String role;
    private ArrayList<Card> cards;

    /*
    Constructor for making a new column
     */
    public Column(String nameIn, String roleIn){
        name = nameIn;
        role = roleIn;
        cards =  new ArrayList<Card>();
    }

    public String getName(){
        return name;
    }

    public String getRole(){
        return role;
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public void setName(String nameIn){
        name = nameIn;
    }

    public void setRole(String roleIn){
        role = roleIn;
    }

    public void addCard(Card c){
        cards.add(c);
    }

    public void removeCard(Card c){
        cards.remove(c);
    }
}
