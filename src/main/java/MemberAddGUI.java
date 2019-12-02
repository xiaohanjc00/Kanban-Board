import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MemberAddGUI extends JFrame implements ActionListener{
    /*
    Creation of panel, text fields and submit button
     */
    JPanel p = new JPanel();
    JButton submit = new JButton("Add Member");
    JTextField usernameField = new JTextField("Enter Username");
    JTextField firstNameField = new JTextField("Enter First Name");
    JTextField secondNameField = new JTextField("Enter Second Name");

    public static void main(String[] args){
        new MemberAddGUI();
    }

    /*
    GUI set up in the constructor
     */
    public MemberAddGUI(){
        super("Add Member");
        setSize(400,300);
        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        submit.addActionListener(this);
        p.add(usernameField);
        p.add(firstNameField);
        p.add(secondNameField);
        p.add(submit);
        add(p);

    }

    /*
    Adds functionality for the submit button in the GUI, takes the text in the text fields and creates a new member.
     */
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == submit){
            String username = usernameField.getText();
            String firstName = firstNameField.getText();
            String secondName = secondNameField.getText();
            Members newMember = new Members(username,firstName,secondName);
            JOptionPane.showMessageDialog(null, newMember.getUsername() + " created!");
        }
    }


 }
