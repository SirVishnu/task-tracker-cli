import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskManager{
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    static void addTask(String []args){
        if (args.length != 2){
            System.out.println("Invalid number of arguments");
            System.out.println("Usage: add \"Task-name\"");
            return;
        }
        String description = args[1].trim();

        ArrayList<Task> tasks = LoadTasks();

        int id = ++Task.countId;
        String time = LocalDateTime.now().format(formatter);
        tasks.add(new Task(id, description, "todo", time, time));

        for (Task t: tasks){
            System.out.println(t);
        }
        saveTasks(tasks);
    }

    public static void saveTasks(ArrayList<Task> tasks){
        String filename = "../tasks.txt";
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

        try(BufferedWriter br = new BufferedWriter(new FileWriter(file))){
            for (Task task: tasks){    
                br.write(task.toString());
                br.newLine();
            }
        }
        catch(IOException e){
            System.out.println("error occured while writing the file");
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


        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null && line.length() > 1){
                String []data = line.split("\\|");
                int id = Integer.parseInt(data[0]);
                String description = data[1];
                String status = data[2];
                String created = data[3];
                String updated = data[4];

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