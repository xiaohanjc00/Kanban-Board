import javax.swing.*;
import java.awt.event.*;

public class CardGUI {

     public Card card;

     public CardGUI(Card card) {
       this.card = card;
      makeFrame();
     }

     public void makeFrame() {
    //public static void main(String[] args) {
        JFrame f=new JFrame();

        JButton closeWindowBtn = new JButton("X Close");
        closeWindowBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                f.setVisible(false);
                f.dispose();
            }
        });
        //JLabel cardName = new JLabel(card.getTitle());
        JLabel cardName = new JLabel("Dummy");
        String members = "Members: ";
        // for (String member : card.getMembers()) {
        //     members = members + member + " ";
        // }
        JLabel membersLabel = new JLabel(members);

        JLabel descriptionLabel = new JLabel("Description");
        JTextArea descriptionText = new JTextArea("Put the details here...");
        // JTextArea descriptionText = new JTextArea(card.getDescription());
        JButton saveDescriptionBtn = new JButton("Save Description");
        saveDescriptionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            //card.setDescription(descriptionText.getText());
            JOptionPane.showMessageDialog(f, "Description Saved!");
            Main.log.setDescription(card);
            }
        });

        JLabel activityLabel = new JLabel("Activity");
        JTextArea activityText = new JTextArea("Write a comment...");

        JLabel addLabel = new JLabel("ADD..."); 
        JButton addMemberBtn = new JButton("Add Member");
        addMemberBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            JFrame newFrame = new JFrame();
            JLabel inputMemberLabel = new JLabel("Type the name of the member you wish to be added:");
            JTextArea memberInput = new JTextArea("Member...");
            JButton addBtn = new JButton("Add");
            inputMemberLabel.setBounds(0, 0, 200, 30);
            memberInput.setBounds(0, 100, 100, 30);
            addBtn.setBounds(100, 100, 100, 30);
            addBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                //card.addMember(memberInput.getText());
                JOptionPane.showMessageDialog(newFrame, "Member Added!");
                Main.log.addMemberLog(memberInput.getText(), card.getTitle());
                }
            });
            newFrame.add(addBtn);
            newFrame.add(memberInput);
            newFrame.add(inputMemberLabel);
            // TODO: import member list
            newFrame.setSize(400,200);
            newFrame.setVisible(true);
            }
        });
        JButton removeMemberBtn = new JButton("Remove Member");
        removeMemberBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            JFrame newFrame = new JFrame();
            JLabel inputMemberLabel = new JLabel("Type the name of the member you wish to be removed:");
            JTextArea memberInput = new JTextArea("Member...");
            JButton removeBtn = new JButton("Remove");
            inputMemberLabel.setBounds(0, 0, 200, 30);
            memberInput.setBounds(0, 100, 100, 30);
            removeBtn.setBounds(100, 100, 100, 30);
            removeBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                //card.removeMember(memberInput.getText());
                JOptionPane.showMessageDialog(newFrame, "Member Removed!");
                Main.log.deleteMemberLog(memberInput.getText(), card.getTitle());
                }
            });
            newFrame.add(removeBtn);
            newFrame.add(memberInput);
            newFrame.add(inputMemberLabel);
            // TODO: import member list
            newFrame.setSize(400,200);
            newFrame.setVisible(true);
            }
        });
        JButton checklistBtn = new JButton("Checklist");
        JButton dueDateBtn = new JButton("Due Date");
        dueDateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            JFrame newFrame = new JFrame();
            JLabel dayLabel = new JLabel("DAY");
            JLabel monthLabel = new JLabel("MONTH");
            JLabel yearLabel = new JLabel("YEAR");
            JTextArea day = new JTextArea("01");
            JTextArea month = new JTextArea("01");
            JTextArea year = new JTextArea("2019");

            dayLabel.setBounds(20, 20, 40, 20);
            monthLabel.setBounds(60, 20, 60, 20);
            yearLabel.setBounds(0, 0, 50, 20);
            day.setBounds(20, 40, 40, 20);
            month.setBounds(60, 40, 60, 20);
            year.setBounds(120, 40, 50, 20);
            newFrame.add(dayLabel);
            newFrame.add(monthLabel);
            newFrame.add(yearLabel);
            newFrame.add(day);
            newFrame.add(month);
            newFrame.add(year);
            newFrame.setSize(300,300);
            newFrame.setVisible(true);
            }
        });
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
        saveDescriptionBtn.setBounds(230, 400, 150, 30);
        activityLabel.setBounds(20, 420, 100, 30);
        activityText.setBounds(20, 470, 350, 100);

        addLabel.setBounds(400, 60, 150, 30);
        addMemberBtn.setBounds(400, 100, 150, 30);

        removeMemberBtn.setBounds(400, 140, 150, 30);
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
        f.add(saveDescriptionBtn);
        f.add(activityLabel);
        f.add(activityText);
        f.add(closeWindowBtn);
        f.add(addLabel);
        f.add(addMemberBtn);
        f.add(removeMemberBtn);
        // f.add(checklistBtn);
        f.add(dueDateBtn);
        // f.add(coverBtn);
        f.add(actionLabel);
        // f.add(moveBtn);
        // f.add(copyBtn);
        f.add(archiveBtn);
        // f.add(shareBtn);
        f.setSize(600,800);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
    }
}
