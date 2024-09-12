public class Main {

    public static void main(String[] args) {

        // ARGUMENTS VALIDATION
        // If the number of arguments is not 1, print an error and exit the program.
        if (args.length != 1) {
            System.err.println("There are no arguments");
            System.exit(0);
        }

        // If the argument is not an integer, print an error and exit the program.
        try {
            int arg = Integer.parseInt(args[0]);

            // If the argument is 0 or negative, print an error and exit the program.
            if (arg < 1) {
                System.err.println("The argument must greater than 0");
                System.exit(0);
            }

            // EXECUTION
            Ants ants = new Ants(new Graph());
            ants.generateRoutes(arg);
            ants.printBestRoute();

        }
        catch (NumberFormatException e) {
            System.err.println("The argument must be an integer");
            System.exit(0);
        }
        
    }

}