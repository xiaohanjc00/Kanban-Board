/*
 Class to store list of all boards created
 This list is displayed in the homepage
*/
import java.util.*;
public class BoardList{
    
private ArrayList<Board> boards;
    
public BoardList()
{
 boards = new ArrayList<Board>();
}
    
public void addBoard(Board boardIn)
{
    boards.add(boardIn);
}

public Board getBoard(int index)
{
    return boards.get(index);
}
public ArrayList<Board> getAllBoards()
{
    return boards;
}
    
}