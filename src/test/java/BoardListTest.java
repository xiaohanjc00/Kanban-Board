import static org.junit.Assert.assertEquals;
import org.junit.Test;


import java.util.ArrayList;

public class BoardListTest{
    //Tests the constructor of the boardList class 
    @Test
    public void boardListCreationTest(){
        //Creation of an array for expected result
        ArrayList<Board> boards = new ArrayList<Board>();
        BoardList boardList = new BoardList();
        //Checking that the array holding the boards in boardList is equal to the expected answer array.
        assertEquals(boards, boardList.getAllBoards());
    }
    
    //Tests adding a board to the boardList class works as expected. 
    @Test
    public void boardListAddTest(){
        //Creation of array to hold boards and creation of boardList object
        ArrayList<Board> boards = new ArrayList<Board>();
        BoardList boardList = new BoardList();
        //Creation of a board to be added to the boardList class
        String title = "New Board";
        Board newBoard = new Board(title);
        //Adding the board to the array with expected result 
        boards.add(newBoard);
        boardList.addBoard(newBoard);
        //Asserting that the boardList contains the board by comparing to the array with the board added.
        assertEquals(boards, boardList.getAllBoards());
    }
    //Tests function that gets a specific board from the boardList class 
    @Test
    public void boardListGetBoardTest(){
        //Creation of boardList object
        BoardList boardList = new BoardList();
        String title = "New Board";
        //Creation of board to add to boardList 
        Board newBoard = new Board(title);
        boardList.addBoard(newBoard);
        //Asserts that the board added, in position 0, is equal to the board that was created.
        assertEquals(newBoard, boardList.getBoard(0));
    }
    
}
