package biz.letsweb.fulljar.time;

import biz.letsweb.fulljar.Constants;
import biz.letsweb.fulljar.FulljarRuntimeException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Tomasz
 */
public class TimeController {
    
    public static String dateToFormattedString(Date date) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.APPLICATION_PROPERTIES.getProperty("formatter.date.format"));
        return dateFormat.format(date);
    }
    
    public static Date parseToDate(final String formattedDateString) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.APPLICATION_PROPERTIES.getProperty("formatter.date.format"));
        final Date date;
        try {
            date = dateFormat.parse(formattedDateString);
        } catch (ParseException ex) {
            throw new FulljarRuntimeException(formattedDateString + "couldn't be parsed to a date object ", ex);
        }
        return date;
    }
    
    
}
