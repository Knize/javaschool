package ru.knize.hyperloop.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by knize on 15.10.16.
 */
public class TimeUtil {

    private static Timestamp parseTimestamp(String str, SimpleDateFormat sdf){
        try {
            System.out.println("Timestamp: " + str);
            Date timestampDate = sdf.parse(str);
            long time = timestampDate.getTime();
            return new Timestamp(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final SimpleDateFormat timestampFormat = new SimpleDateFormat("dd MMMMMMMMMMMM, yyyy hh:mm");
    private static final SimpleDateFormat timestampDateFormat = new SimpleDateFormat("dd MMMMMMMMMMMM, yyyy");

    public static Timestamp parseTimestamp(String timestampStr) {
        return parseTimestamp(timestampStr, timestampFormat);
    }

    public static Timestamp parseTimestampDate(String timestampStr) {
        return parseTimestamp(timestampStr, timestampDateFormat);
    }

}
