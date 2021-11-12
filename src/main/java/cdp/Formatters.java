package cdp;

import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;

public class Formatters {
    public static final DateTimeFormatter dateFormatter;
    public static final DateTimeFormatter timeFormatter;
    public static MaskFormatter cpfMask;
    public static MaskFormatter dateMask;
    public static MaskFormatter timeMask;
    public static MaskFormatter int2Mask;
    public static MaskFormatter int4Mask;

    public static DefaultFormatterFactory getFormatterFactory(MaskFormatter mask) { return new DefaultFormatterFactory(mask); }

    static {
        dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
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
        } catch (ParseException e) { e.printStackTrace(); }
    }
}
