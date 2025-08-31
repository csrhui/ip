import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import taskmodule.Task;
import taskmodule.TaskList;


public class Storage {
    protected TaskList tasklist;

    public Storage(TaskList tasklist) {
        this.tasklist = tasklist;
    }

    public void saveTasks() throws IOException {
        String filePath = "data/task_storage.csv";
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        try {
            if (parentDir != null && !parentDir.exists()) {
                boolean a = parentDir.mkdirs();
            }
            if (!file.exists()) {
                boolean b = file.createNewFile();
            }

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, false))) {
                for (int i = 0; i < tasklist.getTaskCount(); i++) {
                    Task task = this.tasklist.getTask(i);
                    bufferedWriter.write(task.toDataString());
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
