import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

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
        }
    }

    private static void inputTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String taskName = scanner.next();
        System.out.print("Введите описание задачи: ");
        String taskDescription = scanner.next();
        System.out.println("Личная или рабочая?: ");
        String taskType = inputTaskType(scanner);
        System.out.println("Как часто необходима решать задачу: ");
        String taskRepeatability = inputTaskRepeatability(scanner);
        System.out.println("Ведите дату задачи в формате YYYY-MM-DD: ");
        String date = scanner.next();
        System.out.println("Введите время задачи в формате HH:MM");
        date = date + "T" + scanner.next() + ":00";
        LocalDateTime localDateTime = null;
        try {
            localDateTime = LocalDateTime.parse(date);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("не правильно ввели дату или время");
        }
        ServiceTask.addTask(new Task(taskName, taskDescription, taskType, taskRepeatability, localDateTime));
    }

    private static String inputTaskType(Scanner scanner) {
        while (true) {
            System.out.println("Выберите 1 если личная, 2 если рабочая: ");
            if (scanner.hasNext()) {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        return "личная";
                    case 2:
                        return "рабочая";
                }
            } else {
                scanner.nextInt();
                System.out.println("Выберите пункт меню из списка!");
            }
        }
    }

    private static String inputTaskRepeatability(Scanner scanner) {
        while (true) {
            System.out.println("Выбирете 1 если однократная, 2 если ежедневная, 3 если еженедельная, 4 если ежемесячная, 5 если ежегодная: ");
            if (scanner.hasNext()) {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        return "однократная";
                    case 2:
                        return "ежедневная";
                    case 3:
                        return "еженедельная";
                    case 4:
                        return "ежемесячная";
                    case 5:
                        return "ежегодная";
                }
            } else {
                scanner.nextInt();
                System.out.println("Выберите пункт меню из списка!");
            }
        }
    }


    private static void printTasksForTheDay(Scanner scanner) {
        while (true) {
            System.out.println("Напишите нужный день в формате YYYY-MM-DD: ");
            String s = scanner.next();
            s += "T00:00:00";
            LocalDateTime localDateTime;
            try {
                localDateTime = LocalDateTime.parse(s);
            } catch (DateTimeParseException e) {
                throw new RuntimeException("не правильно ввели дату");
            }
            ServiceTask.printTasksForTheDay(localDateTime);
        }
    }

    private static void printMenu() {
        System.out.println("1. Добавить задачу");
        System.out.println("2. Удалить задачу");
        System.out.println("3. Получить задачи на указанный день");
        System.out.println("0. Выход");
    }
}
