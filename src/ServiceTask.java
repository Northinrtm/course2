import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceTask {
    private static final Map<Integer, Task> mapTask = new HashMap<>();

    public static void addTask(Task task) {
        mapTask.put(task.getTaskId(), task);
    }

    public static void deleteTask(Integer i) {
        mapTask.remove(i);
    }

    public static void printTasksForTheDay(LocalDate localDate) {
        List<Task> taskList = new ArrayList<Task>();
        for (int i = 0; i < mapTask.size(); i++) {
            LocalDate localDate1 = LocalDate.from(mapTask.get(i).getLocalDateTime());
            while (localDate.isAfter(localDate1.minusDays(1))) {
                if (mapTask.get(i).getRepeatability().nextTime(localDate1) == null) {
                    continue;
                }
                if (LocalDate.from(mapTask.get(i).getLocalDateTime()).equals(localDate)) {
                    taskList.add(mapTask.get(i));
                }
                localDate1 = mapTask.get(i).getRepeatability().nextTime(localDate1);
            }
            for (Task task : taskList) {
                System.out.println(task + " ");
            }
        }
    }
}
