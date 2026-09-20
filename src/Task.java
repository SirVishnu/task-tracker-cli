import java.time.LocalDateTime;

public class Task {
    static int countId = 0;
    int id; 
    String description;
    String status;
    LocalDateTime createdDateTime;
    LocalDateTime updatedDateTime;

    Task(int id, String description, String status, LocalDateTime created, LocalDateTime updated){
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdDateTime = created;
        this.updatedDateTime = updated;
        countId = Math.max(id, countId);
    }

    @Override 
    public String toString(){
        return this.id + "|" + this.description + "|" + this.status + "|" +  this.createdDateTime + "|" + this.updatedDateTime;
    }
}
