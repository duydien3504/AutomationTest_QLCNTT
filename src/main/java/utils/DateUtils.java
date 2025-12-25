package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter INPUT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter OUTPUT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String formatDate(String date) {
        return LocalDate.parse(date, INPUT).format(OUTPUT);
    }

    public static String formatDateRange(String start, String end) {
        return formatDate(start) + " - " + formatDate(end);
    }
}
