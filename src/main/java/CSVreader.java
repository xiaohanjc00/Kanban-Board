import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVreader {
    FileReader csvReader;
    String fileName;

    public CSVreader(String fileName) throws IOException {
        this.fileName = fileName;
        csvReader = new FileReader(fileName);
        
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
    public List<String> CSVGetString() throws IOException{
        File file = new File(fileName);
        Scanner input = new Scanner(file);
        List<String> list = new ArrayList<String>();

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
