import javax.swing.*;
import javax.swing.JSpinner.NumberEditor;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class CardGUI {

    private Card card;
    private ArrayList<JFrame> frames;
    private JFrame mainFrame;

     public CardGUI(Card card) {
        this.card = card;
        mainFrame = new JFrame();
        frames = new ArrayList<>();
        makeFrame();
     }

     public void makeFrame() {

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout(10,10));
        JPanel topLeftPanel = new JPanel();
        topLeftPanel.setLayout(new BoxLayout(topLeftPanel, BoxLayout.Y_AXIS));
        JLabel cardName = new JLabel("Card: " + card.getTitle());
        JLabel id = new JLabel("ID: " + card.getId());
        JLabel storyPoint;
        int storyPointValue = card.getStoryPoint();
        if (storyPointValue == 0) {
            storyPoint = new JLabel("Story Point: NOT SET");
        }
        else {
            storyPoint = new JLabel("Story Point: " + storyPointValue);
        }
        cardName.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        id.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        storyPoint.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        topLeftPanel.add(cardName);
        topLeftPanel.add(id);
        topLeftPanel.add(storyPoint);
        JButton closeWindowBtn = new JButton("X Close");
        closeWindowBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                mainFrame.setVisible(false);
                mainFrame.dispose();
                closeWindows();
            }
        });
        topPanel.add(topLeftPanel, BorderLayout.LINE_START);
        topPanel.add(closeWindowBtn, BorderLayout.LINE_END);
        topPanel.setBorder(BorderFactory.createLineBorder(Color.black));


        // addLabel.setBounds(400, 60, 150, 30);
        // addMemberBtn.setBounds(400, 100, 150, 30);

        // removeMemberBtn.setBounds(400, 140, 150, 30);
        // checklistBtn.setBounds(400, 180, 150, 30);
        // dueDateBtn.setBounds(400, 220, 150, 30);
        // coverBtn.setBounds(400, 260, 150, 30);
        
        // actionLabel.setBounds(400, 320, 80, 30);
        // moveBtn.setBounds(400, 360, 150, 30);
        // copyBtn.setBounds(400, 400, 150, 30);
        // archiveBtn.setBounds(400, 440, 150, 30);
        // shareBtn.setBounds(400, 480, 150, 30);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(topPanel, BorderLayout.PAGE_START);

        panel.add(makeLeftPanel(), BorderLayout.CENTER);

        panel.add(makeRightPanel(), BorderLayout.LINE_END);
        
        mainFrame.add(panel);
        mainFrame.setSize(600,800);
        mainFrame.setVisible(true);
    }

    public JPanel makeLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        String members = "Members: ";
        for (String member : card.getMembers()) {
            members = members + member + " ";
        }
        JLabel membersLabel = new JLabel(members);

        JLabel descriptionLabel = new JLabel("Description");
        //JTextArea descriptionText = new JTextArea("Put the details here...");
        JTextArea descriptionText = new JTextArea(card.getDescription());
        descriptionText.setMinimumSize(new Dimension(300,100));
        descriptionText.setMaximumSize(new Dimension(300,100));
        descriptionText.setLineWrap(true);
        JButton saveDescriptionBtn = new JButton("Save Description");
        saveDescriptionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            card.setDescription(descriptionText.getText());
            JOptionPane.showMessageDialog(leftPanel, "Description Saved!");}
        });

        // JLabel activityLabel = new JLabel("Activity");
        // JTextArea activityText = new JTextArea("Write a comment...");
        

        // closeWindowBtn.setBounds(520, 10, 80, 30);

        // cardName.setBounds(20, 10, 200, 30);
        // descriptionLabel.setBounds(20, 200, 100, 30);
        // descriptionText.setBounds(20, 250, 350, 150);
        // saveDescriptionBtn.setBounds(230, 400, 150, 30);
        // activityLabel.setBounds(20, 420, 100, 30);
        // activityText.setBounds(20, 470, 350, 100);
        descriptionLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        descriptionText.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        saveDescriptionBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        leftPanel.add(descriptionLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        leftPanel.add(descriptionText);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        leftPanel.add(saveDescriptionBtn);
        // leftPanel.add(activityLabel);
        // leftPanel.add(activityText);
        
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        return leftPanel;
    }

    public JPanel makeRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        JLabel addLabel = new JLabel("ADD..."); 
        JButton addMemberBtn = new JButton("Add Member");
        addMemberBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JFrame newFrame = new JFrame();
                addFrameToList(newFrame);
                JPanel newPanel = new JPanel();
                newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
                JLabel inputMemberLabel = new JLabel("Type the name of the member you wish to be added:");
                JTextArea memberInput = new JTextArea();
                JButton addBtn = new JButton("Add");
                addBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        card.addMember(memberInput.getText());
                        JOptionPane.showMessageDialog(newFrame, "Member Added!");
                        removeFrameFromList(newFrame);
                        newFrame.setVisible(false);
                        newFrame.dispose();
                    }
                });
                newPanel.add(inputMemberLabel);
                newPanel.add(memberInput);
                newPanel.add(addBtn);
                newFrame.add(newPanel);
                newFrame.setSize(400,200);
                newFrame.setVisible(true);
            }
        });
        addMemberBtn.setSize(new Dimension(150, 30));

        JButton removeMemberBtn = new JButton("Remove Member");
        removeMemberBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JFrame newFrame = new JFrame();
                addFrameToList(newFrame);

                JLabel inputMemberLabel = new JLabel("Type the name of the member you wish to be removed:");
                JTextArea memberInput = new JTextArea("Member...");
                JButton removeBtn = new JButton("Remove");
                inputMemberLabel.setBounds(0, 0, 200, 30);
                memberInput.setBounds(0, 100, 100, 30);
                removeBtn.setBounds(100, 100, 100, 30);
                removeBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    card.removeMember(memberInput.getText());
                    JOptionPane.showMessageDialog(newFrame, "Member Removed!");
                    removeFrameFromList(newFrame);
                    newFrame.setVisible(false);
                    newFrame.dispose();}
                });
                newFrame.add(removeBtn);
                newFrame.add(memberInput);
                newFrame.add(inputMemberLabel);
                // TODO: import member list
                newFrame.setSize(400,200);
                newFrame.setVisible(true);
            }
        });
        removeMemberBtn.setSize(new Dimension(150, 30));

        JButton storyPointsBtn = new JButton("Set Story Point");
        storyPointsBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JFrame newFrame = new JFrame();
                addFrameToList(newFrame);
                JPanel newPanel = new JPanel();
                newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
                addFrameToList(newFrame);
                JLabel storyPointsLabel = new JLabel("Please input the Story Point");
                SpinnerModel model = new SpinnerNumberModel(1, 1, 5, 1);             
                JSpinner pointsSpinner = new JSpinner(model);
                pointsSpinner.setEditor(new JSpinner.NumberEditor(pointsSpinner));
                pointsSpinner.setMinimumSize(new Dimension(300,100));
                pointsSpinner.setMaximumSize(new Dimension(300,100));
                JButton submitBtn = new JButton("Submit");
                submitBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(pointsSpinner.getValue().hashCode());
                        card.setStoryPoint(pointsSpinner.getValue().hashCode());
                        JOptionPane.showMessageDialog(newFrame, "Story Point saved!");
                        removeFrameFromList(newFrame);
                        newFrame.setVisible(false);
                        newFrame.dispose();
                        refreshFrame();
                    }
                });
                storyPointsLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
                pointsSpinner.setAlignmentX(JComponent.CENTER_ALIGNMENT);
                submitBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
                newPanel.add(storyPointsLabel);
                newPanel.add(pointsSpinner);
                newPanel.add(submitBtn);
                newFrame.add(newPanel);
                newFrame.setSize(400,200);
                newFrame.setVisible(true);
            }
        });



        // JButton dueDateBtn = new JButton("Due Date");
        // dueDateBtn.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         JFrame newFrame = new JFrame();
        //         JLabel dayLabel = new JLabel("DAY");
        //         JLabel monthLabel = new JLabel("MONTH");
        //         JLabel yearLabel = new JLabel("YEAR");
        //         JTextArea day = new JTextArea("01");
        //         JTextArea month = new JTextArea("01");
        //         JTextArea year = new JTextArea("2019");

        //         dayLabel.setBounds(20, 20, 40, 20);
        //         monthLabel.setBounds(60, 20, 60, 20);
        //         yearLabel.setBounds(0, 0, 50, 20);
        //         day.setBounds(20, 40, 40, 20);
        //         month.setBounds(60, 40, 60, 20);
        //         year.setBounds(120, 40, 50, 20);
        //         newFrame.add(dayLabel);
        //         newFrame.add(monthLabel);
        //         newFrame.add(yearLabel);
        //         newFrame.add(day);
        //         newFrame.add(month);
        //         newFrame.add(year);
        //         newFrame.setSize(300,300);
        //         newFrame.setVisible(true);
        //     }
        // });
        // dueDateBtn.setSize(new Dimension(150, 30));


        JLabel actionLabel = new JLabel("ACTIONS...");
        // JButton moveBtn = new JButton("Move");
        // JButton copyBtn = new JButton("Copy");
        JButton archiveBtn = new JButton("Archive");
        archiveBtn.setPreferredSize(new Dimension(150, 30));
        // JButton shareBtn = new JButton("Share");

        rightPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        rightPanel.add(addLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(addMemberBtn);
        rightPanel.add(removeMemberBtn);
        rightPanel.add(storyPointsBtn);
        // f.add(checklistBtn);
        // rightPanel.add(dueDateBtn);
        // f.add(coverBtn);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 100)));
        rightPanel.add(actionLabel);
        // f.add(moveBtn);
        // f.add(copyBtn);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(archiveBtn);
        // f.add(shareBtn);
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        return rightPanel;
    }

    public void addFrameToList(JFrame frame) {
        frames.add(frame);
    }

    public void removeFrameFromList(JFrame frame) {
        frames.remove(frame);
    }

    public void closeWindows() {
        for (JFrame frame : frames) {
            frame.setVisible(false);
            frame.dispose();
        }
    }

    public void refreshFrame() {
        mainFrame.getContentPane().removeAll();
        makeFrame();
    }
}