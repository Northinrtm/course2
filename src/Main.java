import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            System.out.println("Введите id задачи которую нужно удалить: ");
                            ServiceTask.deleteTask(scanner.nextInt());
                            break;
                        case 3:
                            printTasksForTheDay(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        } catch (MyOriginalExceptionName e) {
            throw new RuntimeException(e);
        }
    }

    private static void inputTask(Scanner scanner) throws MyOriginalExceptionName {
        System.out.print("Введите название задачи: ");
        String taskName = scanner.next();
        System.out.print("Введите описание задачи: ");
        String taskDescription = scanner.next();
        System.out.println("Личная или рабочая?: ");
        Task.Type taskType = inputTaskType(scanner);
        System.out.println("Как часто необходима решать задачу: ");
        Repeatability taskRepeatability = inputTaskRepeatability(scanner);
        System.out.println("Ведите дату задачи в формате YYYY-MM-DD: ");
        String date = scanner.next();
        if(!Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", date )){
            throw new MyOriginalExceptionName();
        }
        System.out.println("Введите время задачи в формате HH:MM");
        date = date + "T" + scanner.next() + ":00";
        if(!Pattern.matches("^\\d{2}:\\d{2}$", date )){
            throw new MyOriginalExceptionName();
        }
        LocalDateTime localDateTime;
        localDateTime = LocalDateTime.parse(date);
        ServiceTask.addTask(new Task(taskName, taskDescription, taskType, taskRepeatability, localDateTime));
    }

    private static Task.Type inputTaskType(Scanner scanner) {
        while (true) {
            System.out.println("Выберите 1 если личная, 2 если рабочая: ");
            if (scanner.hasNext()) {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        return Task.Type.PERSONAL;
                    case 2:
                        return Task.Type.WORKING;
                }
            } else {
                scanner.nextInt();
                System.out.println("Выберите пункт меню из списка!");
            }
        }
    }

    private static Repeatability inputTaskRepeatability(Scanner scanner) {
        while (true) {
            System.out.println("Выбирете 1 если однократная, 2 если ежедневная, 3 если еженедельная, 4 если ежемесячная, 5 если ежегодная: ");
            if (scanner.hasNext()) {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        return new Single();
                    case 2:
                        return new Daily();
                    case 3:
                        return new Weekly();
                    case 4:
                        return new Monthly();
                    case 5:
                        return new Yearly();
                }
            } else {
                scanner.nextInt();
                System.out.println("Выберите пункт меню из списка!");
            }
        }
    }


    private static void printTasksForTheDay(Scanner scanner) {
        System.out.println("Напишите нужный день в формате YYYY-MM-DD: ");
        String s = scanner.next();
        LocalDate localDate;
        localDate = LocalDate.parse(s);
        ServiceTask.printTasksForTheDay(localDate);
    }

    private static void printMenu() {
        System.out.println("1. Добавить задачу");
        System.out.println("2. Удалить задачу");
        System.out.println("3. Получить задачи на указанный день");
        System.out.println("0. Выход");
    }
}