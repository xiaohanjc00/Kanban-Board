import java.util.ArrayList;
public class Column {
    private String name;
    private String role;
    private ArrayList<CardGui> cards;

    /*
    Constructor for making a new column
     */
    public Column(String nameIn, String roleIn){
        name = nameIn;
        role = roleIn;
        cards =  new ArrayList<CardGui>();
    }
    /*
    Return the name of a column
     */
    public String getName(){
        return name;
    }
    /*
    Return the role of the column
     */
    public String getRole(){
        return role;
    }
    /*
    Returns the array list which holds all the cards to be displayed in the column
     */
    public ArrayList<CardGui> getCards(){
        return cards;
    }

    /*
    Change the name of a column
     */
    public void setName(String nameIn){
        name = nameIn;
    }
    /*
    Change the role of a column
     */
    public void setRole(String roleIn){
        role = roleIn;
    }
    /*
    Add cards to a column
     */
    public void addCard(CardGui newCard){
        cards.add(newCard);
    }

    /*
    Remove cards from a column
     */
    public void removeCard(CardGui newCard){
        cards.remove(newCard);
    }
}
