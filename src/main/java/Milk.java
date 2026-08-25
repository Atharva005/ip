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
        String[] tasks = new String[100];
        boolean[] isMarked = new boolean[100];
        int numTasks = 0;

        String line = scanner.nextLine();
        String[] words = line.split(" ");
        while (!words[0].matches("bye")) {
            switch(words[0]) {
                case "list":
                    System.out.println("> Here's your tasks!");
                    for (int i = 0; i < numTasks; ++i) {
                        String statusIcon = isMarked[i] ? "[X]" : "[ ]";
                        System.out.println("  " + Integer.toString(i + 1) + ") " + statusIcon + " " + tasks[i]);
                    }
                    break;
                case "mark":
                    int toMark = Integer.parseInt(words[1]);
                    isMarked[toMark - 1] = true;
                    System.out.println("> " + tasks[toMark - 1] + " has been completed!");
                    break;
                case "unmark":
                    int toUnmark = Integer.parseInt(words[1]);
                    isMarked[toUnmark - 1] = false;
                    System.out.println("> " + tasks[toUnmark - 1] + " has been unmarked!");
                    break;
                default:
                    tasks[numTasks] = line;
                    ++numTasks;
                    System.out.println("> Okay!! Added: " + line);
                    break;
            }
            line = scanner.nextLine();
            words = line.split(" ");
        }

        System.out.println("> See you next time!~");
    }
}