/**
* This represents a JPanel that can be dragged.

* It uses the DragGestureHandler class to link with Drag events.

*/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import javax.swing.JPanel;

public class DragPane extends JPanel {

    private DragGestureRecognizer dgr;
    private DragGestureRecognizer dgr1;
    private DragGestureHandler dragGestureHandler;

    private DragGestureHandler dragGestureHandler1;
    private int index;
    private boolean isCard;
    private Column cur_col;
    private Card cur_card;
    public DragPane(int ind, boolean isCard){
        this.isCard = isCard;
        index = ind;
        dragGestureHandler1 = new DragGestureHandler(this, isCard);
        dgr1 = DragSource.getDefaultDragSource().createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, dragGestureHandler1);
    }
     
    public DragPane(Column cur_col, Card cur_card, boolean isCard) {
        this.isCard = isCard;
        this.cur_card = cur_card;
        this.cur_col = cur_col;
        dragGestureHandler = new DragGestureHandler(this, isCard);
        dgr = DragSource.getDefaultDragSource().createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, dragGestureHandler);
    }
    
    public Column getCol()
    {
        return cur_col;
    }
    
    public Card getCard()
    {
        return cur_card;
    }
    public int getInd(){
        return index;
    }


}