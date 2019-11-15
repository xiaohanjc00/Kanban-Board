

import java.time.*;
import java.util.ArrayList;

/*
* Card object class
* Cards must contain a title, creator
* Cards can contain a description, members assigned, a deadline
*
*/
public class Card{

private String title;
private String description;
//to be changed from String to member/user object once class has been implemented
private final String creator;
private ArrayList<String> members;
//private Label label;
private LocalDateTime deadline;
private LocalDateTime creationDate;

/*
*Create a new Card object
*Requires a creator and a Title
*/
public Card(String creatorIn, String titleIn){
  creator = creatorIn;
  title = titleIn;
  creationDate = LocalDateTime.now();
}

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ArrayList<String> getMembers() {
    return members;
  }

  public void addMember(String newMember) {
    this.members.add(newMember);
  }


 public void removeMember(String member) {
   this.members.remove(member);
  }

  public LocalDateTime getDeadline() {
    return deadline;
  }

  //this method assumes the GUI checks that the date inputted has not alread passed
  public void setDeadline(LocalDateTime deadline) {
    this.deadline = deadline;
  }
  public String getCreator(){
    return creator;
  }
}
