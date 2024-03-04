package Components;

import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import java.awt.Color;
import javax.swing.text.StyleConstants;

public class JavaSyntaxHighlighter {

    public static void highlight(JTextPane textPane, StyledDocument doc) {
        // Define syntax highlighting styles for Java
        Style defaultStyle = textPane.getStyle(StyleContext.DEFAULT_STYLE);
        Style keywordStyle = textPane.addStyle("KeywordStyle", defaultStyle);
        StyleConstants.setForeground(keywordStyle, Color.BLUE);

        // Apply styles to keywords
        String[] keywords = {
            "abstract", "boolean", "break", "class", "extends", "for", "if", "new", "return",
            "while", "public", "private", "static", "void", "int", "double"
        };

        for (String keyword : keywords) {
            String text = textPane.getText();
            int pos = 0;

            while ((pos = text.indexOf(keyword, pos)) >= 0) {
                SyntaxHighlighter.setTextColor(doc, pos, keyword.length(), Color.BLUE);
                pos += keyword.length();
            }
        }
    }
}
