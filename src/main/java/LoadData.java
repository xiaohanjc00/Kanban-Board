import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LoadData{
    CSVReader csvReader;

    public LoadData(String fileName) {
        //readFile("src/BoardData/" + fileName);
        readFile(fileName + "DATA.csv");
    }
    
    /**
     * Read the CSV corresponding to it board name
     * @param fileName
     */
    public void readFile(String fileName){
        try {
            csvReader = new CSVReader(fileName, "data");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the name of the board
     * @return name of the board
     * @throws IOException if the CSV cannot be read
     */
    public String getBoardName() throws IOException{
        ArrayList<String> lineList = csvReader.CSVGetString();
        String firstLine = lineList.get(0);
        String finalReturn = "";

        ArrayList<String> list = new ArrayList<String>(Arrays.asList(firstLine.split(", ")));
        if(list.get(0).equals("boardName")){
            finalReturn = list.get(1);
        }
        return finalReturn;
    }

    /**
     * Returns the information taken from the CSV
     * @return ArrayList of ColumnDetails(ArrayList of column information and the cards it contains(another ArrayList))
     * @throws IOException if the CSV cannot be read
     */
    public ArrayList<ArrayList<Object>> getInformation() throws IOException{
        ArrayList<String> lineList = csvReader.CSVGetString();

        ArrayList<ArrayList<Object>> finalList = new ArrayList<>();

        for(int i=1; i<lineList.size(); i++){
            String line = lineList.get(i);
            ArrayList<String> list = new ArrayList<String>(Arrays.asList(line.split(", ")));
            String lineType = list.get(0);

            if(lineType.equals("columnName")){
                ArrayList<Object> columnDetail = new ArrayList<>();
                columnDetail.add((String) list.get(1));
                if(list.size() > 2){
                    columnDetail.add((String) list.get(2));
                }
                else{
                    columnDetail.add("null");
                }

                if(i+1 < lineList.size()){  //If there is next line
                    String nextLine = lineList.get(i+1);
                    ArrayList<String> tempList = new ArrayList<String>(Arrays.asList(nextLine.split(", ")));
                    if(!tempList.isEmpty()){
                        String tempLineType = tempList.get(0);

                        if(tempLineType.equals("cardName")){
                            int j = i + 1;
                            do{
                            ArrayList<String> cardDetails = tempList;
                            cardDetails.remove(0);
                            columnDetail.add(cardDetails);

                            j = j + 1;
                            nextLine = lineList.get(j);
                            tempList = new ArrayList<String>(Arrays.asList(nextLine.split(", ")));
                            tempLineType = tempList.get(0);
                            } while(tempLineType.equals("cardName"));

                            i = j;
                        }
                    }
                }
                finalList.add(columnDetail);
            }
        }
        return finalList;
    }
    /**
     * Whether the CSV information only contains the board name without any more information
     * @return whether is empty
     * @throws IOException if CSV cannot be read
     */
    public boolean isEmpty() throws IOException{
        ArrayList<String> lineList = csvReader.CSVGetString();
        boolean isEmpty = true;
        for(String line : lineList){
            ArrayList<String> list = new ArrayList<String>(Arrays.asList(line.split(", ")));
            if(list.get(0).equals("columnName")){
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }
    /**
     * Get the column name from a columnDetail ArrayList
     * @param columnDetails selected column from the ArrayList of columns
     * @return  column name
     */
    public String getColumnName(ArrayList<Object> columnDetails){
        return (String) columnDetails.get(0);
    }

    /**
     * Get the column role from a columnDetail ArrayList
     * @param columnDetails selected column from the ArrayList of columns
     * @return  column role, null if no specified
     */
    public String getColumnRole(ArrayList<Object> columnDetails){
        return (String) columnDetails.get(1);
    }

    /** 
     * Get the list of Cards from a selected column
     * @param columnDetails selected column from the ArrayList of columns
     * @return  ArrayList of the cards in a column
     */
    @SuppressWarnings("unchecked")
    public ArrayList<ArrayList<String>> getCardDetails(ArrayList<Object> columnDetails){
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        if(columnDetails.size() > 2){
            for(int i=2; i < columnDetails.size(); i++){
                list.add((ArrayList<String>) columnDetails.get(i));
            }
        }
        return list;
    }

    /**
     * Get card name from a selected card
     * @param cardDetails selected card from the ArrayList of cards
     * @return  name of the card
     */
    public String getCardName(ArrayList<String> cardDetails){
        return cardDetails.get(0);
    }

    /**
     * Get card ID from a selected card
     * @param cardDetails selected card from the ArrayList of cards
     * @return ID of the card
     */
    public int getCardID(ArrayList<String> cardDetails){
        return Integer.parseInt(cardDetails.get(1));
    }

    /**
     * Get the card story point from a selected card
     * @param cardDetails card from the ArrayList of cards
     * @return  story point of the card
     */
    public int getCardStoryPoints(ArrayList<String> cardDetails){
        return Integer.parseInt(cardDetails.get(2));
    }

    /**
     * Get the card description from a selected card
     * @param cardDetails card from the ArrayList of cards
     * @return description of the card
     */
    public String getCardDescription(ArrayList<String> cardDetails){
        return cardDetails.get(3);
    }

}