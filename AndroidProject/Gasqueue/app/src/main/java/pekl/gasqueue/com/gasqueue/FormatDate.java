package pekl.gasqueue.com.gasqueue;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by kevin on 10/05/2016.
 */
public class FormatDate {
    public FormatDate() {
    }
    public String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy.MM.dd  HH:mm ");
        String date = formatDate.format(cal.getTime());
        return date;
    }
}

