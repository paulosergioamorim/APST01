package models;

import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Format {
    public static final DateTimeFormatter dateFormatter;
    public static final DateTimeFormatter timeFormatter;
    public static final Pattern pessoaNomePattern;
    public static final Pattern cursoNomePattern;
    public static MaskFormatter cpfMask;
    public static MaskFormatter dateMask;
    public static MaskFormatter timeMask;
    public static MaskFormatter int2Mask;
    public static MaskFormatter int4Mask;

    static {
        dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        pessoaNomePattern = Pattern.compile("([\\p{javaLetter}\\s]+)\\s([\\p{javaLetter}\\s]+)$");
        cursoNomePattern = Pattern.compile("[\\p{javaLetter}\\s]+");
        try {
            int2Mask = new MaskFormatter("##");
            int2Mask.setPlaceholderCharacter('0');
            int4Mask = new MaskFormatter("####");
            int4Mask.setPlaceholderCharacter('0');
            cpfMask = new MaskFormatter("###.###.###-##");
            cpfMask.setPlaceholderCharacter('0');
            dateMask = new MaskFormatter("##/##/####");
            dateMask.setPlaceholderCharacter('_');
            timeMask = new MaskFormatter("##:##");
            timeMask.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static DefaultFormatterFactory getFormatterFactory(MaskFormatter mask) {
        return new DefaultFormatterFactory(mask);
    }
}
