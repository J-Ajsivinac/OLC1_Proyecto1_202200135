package Components.tabs;

import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

public class TabbedNotepad {

    static int count = 1;
    JTabbedPane _tabbedPane;
    DefaultListModel listModel;

    public TabbedNotepad() {

    }

    public void File_New_Action() {
        //crerate textpane object    
        JTextPane _textPane = new JTextPane();

        _textPane.setFont(new Font("Calibri", Font.PLAIN, 14));

        JScrollPane jsp = new JScrollPane(_textPane);
        // add key listener & Undoable edit listener to text pane    
        //add tab to _tabbedPane with control textpane    
        _tabbedPane.addTab("Document " + count + " ", jsp);
        //add caret listener & mouse listener to text pane    

        int index = _tabbedPane.getTabCount() - 1;

        _tabbedPane.setSelectedIndex(index);

        // set save icon to added tab    
        listModel.addElement("Document " + count + " ");


        //change the title    

        count++;

    }

}
