
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



public class CSVTest {
  @Test
  public void creationTest() {
      CSVCreator csv = new CSVCreator("Test", "data");
   
   csv.closeCSV();
   File testFile = new File("src/main/resources/Data/Test");

   //nb: the deletion of the file is done in each test, so that one test
   //doesn't have to rely on the previous test to pass
   if(!testFile.delete()) assertEquals("deleted file", "couldn't delete file"); //fail
  }

  @Test
  public void writerTest(){
    CSVCreator csv = new CSVCreator("Test", "data");
    for(int i = 0; i < 100; i++){
        csv.appendCSV("Line n. "+ i + ", ");

       }
       csv.closeCSV();

       File testFile = new File("src/main/resources/Data/Test");
   if(!testFile.delete()) assertEquals("deleted file", "couldn't delete file"); //fail

  }

  @Test
  public void readerTestString(){
    CSVCreator csv = new CSVCreator("Test", "data");
    String shouldBe = new String();
    for(int i = 0; i < 100; i++){
        csv.appendCSV("Line n. "+ i + ", ");
        shouldBe = shouldBe + "Line n. "+ i + ", ";
       }
       csv.closeCSV();
       try{
       CSVReader csvR = new CSVReader("Test", "data");
       assertEquals(shouldBe, csvR.CSVAsString());
       csvR.closeCSV();
       } catch (IOException e) {            
        e.printStackTrace();
    }
    File testFile = new File("src/main/resources/Data/Test");
    if(!testFile.delete()) assertEquals("deleted file", "couldn't delete file"); //fail
  }

  



  

}