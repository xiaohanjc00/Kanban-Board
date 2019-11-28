import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.ArrayList;

public class MemberTest{
    @Test
    // basic creation of a member
    public void MemberCreationTest(){
        String u = "username";
        String a = "Alan";
        String b = "Mahmoud";
        Member newMember = new Member(u,a,b);
        assertEquals(u, newMember.getUsername());
        assertEquals(a, newMember.getFirstName());
        assertEquals(b, newMember.getLastName());
    }
    @Test
    //Testing change first name
    public void MemberChangeFirstName(){
        String u = "username";
        String a = "Alan";
        String b = "Mahmoud";
        Member newMember = new Member(u,a,b);
        String d = "Alano";
        newMember.setFirstName(d);
        assertEquals(d, newMember.getFirstName());
    }
    @Test
    //Testing change last name
    public void MemberChangeLastName(){
        String u = "username";
        String a = "Alan";
        String b = "Mahmoud";
        Member newMember = new Member(u,a,b);
        String d = "Mahmoudo";
        newMember.setLastName(d);
        assertEquals(d, newMember.getLastName());
    }

    @Test
    //Testing change bio
    public void MemberChangeBio(){
        String u = "username";
        String a = "Alan";
        String b = "Mahmoud";
        Member newMember = new Member(u,a,b);
        String d = "This is my bio";
        newMember.setBio(d);
        assertEquals(d, newMember.getBio());
    }

    @Test
    //Testing get full name
    public void MemberDisplayName(){
        String u = "username";
        String a = "Alan";
        String b = "Mahmoud";
        String c = a + " " + b;
        assertEquals(c, newMember.getName());
    }
}