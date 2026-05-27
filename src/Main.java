import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Консольный менеджер задач ===\n");

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addTaskFlow(scanner, manager);
                case "2" -> manager.listTasks();
                case "3" -> markDoneFlow(scanner, manager);
                case "4" -> deleteTaskFlow(scanner, manager);
                case "0" -> {
                    System.out.println("Выход из программы. До встречи!");
                    running = false;
                }
                default -> System.out.println("❌ Неверный выбор. Попробуйте снова.\n");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("1. Добавить задачу");
        System.out.println("2. Показать все задачи");
        System.out.println("3. Отметить задачу как выполненную");
        System.out.println("4. Удалить задачу");
        System.out.println("0. Выход");
        System.out.print("\nВыберите действие: ");
    }

    private static void addTaskFlow(Scanner scanner, TaskManager manager) {
        System.out.print("Введите название задачи: ");
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("❌ Название задачи не может быть пустым!\n");
            return;
        }

        System.out.print("Введите приоритет (LOW/MED/HIGH): ");
        String priority = scanner.nextLine().trim().toUpperCase();

        if (!priority.equals("LOW") && !priority.equals("MED") && !priority.equals("HIGH")) {
            System.out.println("❌ Неверный приоритет! Используйте LOW, MED или HIGH.\n");
            return;
        }

        manager.addTask(title, priority);
        System.out.println();
    }

    private static void markDoneFlow(Scanner scanner, TaskManager manager) {
        System.out.print("Введите ID задачи для завершения: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            manager.markDone(id);
        } catch (NumberFormatException e) {
            System.out.println("❌ Введите корректный номер (число)!\n");
        }
        System.out.println();
    }

    private static void deleteTaskFlow(Scanner scanner, TaskManager manager) {
        System.out.print("Введите ID задачи для удаления: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            manager.deleteTask(id);
        } catch (NumberFormatException e) {
            System.out.println("❌ Введите корректный номер (число)!\n");
        }
        System.out.println();
    }
}