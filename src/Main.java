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
        System.out.println("=== Добавление задачи ===\n");

        // Ввод названия (просто проверка на пустоту)
        String title = readWithRetry(scanner,
                "Введите название задачи (или \"cancel/c\" для отмены): ",
                "➕ Добавление задачи отменено.",
                "❌ Название не может быть пустым!");

        if (title == null) return;  // Если отмена → выход

        // Ввод приоритета (проверка по списку LOW/MED/HIGH)
        String priorityNumber = readWithRetry(scanner,
                "Введите приоритет: \n1. LOW\n2. MED\n3. HIGH\n0. cancel\n",
                "➕ Добавление задачи отменено.",
                "❌ Неверный приоритет! Используйте LOW, MED или HIGH.",
                "0", "1", "2", "3");  // ← Вот список допустимых!

        if (priorityNumber == null) return;  // Если отмена → выход

// КОНВЕРТАЦИЯ номера в текст
        String priority;
        switch (priorityNumber) {
            case "1" -> priority = "LOW";
            case "2" -> priority = "MED";
            case "3" -> priority = "HIGH";
            default -> {
                System.out.println("❌ Ошибка конвертации!");
                return;
            }
        }
        manager.addTask(title, priority);
        System.out.println("✅ Задача добавлена!\n");
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
    private static String readWithRetry(Scanner scanner, String prompt,
                                        String cancelMessage,
                                        String errorMessage,
                                        String... validValues) {

        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            // Проверяем на отмену (латинская c, Cyrillic с, cancel, C, С)
            if (input.equalsIgnoreCase("cancel") ||
                    input.equalsIgnoreCase("c") ||
                    input.equals("с") ||  // Кириллическая маленькая с
                    input.equals("С")) {  // Кириллическая заглавная С
                System.out.println(cancelMessage);
                return null;
            }

            // Если есть список допустимых значений
            if (validValues.length > 0) {
                String upperInput = input.toUpperCase();

                // Проходим по всем допустимым значениям
                for (String valid : validValues) {
                    if (valid.equals(upperInput)) {
                        return upperInput;  // Нашли!
                    }
                }

                // Не нашли в списке → ошибка
                System.out.println(errorMessage);

            } else {
                // Списка нет → просто проверяем на пустоту
                if (!input.isEmpty()) {
                    return input;
                }
                System.out.println(errorMessage);
            }
        }
    }
}