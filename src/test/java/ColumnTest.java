import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.ArrayList;

public class ColumnTest{
    @Test
    //Basic Column Creation
    public void ColumnCreationTest() {
        String name = "To Do";
        String role = "Backlog";
        Column newColumn = new Column(name, role);
        assertEquals(name, newColumn.getName());
        assertEquals(role, newColumn.getRole());
        assertEquals(0, newColumn.getCards().size());
    }
    @Test
    //Tests that changing the name of a column does what is expected
    public void ColumnChangeNameTest(){
        String name = "To Do";
        String role = "Backlog";
        Column newColumn = new Column(name, role);
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
        Column newColumn = new Column(name, role);
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
        Column newColumn = new Column(name, role);
        Card c = new Card("James", "Do This");
        newColumn.addCard(c);
        //Asserts that the correct card has been added in first position in array list.
        assertEquals(c, newColumn.getCards().get(0));
        //Asserts that only 1 card has been added.
        assertEquals(1, newColumn.getCards().size());
    }

    @Test
    //Test to make sure that cards can be removed from a column
    public void RemoveCardTest(){
        String name = "To Do";
        String role = "Backlog";
        Column newColumn = new Column(name, role);
        Card c = new Card("James", "Do This");
        Card c2 = new Card("John", "Important");
        //Add 2 cards to a column
        newColumn.addCard(c);
        newColumn.addCard(c2);
        //Remove 2nd card
        newColumn.removeCard(c2);
        ArrayList<Card> shouldBe = new ArrayList<Card>();
        shouldBe.add(c);
        //Check that column only has 1 card and the correct card
        assertEquals(1, newColumn.getCards().size());
        assertEquals(shouldBe, newColumn.getCards());
    }
    @Test
    //Test that functionality for creation of cards ArrayList and adding and removing works.
    public void ColumnCardsTest(){
        String name = "To Do";
        String role = "Backlog";
        Column newColumn = new Column(name, role);
        ArrayList<Card> shouldBe = new ArrayList<Card>();
        //Check that upon creation of a column cards should be empty
        assertEquals(shouldBe, newColumn.getCards());
        //Check that adding a card and returning the list of cards returns an ArrayList with the card added
        Card c = new Card("James", "Do This");
        shouldBe.add(c);
        newColumn.addCard(c);
        assertEquals(shouldBe, newColumn);
        //Check that removing the card and returning the list of cards returns an ArrayList with the card removed (empty)
        newColumn.removeCard(c);
        shouldBe.remove(c);
        assertEquals(shouldBe, newColumn);
    }

}