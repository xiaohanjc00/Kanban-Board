import java.util.ArrayList;

public class Column {

    private String name;
    private String role;
    private ArrayList<Card> cards;
    private ColumnGUI columnGui;

    /*
     * Constructor for making a new column
     */
    public Column(String nameIn, String roleIn, ColumnGUI columnGuiIn){
        name = nameIn;
        role = roleIn;
        cards =  new ArrayList<Card>();
        columnGui = columnGuiIn;
    }

    public ArrayList<Card> getCardsList(){
        return cards;
    }

    /*
     * Get the name of the column.
     * @return The name of a column
     */
    public String getName(){
        return name;
    }

    /*
     * Get the role of the column.
     * @return The role of the column
     */
    public String getRole(){
        return role;
    }

    /*
     * Get the ArrayList of cards.
     * @return the array list which holds all the cards to be displayed in the column
     */
    public ArrayList<Card> getCards(){
        return cards;
    }

    /*
     * Change the name of a column
     * @param nameIn Name of the column
     */
    public void setName(String nameIn){
        name = nameIn;
    }
    /*
     * Change the role of a column
     * @param roleIn Role of the column
     */
    public void setRole(String roleIn){
        role = roleIn;
    }
    /*
     * Add cards to a column
     * @param creator Creator of the card
     * @param cardName Title of the card
     */
    public void addCard(String creator, String cardName){
        Card newCard = new Card(creator, cardName, columnGui.getBoardGui().getBoard().getId());
        cards.add(newCard);
        columnGui.getBoardGui().getBoard().incrementId();
    }

    /*
     * Remove cards from a column
     * @param newCard Card to be removed
     */
    public void removeCard(Card newCard){
        cards.remove(newCard);
    }

    /*
     * Move card to another given column
     * @param card Card to be moved
     * @param newColumn Column to be moved to
     */
    public void moveCard(Card card, Column newColumn) {
        cards.remove(card);
        newColumn.getCards().add(card);
        columnGui.refreshColumn();
        newColumn.columnGui.refreshColumn();
    }
}
