import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
/*
 * ActivityLog object class
 * Manages the activity log of the kanban board.
 */
public class ActivityLog {
    CSVCreator csvWriter;
    CSVReader csvReader;

    /*
     * Constructor of ActivityLog
     * @param fileName The name of file to be imported.
     */
    public ActivityLog(String fileName) throws IOException {
        csvWriter = new CSVCreator(fileName + "ActivityLog.csv", "log");
        viewActivityLog(fileName);
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
     * Get the current activity from the ActivityLog file
     * @throws IOException if there is no such file
     */
    public void viewActivityLog(String fileName) throws IOException {
        try {
            csvReader = new CSVReader(fileName + "ActivityLog.csv", "log");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Get the Activity log as a list of strings
     * @return list of strings of each line of the CSV
     */
    public List<String> getActivityLog(){
        ArrayList<String> list = new ArrayList<String>();
        try {
            list = (ArrayList<String>) csvReader.CSVGetString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * Get the Activity log as a unified string
     * @return String of the entire CSV
     */
    public String getActivityLogAsString(){
        String output = "";
        try {
            output = csvReader.CSVAsString();
            if(output == null){
                output = "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    /**
     * Extra method for testing
     */
    public String activityLogAsString(){
        List<String> list = new ArrayList<String>();
        list = getActivityLog();
        String finalString = "";

        for(String line : list){
            finalString = finalString + line + "\n";
        }
        return finalString;
    }

    /**
     * Log for creating new board
     * @param board name of the board
     */
    public void createBoardLog(String board){
        String text = "New board " + board + " has been created";
        //activityList.put(time, text);
        csvWriter.appendCSV(text + " , " + getCurrentTime());
        csvWriter.appendCSV("\n");
    }

    /**
     * Log for creating new column
     * @param column name of the column
     * @param board name of the board
     * @return log for creating a column
     */
    public String createColumnLog(String column, String board){
        String text = "New column " + column + " has been created to " + board + " , " + getCurrentTime();
        //activityList.put(time, text);
        csvWriter.appendCSV(text);
        csvWriter.appendCSV("\n");
        return text;
    }

    /**
     * Log for creating new column
     * @param newColumn new name of the column
     * @param oldColumn old name of the column
     * @param newRole new role of the column
     * @param oldRole old role of the column
     * @return log for editing a column
     */
    public String editColumnLog(String newColumn, String oldColumn, String newRole, String oldRole){
        String text = "";
        if(!(oldColumn.equals(newColumn) && oldRole.equals(newRole))){
            if(!oldColumn.equals(newColumn) && !oldRole.equals(newRole)){
                text = "Column " + oldColumn + " has changed to " + newColumn + " and role changed to " + newRole + " , " + getCurrentTime();
            }
            else if(!oldColumn.equals(newColumn)){
                text = "Column " + oldColumn + " has changed name to " + newColumn + " , " + getCurrentTime();
            }
            else if(!oldRole.equals(newRole)){
                text = "Role of column " + oldColumn + " has been edited to " + newRole + " , " + getCurrentTime();
            }
            //activityList.put(time, text);
            csvWriter.appendCSV(text);
            csvWriter.appendCSV("\n");
        }
        return text;
    }

    /**
     * Log for creating new column
     * @param column name of the column
     * @param board name of the board
     * @return log for deleting a column
     */
    public String deleteColumnLog(String column, String board){
        String text = "Column " + column + " has been deleted from " + board + " , " + getCurrentTime();
        //activityList.put(time, text);
        csvWriter.appendCSV(text);
        csvWriter.appendCSV("\n");
        return text;
    }

    /**
     * Log for creating new column
     * @param card name of the card
     * @param column name of the column
     * @return log for creating a card
     */
    public String createCardLog(String card, String column){
        String text = "New card " + card + " has been created to " + column + " , " + getCurrentTime();
        //activityList.put(time, text);
        csvWriter.appendCSV(text);
        csvWriter.appendCSV("\n");
        return text;
    }

    /**
     * Log for creating new column
     * @param card name of the card
     * @param column name of the column
     * @return log for deleting a card
     */
    public String deleteCardLog(String card, String column){
        String text = "Column " + card + " has been deleted from " + column + " , " + getCurrentTime();
        //activityList.put(time, text);
        csvWriter.appendCSV(text);
        csvWriter.appendCSV("\n");
        return text;
    }

    /**
     * Log for setting card title
     * @param card Name of the card
     * @return log for editing the card
     */
    public String editCardTitleLog(String oldName, Card card){
        String text = "Card " + oldName + " changed name to " + card.getTitle() + " , " + getCurrentTime();
        //activityList.put(time, text);
        csvWriter.appendCSV(text + " , " + getCurrentTime());
        csvWriter.appendCSV("\n");
        return text;
    }

    /**
     * Log for setting card description
     * @param card Name of the card
     * @return log for the new description
     */
    public String setDescriptionLog(Card card){
        String text = "New card description has been set to " + card.getTitle() + " , " + getCurrentTime();
        //activityList.put(time, text);
        csvWriter.appendCSV(text);
        csvWriter.appendCSV("\n");
        return text;
    }

    /**
     * Log for setting card story point
     * @param card Name of the card
     * @return log of the setting story point 
     */
    public String setStoryPointLog(Card card){
        String text = card.getTitle() + " story point has been set to " + card.getStoryPoint() + " , " + getCurrentTime();
        //activityList.put(time, text);
        csvWriter.appendCSV(text);
        csvWriter.appendCSV("\n");
        return text;
    }
    /**
     * Log for the pressing the save button
     * @param board the board which is saved
     * @return the save button log
     */
    public String saveButtonLog(String board){
        String text = "Board " + board + " has been saved correctly, " + getCurrentTime();
        csvWriter.appendCSV(text);
        csvWriter.appendCSV("\n");
        return text;
    }

    /**
     * Log for moving the cards
     * @param card the moving card
     * @param column the new column
     * @return the moving card log
     */
    public String moveCardLog(Card card, Column column){
        String text = "Card " + card.getTitle() + " has been moved to " + column.getName() + ", " + getCurrentTime();
        csvWriter.appendCSV(text);
        csvWriter.appendCSV("\n");
        return text;
    }
}
