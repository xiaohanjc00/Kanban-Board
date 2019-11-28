import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class MemberDeleteGUI extends JFrame{
    JPanel p = new JPanel();
    JButton submit = new JButton("Delete Member");
    JTextField usernameField = new JTextField("Enter Username");

    public static void main(String[] args){
        new MemberDeleteGUI();
    }

    public MemberDeleteGUI(){
        super("Delete Member");
        setSize(400,300);
        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        p.add(usernameField);
        p.add(submit);
        add(p);
    }
}