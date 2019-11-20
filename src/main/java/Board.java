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

public Board(String nameIn){
    name = nameIn;
    history = new HashMap<LocalDateTime, String>();
    columns = new ArrayList<Column>();
    members = new ArrayList<String>();
}

public void addToHistory(LocalDateTime time, String data){
  history.put(time, data);
}

public void setName(String nameIn){
  name = nameIn;
  addToHistory(LocalDateTime.now(), "Changed Board name to" + name);
}

public void addColumn(Column col){
  columns.add(col);
  addToHistory(LocalDateTime.now(), "Added column " + col.getName());
}

public void removeColumn(Column col){
  columns.remove(col);
  addToHistory(LocalDateTime.now(), "Removed column " + col.getName());
}

public void addMember(String member){
  members.add(member);
  addToHistory(LocalDateTime.now(), "Added Member " + member);
}

public void removeMember(String member){
  members.remove(member);
  addToHistory(LocalDateTime.now(), "Removed Member " + member);
}

}
