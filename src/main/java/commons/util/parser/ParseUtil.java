package commons.util.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import logic.parser.exceptions.ParseException;

public class ParseUtil {
    private static final String INPUT_DATE_TIME_FORMAT = "yyyy-MM-dd";
    private static final String OUTPUT_DATE_TIME_FORMAT = "MMM dd yyyy";

    /**
     * Parse a String to LocalDate.
     *
     * @param date string of date
     * @return LocalDate
     * @throws ParseException exception occur while using LocalDate.parse()
     */
    public static LocalDate parseStringToLocalDate(String date) throws ParseException {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern(INPUT_DATE_TIME_FORMAT));
        } catch (DateTimeParseException e) {
            throw new ParseException("DateTimeParseException");
        } catch (RuntimeException e) {
            throw new ParseException("RuntimeException");
        }
    }

    /**
     * Parse a LocalDate to String.
     *
     * @param date LocalDate of date
     * @return String
     * @throws ParseException  exception occur while using LocalDate.format()
     */
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
