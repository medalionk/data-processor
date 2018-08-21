package ee.bilal.dev.dataprocessor.util;

import java.time.LocalDateTime;
import java.util.Date;

public class DateUtil {
    private DateUtil() {
        throw new AssertionError();
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return new java.sql.Timestamp(date.getTime()).toLocalDateTime();
    }
}
