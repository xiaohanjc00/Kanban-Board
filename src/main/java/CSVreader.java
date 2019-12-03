import java.io.FileReader;
import java.io.IOException;

public class CSVreader {
    FileReader csvReader;

    public CSVreader(String fileName) throws IOException {
        csvReader = new FileReader(fileName);
        readCSV();
    }

    /**
     * Reade the content of a file
     * @throws IOException if there is no such file
     */
    public void readCSV() throws IOException {
        int i;
        while ((i = csvReader.read()) != -1) {
            System.out.print((char) i);
    }
    closeCSV();
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
