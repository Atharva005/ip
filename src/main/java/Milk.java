import java.util.Scanner;

public class Milk {
    public static void main(String[] args) {
        // introduction
        String banner = " __  __   _   _   _        _   _    \n"
                + "|  \\/  | (_) | | | | __   | | | |\n"
                + "| |  | | | | | | |   <    |_| |_|   \n"
                + "|_|  |_| |_| |_| |_|\\_\\   (_) (_)   \n";
        // used https://www.asciiart.eu/text-to-ascii-art for this!
        System.out.println(banner);
        System.out.println("> Milk is here!! What do you need today?");

        // setup
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] tasks = new String[100];
        int numTasks = 0;

        while (!line.matches("bye")) {
            switch(line) {
                case "list":
                    System.out.println("> Here's your tasks!");
                    for (int i = 0; i < numTasks; ++i) {
                        System.out.println("  " + Integer.toString(i + 1) + ") " + tasks[i]);
                    }
                    break;
                default:
                    System.out.println("> Okay!! Added: " + line);
                    tasks[numTasks] = line;
                    ++numTasks;
                    break;
            }
            line = scanner.nextLine();
        }

        System.out.println("> See you next time!~");
    }
}