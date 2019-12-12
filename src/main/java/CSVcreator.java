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
    public CSVCreator(String fileName){
        try {
            createCSV(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Constructor
     * @param fileName The name of file.
     * @param appendMode True of you want to append to file, false otherwise.
     */
    public CSVCreator(String fileName, Boolean appendMode){
            try {
                if(appendMode == false){
                    createNewCSV(fileName);
                }
                else{
                    createCSV(fileName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    /**
     * Create CSV file for the activity log
     * @param fileName name of the file
     * @throws IOException
     */
    public void createCSV(String fileName) throws IOException{
        csvWriter = new FileWriter(fileName, true);
    }

    public void createNewCSV(String fileName) throws IOException{
        csvWriter = new FileWriter(fileName, false);
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
