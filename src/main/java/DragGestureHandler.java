/*
*This represents the drag gesture handler which reads a drag event and then initiates the drag.
* It implements interfaces  DragGestureListener, DragSourceListener, Serializable.
*
*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.io.*;

public class DragGestureHandler implements DragGestureListener, DragSourceListener, Serializable {

    private Container parent;
    private DragPane child;
    private int place = 0;
    private JPanel par;
    static Transferable transferable;
    static  boolean isCard;

    public DragGestureHandler(DragPane child, boolean isCard) {
        this.isCard = isCard;
        this.child = child;
    }
    
    /**
     * Get the panel 
     * @return  Jpanel of the child
     */
    public JPanel getPanel() {
        return  ((JPanel) child);
    }
    
    /**
     * Return whether is a card
     * @return true of is card
     */
    public static boolean getType() {
        return isCard;
    }

    /**
     * Set parent of the panel
     * @param parent parent JPanel of a panel
     */
    public void setParent(Container parent) {
        this.parent = parent;
        par = (JPanel) parent;
    }

    /**
     * get the parent of a panel
     * @return Container of the parent
     */
    public Container getParent() {
        return parent;
    }

   @Override
    public void dragGestureRecognized(DragGestureEvent dge) {

        Container parent = getPanel().getParent();
        setParent(parent);
        child.getCol();
        
        transferable = new PanelTransferable(getPanel(), getType()  );
        // Start the "drag" process...
        DragSource ds = dge.getDragSource();

        ds.startDrag(dge, null, transferable, this);
        parent.remove(getPanel());
        
        // Update the display
        parent.revalidate();
        parent.repaint();
    }

    @Override
    public void dragEnter(DragSourceDragEvent dsde) {
    }

    @Override
    public void dragOver(DragSourceDragEvent dsde) {
    }

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde) {
    }

    @Override
    public void dragExit(DragSourceEvent dse) {
    }

    @Override
    public void dragDropEnd(DragSourceDropEvent dsde) {
    
        GridBagConstraints c3 = new GridBagConstraints();
        c3.anchor = GridBagConstraints.NORTHWEST;
        c3.gridx =((DragPane)getPanel()).getInd();
        c3.gridy = 0;
        c3.weightx = 0.01;
        c3.weighty = 0.01;
        if (!dsde.getDropSuccess()) {
            getParent().add(getPanel(), c3);

        } else {    
            Card cc = child.getCard();
            child.getCol().moveCard(cc, DropHandler.mov_col);
            if(DropHandler.swap != null){
                par.add(DropHandler.swap);
            }

            /*Move card log */
            String text = Board.actLog.moveCardLog(cc, DropHandler.mov_col);
            boardGui.addNewLogLine(text);

        }

        getParent().revalidate();
        getParent().repaint();
    
    }
}