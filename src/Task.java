import java.time.LocalDateTime;

public class Task {
    private String header;
    private String description;
    private final String type;
    private final int taskId;
    static int id = 0;
    LocalDateTime localDateTime;
    private final String repeatability;

    public Task(String header, String description, String type, String repeatability, LocalDateTime localDateTime) {
        taskId = id++;
        this.localDateTime = localDateTime;
        if (header != null) {
            this.header = header;
        } else {
            throw new RuntimeException("не указан Заголовок");
        }
        if (description != null) {
            this.description = description;
        } else {
            throw new RuntimeException("не указано Описание");
        }
        this.type = type;
        this.repeatability = repeatability;
    }

    public String getHeader() {
        return header;
    }

    public String getRepeatability() {
        return repeatability;
    }

    public int getTaskId() {
        return taskId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "header='" + header + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", taskId=" + taskId +
                ", localDateTime=" + localDateTime +
                ", repeatability='" + repeatability + '\'' +
                '}';
    }
}
