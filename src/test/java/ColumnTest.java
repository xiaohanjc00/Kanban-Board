import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.ArrayList;

public class ColumnTest{
    @Test
    //Basic Column Creation
    public void ColumnCreationTest() {
        String name = "To Do";
        String role = "Backlog";
        Board board = new Board("board");
        BoardGUI bGUI = new BoardGUI("Testing", board);
        ColumnGUI columnGUI = new ColumnGUI(name, role, bGUI);
        Column newColumn = new Column(name, role, columnGUI);
        assertEquals(name, newColumn.getName());
        assertEquals(role, newColumn.getRole());
        assertEquals(0, newColumn.getCards().size());
    }

    @Test
    //Tests that changing the name of a column does what is expected
    public void ColumnChangeNameTest(){
        String name = "To Do";
        String role = "Backlog";
        Board board = new Board("board");
        BoardGUI bGUI = new BoardGUI("Testing", board);
        ColumnGUI columnGUI = new ColumnGUI(name, role, bGUI);
        Column newColumn = new Column(name, role, columnGUI);
        //Change Name
        String newName = "To Do 2";
        newColumn.setName(newName);
        //Test that new name is equal to column name
        assertEquals(newName, newColumn.getName());
    }

    @Test
    //Tests that changing the role of a column does what is expected
    public void ColumnChangeRoleTest(){
        String name = "To Do";
        String role = "Backlog";
        Board board = new Board("board");
        BoardGUI bGUI = new BoardGUI("Testing", board);
        ColumnGUI columnGUI = new ColumnGUI(name, role, bGUI);
        Column newColumn = new Column(name, role, columnGUI);
        //Change role
        String newRole = "Testing";
        newColumn.setRole(newRole);
        //Test that new role is equal to column role
        assertEquals(newRole, newColumn.getRole());
    }

    @Test
    //Tests that adding a card to a column successfully adds the card
    public void AddCardTest(){
        String name = "To Do";
        String role = "Backlog";
        Board board = new Board("board");
        BoardGUI bGUI = new BoardGUI("Testing", board);
        ColumnGUI columnGUI = new ColumnGUI(name, role, bGUI);
        Column newColumn = new Column(name, role, columnGUI);
        newColumn.addCard("jK", "card1");
        //Asserts that the correct card has been added in first position in array list.
        assertEquals("jK", newColumn.getCards().get(0).getCreator());
        assertEquals("card1", newColumn.getCards().get(0).getTitle());
        assertEquals(0, newColumn.getCards().get(0).getId());
        //Asserts that only 1 card has been added.
        assertEquals(1, newColumn.getCards().size());
    }

    @Test
    //Test to make sure that cards can be removed from a column
    public void RemoveCardTest(){
        String name = "To Do";
        String role = "Backlog";
        Board board = new Board("board");
        BoardGUI bGUI = new BoardGUI("Testing", board);
        ColumnGUI columnGUI = new ColumnGUI(name, role, bGUI);
        Column newColumn = new Column(name, role, columnGUI);

        Card c = new Card("James", "Do This", 0);
        Card c2 = new Card("John", "Important", 1);
        //Add 2 cards to a column
        newColumn.addCard("James", "Do This");
        newColumn.addCard("John", "Important");
        //Remove 2nd card
        newColumn.removeCard(newColumn.getCards().get(0));
        ArrayList<Card> shouldBe = new ArrayList<Card>();
        shouldBe.add(c);
        //Check that column only has 1 card
        assertEquals(1, newColumn.getCards().size());
    }
    
    @Test
    //Test a column can handle a sizeable amount of cards
    public void ColumnSizeTest(){
        String name = "To Do";
        String role = "Backlog";
        Board board = new Board("board");
        BoardGUI bGUI = new BoardGUI("Testing", board);
        ColumnGUI columnGUI = new ColumnGUI(name, role, bGUI);
        Column newColumn = new Column(name, role, columnGUI);

        ArrayList<Card> shouldBe = new ArrayList<Card>();
        for(int i = 0; i < 100; i++){
            
        
        //Check that adding a card and returning the list of cards returns an ArrayList with the card added
        Card c = new Card("James", "Do This", i);
        shouldBe.add(c);
        newColumn.addCard("James", "Do This");
        assertEquals("James", newColumn.getCards().get(i).getCreator());
        assertEquals("Do This", newColumn.getCards().get(i).getTitle());
        assertEquals(i, newColumn.getCards().get(i).getId());
        }
        assertEquals(shouldBe.size(), newColumn.getCards().size());
    }

}