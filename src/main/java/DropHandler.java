/**
*This represents the DropHandler which reads a drop event, and processes the drop.
*
*/


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.io.*;
import java.util.*;
public class DropHandler implements DropTargetListener, Serializable {

    public static JPanel swap;
    public static Column mov_col;
    public void dragEnter(DropTargetDragEvent dtde) {

        
        if (dtde.isDataFlavorSupported(PanelDataFlavor.SHARED_INSTANCE)) {

            dtde.acceptDrag(DnDConstants.ACTION_MOVE);

        } else {

            dtde.rejectDrag();

        }

    }

    public void dragOver(DropTargetDragEvent dtde) {
    }


    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    public void dragExit(DropTargetEvent dtde) {
    }

   @Override
    public void drop(DropTargetDropEvent dtde) {
        boolean success = false;
        // Basically, we want to unwrap the present...
        if (dtde.isDataFlavorSupported(PanelDataFlavor.SHARED_INSTANCE)) {
            Transferable transferable = DragGestureHandler.transferable;
            try {
                Object data = transferable.getTransferData(PanelDataFlavor.SHARED_INSTANCE);
                
                
                boolean checkifCard =  DragGestureHandler.getType();
                /////
                if (data instanceof JPanel) {
                    JPanel panel = (JPanel) data;
                    DropTargetContext dtc = dtde.getDropTargetContext();
                    Component component = dtc.getComponent();
                    if (component instanceof JComponent) {
                        
                        //checking if the dragged object is the column itself
                        
                        if(checkifCard == false){
                        Container parent = panel.getParent();
                        if (parent != null) {
                            parent.remove(panel);
                            parent.revalidate();
                            parent.repaint();
                        }
                        Component[] cc = ((JPanel) component).getComponents();
                        //if the container being moved to has no contents then simply add the dragged component.
                        if(cc.length == 0)
                        {
                        ((JPanel) component).add(panel);
                        success = true;
                        dtde.acceptDrop(DnDConstants.ACTION_MOVE);
                        ((JPanel) component).invalidate();
                        ((JPanel)component).repaint();
                        }
                        //if the container being moved to, has one or more content in it, then the dragged component and this child component must be swapped.
                        else
                        {
                            swap =(JPanel)(cc[0]);
                            JPanel box = ((JPanel) component);
                        ((JPanel) component).removeAll();
                        ((JPanel) component).add(panel);
                            
                        success = true;
                        dtde.acceptDrop(DnDConstants.ACTION_MOVE);
                        ((JPanel) component).revalidate();
                        ((JPanel) component).repaint(); 
                            panel.revalidate();
                            panel.repaint();
                        }
                        }
                        
                        //checking if the dragged object is a card
                        else
                        {
                            
                            DropPane x = ((DropPane) component);
                            mov_col = x.getCol();
                            swap = null;
                        Container parent = panel.getParent();
                        
                        if (parent != null) {
                            parent.remove(panel);
                            parent.revalidate();
                            parent.repaint();
                        }
                            
                            
                        JPanel child1 = ((JPanel) ((JPanel) component).getComponents()[0]);
                        JPanel child2 = ((JPanel) child1.getComponents()[0]);
                        JPanel child3 = ((JPanel) child2.getComponents()[1]);

                    (child3).add(panel);
                        success = true;
                        dtde.acceptDrop(DnDConstants.ACTION_MOVE);
                        child3.revalidate();
                        child3.repaint();
                        ((JPanel) component).revalidate();
                        ((JPanel) component).repaint(); 
                            panel.revalidate();
                            panel.repaint();    
                        }
                        
                    } else {
                        success = false;
                        dtde.rejectDrop();
                    }
                } else {
                    success = false;
                    dtde.rejectDrop();
                }
            } catch (Exception exp) {
                success = false;
                dtde.rejectDrop();
                exp.printStackTrace();
            }
        } else {
            success = false;
            dtde.rejectDrop();
        }
        dtde.dropComplete(success);
    }
}
