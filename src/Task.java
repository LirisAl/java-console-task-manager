public class Task {
    private int id;
    private String title;
    private String priority; // LOW, MED, HIGH
    private String status;   // TODO, DONE

    public Task(int id, String title, String priority) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Название задачи не может быть пустым");
        }
        this.id = id;
        this.title = title.trim();
        this.priority = priority.toUpperCase();
        this.status = "TODO";
    }

    // Геттеры
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getPriority() { return priority; }
    public String getStatus() { return status; }

    // Метод для изменения статуса
    public void markAsDone() {
        this.status = "DONE";
    }

    // Строковое представление
    @Override
    public String toString() {
        return String.format("[%d] %s | Priority: %s | Status: %s",
                id, title, priority, status);
    }
}