

import static org.junit.Assert.*;
import org.junit.Test;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.ArrayList;



public class CardTest {
  @Test
  public void cardCreationTest() {
    String creator = "Jeroen Keppens";
    String title = "My first card";
    Card newCard = new Card(creator, title, 0);
    assertEquals(creator, newCard.getCreator());
    assertEquals(title, newCard.getTitle());
  }

  @Test
  public void cardEditTest() {
    //create the card
    String creator = "Jeroen Keppens";
    String title = "My first card";
    Card newCard = new Card(creator, title, 0);

    //title edit test
    String newTitle = "new Title";
    newCard.setTitle(newTitle);
    assertEquals(newTitle, newCard.getTitle());

    //description edit test
    String desc = "The FitnessGram™ Pacer Test is a multistage aerobic capacity test that progressively gets more difficult as it continues. The 20 meter pacer test will begin in 30 seconds. Line up at the start. The running speed starts slowly, but gets faster each minute after you hear this signal. [beep] A single lap should be completed each time you hear this sound. [ding] Remember to run in a straight line, and run as long as possible. The second time you fail to complete a lap before the sound, your test is over. The test will begin on the word start. On your mark, get ready, start.";
    newCard.setDescription(desc);
    assertEquals(desc, newCard.getDescription());

  }

    @Test
    public void membersTest() {
      //create the card
      String creator = "Jeroen Keppens";
      String title = "My first card";
      Card newCard = new Card(creator, title, 0);
    
      //members test
      String firstMember = "JKeppens";
      String secondMember = "Kony2012";
      String thirdMember = "Tony Stark";
      ArrayList<String> expectedMembers = new ArrayList<String>();
    
      expectedMembers.add(firstMember);
      newCard.addMember(firstMember);
      assertArrayEquals(expectedMembers.toArray(), newCard.getMembers().toArray());
    
      expectedMembers.add(secondMember);
      newCard.addMember(secondMember);
      expectedMembers.add(thirdMember);
      newCard.addMember(thirdMember);
      assertArrayEquals(expectedMembers.toArray(), newCard.getMembers().toArray());
    
      newCard.removeMember(secondMember);
      expectedMembers.remove(secondMember);
      assertArrayEquals(expectedMembers.toArray(), newCard.getMembers().toArray());
    }

    // @Test
    // public void deadlineTest(){
    //   //create the card
    //   String creator = "Jeroen Keppens";
    //   String title = "My first card";
    //   Card newCard = new Card(creator, title, 0);

    //   //deadline test
    //   String deadlineString = "2020-04-20 16:20";
    //   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    //   LocalDateTime deadline = LocalDateTime.parse(deadlineString, formatter);

    //   newCard.setDeadline(deadline);

    //   assertEquals(deadlineString, newCard.getDeadline().format(formatter));

    // }

    @Test
    public void storyPointsTest(){
      //create the card
      String creator = "Jeroen Keppens";
      String title = "My first card";
      Card newCard = new Card(creator, title, 0);

      //story points test
      newCard.setStoryPoint(4);
      assertEquals(4, newCard.getStoryPoint());
    }

    //now that all card backend functions are tested, we'll set up a general
    //purpose test to check nothing spooky happens under weird circumstances

    @Test
    public void emptyNameTest(){
      //create the card with empty string
      String creator = "Jeroen Keppens";
      String title = "title";
      Card newCard = new Card(creator, title, 99999);

      Card secondCard = new Card(creator, title, 99999);

      
      
      assertEquals("", newCard.getTitle());
    }

    // @Test(expected=IllegalArgumentException.class)
    // public void negativeIntTest(){
    //   //create the card
    //   String creator = "Jeroen Keppens";
    //   String title = "title";
    //   Card newCard = new Card(creator, title, -5);
    // }
    

    // //localdateTime before now
    // @Test(expected=IllegalArgumentException.class)
    // public void timeTravelTest(){
    //   //create the card
    //   String creator = "Jeroen Keppens";
    //   String title = "title";
    //   String deadlineString = "2000-01-01 16:20";
    //   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    //   LocalDateTime deadline = LocalDateTime.parse(deadlineString, formatter);
    //   Card newCard = new Card(creator, title, 1);

    //   newCard.setDeadline(deadline);
    // }



}
