import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskManager{
    static void addTask(String []args){
        if (args.length != 2){
            System.out.println("Invalid number of arguments");
            System.out.println("Usage: add \"Task-name\"");
            return;
        }
        String description = args[1].trim();

        ArrayList<Task> tasks = LoadTasks();

        int id = ++Task.countId;
        tasks.add(new Task(id, description, "todo", LocalDateTime.now(), LocalDateTime.now()));

        for (Task t: tasks){
            System.out.println(t);
        }
    }

    public static ArrayList<Task> LoadTasks(){
        String filename = "../tasks.txt";
        ArrayList <Task> tasks = new ArrayList<>();
        File file = new File(filename);

        if(!file.exists()){
            try{
                file.createNewFile();
                System.out.println("New file tasks.txt created");
            }
            catch(IOException e){
                System.out.println("Something went wrong while creating a file");
            }
        }


        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while((line = br.readLine()) != null){
                String []data = line.split("\\|");
                int id = Integer.parseInt(data[0]);
                String description = data[1];
                String status = data[2];
                LocalDateTime created = LocalDateTime.parse(data[3]);
                LocalDateTime updated = LocalDateTime.parse(data[4]);

                // append task object in list    
                tasks.add(new Task(id, description, status, created, updated));
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        catch(IOException e){
            System.out.println("Something went wrong");
        }
        return tasks;
    }
}