/**
* This represents the Transfer wrapper that holds the object being transferred in the drag and drop event.
*
*/


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class PanelTransferable implements Transferable {

    private DataFlavor[] flavors = new DataFlavor[]{PanelDataFlavor.SHARED_INSTANCE};
    private JPanel panel;
    private JPanel par;
    private boolean card;
    public PanelTransferable(JPanel panel, boolean card) {
        this.panel = panel;
        this.card = card;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return flavors;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {

        boolean supported = false;

        for (DataFlavor mine : getTransferDataFlavors()) {

            if (mine.equals(flavor)) {

                supported = true;
                break;

            }

        }

        return supported;

    }

    public JPanel getPanel() {

        return panel;

    }
    public JPanel getPar() {

        return par;

    }
    
    public boolean getType() {
        
        return card;
    }
    
   

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {

        //Object[] data  =new Object[2];
       Object data = null;
        if (isDataFlavorSupported(flavor)) {

            data = getPanel();
          //  data[1] = getType();
        } else {

            throw new UnsupportedFlavorException(flavor);

        }

        return data;

    }

}