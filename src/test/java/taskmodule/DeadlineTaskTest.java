package taskmodule;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class DeadlineTaskTest {
    @Test
    public void DeadlineTask_toString_toDataString_success() {
        DeadlineTask deadlineTask =
                new DeadlineTask("do assignment", LocalDate.of(2025, 12, 5));
        assertInstanceOf(Task.class, deadlineTask);
        assertEquals("[D][ ] do assignment (by: 2025-12-05)", deadlineTask.toString());
        assertEquals("D | 0 | do assignment | 2025-12-05", deadlineTask.toDataString());
    }
}
