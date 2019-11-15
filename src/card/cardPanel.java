import javax.swing.*;
import java.awt.event.*;

public class cardPanel {
    public static void main(String[] args) {
        JFrame f=new JFrame();//creating instance of JFrame

        JButton b=new JButton("click");//creating instance of JButton
        b.setBounds(130,100,100, 40);//x axis, y axis, width, height

        //f.add(b);//adding button in JFrame
        JButton closeWindowBtn = new JButton("X Close");
        // closeWindowBtn.addActionListener(new ActionListener(){  
        //     public void closeWindow(ActionEvent e){  
        //        System.exit(0);
        //     }  
        // });

        JLabel cardName = new JLabel("TODO: [cardName]");

        JLabel membersLabel = new JLabel("Members");
        // DefaultListModel<String> l1 = new DefaultListModel<>();  
        // l1.addElement("Item1");  
        // l1.addElement("Item2");  
        // l1.addElement("Item3");  
        // l1.addElement("Item4");  
        // JList<String> list = new JList<>(l1);  
        // list.setBounds(100,100, 75,75);

        JLabel descriptionLabel = new JLabel("Description");
        JTextArea descriptionText = new JTextArea("Put the details here...");

        JLabel activityLabel = new JLabel("Activity");
        JTextArea activityText = new JTextArea("Write a comment...");

        JLabel addLabel = new JLabel("ADD..."); 
        JButton membersBtn = new JButton("Members");
        JButton labelsBtn = new JButton("Labels");
        JButton checklistBtn = new JButton("Checklist");
        JButton dueDateBtn = new JButton("Due Date");
        JButton coverBtn = new JButton("Cover");
        
        JLabel actionLabel = new JLabel("ACTIONS...");
        JButton moveBtn = new JButton("Move");
        JButton copyBtn = new JButton("Copy");
        JButton archiveBtn = new JButton("Archive");
        JButton shareBtn = new JButton("Share");

        closeWindowBtn.setBounds(520, 10, 80, 30);

        cardName.setBounds(20, 10, 200, 30);
        descriptionLabel.setBounds(20, 200, 100, 30);
        descriptionText.setBounds(20, 250, 350, 150);

        activityLabel.setBounds(20, 420, 100, 30);
        activityText.setBounds(20, 470, 350, 100);

        addLabel.setBounds(400, 60, 150, 30);
        membersBtn.setBounds(400, 100, 150, 30);
        labelsBtn.setBounds(400, 140, 150, 30);
        checklistBtn.setBounds(400, 180, 150, 30);
        dueDateBtn.setBounds(400, 220, 150, 30);
        coverBtn.setBounds(400, 260, 150, 30);
        
        actionLabel.setBounds(400, 320, 80, 30);
        moveBtn.setBounds(400, 360, 150, 30);
        copyBtn.setBounds(400, 400, 150, 30);
        archiveBtn.setBounds(400, 440, 150, 30);
        shareBtn.setBounds(400, 480, 150, 30);

        f.add(cardName);
        f.add(descriptionLabel);
        f.add(descriptionText);
        f.add(activityLabel);
        f.add(activityText);
        f.add(closeWindowBtn);
        f.add(addLabel);
        f.add(membersBtn);
        f.add(labelsBtn);
        f.add(checklistBtn);
        f.add(dueDateBtn);
        f.add(coverBtn);
        f.add(actionLabel);
        f.add(moveBtn);
        f.add(copyBtn);
        f.add(archiveBtn);
        f.add(shareBtn);
        f.setSize(600,800);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible


    }
}