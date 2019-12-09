import java.util.ArrayList;
import java.util.HashMap;


public class SaveData {
    CSVcreator csvWriter;
    Board board;

    public SaveData(String fileName, Board board){
        csvWriter = new CSVcreator("src/BoardData/" + fileName, false);
        this.board = board;
        writeInformation();
        LoadData aa = new LoadData(fileName);
    }

    /**
     * Get all the current information from the board, column and cards
     * @return Arraylist of Columns(ArrayList of Cards(ArrayList of it details))
     */
    public ArrayList<ArrayList<ArrayList<String>>> getBoardInformation(){
        //Get list of columns
        ArrayList<Column> columnList = board.getColumnsList();

        //Create the final board list
        ArrayList<ArrayList<ArrayList<String>>> boardDetails = new ArrayList<>();

        for(Column col : columnList){
            ArrayList<ArrayList<String>> columnDetails = new ArrayList<>();

            //Index 0 for the name of the column
            ArrayList<String> firstValue = new ArrayList<>();
            firstValue.add(col.getName());
            if(!col.getRole().equals("")){
                firstValue.add(col.getRole());
            }
            else{
                firstValue.add("null");
            }
            columnDetails.add(firstValue);

            //Following indexes for the cards inside the column
            ArrayList<Card> cardList = col.getCardsList();
            for(Card card : cardList){
                ArrayList<String> cardDetails = new ArrayList<>();

                String cardName = card.getTitle();
                String cardID = card.getId();
                String cardStoryPoint = ((Integer)card.getStoryPoint()).toString();
                String cardDescription = card.getDescription();

                cardDetails.add(cardName);
                cardDetails.add(cardID);
                cardDetails.add(cardStoryPoint);
                cardDetails.add(cardDescription);

                columnDetails.add(cardDetails);
            }
            
            boardDetails.add(columnDetails);
        }
        return boardDetails;
    }

    /**
     * Write the current information of the board to it corresponding CSV
     */
    public void writeInformation(){
        //Write in board name
        csvWriter.appendCSV("boardName, " + board.getName());
        csvWriter.appendCSV("\n");

        ArrayList<ArrayList<ArrayList<String>>> boardDetails = getBoardInformation();

        for(ArrayList<ArrayList<String>> columnDetail : boardDetails){
            //Write in column name
            String columnName = columnDetail.get(0).get(0);
            String columnRole = columnDetail.get(0).get(1);
            csvWriter.appendCSV("\n");
            csvWriter.appendCSV("columnName, " + columnName + " , " + columnRole);
            csvWriter.appendCSV("\n");

            for(int i=1; i < columnDetail.size(); i++){
                ArrayList<String> cardDetails = columnDetail.get(i);
                csvWriter.appendCSV("cardName, ");
                for(String cardInfo : cardDetails){
                    csvWriter.appendCSV(cardInfo + ", ");
                }
                csvWriter.appendCSV("\n");
            }
            csvWriter.appendCSV("ColumnEnd \n");
        }
    }
}