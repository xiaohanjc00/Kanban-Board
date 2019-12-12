/** 
 * Represents the Card GUI for the application. 
 * It represents the new JFrame that pops up when a card is viewed.
 * It contains different functionalities to modify the state of a card in the board.
 */
import javax.swing.*;
import javax.swing.JSpinner.NumberEditor;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class CardGUI {

    private Card card;
    private ArrayList<JFrame> frames;
    private JFrame mainFrame;
    private ColumnGUI columnGui;

     public CardGUI(Card card, ColumnGUI columnGuiIn) {
        this.card = card;
        mainFrame = new JFrame();
        frames = new ArrayList<>();
        columnGui = columnGuiIn;
        makeFrame();
     }
    
    /**
    * This creates the new JFrame displaying the Card. it contains all options to modidy or edit a card.
    */

     public void makeFrame() {

         /* The Top Panel of the card Frame that contains the Title of the Card, Story Points of the Card and the Close Button */
         
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

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(topPanel, BorderLayout.PAGE_START);

        panel.add(makeLeftPanel(), BorderLayout.CENTER);

        panel.add(makeRightPanel(), BorderLayout.LINE_END);
        
        mainFrame.add(panel);
        mainFrame.setSize(600,800);
        mainFrame.setVisible(true);
    }
    
    /**
    * This creates the leftPanel of the Card Frame that contains the description of the card.
    * Returns a JPanel.
    */

    public JPanel makeLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        String members = "Members: ";
        for (String member : card.getMembers()) {
            members = members + member + " ";
        }
        JLabel membersLabel = new JLabel(members);

        JLabel descriptionLabel = new JLabel("Description");
        JTextArea descriptionText = new JTextArea(card.getDescription());
        descriptionText.setMinimumSize(new Dimension(300,100));
        descriptionText.setMaximumSize(new Dimension(300,100));
        descriptionText.setLineWrap(true);
        JButton saveDescriptionBtn = new JButton("Save Description");
        saveDescriptionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                card.setDescription(descriptionText.getText());
                JOptionPane.showMessageDialog(leftPanel, "Description Saved!");
                columnGui.refreshColumn();
                
                /*Edit description log */
                String text = Board.actLog.setDescriptionLog(card);
                BoardGUI.addNewLogLine(text);
            }
        });

        descriptionLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        descriptionText.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        saveDescriptionBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        leftPanel.add(descriptionLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        leftPanel.add(descriptionText);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        leftPanel.add(saveDescriptionBtn);
        
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        return leftPanel;
    }
    
    /**
    * This creates the rightPanel of the Card Frame that contains the different
    * actions on a card. 
    * These include, editting story points and editting card title.
    * Returns a JPanel.
    */

    public JPanel makeRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        JLabel actionLabel = new JLabel("CARD ACTIONS...");

        JButton storyPointsBtn = new JButton("Set Story Point");
        storyPointsBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JFrame newFrame = new JFrame();
                addFrameToList(newFrame);
                JPanel newPanel = new JPanel();
                newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
                JLabel storyPointsLabel = new JLabel("Please input the Story Point:");
                SpinnerModel model = new SpinnerNumberModel(1, 1, 5, 1);             
                JSpinner pointsSpinner = new JSpinner(model);
                pointsSpinner.setEditor(new JSpinner.NumberEditor(pointsSpinner));
                ((JSpinner.DefaultEditor) pointsSpinner.getEditor()).getTextField().setEditable(false);
                pointsSpinner.setMinimumSize(new Dimension(300,100));
                pointsSpinner.setMaximumSize(new Dimension(300,100));
                JButton submitBtn = new JButton("Submit");
                submitBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        card.setStoryPoint(pointsSpinner.getValue().hashCode());
                        JOptionPane.showMessageDialog(newFrame, "Story Point saved!");
                        removeFrameFromList(newFrame);
                        newFrame.setVisible(false);
                        newFrame.dispose();
                        refreshFrame();
                        columnGui.refreshColumn();

                        /*Edit story point log */
                        String text = Board.actLog.setStoryPointLog(card);
                        BoardGUI.addNewLogLine(text);
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

        JButton cardTitleBtn = new JButton("Change Card Title");
        cardTitleBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JFrame newFrame = new JFrame();
                addFrameToList(newFrame);
                JPanel newPanel = new JPanel();
                newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
                JLabel cardTitleLabel = new JLabel("Please input the new card title:");
                JTextArea cardTitleText = new JTextArea(card.getTitle());
                cardTitleText.setMinimumSize(new Dimension(150,50));
                cardTitleText.setMaximumSize(new Dimension(150,50));
                cardTitleText.setLineWrap(true);
                JButton submitBtn = new JButton("Submit");
                submitBtn.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        String temp = card.getTitle();
                        card.setTitle(cardTitleText.getText());
                        JOptionPane.showMessageDialog(newFrame, "Card Title saved!");
                        removeFrameFromList(newFrame);
                        newFrame.setVisible(false);
                        newFrame.dispose();
                        refreshFrame();
                        columnGui.refreshColumn();

                        /*Edit card title log */
                        String text = Board.actLog.editCardTitleLog(temp, card);
                        BoardGUI.addNewLogLine(text);
                    }
                });
                cardTitleLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
                cardTitleText.setAlignmentX(JComponent.CENTER_ALIGNMENT);
                submitBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
                newPanel.add(cardTitleLabel);
                newPanel.add(cardTitleText);
                newPanel.add(submitBtn);
                newFrame.add(newPanel);
                newFrame.setSize(400,200);
                newFrame.setVisible(true);
            }
        });

        rightPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        rightPanel.add(actionLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(storyPointsBtn);
        rightPanel.add(cardTitleBtn);
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

    /*
    *This refreshes a Card Frame instance by removing all contents of the Frame, 
    * and then making the Frame all over again.
    */
    
    public void refreshFrame() {
        mainFrame.getContentPane().removeAll();
        makeFrame();
    }
}
