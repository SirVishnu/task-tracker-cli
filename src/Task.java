import java.time.LocalDateTime;

public class Task {
    int id; 
    String description;
    String status;
    LocalDateTime createdDateTime;
    LocalDateTime updatedDateTime;

    Task(int id, String description, String status){
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdDateTime = LocalDateTime.now();
        this.updatedDateTime = LocalDateTime.now();
    }
}
