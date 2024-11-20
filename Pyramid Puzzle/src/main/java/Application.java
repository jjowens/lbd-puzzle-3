import modules.PyramidService;

import java.util.List;

public class Application {

    public static void main(String[] args) {

        if (args.length == 0 || args[0].isEmpty()) {
            printPrompt();
            return;
        }

        PyramidService service = new PyramidService(args[0].trim());

        String results = service.getOptimalPath();

        System.out.print("Optimal path could be: ");
        System.out.println(results);
    }

    private static void printPrompt() {
        System.out.println("## ERROR ##");
        System.out.println("Please enter an filename to get optimal path. Available Filenames are:");

        String[] filenames = {"example1.txt", "example2.txt", "part1.txt", "part2.txt", "part3.txt"};

        for (String filename : filenames) {
            System.out.println(filename);
        }
    }
}
