package Components;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class CSyntaxHighlighter {

    public static void highlight(JTextPane textPane, StyledDocument doc) {
        // Define syntax highlighting patterns
        String keywords = "\\b(int|char|float|double|long|short|void|if|else|while|for|switch|case|break|return)\\b";
        String comments = "//.*|/\\*(.|\\R)*?\\*/ ";
        String dataTypes = "\\b([A-Za-z_]\\w*)\\b";
        String macros = "#\\w+";
        String numbers = "\\b\\d+\\.?\\d*\\b";
        String strings = "\".*?\"";
        String functions = "\\b[A-Za-z_]\\w*\\s*\\(";

        // Set font style attributes for each type
        Style keywordStyle = textPane.addStyle("KeywordStyle", null);
        StyleConstants.setForeground(keywordStyle, java.awt.Color.BLACK);

        Style commentStyle = textPane.addStyle("CommentStyle", null);
        StyleConstants.setForeground(commentStyle, java.awt.Color.GREEN);

        Style dataTypeStyle = textPane.addStyle("DataTypeStyle", null);
        StyleConstants.setForeground(dataTypeStyle, java.awt.Color.blue);

        Style macroStyle = textPane.addStyle("MacroStyle", null);
        StyleConstants.setForeground(macroStyle, java.awt.Color.black);

        Style numberStyle = textPane.addStyle("NumberStyle", null);
        StyleConstants.setForeground(numberStyle, java.awt.Color.magenta);

        Style stringStyle = textPane.addStyle("StringStyle", null);
        StyleConstants.setForeground(stringStyle, java.awt.Color.magenta);

        Style functionStyle = textPane.addStyle("FunctionStyle", null);
        StyleConstants.setForeground(functionStyle, java.awt.Color.yellow);

        // Combine patterns into a single pattern
        String pattern = String.format("(%s)|(%s)|(%s)|(%s)|(%s)|(%s)|(%s)",
                keywords, comments, dataTypes, macros, numbers, strings, functions);

        Pattern compiledPattern = Pattern.compile(pattern);

        try {
            String text = doc.getText(0, doc.getLength());
            Matcher matcher = compiledPattern.matcher(text);

            while (matcher.find()) {
                if (matcher.group(1) != null) {
                    doc.setCharacterAttributes(matcher.start(1), matcher.end(1) - matcher.start(1), keywordStyle, false);
                } else if (matcher.group(2) != null) {
                    doc.setCharacterAttributes(matcher.start(2), matcher.end(2) - matcher.start(2), commentStyle, false);
                } else if (matcher.group(3) != null) {
                    doc.setCharacterAttributes(matcher.start(3), matcher.end(3) - matcher.start(3), dataTypeStyle, false);
                } else if (matcher.group(4) != null) {
                    doc.setCharacterAttributes(matcher.start(4), matcher.end(4) - matcher.start(4), macroStyle, false);
                } else if (matcher.group(5) != null) {
                    doc.setCharacterAttributes(matcher.start(5), matcher.end(5) - matcher.start(5), numberStyle, false);
                } else if (matcher.group(6) != null) {
                    doc.setCharacterAttributes(matcher.start(6), matcher.end(6) - matcher.start(6), stringStyle, false);
                } else if (matcher.group(7) != null) {
                    doc.setCharacterAttributes(matcher.start(7), matcher.end(7) - matcher.start(7), functionStyle, false);
                }
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}
