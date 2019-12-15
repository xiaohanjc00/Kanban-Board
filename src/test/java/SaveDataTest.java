
import static org.junit.Assert.*;
import org.junit.Test;

public class SaveDataTest {
    @Test
    public void SaveData() {
        Board board = new Board("TestBoard");
        BoardGUI boardGUI = new BoardGUI("Testing", board);

        String title = "My first card";
        Card newCard = new Card("", title, 0);

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
        Column newColumn1 = new Column(col1Title, col1Role, columnGUI);
        newColumn1.addCard("", title1);

        board.addColumn(newColumn);
        board.addColumn(newColumn1);

        SaveData save = new SaveData("TestBoard", board);

        // Try to read the saved file.
        try {
            CSVReader reader = new CSVReader("TestBoardDATA.csv", "data");
            String result = reader.CSVAsString();
            assertEquals("boardName, TestBoard\n\ncolumnName, To-Do , Backlog\ncardName, My first card, 0, 0, null, \nColumnEnd \n\ncolumnName, Doing , Backlog\ncardName, My second card, 1, 0, null, \nColumnEnd \n", result);
        } catch (Exception e) {}

    }
  
  
}