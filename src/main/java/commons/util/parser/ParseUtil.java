package commons.util.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import logic.parser.exceptions.ParseException;

public class ParseUtil {
    private final static String INPUT_DATE_TIME_FORMAT = "yyyy-MM-dd";
    private final static String OUTPUT_DATE_TIME_FORMAT = "MMM dd yyyy";

    public static LocalDate parseStringToLocalDate(String date) throws ParseException {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern(INPUT_DATE_TIME_FORMAT));
        } catch (DateTimeParseException e) {
            throw new ParseException("DateTimeParseException");
        } catch (RuntimeException e) {
            throw new ParseException("RuntimeException");
        }
    }

    public static String parseLocalDateToString(LocalDate date) throws ParseException {
        try {
            return date.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_TIME_FORMAT));
        } catch (DateTimeParseException e) {
            throw new ParseException("DateTimeParseException");
        } catch (RuntimeException e) {
            throw new ParseException("RuntimeException");
        }
    }
}
