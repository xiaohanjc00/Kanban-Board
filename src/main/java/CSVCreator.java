import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/* 
 * CSV Creator creates a CSV for each board
 */

public class CSVCreator {
    FileWriter csvWriter;

    /*
     * Constructor
     * @param fileName The name of file.
     */
    public CSVCreator(String fileName, String mode){
        try {
            createCSV(fileName, mode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Constructor
     * @param fileName The name of file.
     * @param appendMode True of you want to append to file, false otherwise.
     */
    public CSVCreator(String fileName, Boolean appendMode, String mode){
            try {
                if(appendMode == false){
                    createNewCSV(fileName, mode);
                }
                else{
                    createCSV(fileName, mode);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public File toResourceFolder(String fileName, String mode){
        File folder = null;
        if(mode.equals("data")){
            folder = new File("src/main/resources/Data"); 
        }
        else if(mode.equals("log")){
            folder = new File("src/main/resources/ActivityLog");
        }
        File file = new File(folder, fileName); 
        try {
            file.createNewFile();
        } catch (IOException e) {            
            e.printStackTrace();
        }
        return file;
    }

    /**
     * Create CSV file for the activity log
     * @param fileName name of the file
     * @throws IOException
     */
    public void createCSV(String fileName, String mode) throws IOException{
        File newFile = toResourceFolder(fileName, mode);
        csvWriter = new FileWriter(newFile, true);

    }

    /**
     * Create CSV file for the activity log,
     * which overwrites everything previously in that file.
     * @param fileName name of the file
     * @throws IOException
     */
    public void createNewCSV(String fileName, String mode) throws IOException{
        File newFile = toResourceFolder(fileName, mode);
        csvWriter = new FileWriter(newFile, false);
    }

    /**
     * Append new action to the CSV file
     * @param text The text to be appended to the CSV file
     */
    public void appendCSV(String text){
        try {
            csvWriter.append(text);
            flushCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * FLush CSVWriter
     */
    public void flushCSV(){
        try {
            csvWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Close CSVWriter
     */
    public void closeCSV(){
        try {
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
