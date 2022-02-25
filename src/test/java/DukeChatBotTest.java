import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.Task;
import model.TaskTypeEnum;
import utils.Constant;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DukeChatBotTest {
    private DukeChatBot dukeChatBot;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setup() {
        dukeChatBot = new DukeChatBot();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void cleanup() {
        dukeChatBot.deleteTaskFromListWithIndex(1);
        System.setOut(originalOut);
    }

    /**
     * Basically all test about addStringToList should be tested in DukeCommandHandler.
     */
    @Test
    public void testAddStringToList_success() {
        dukeChatBot.addStringToList("task name", TaskTypeEnum.TODO, "");
        Task task = dukeChatBot.getListOfChatBotContent().get(0);
        assertEquals("task name", task.getTaskName());
        assertEquals(TaskTypeEnum.TODO, task.getType());
        assertEquals(false, task.getIsDone());
        assertNull(task.getSpecificTime());
    }

    /**
     * In DukeChatBot private method, will remove extra space in front of the task name.
     */
    @Test
    public void testAddStringToList_todoWithExtraSpace_success() {
        dukeChatBot.addStringToList("         task name", TaskTypeEnum.TODO, "");
        Task task = dukeChatBot.getListOfChatBotContent().get(0);
        assertEquals("task name", task.getTaskName());
        assertEquals(TaskTypeEnum.TODO, task.getType());
        assertEquals(false, task.getIsDone());
        assertNull(task.getSpecificTime());
    }

    /**
     * This case should never happen because DukeCommandHandler won't pass time parameter to DukeChatBot, however, this
     * case is handled in Task Constructor, while constructing Task with NOT_EMPTY value, Task constructor would ignore
     * this value and set Time as null.
     */
    @Test
    public void testAddStringToList_todoWithNotEmptyTime_success() {
        dukeChatBot.addStringToList("task name", TaskTypeEnum.TODO, "strange case with value");
        Task task = dukeChatBot.getListOfChatBotContent().get(0);
        assertEquals("task name", task.getTaskName());
        assertEquals(TaskTypeEnum.TODO, task.getType());
        assertEquals(false, task.getIsDone());
        assertNull(task.getSpecificTime());
    }

    /**
     * All testing about deleteTaskFromListWithIndex should be tested in DukeCommandHandler, except the case that
     * index equals to zero/index is greater than list size.
     */
    @Test
    public void testDeleteTaskFromListWithIndex_indexOutOfBound_failed() {
        dukeChatBot.deleteTaskFromListWithIndex(0);
        dukeChatBot.deleteTaskFromListWithIndex(1);
        assertEquals(Constant.ERROR_WHILE_DELETE_TASK_FROM_LIST + "\n"
                + Constant.ERROR_WHILE_DELETE_TASK_FROM_LIST + "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }

    @Test
    public void testMarkAsDone_indexOutOfBound_failed() {
        dukeChatBot.markTaskAsDone(0);
        dukeChatBot.markTaskAsDone(1);
        assertEquals(Constant.ERROR_WHILE_MARK_TASK_AS_DONE + "\n"
                        + Constant.ERROR_WHILE_MARK_TASK_AS_DONE + "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }
}
