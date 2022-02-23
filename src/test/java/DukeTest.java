import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DukeTest {
    @Test
    @DisplayName("This is the entry point of Duke test.")
    void testEntrySet_success() {

    }

    public static String replaceCarriageReturnAndNewLineToNewLine(String input) {
        return input.replace("\r\n", "\n");
    }
}
