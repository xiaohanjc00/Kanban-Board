
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.datatransfer.*;
public class PanelDataFlavor extends DataFlavor {

    public static final PanelDataFlavor SHARED_INSTANCE = new PanelDataFlavor();

    public PanelDataFlavor() {

        super(JPanel.class, null);

    }

}