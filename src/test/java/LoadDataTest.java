import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class LoadDataTest {
    @Test
    public void SaveData() {
        Board board = new Board("TestBoard");
        BoardGUI boardGUI = new BoardGUI("Testing", board);

        String title = "My first card";
        Card newCard = new Card("", title, 0);
        newCard.setStoryPoint(3);
        newCard.setDescription("Card description");

        String colTitle = "To-Do";
        String colRole = "Backlog";
        ColumnGUI columnGUI = new ColumnGUI(colTitle, colRole, boardGUI);
        Column newColumn = new Column(colTitle, colRole, columnGUI);
        newColumn.addCard("", title);

        String title1 = "My second card";
        Card newCard1 = new Card("", title1, 1);

        String col1Title = "Doing";
        String col1Role = "Backlog";
        ColumnGUI column1GUI = new ColumnGUI(col1Title, col1Role, boardGUI);
        Column newColumn1 = new Column(col1Title, col1Role, column1GUI);
        newColumn1.addCard("", title1);

        board.addColumn(newColumn);
        board.addColumn(newColumn1);

        SaveData save = new SaveData("TestBoard", board);

        // Try to load the file.
        LoadData load = new LoadData("TestBoard");

        try {
            // Check the name of board.
            assertEquals("TestBoard", load.getBoardName());

            // Check the content of board
            assertEquals("[[To-Do , Backlog, [My first card, 0, 0, null]], [Doing , Backlog, [My second card, 1, 0, null]]]", load.getInformation().toString());

            // Check if the board is empty
            assertFalse(load.isEmpty());
        } catch (Exception e) {}
        

        ArrayList<Object> colArr = new ArrayList<>();
        colArr.add(colTitle);
        colArr.add(colRole);
        colArr.add(newCard);
        // Check the name of column
        assertEquals("To-Do", load.getColumnName(colArr));

        ArrayList<String> cardArr = new ArrayList<>();
        cardArr.add(newCard.getTitle());
        cardArr.add(Integer.toString(newCard.getId()));
        cardArr.add(Integer.toString(newCard.getStoryPoint()));
        cardArr.add(newCard.getDescription());
        
        // Check the card details
        assertEquals("My first card", load.getCardName(cardArr));
        assertEquals(0, load.getCardID(cardArr));
        assertEquals(3, load.getCardStoryPoints(cardArr));
        assertEquals("Card description", load.getCardDescription(cardArr));
    }
  
  
}