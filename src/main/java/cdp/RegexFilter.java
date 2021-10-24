package cdp;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An extension of the DocumentFilter for using Patterns
 * as filters, just like a MaskFormatter.
 * <p>
 * <strong>This implementation is still unfinished, not all operators work correctly
 * please take a look at the code and test to see which implementations works</strong>
 *
 * @author Hanake0
 */
public class RegexFilter extends DocumentFilter {
    private final Matcher             originalMatcher;
    private final JFormattedTextField formattedTextField;
    private       Matcher             partialMatcher;
    private       String              placeholderStr;
    private       String              placeHolder;

    /**
     * Create a new RegexFilter instance and sets this as the internal document filter
     * <p>
     * <strong>This implementation is still unfinished, not all operators work correctly
     * please take a look at the code and test to see which implementations works</strong>
     *
     * @param pattern            The pattern to filter input for
     * @param formattedTextField The field this filter will act upon
     */
    public RegexFilter(Pattern pattern, JFormattedTextField formattedTextField) {
        this.originalMatcher    = pattern.matcher("");
        this.formattedTextField = formattedTextField;
        this.setPlaceHolder('_');
        AbstractDocument absDoc = (AbstractDocument) formattedTextField.getDocument();
        absDoc.setDocumentFilter(this);

        // Sets the caret position to the first placeholder character it encounters
        // everytime the field is clicked
        formattedTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                String text = null;
                try {
                    text = formattedTextField.getDocument().getText(0, formattedTextField.getDocument().getLength());
                } catch (BadLocationException badLocationException) {
                    formattedTextField.setCaretPosition(0);
                } finally {
                    int index = text.indexOf(placeHolder) == -1 ? text.length() : text.indexOf(placeHolder);
                    formattedTextField.setCaretPosition(index);
                }
            }
        });
    }

    /**
     * Changes the default placeholder, also updating internal values.
     * Using this method will also replace the document content with a placeholder string.
     * <p>
     * If it fails to replace the document content, creates a new PlainDocument.
     *
     * @param placeholder the string to use as placeholder
     */
    public void setPlaceHolder(char placeholder) {
        this.placeHolder = "_";

        // Create the partial Regex
        String partialRegex = this.originalMatcher.pattern().toString();

        Pattern setDefinitionCompiler = Pattern.compile("(\\[.*?\\](?!\\?)[+*]?)", Pattern.MULTILINE);
        partialRegex = setDefinitionCompiler.matcher(partialRegex).replaceAll("($1|"+ placeholder +")");

        this.partialMatcher = Pattern.compile(partialRegex).matcher("");

        // Create the placeholder String
        String placeholderStr = setDefinitionCompiler
                .matcher(this.originalMatcher.pattern().toString())
                .replaceAll(String.valueOf(placeholder));

        // Remove back slashes
        Pattern backSlashCompiler = Pattern.compile("(\\\\(?!\\\\))", Pattern.MULTILINE);
        placeholderStr = backSlashCompiler.matcher(placeholderStr).replaceAll("");

        // Remove quantifiers
        Pattern quantifiersCompiler = Pattern.compile("([+*?]|(\\{[0-9]+,?[0-9]+\\}))", Pattern.MULTILINE);
        placeholderStr = quantifiersCompiler.matcher(placeholderStr).replaceAll("");

        this.placeholderStr = placeholderStr;

        // Try setting the new placeholder string
        try {
            Document doc = this.formattedTextField.getDocument();
            doc.remove(0, doc.getLength());
            doc.insertString(0, placeholderStr, null);

            // If it fails create a new empty document
        } catch (BadLocationException e) {
            Document newDoc = new PlainDocument();
            try {
                newDoc.insertString(0, this.placeholderStr, null);
            } catch (BadLocationException badLocationException) {
                badLocationException.printStackTrace();
            }

            // Sets this as the document filter
            AbstractDocument absNewDoc = (AbstractDocument) formattedTextField.getDocument();
            absNewDoc.setDocumentFilter(this);
            this.formattedTextField.setDocument(newDoc);
        }
    }

    /**
     * Checks if the document content matches the internal Pattern
     *
     * @return true if the document content matches the Pattern,
     * false if not.
     */
    public boolean isExactMatch() {
        String docText = this.formattedTextField.getText();
        return this.originalMatcher.reset(docText).matches();
    }

    /**
     * Resets the field text to the placeholder string
     */
    public void cleanField() {
        this.formattedTextField.setText(
                this.placeholderStr);
    }

    /**
     * Invoked prior to removal of the specified region in the Document.
     * Will only remove input inserted through the <code>replace</code> method
     *
     * @param fb     FilterBypass that can be used to mutate Document
     * @param offset the offset from the beginning &gt;= 0
     * @param length the number of characters to remove &gt;= 0
     *
     * @throws BadLocationException if some portion of the removal range
     *                              was not a valid part of the document.
     *                              The location in the exception is the
     *                              first bad position encountered.
     */
    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        Document doc = fb.getDocument();
        String docText = doc.getText(0, doc.getLength());
        if(offset == doc.getLength()) return;

        // Repeat this for each backspace
        for (int i = 0; i < length; i++) {

            // Try removing each character before the offset, until it finds one
            for (int j = offset; j >= 0 ; j--) {
                String docInit = docText.substring(0, j);
                String docEnd  = docText.substring(j + 1);

                // Try to just remove the character
                if (this.partialMatcher.reset(docInit + docEnd).matches()) {
                    fb.remove(j, 1);
                    this.formattedTextField.setCaretPosition(j);
                    offset = j;
                    break;

                    // Else, try to remove and replace it with a placeholder
                } else if(this.partialMatcher.reset(docInit+ this.placeHolder +docEnd).matches()) {
                    fb.remove(j, 1);
                    fb.insertString(j, this.placeHolder, null);
                    this.formattedTextField.setCaretPosition(j);
                    offset = j;
                    break;

                    // If none of the above works and there is no input left, throws an exception
                } else if (j == 0)
                    throw new BadLocationException("No input to remove", offset);
            }
        }
    }

    /**
     * Invoked prior to insertion of a string in the Document.
     * Will try to use the whole text as replacement, if it fails
     * will insert char by char until an exception is thrown.
     *
     * @param fb     FilterBypass that can be used to mutate Document
     * @param offset the offset into the document to insert the content &gt;= 0.
     *               All positions that track change at or after the given location
     *               will move.
     * @param string the string to insert
     * @param attr   the attributes to associate with the inserted
     *               content.  This may be null if there are no attributes.
     *
     * @throws BadLocationException if the given insert position is not a
     *                              valid position within the document
     */
    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        Document doc = fb.getDocument();

        // Try using the whole raw string as a match
        if (this.partialMatcher.reset(string).matches()) {
            fb.remove(0, doc.getLength());
            fb.insertString(0, string, null);
            return;
        }

        // Just so we don't call StringBuilder unnecessarily
        String docText = doc.getText(0, doc.getLength());

        // Else, try inserting char by char
        for (char c : string.toCharArray()) {
            this.insertChar(fb, offset, c, attr);
            int nextPH = docText.indexOf(this.placeHolder, offset);
            offset = (nextPH == -1) ? offset + 1 : nextPH + 1;
        }
    }

    /**
     * Will try to insert a single character in a position,
     * first by just inserting, and if it fails, by replacing
     * it with the character at the next possible location.
     *
     * @param fb     FilterBypass that can be used to mutate Document
     * @param offset the offset into the document to insert the content &gt;= 0.
     *               All positions that track change at or after the given location
     *               will move.
     * @param c      the character to insert
     * @param attr   the attributes to associate with the inserted
     *               content.  This may be null if there are no attributes.
     *
     * @throws BadLocationException if the given insert position is not a
     *                              valid position within the document
     */
    public void insertChar(DocumentFilter.FilterBypass fb, int offset, char c, AttributeSet attr) throws BadLocationException {
        String docText = fb.getDocument().getText(0, fb.getDocument().getLength());
        String docInit = docText.substring(0, offset);
        String docEnd  = docText.substring(offset);

        // Replace a placeholder if its exactly on the offset
        if (docText.indexOf(this.placeHolder, offset) == offset) {
            docInit = docText.substring(0, offset);
            docEnd  = docText.substring(offset + 1);

            if (this.partialMatcher.reset(docInit + c + docEnd).matches()) {
                fb.remove(offset, 1);
                fb.insertString(offset, String.valueOf(c), attr);

                this.formattedTextField.setCaretPosition(offset + 1);
                return;
            }
        }

        // Inclusive addition, without replacing
        if (this.partialMatcher.reset(docInit + c + docEnd).matches()) {
            fb.insertString(offset, String.valueOf(c), attr);
            this.formattedTextField.setCaretPosition(offset + 1);
            return;
        }

        // Tries to replace next placeholder in the string
        if (docText.indexOf(this.placeHolder, offset) != -1) {
            int nextPH = docText.indexOf(this.placeHolder, offset);
            docInit = docText.substring(0, nextPH);
            docEnd  = docText.substring(nextPH + 1);

            if (this.partialMatcher.reset(docInit + c + docEnd).matches()) {
                fb.remove(nextPH, 1);
                fb.insertString(nextPH, String.valueOf(c), attr);

                this.formattedTextField.setCaretPosition(nextPH + 1);
                nextPH = docText.indexOf(this.placeHolder, nextPH + 1);
                if (nextPH != -1) this.formattedTextField.setCaretPosition(nextPH);
                return;
            }
        }

        // Throws Exception if character cannot be added
        throw new BadLocationException("Addition of this character does not match regex", offset);
    }

    /**
     * Invoked prior to replacing a region of text in the Document.
     * Will try to use the whole text as replacement, if it fails
     * will insert char by char until an exception is thrown.
     *
     * @param fb     FilterBypass that can be used to mutate Document
     * @param offset Location in Document
     * @param length Length of text to delete
     * @param text   Text to insert, null indicates no text to insert
     * @param attrs  AttributeSet indicating attributes of inserted text,
     *               null is legal.
     *
     * @throws BadLocationException if the given insert position is not a
     *                              valid position within the document
     */
    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        Document doc = fb.getDocument();

        // Just so we don't call StringBuilder unnecessarily
        String docText = doc.getText(0, doc.getLength());
        String docInit = docText.substring(0, offset);
        String docEnd  = docText.substring(offset + length);

        // Try inserting the whole string in position
        if ( length > 0 && this.partialMatcher.reset(docInit + text + docEnd).matches()) {
            fb.remove(offset, length);
            fb.insertString(offset, text, attrs);

            // Else, try inserting char by char
        } else for (char c : text.toCharArray()) {
            this.insertChar(fb, offset, c, attrs);
            int nextPH = docText.indexOf(this.placeHolder, offset);
            offset = (nextPH == -1) ? offset + 1 : nextPH + 1;
        }
    }
}

