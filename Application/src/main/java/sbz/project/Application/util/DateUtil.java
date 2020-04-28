package sbz.project.Application.util;


import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static String formatInstant(Instant date) {
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd-mmm-yyyy hh:mm")
                .withZone(ZoneId.systemDefault());

        return formatter.format(date);
    }

    // @unit - Calendar.MINUTE, Calendar.HOUR, Calendar.SECONDS
    public static Date addTime(int unit, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(unit, amount);
        return calendar.getTime();
    }
}
