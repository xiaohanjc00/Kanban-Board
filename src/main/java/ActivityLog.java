import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class ActivityLog {
    //HashMap<String, String> activityList;
    CSVcreator csvWriter;
    CSVreader csvReader;

    public ActivityLog() throws IOException {
        csvWriter = new CSVcreator();
        viewActivityLog();
    }

    /**
     * Get the date and time
     * @return current date and time
     */
    public String getCurrentTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String time = localDateTime.format(formatter);
        return time;
    }

    /**
     * Get the current activity fron¡m the ActivityLog file
     * @throws IOException if there is no such file
     */
    public void viewActivityLog() throws IOException {
        try {
            csvReader = new CSVreader("ActivityLog.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Log for creating new board
     * @param board name of the board
     */
    public void createBoardLog(String board){
        String text = "New board " + board + " has been created";
        //activityList.put(time, text);
        csvWriter.appendCSV(text + " , " + getCurrentTime());
    }

    /**
     * Log for deleting an existing board
     * @param board name of the board
     */
    public void deleteBoardLog(String board){
        String text = "Board" + board + "has been deleted";
        String time = LocalDateTime.now().toString();
        //activityList.put(time, text);
        csvWriter.appendCSV(text + " , " + time);
        csvWriter.appendCSV("\n");
    }

    /**
     * Log for creating new column
     * @param column name of the column
     * @param board name of the board
     */
    public void createColumnLog(String column, String board){
        String text = "New column" + column + "has been created to " + board;
        String time = LocalDateTime.now().toString();
        //activityList.put(time, text);
        csvWriter.appendCSV(text + " , " + time);
        csvWriter.appendCSV("\n");
    }

    /**
     * Log for creating new column
     * @param column name of the column
     * @param board name of the board
     */
    public void deleteColumnLog(String column, String board){
        String text = "Column" + column + "has been deleted from " + board;
        String time = LocalDateTime.now().toString();
        //activityList.put(time, text);
        csvWriter.appendCSV(text + " , " + time);
        csvWriter.appendCSV("\n");
    }

    /**
     * Log for creating new column
     * @param card name of the card
     * @param column name of the column
     */
    public void createCardLog(String card, String column){
        String text = "New card" + card + "has been created to " + column;
        String time = LocalDateTime.now().toString();
        //activityList.put(time, text);
        csvWriter.appendCSV(text + " , " + time);
        csvWriter.appendCSV("\n");
    }

    /**
     * Log for creating new column
     * @param card name of the card
     * @param column name of the column
     */
    public void deleteCardLog(String card, String column){
        String text = "Column" + card + "has been deleted from " + column;
        String time = LocalDateTime.now().toString();
        //activityList.put(time, text);
        csvWriter.appendCSV(text + " , " + time);
        csvWriter.appendCSV("\n");
    }

    /**
     * Log for creating a new member
     * @param member name of the new member
     * @param card name of the card which is being added to
     */
    public void addMemberLog(String member, String card){
        String text = "new member " + member + "has been added to " + card;
        String time = LocalDateTime.now().toString();
        //activityList.put(time, text);
        csvWriter.appendCSV(text + " , " + time);
        csvWriter.appendCSV("\n");
    }

    /**
     * Log for deleting a member
     * @param member name of the deleted member
     * @param card name of the card which is being deleted from
     */
    public void deleteMemberLog(String member, String card){
        String text = "Member " + member + "has been deleted from" + card;
        String time = LocalDateTime.now().toString();
        //activityList.put(time, text);
        csvWriter.appendCSV(text + " , " + time);
        csvWriter.appendCSV("\n");
    }

    /**
     * Log for setting card title
     * @param card Name of the card
     */
    public void setCardTitleLog(Card card){
        String text = "Card title has been set to " + card.getTitle();
        String time = LocalDateTime.now().toString();
        //activityList.put(time, text);
        csvWriter.appendCSV(text + " , " + time);
        csvWriter.appendCSV("\n");
    }

    /**
     * Log for setting card description
     * @param card Name of the card
     */
    public void setDescription(Card card){
        String text = "New card description has been set to " + card.getTitle();
        String time = LocalDateTime.now().toString();
        //activityList.put(time, text);
        csvWriter.appendCSV(text + " , " + time);
        csvWriter.appendCSV("\n");
    }

    /**
     * Log for setting card deadline
     * @param card Name of the card
     */
    public void setDeadLine(Card card){
        String text = "New deadline has been set to " + card.getDeadline();
        String time = LocalDateTime.now().toString();
        //activityList.put(time, text);
        csvWriter.appendCSV(text + " , " + time);
        csvWriter.appendCSV("\n");
    }
}
