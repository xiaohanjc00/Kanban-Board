/*
 * Class to store list of all boards created
 * This list is displayed in the homepage
 */
import java.util.*;
public class BoardList{
    
private ArrayList<Board> boards;
    
    /*
    * Constructor of BoardList
    */
    public BoardList()
    {
        boards = new ArrayList<Board>();
    }

    /*
    * Add a board.
    * @param boardIn Board object to be added.
    */
    public void addBoard(Board boardIn)
    {
        boards.add(boardIn);
    }

    /*
    * Get a specific board from list.
    * @param index Index of the board on list.
    */
    public Board getBoard(int index)
    {
        return boards.get(index);
    }

    /*
    * Get all boards.
    * @return an ArrayList of Board-s.
    */
    public ArrayList<Board> getAllBoards()
    {
        return boards;
    }

    public void clearList(){
        boards.clear();
    }
    
}