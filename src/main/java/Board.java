/*
* Kanban board object class
* board must contain a title
* board can contain a description, members assigned, a deadline
*
*/
import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Board{
  private String name;
  private ArrayList<String> members;
  private ArrayList<Column> columns;
  private HashMap<LocalDateTime, String> history;
  private int nextId;

  public Board(String nameIn){
      name = nameIn;
      history = new HashMap<LocalDateTime, String>();
      columns = new ArrayList<Column>();
      members = new ArrayList<String>();
      nextId = 0;
  }

  public void addToHistory(String data){
    history.put(LocalDateTime.now(), data);
  }

  public HashMap<LocalDateTime, String> getLog(){
    return history;
  }

  public void setName(String nameIn){
    name = nameIn;
    addToHistory("Changed Board name to" + name);
  }

  public String getName(){
    return name;
  }

  public void addColumn(Column col){
    columns.add(col);
    addToHistory("Added column " + col.getName());
  }

  public void removeColumn(Column col){
    columns.remove(col);
    addToHistory("Removed column " + col.getName());
  }

  public ArrayList<Column> getColumns(){
    return columns;
  }

  public void addMember(String member){
    members.add(member);
    addToHistory("Added Member " + member);
  }

  public void removeMember(String member){
    members.remove(member);
    addToHistory("Removed Member " + member);
  }

  public ArrayList<String> getMembers(){
    return members;
  }

  public int getId() {
    return nextId;
  }

  public void incrementId() {
    nextId++;
  }

}
