import java.time.LocalDateTime;

public class Task {
    enum Type {PERSONAL, WORKING}

    private String header;
    private String description;
    private final Type type;
    private final int taskId;
    private static int id = 0;
    private LocalDateTime localDateTime;
    private final String repeatability;

    public Task(String header, String description, Type type, String repeatability, LocalDateTime localDateTime) {
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public int getTaskId() {
        return taskId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "typeTask=" + type +
                ", header='" + header + '\'' +
                ", description='" + description + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
