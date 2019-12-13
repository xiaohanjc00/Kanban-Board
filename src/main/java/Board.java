
/*
 * Kanban board object class
 * Board must contain a title.
 * Board can contain a description, members assigned, a deadline (can be added afterwards)
*/
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.time.*;

public class Board implements Serializable {
  private String name;
  private ArrayList<String> members;
  private ArrayList<Column> columns;
  private int nextId;
  static ActivityLog actLog;

  /*
   * Constructor of Board class.
   * 
   * @param nameIn The title of the Board.
   */

  public Board(String nameIn) {
    name = nameIn;
    columns = new ArrayList<Column>();
    members = new ArrayList<String>();
    nextId = 0;

    // Create an activity log for the board
    try {
      actLog = new ActivityLog(name);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /*
   * Get the log of the Board.
   * 
   * @return Log of the Board in a list of strings.
   */
  public List<String> getLog() {
    return actLog.getActivityLog();
  }

  /*
   * Get the ArrayList of Columns.
   * 
   * @return ArrayList of Columns.
   */
  public ArrayList<Column> getColumnsList() {
    return columns;
  }

  /*
   * Set the title of the Board.
   * 
   * @param nameIn The new title of the Board.
   */
  public void setName(String nameIn) {
    name = nameIn;
  }

  /*
   * Get the title of the Board.
   * 
   * @return The title of the Board.
   */
  public String getName() {
    return name;
  }

  /*
   * Add a column to the Board.
   * 
   * @param col Column object to be added.
   */
  public void addColumn(Column col) {
    columns.add(col);
  }

  /*
   * Remove a column from the Board.
   * 
   * @param col Column object to be removed.
   */
  public void removeColumn(Column col) {
    columns.remove(col);
  }

  /*
   * Get all the columns in the board.
   * 
   * @return An ArrayList of Column objects.
   */
  public ArrayList<Column> getColumns() {
    return columns;
  }

  /*
   * Add a member to the board.
   * 
   * @param col Column object to be added.
   */
  public void addMember(String member) {
    members.add(member);
  }

  public void removeMember(String member) {
    members.remove(member);
  }

  public ArrayList<String> getMembers() {
    return members;
  }

  /*
   * Get all the ID of the next Card.
   * Each card in the board has unique IDs,
   * therefore the board decides the ID of cards.
   * 
   * @return The ID of the next card
   */
  public int getId() {
    return nextId;
  }

  /*
   * Increments the ID of the next Card.
   */
  public void incrementId() {
    nextId++;
  }

  public void clearColumns(){
    columns.clear();
  }

}
