public class App {
    public static void main(String[] args) throws Exception {
       String []commands = {"add", "update", "delete", "list", "mark-in-progress", "mark-done"};

       // check if valid argument
       if (!isValidArgument(args, commands)){
        System.out.println("Invalid argument");
        return;
       }
    }

    static boolean isValidArgument(String []args, String []commands){
        // check if valid no of arguments
        if (args.length > 3 || args.length < 1) return false;

        for (String s: commands){
            if(s.equals(args[0])) return true;
        }
        
        return false;
    }
}
