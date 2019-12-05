import java.io.FileWriter;
import java.io.IOException;

public class CSVcreator {
    FileWriter csvWriter;

    public CSVcreator(String fileName){
        try {
            createNewCSV(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create CSV file for the activity log
     * @throws IOException
     */
    public void createNewCSV(String fileName) throws IOException{
        csvWriter = new FileWriter(fileName, true);
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
