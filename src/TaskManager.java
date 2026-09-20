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
        saveTasks(tasks);
    }

    public static void deleteTask(String []args){
        if (args.length != 2){
            System.out.println("Usage: \"delete <id>\"");
            return;
        }

        try{
            int id = Integer.parseInt(args[1]);
            ArrayList<Task> tasks = LoadTasks();
            for (Task task: tasks){
                if (task.id == id){
                    tasks.remove(task);
                    System.out.println("task deleted");
                    break;
                }
            }
            saveTasks(tasks);
        }
        catch(NumberFormatException e){
            System.out.println("id should be numeric");
            return;
        }
    }

    public static void listTasks(String []args){
        if (args.length != 1 && args.length != 2){
            System.out.println("Usage: \"list <status>(optional)\"");
            return;
        }

        if (args.length == 2){
            listTasksByStatus(args[1].toLowerCase().trim());
            return;
        }

        ArrayList<Task> tasks = LoadTasks();
        System.out.println("\nid|description|status|created date|last updated");
        for (Task task: tasks){
            System.out.println(task);
        }
        System.out.println("\n");
    }

    public static void listTasksByStatus(String status){
        if (!status.equals("done") && !status.equals("todo") && !status.equals("in-progress")){
            System.out.println("invalid status");
            return;
        }

        ArrayList<Task> tasks = LoadTasks();
        System.out.println("\nid|description|status|created date|last updated");
        for (Task task: tasks){
            if (task.status.equals(status)) System.out.println(task);
        }
        System.out.println("\n");
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

    public static void markDone(String []args){
        if(args.length != 2){
            System.out.println("Usage: mark-done <id>");
            return;
        }

        try{
            int id = Integer.parseInt(args[1]);
            ArrayList<Task> tasks = LoadTasks();
            for (Task task: tasks){
                if (task.id == id){
                    task.status = "done";
                    updateTime(task);
                    System.out.println("status updated");
                    break;
                }
            }
            saveTasks(tasks);
        }
        catch(NumberFormatException e){
            System.out.println("id should be numeric");
            return;
        }
        
    }

    public static void markInProgress(String []args){
        if(args.length != 2){
            System.out.println("Usage: mark-in-progress <id>");
            return;
        }

        try{
            int id = Integer.parseInt(args[1]);
            ArrayList<Task> tasks = LoadTasks();
            for (Task task: tasks){
                if (task.id == id){
                    task.status = "in-progress";
                    updateTime(task);
                    System.out.println("status updated");
                    break;
                }
            }
            saveTasks(tasks);
        }
        catch(NumberFormatException e){
            System.out.println("id should be numeric");
            return;
        }
        
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

    public static void updateTask(String []args){
        if (args.length != 3){
            System.out.println("Usage: \"update <id> <description>\"");
            return;
        }
        try{
            String description = args[2].trim();
            int id = Integer.parseInt(args[1]);
            ArrayList<Task> tasks = LoadTasks();
            for (Task task: tasks){
                if (task.id == id){
                    task.description = description;
                    updateTime(task);
                    System.out.println("task updated");
                    break;
                }
            }
            saveTasks(tasks);
        }
        catch(NumberFormatException e){
            System.out.println("id should be numeric");
            return;
        }        
    }

    public static void updateTime(Task task){
        String time = LocalDateTime.now().format(formatter);
        task.updatedDateTime = time;
    }
}