import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* 
 * CSV Creator reads a CSV file
 */

public class CSVReader {
    FileReader csvReader;
    File file;

    /**
     * Constructor
     * @param fileName The name of file.
     */
    public CSVReader(String fileName, String mode) throws IOException {
        File file = getResource(fileName, mode);
        this.file = file;
        csvReader = new FileReader(file);
        
    }

    public File getResource(String fileName, String mode){
        File file = null;
        if(mode.equals("data")){
            file = new File("src/main/resources/Data/" + fileName); 
        }
        else if(mode.equals("log")){
            file = new File("src/main/resources/ActivityLog/" + fileName);
        }
        return file;
    }

    /**
     * Reade the content of a file and print it out
     * @throws IOException if there is no such file
     */
    public void readCSV() throws IOException {
        int i;
        while ((i = csvReader.read()) != -1) {
            System.out.print((char) i);
        }
    }

    /**
     * Get the CSV content as a list of String
     * @return list of string of each line of the CSV
     * @throws IOException if there is no such file
     */
    public ArrayList<String> CSVGetString() throws IOException{
        Scanner input = new Scanner(file);
        ArrayList<String> list = new ArrayList<String>();

        while (input.hasNextLine()) {
            list.add(input.nextLine());
        }
        return list;
    }
    /**
     * Get the CSV content as an unified String
     * @returnString of the content of the CSV
     * @throws IOException if there is no such file
     */
    public String CSVAsString() throws IOException{
        int i;
        String output = "";
        while ((i = csvReader.read()) != -1) {
            output = output + (char) i;
        }
        return output;
    }

    /**
     * Close the CSV file
     */
    public void closeCSV(){
        try {
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
