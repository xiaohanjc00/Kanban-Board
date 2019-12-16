import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;



import java.io.IOException;

/*
Due to Activity Log using current time to return strings, it was not possible to assert strings
came out as expected as time would change instantly, therefore assertNotNull was used to ensure the
text was returned, if the text was returned - due to the nature of the functions inside activityLog 
it can be assumed that the function works correctly. 
*/


/*Tests constructor by creating an activity log and checking it is created by seeing if it returns
 an empty string for the contents of the activity log. 
*/
public class ActivityLogTest{
    @Test
    public void activityLogCreationTest(){  
     ActivityLog actLog;
     try {
         actLog = new ActivityLog("Test File");
        } catch (IOException e){
            actLog = null;
        }
     assertEquals("", actLog.getActivityLogAsString());
     assertNotNull(actLog.getActivityLog());
    }
    //Checks that an output is created when a column is created 
    @Test
    public void createColumnLogTest(){
        String fileName = "New File";   
        ActivityLog actLog;
        try {
            actLog = new ActivityLog(fileName);
           } catch (IOException e){
               actLog = null;
           }
        String log = actLog.createColumnLog("column", "board");
        assertNotNull(log);
    }
    //Tests that an output is created when a column is edited
    @Test
    public void editColumnLogTest(){
        String fileName = "New File";   
        ActivityLog actLog;
        try {
            actLog = new ActivityLog(fileName);
           } catch (IOException e){
               actLog = null;
           }
        String log = actLog.editColumnLog("newColumn", "oldColumn", "newRole", "oldRole");
        assertNotNull(log);
    }
    //Tests that an output is created once a column is deleted
    @Test
    public void deleteColumnLogTest(){
        String fileName = "New File";   
        ActivityLog actLog;
        try {
            actLog = new ActivityLog(fileName);
           } catch (IOException e){
               actLog = null;
           }
        String log = actLog.deleteColumnLog("column", "board");
        assertNotNull(log);
    }
    //Tests that an output is created, when a card is created. 
    @Test
    public void createCardLogTest(){
        String fileName = "New File";   
        ActivityLog actLog;
        try {
            actLog = new ActivityLog(fileName);
           } catch (IOException e){
               actLog = null;
           }
        String log = actLog.createCardLog("card", "column");
        assertNotNull(log);
    }
    //Tests that an output is created, when a card is deleted
    @Test
    public void deleteCardLogTest(){
        String fileName = "New File";   
        ActivityLog actLog;
        try {
            actLog = new ActivityLog(fileName);
           } catch (IOException e){
               actLog = null;
           }
        String log = actLog.deleteCardLog("card", "column");
        assertNotNull(log);
    }

    //Tests that an output is created, when a card is edited. 
    @Test
    public void editCardLogTest(){
        String fileName = "New File";   
        ActivityLog actLog;
        try {
            actLog = new ActivityLog(fileName);
           } catch (IOException e){
               actLog = null;
           }
        Card c = new Card("creator", "title", 0);
        String log = actLog.editCardTitleLog("oldName", c);
        assertNotNull(log);
    }

    //Tests that an output is created, when a card description is edited. 
    @Test
    public void editCardDescriptionLogTest(){
        String fileName = "New File";   
        ActivityLog actLog;
        try {
            actLog = new ActivityLog(fileName);
           } catch (IOException e){
               actLog = null;
           }
        Card c = new Card("creator", "title", 0);
        String log = actLog.setDescriptionLog(c);
        assertNotNull(log);
    }

    //Tests that an output is created, when setting a story point of a card. 
    @Test
    public void setStoryPointLogTest(){
        String fileName = "New File";   
        ActivityLog actLog;
        try {
            actLog = new ActivityLog(fileName);
           } catch (IOException e){
               actLog = null;
           }
        Card c = new Card("creator", "title", 0);
        String log = actLog.setStoryPointLog(c);
        assertNotNull(log);
    }

    //Tests that an output is created, when saving. 
    @Test
    public void saveButtonLogTest(){
        String fileName = "New File";   
        ActivityLog actLog;
        try {
            actLog = new ActivityLog(fileName);
           } catch (IOException e){
               actLog = null;
           }
        String log = actLog.saveButtonLog("board");
        assertNotNull(log);
    }

    //Tests that an output is created, when a card is moved to a different column.
    @Test
    public void moveCardLogTest(){
        String fileName = "New File";   
        ActivityLog actLog;
        try {
            actLog = new ActivityLog(fileName);
           } catch (IOException e){
               actLog = null;
           }
        Card card = new Card("creator", "title", 0);
        Board b = new Board("name");
        BoardGUI bg = new BoardGUI("name", b);
        ColumnGUI cg = new ColumnGUI(fileName, fileName, bg);
        Column c = new Column(fileName, fileName, cg);
        String log = actLog.moveCardLog(card, c);
        assertNotNull(log);
    }
}
