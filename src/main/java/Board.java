
/*
* Kanban board object class
* board must contain a title
* board can contain a description, members assigned, a deadline
*
*/
import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.*;

public class Board implements Serializable{
  private String name;
  private ArrayList<String> members;
  private ArrayList<Column> columns;
  private int nextId;
  static ActivityLog actLog;

public Board(String nameIn){
    name = nameIn;
    columns = new ArrayList<Column>();
    members = new ArrayList<String>();
    nextId = 0;

    /*Board Log*/
    try{
      actLog = new ActivityLog(name + "ActivityLog.csv");
    } 
    catch (IOException e) {
        e.printStackTrace();
    }
}

  public List<String> getLog(){
    return actLog.getActivityLog();
  }

  public ArrayList<Column> getColumnsList(){
    return columns;
  }

  public void setName(String nameIn){
    name = nameIn;
  }

  public String getName(){
    return name;
  }

  public void addColumn(Column col){
    columns.add(col);
  }

  public void removeColumn(Column col){
    columns.remove(col);
  }

  public ArrayList<Column> getColumns(){
    return columns;
  }

  public void addMember(String member){
    members.add(member);
  }

  public void removeMember(String member){
    members.remove(member);
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
