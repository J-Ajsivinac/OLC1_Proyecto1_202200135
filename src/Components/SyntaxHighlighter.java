package Components;

import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.Color;

class SyntaxHighlighter {
    public static void setTextColor(StyledDocument doc, int start, int length, Color color) {
        Style style = doc.addStyle("TextColor", null);
        StyleConstants.setForeground(style, color);
        doc.setCharacterAttributes(start, length, style, false);
    }
}