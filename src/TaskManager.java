import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public void addTask(String title, String priority) {
        tasks.add(new Task(nextId++, title, priority));
        System.out.println("Задача добавлена!");
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Задач пока нет.");
            return;
        }
        System.out.println("\n=== Список задач ===");
        for (Task task : tasks) {
            System.out.println(task);
        }
        System.out.println("===================\n");
    }

    public boolean markDone(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.markAsDone();
                System.out.println("Задача #" + id + " выполнена!");
                return true;
            }
        }
        System.out.println("Задача #" + id + " не найдена.");
        return false;
    }

    public boolean deleteTask(int id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                tasks.remove(i);
                System.out.println("Задача #" + id + " удалена!");
                return true;
            }
        }
        System.out.println("Задача #" + id + " не найдена.");
        return false;
    }
}