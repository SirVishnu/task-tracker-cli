public class App {
    public static void main(String[] args) throws Exception {
        // list of commands
        String []commands = {"add", "update", "delete", "list", "mark-in-progress", "mark-done"};

        // check if valid argument
        if (!isValidArgument(args, commands)){
            System.out.println("Invalid argument");
            return;
        }

        switch (args[0].trim().toLowerCase()) {
            case "add" -> TaskManager.addTask(args);
        
            default -> System.out.println("invalid argument");
        }

        
    }

    static boolean isValidArgument(String []args, String []commands){
        // check if valid no of arguments
        if (args.length > 3 || args.length < 1) return false;

        for (String s: commands){
            if(s.equals(args[0].trim().toLowerCase())) return true;
        }
        
        return false;
    }
}
