import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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
        ArrayList<Task> arrayList = new ArrayList<>();
        for (int i = 0; i <= mapTask.size(); i++) {
            if ((mapTask.get(i).getRepeatability().equals("однократная"))
                    && (LocalDate.of(mapTask.get(i).getLocalDateTime().getYear(),
                    mapTask.get(i).getLocalDateTime().getMonth(),
                    mapTask.get(i).getLocalDateTime().getDayOfMonth()).equals(localDate))) {
                arrayList.add(mapTask.get(i));
            }
            if (mapTask.get(i).getRepeatability().equals("ежедневная")) {
                arrayList.add(mapTask.get(i));
            }
            if (mapTask.get(i).getRepeatability().equals("еженедельная")) {
                LocalDate localDate1 = LocalDate.of(mapTask.get(i).getLocalDateTime().getYear(),
                        mapTask.get(i).getLocalDateTime().getMonth(),
                        mapTask.get(i).getLocalDateTime().getDayOfMonth());
                while ((localDate.isAfter(localDate1)) || (localDate.equals(localDate1))) {
                    if (localDate1.equals(localDate)) {
                        arrayList.add(mapTask.get(i));
                    }
                    localDate1.plusWeeks(1);
                }
            }
            if (mapTask.get(i).getRepeatability().equals("ежемесячная")) {
                LocalDate localDate1 = LocalDate.of(mapTask.get(i).getLocalDateTime().getYear(),
                        mapTask.get(i).getLocalDateTime().getMonth(),
                        mapTask.get(i).getLocalDateTime().getDayOfMonth());
                while ((localDate.isAfter(localDate1)) || (localDate.equals(localDate1))) {
                    if (localDate1.equals(localDate)) {
                        arrayList.add(mapTask.get(i));
                    }
                    localDate1.plusMonths(1);
                }
            }
            if (mapTask.get(i).getRepeatability().equals("ежегодная")) {
                LocalDate localDate1 = LocalDate.of(mapTask.get(i).getLocalDateTime().getYear(),
                        mapTask.get(i).getLocalDateTime().getMonth(),
                        mapTask.get(i).getLocalDateTime().getDayOfMonth());
                while ((localDate.isAfter(localDate1)) || (localDate.equals(localDate1))) {
                    if (localDate1.equals(localDate)) {
                        arrayList.add(mapTask.get(i));
                    }
                    localDate1.plusYears(1);
                }
            }
        }
        for (Task task : arrayList) {
            System.out.print(task + " ");
        }
    }
}
