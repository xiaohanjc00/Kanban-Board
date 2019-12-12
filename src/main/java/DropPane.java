/**
* This represents a JPanel that acts as a drop area where drag objects can be dropped.

* It uses the DropHandler class to link with Drop events.


*/


import java.awt.Color;
import java.awt.Dimension;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import javax.swing.JPanel;

public class DropPane extends JPanel {

    DropTarget dropTarget;
    DropHandler dropHandler;
    Column cur_col;
    public DropPane(Column cur_col){
        this.cur_col = cur_col;
    }
   
    public void addNotify() {
        super.addNotify(); //To change body of generated methods
        dropHandler = new DropHandler();
        dropTarget = new DropTarget(this, DnDConstants.ACTION_MOVE, dropHandler, true);
    }


    public void removeNotify() {
        super.removeNotify(); //To change body of generated methods
        dropTarget.removeDropTargetListener(dropHandler);
    }

    public Column getCol()
    {
        return cur_col;
    }

}