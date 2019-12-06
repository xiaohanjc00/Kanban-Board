
import java.time.*;
import java.util.ArrayList;

/*
* Card object class
* Cards must contain a title, creator
* Cards can contain a description, members assigned, a deadline
*
*/
public class Card {

    private String title;
    private String description;
    // to be changed from String to member/user object once class has been
    // implemented
    private final String creator;
    private int id;
    private ArrayList<String> members;
    private LocalDateTime deadline;
    private LocalDateTime creationDate;
    private int storyPoint;
    private Column column;

    /*
     * Create a new Card object Requires a creator and a Title
     */
    public Card(String creatorIn, String titleIn, int idIn) {
        creator = creatorIn;
        title = titleIn;
        id = idIn;
        members = new ArrayList<String>();
        creationDate = LocalDateTime.now();
    }

    /*
     * Get the title of the card.
     * @return The title of a card
     */
    public String getTitle() {
        return title;
    }

    /*
     * Set the title of the card.
     * @param title The title of the card
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /*
     * Get the description of the card.
     * @return The description of the card.
     */
    public String getDescription() {
        return description;
    }

    /*
     * Set the description of the card.
     * @param description The description of the card.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /*
     * Get the ID of the card.
     * @return The ID of the card.
     */
    public String getId() {
        return String.valueOf(id);
    }

    /*
     * Get the ArrayList of members.
     * @return the ArrayList of members assigned in the card
     */
    public ArrayList<String> getMembers() {
        return members;
    }

    /*
     * Get the ArrayList of members.
     * @return the ArrayList of members assigned in the card
     */
    public void addMember(String newMember) {
        this.members.add(newMember);
    }

    public void removeMember(String member) {
        this.members.remove(member);
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    // this method assumes the GUI checks that the date inputted has not alread
    // passed
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public String getCreator() {
        return creator;
    }

    /*
     * Set the story point of a card.
     * @param point The story point of a card
     */
    public void setStoryPoint(int point) {
        this.storyPoint = point;
    }

    /*
     * Get the story point of a card.
     * @return The story point of a card
     */
    public int getStoryPoint() {
        return storyPoint;
    }

}
