import static org.junit.Assert.assertEquals;
import org.junit.Test;



import java.util.ArrayList;

public class BoardTest{
  //Tests the constructor of a board, using the getName method to make sure board has been created.
  @Test
  public void boardCreationTest() {
    String title = "My first board";
    Board newBoard = new Board(title);
    assertEquals(title, newBoard.getName());
  }
  //Tests changing the name of a board 
  @Test
  public void boardEditTest() {
    //Creation of a board
    String title = "My first board";
    Board newBoard = new Board(title);
    title = "Edited";
    //Change the name of the board
    newBoard.setName(title);
    //Check that the board name is equal to the string we changed it to 
    assertEquals(title, newBoard.getName());
  }
  //Tests adding columns to a board
  @Test
  public void addColumnTest() {
    //Board creation
    String title = "My first board";
    Board newBoard = new Board(title);
    boardGUI boardGui = new boardGUI(title, newBoard);
    ColumnGUI columnGui = new ColumnGUI("name", "role", boardGui);
    //Creation of column and column array for expected result
    Column column = new Column("col 1", "role", columnGui);
    ArrayList<Column> colList = new ArrayList<Column>();
    //Adding column to the board and to the expected array
    colList.add(column);
    newBoard.addColumn(column);
    //Checking that the board columns is the same as the created array for expected result
    assertEquals(newBoard.getColumnsList(), colList);
  }
  //Tests removing columns from a board
  @Test
  public void removeColumnTest() {
    //Creation of a board and column
    String title = "My first board";
    Board newBoard = new Board(title);
    boardGUI boardGui = new boardGUI(title, newBoard);
    ColumnGUI columnGui = new ColumnGUI("name", "role", boardGui);
    Column column = new Column("col 1", "role", columnGui);
    //Creating column array for expected result
    ArrayList<Column> colList = new ArrayList<Column>();
    //Adding 2 columns to the board
    newBoard.addColumn(column);
    Column column2 = new Column("col 2", "rooooole", columnGui);
    newBoard.addColumn(column2);
    //Removing the original column
    newBoard.removeColumn(column);
    //Adding the column that still exists within the board inside the column array
    colList.add(column2);
    //Asserting that the board array is equal to the array with the expected result. 
    assertEquals(newBoard.getColumnsList(), colList);
  }
  //Checks that board ID increments when a card is added to a column in the board. 
  @Test
  public void boardIDTest(){
    //Board and column creation
    String title = "My first board";
    Board newBoard = new Board(title);
    boardGUI boardGui = new boardGUI(title, newBoard);
    ColumnGUI columnGui = new ColumnGUI("name", "role", boardGui);
    Column column = new Column("col 1", "role", columnGui);
    //Adding column and card to the board
    newBoard.addColumn(column);
    column.addCard("S", "M");
    //Checking that the board Id is successfully incremented once a card is added
    assertEquals(1, newBoard.getId());
    //Adding another card to make sure board Id is still incremented when a 2nd card is added
    column.addCard("S2", "M2");
    //Checking that the board Id is successfully incremented once the new card is added. 
    assertEquals(2, newBoard.getId());
  }
}
