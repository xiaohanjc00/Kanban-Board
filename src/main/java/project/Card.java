package project;

import java.util.Date;
import java.util.List;

public class Card{

private String title;
private String description;


//to be changed from String to member/user object once class has been implemented
private final String creator;
private List<String> members;
//private Label label;


private Date deadline;

public Card(String creatorIn, String titleIn){
  creator = creatorIn;
  title = titleIn;
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

  public List<String> getMembers() {
    return members;
  }

  public void addMember(String newMember) {
    this.members.add(newMember);
  }

  public void removeMember(String member) {
  this.members.remove(member);
  }

  public Date getDeadline() {
    return deadline;
  }

  public void setDeadline(Date deadline) {
    this.deadline = deadline;
  }
}
