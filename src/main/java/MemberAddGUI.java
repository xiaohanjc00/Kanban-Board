import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class MemberAddGUI extends JFrame{
    JPanel p = new JPanel();
    JButton submit = new JButton("Add Member");
    JTextField usernameField = new JTextField("Enter Username");
    JTextField firstNameField = new JTextField("Enter First Name");
    JTextField secondNameField = new JTextField("Enter Second Name");

    public static void main(String[] args){
        new MemberAddGUI();
    }

    public MemberAddGUI(){
        super("Add Member");
        setSize(400,300);
        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        p.add(usernameField);
        p.add(firstNameField);
        p.add(secondNameField);
        p.add(submit);
        add(p);
    }
 }