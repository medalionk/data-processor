package ee.bilal.dev.dataprocessor.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class DateUtil {

    private DateUtil() {
        throw new AssertionError();
    }

    /**
     * Convert util.Date to time.LocalDateTime via util.Timestamp
     * @param date to convert
     * @return new LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return new Timestamp(date.getTime()).toLocalDateTime();
    }

}
