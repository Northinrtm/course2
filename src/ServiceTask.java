import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServiceTask {
    static final Map<Integer, Task> mapTask = new HashMap<>();

    public static void printNextTask(int id) {
        System.out.println(mapTask.get(id).getHeader() + " " + mapTask.get(id).localDateTime + " " + mapTask.get(id).getRepeatability());
    }

    public static void addTask(Task task) {
        mapTask.put(task.getTaskId(), task);
    }

    public static void deleteTask(Integer i) {
        mapTask.remove(i);
    }

    public static void printTasksForTheDay(LocalDateTime localDateTime) {
        if (localDateTime!=null) {
            ArrayList<Task> arrayList = new ArrayList<>();
            for (int i = 0; i <= mapTask.size(); i++) {
                Task task = mapTask.get(i);
                System.out.println(task.localDateTime);
                System.out.println(localDateTime);
                if ((task.getRepeatability() == "однократная") && (task.localDateTime.equals(localDateTime))) {
                    arrayList.add(task);
                }
                if (task.getRepeatability() == "ежедневная") {
                    arrayList.add(task);
                } else {
                    while (localDateTime.isAfter(task.localDateTime)) {
                        if (task.getRepeatability() == "еженедельная") {
                            localDateTime.plusWeeks(1);
                        }
                        if (task.getRepeatability() == "ежемесячная") {
                            localDateTime.plusMonths(1);
                        }
                        if (task.getRepeatability() == "ежегодная") {
                            localDateTime.plusYears(1);
                        }
                        if (task.localDateTime == localDateTime) {
                            arrayList.add(task);
                        }
                    }
                }
            }
            for (Task task : arrayList) {
                System.out.println(task);
            }
        } else {
            System.out.println("null");
        }
    }
}
