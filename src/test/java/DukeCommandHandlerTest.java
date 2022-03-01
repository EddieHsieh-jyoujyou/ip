import static org.junit.jupiter.api.Assertions.assertEquals;

import commons.Constant;
import temp.DukeCommandHandler;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DukeCommandHandlerTest {
    private DukeCommandHandler dukeCommandHandler;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setup() {
        dukeCommandHandler = new DukeCommandHandler();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void cleanup() {
        dukeCommandHandler.deleteHandler("delete 1");
        System.setOut(originalOut);
    }

    @Test
    public void testByeHandler_success() {
        dukeCommandHandler.byeHandler();
        assertEquals(Constant.GOODBYE + "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }

    @Test
    public void testTodoHandler_success() {
        dukeCommandHandler.todoHandler("todo this is a task");
        assertEquals(Constant.formOutputByList(Arrays
                .asList("Got it. I've added this task:",
                        "  [T][X] this is a task",
                        "Now you have 1 tasks in the list."))+ "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }

    @Test
    public void testTodoHandler_invalidInput_failed() {
        dukeCommandHandler.todoHandler("todo");
        assertEquals(Constant.formOutputBySingleString(Constant.STRING_ERROR_EMPTY_TASK_NAME) + "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }

    @Test
    public void testTodoHandler_invalidInputWithASpace_failed() {
        dukeCommandHandler.todoHandler("todo ");
        assertEquals(Constant.formOutputBySingleString(Constant.STRING_ERROR_EMPTY_TASK_NAME) + "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }

    @Test
    public void testTodoHandler_validInputWithMultiSpace_success() {
        dukeCommandHandler.todoHandler("todo       this is a task");
        assertEquals(Constant.formOutputByList(Arrays
                        .asList("Got it. I've added this task:",
                                "  [T][X] this is a task",
                                "Now you have 1 tasks in the list."))+ "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }

    @Test
    public void testTodoHandler_invalidInputWithMultiSpace_failed() {
        dukeCommandHandler.todoHandler("todo       ");
        assertEquals(Constant.formOutputBySingleString(Constant.STRING_ERROR_EMPTY_TASK_NAME) + "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }

    @Test
    public void testDeadlineHandler_success() {
        dukeCommandHandler.deadlineHandler("deadline read /by 2021-12-14");
        assertEquals(Constant.formOutputByList(Arrays
                        .asList("Got it. I've added this task:",
                                "  [D][X] read (by: Dec 14 2021)",
                                "Now you have 1 tasks in the list."))+ "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }

    @Test
    public void testDeadlineHandler_invalidTimeFormat_failed() {
        dukeCommandHandler.deadlineHandler("deadline read /by 123");
        assertEquals(Constant.formOutputBySingleString(Constant.STRING_ERROR_INVALID_TIME_FORMAT) + "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }

    @Test
    public void testDeadlineHandler_emptyTime_failed() {
        dukeCommandHandler.deadlineHandler("deadline read /by ");
        assertEquals(Constant.ERROR_COMMAND + "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }

    @Test
    public void testDeadlineHandler_emptyTaskName_failed() {
        dukeCommandHandler.deadlineHandler("deadline /by 2021-12-14");
        assertEquals(Constant.formOutputBySingleString(Constant.STRING_ERROR_EMPTY_TASK_NAME) + "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }

    @Test
    public void testDeadlineHandler_invalidCommand_failed() {
        dukeCommandHandler.deadlineHandler("deadline read/by 2021-12-14");
        assertEquals(Constant.ERROR_COMMAND + "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }

    // Event handler and deadline handler are almost the same, so I simply add to test for it.
    @Test
    public void testEventHandler_success() {
        dukeCommandHandler.eventHandler("event read /at 2021-12-14");
        assertEquals(Constant.formOutputByList(Arrays
                        .asList("Got it. I've added this task:",
                                "  [E][X] read (at: Dec 14 2021)",
                                "Now you have 1 tasks in the list."))+ "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }

    @Test
    public void testEventHandler_invalidCommand_failed() {
        dukeCommandHandler.eventHandler("event read/at 2021-12-14");
        assertEquals(Constant.ERROR_COMMAND + "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }

    // list function won't fail. So I make two success function test for it, one for 1 task in list and the other is for
    // empty list.
    @Test
    public void listHandler_success() {
        dukeCommandHandler.todoHandler("todo this is a task");
        dukeCommandHandler.listHandler();
        assertEquals(Constant.formOutputByList(Arrays
                        .asList("Got it. I've added this task:",
                                "  [T][X] this is a task",
                                "Now you have 1 tasks in the list."))+ "\n" +
                Constant.formOutputByList(Arrays
                        .asList(Constant.STRING_SHOW_LIST,
                                "1.[T][X] this is a task")) + "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }

    @Test
    public void listHandler_emptyList_success() {
        dukeCommandHandler.listHandler();
        assertEquals(Constant.formOutputBySingleString(Constant.STRING_SHOW_LIST) + "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }

    @Test
    public void deleteHandler_success() {
        dukeCommandHandler.todoHandler("todo this is a task");
        dukeCommandHandler.deleteHandler("delete 1");
        assertEquals(Constant.formOutputByList(Arrays
                        .asList("Got it. I've added this task:",
                                "  [T][X] this is a task",
                                "Now you have 1 tasks in the list."))+ "\n" +
                        Constant.formOutputByList(Arrays
                                .asList(Constant.STRING_DELETE_SUCCESS,
                                        "  [T][X] this is a task",
                                        "Now you have 0 tasks in the list.")) + "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }

    @Test
    public void deleteHandler_invalidCommand_failed() {
        dukeCommandHandler.deleteHandler("delete  test");
        assertEquals(Constant.ERROR_COMMAND + "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }

    @Test
    public void deleteHandler_outOfBoundIndex_failed() {
        dukeCommandHandler.deleteHandler("delete 1");
        assertEquals(Constant.ERROR_WHILE_DELETE_TASK_FROM_LIST + "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }

    // done is very similar to delete, so I only provide 2 test case for it.
    @Test
    public void doneHandler_success() {
        dukeCommandHandler.todoHandler("todo this is a task");
        dukeCommandHandler.doneHandler("done 1");
        assertEquals(Constant.formOutputByList(Arrays
                        .asList("Got it. I've added this task:",
                                "  [T][X] this is a task",
                                "Now you have 1 tasks in the list."))+ "\n" +
                        Constant.formOutputByList(Arrays
                                .asList(Constant.STRING_MARK_AS_DONE,
                                        "  [T][V] this is a task")) + "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }

    @Test
    public void doneHandler_outOfBoundIndex_failed() {
        dukeCommandHandler.doneHandler("done 1");
        assertEquals(Constant.ERROR_WHILE_MARK_TASK_AS_DONE + "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }

    @Test
    public void findHandler_success() {
        dukeCommandHandler.todoHandler("todo this is a task");
        dukeCommandHandler.findHandler("task");
        assertEquals(Constant.formOutputByList(Arrays
                        .asList("Got it. I've added this task:",
                                "  [T][X] this is a task",
                                "Now you have 1 tasks in the list."))+ "\n" +
                        Constant.formOutputByList(Arrays
                                .asList(Constant.STRING_SHOW_MATCHED_LIST,
                                        "1.[T][X] this is a task")) + "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }

    @Test
    public void findHandler_listIsEmpty_success() {
        dukeCommandHandler.findHandler("task");
        assertEquals(Constant.formOutputByList(Arrays
                                .asList(Constant.STRING_SHOW_MATCHED_LIST)) + "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }

    // All invalid command should trigger this.
    @Test
    public void unknownHandler_success() {
        dukeCommandHandler.unknownHandler("test");
        assertEquals(Constant.ERROR_COMMAND + "\n",
                DukeTest.replaceCarriageReturnAndNewLineToNewLine(outputStream.toString()));
    }

}
