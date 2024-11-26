import services.PyramidService;

import java.util.Arrays;

public class Application {

    private static String[] filenames = {"example1.txt", "example2.txt", "part1.txt", "part2.txt", "part3.txt"};

    public static void main(String[] args) {

        if (args.length == 0 || args[0].isEmpty()) {
            printPrompt();
            return;
        }

        String filename = args[0].trim().toLowerCase();

        if (!Arrays.asList(filenames).contains(filename)) {
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
        System.out.println("Please enter correct filename to get optimal path. Available Filenames are:");

        for (String filename : filenames) {
            System.out.println(filename);
        }
    }
}
