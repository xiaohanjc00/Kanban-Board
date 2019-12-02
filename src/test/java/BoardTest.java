import static org.junit.Assert.assertEquals;
import org.junit.Test;


import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.ArrayList;

public class BoardTest{

  @Test
  public void boardCreationTest() {
    String title = "My first board";
    Board newBoard = new Board(title);
    assertEquals(title, newBoard.getName());
  }

  @Test
  public void boardEditTest() {
    String title = "My first board";
    Board newBoard = new Board(title);
    title = "Edited";
    newBoard.setName(title);
    assertEquals(title, newBoard.getName());
  }

  @Test
  public void addColumnTest() {
    String title = "My first board";
    Board newBoard = new Board(title);
    Column column = new Column("col 1", "role");
    ArrayList<Column> colList = new ArrayList<Column>();
    colList.add(column);
    newBoard.addColumn(column);
    assertEquals(newBoard.getColumns(), colList);
  }
  
  @Test
  public void removeColumnTest() {
    String title = "My first board";
    Board newBoard = new Board(title);
    Column column = new Column("col 1", "role");
    ArrayList<Column> colList = new ArrayList<Column>();
    newBoard.addColumn(column);
    Column column2 = new Column("col 2", "rooooole");
    newBoard.addColumn(column2);
    newBoard.removeColumn(column);
    colList.add(column2);
    assertEquals(newBoard.getColumns(), colList);
  }

}
