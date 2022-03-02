//package model;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import commons.Constant;
//import model.exceptions.TaskException;
//
//import org.junit.jupiter.api.Test;
//
//public class TaskTest {
//
//    @Test
//    public void testTaskConstructor_success() throws TaskException {
//        Task task = new Task("task name", TaskTypeEnum.TODO, "");
//        assertEquals("task name", task.getTaskName());
//        assertEquals(TaskTypeEnum.TODO, task.getType());
//        assertEquals(false, task.getIsDone());
//        assertNull(task.getSpecificTime());
//    }
//
//    @Test
//    public void testTaskConstructor_emptyTaskName_failed() {
//        TaskException thrown = assertThrows(TaskException.class, () -> {
//            Task task = new Task("", TaskTypeEnum.TODO, "");
//        });
//        assertEquals(Constant.STRING_ERROR_EMPTY_TASK_NAME, thrown.getMessage());
//    }
//
//    @Test
//    public void testTaskConstructor_invalidTimeFormant_failed() {
//        TaskException thrown = assertThrows(TaskException.class, () -> {
//            Task task = new Task("task", TaskTypeEnum.EVENT, "invalid time format");
//        });
//        assertEquals(Constant.STRING_ERROR_INVALID_TIME_FORMAT, thrown.getMessage());
//    }
//
//    @Test
//    public void testToFileOutput_success() throws TaskException {
//        Task task = new Task("task name", TaskTypeEnum.TODO, "");
//        assertEquals(Constant.SINGLE_CHARACTER_TASK_TYPE_TODO + " | 0 | task name\n" , task.toFileOutput());
//    }
//
//}
