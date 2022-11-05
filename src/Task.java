import java.time.LocalDateTime;

public class Task {
    enum Type {PERSONAL, WORKING}

    private String header;
    private String description;
    private final Type type;
    private final int taskId;
    private static int id = 0;
    private LocalDateTime localDateTime;
    private Repeatability repeatability;

    public Task(String header, String description, Type type, Repeatability repeatability, LocalDateTime localDateTime) {
        taskId = id++;
        this.localDateTime = localDateTime;
        this.header = header;
        this.description = description;
        this.type = type;
        this.repeatability = repeatability;
    }

    public String getHeader() {
        return header;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public int getTaskId() {
        return taskId;
    }

    public Repeatability getRepeatability() {
        return repeatability;
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
