import java.util.Scanner;

public class Milk {
    public static void main(String[] args) {
        String banner = """
                 __  __   _   _   _        _   _
                |  \\/  | (_) | | | | __   | | | |
                | |  | | | | | | |   <    |_| |_|
                |_|  |_| |_| |_| |_|\\_\\   (_) (_)
                """;
        // used https://www.asciiart.eu/text-to-ascii-art for this!
        System.out.println(banner);
        System.out.println("> Milk is here!! What do you need today?");

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int numTasks = 0;

        String line = scanner.nextLine();
        String[] words = line.split(" ");
        while (!words[0].equals("bye")) {
            switch (words[0]) {
                case "list":
                    System.out.println("> Here's your tasks!");
                    for (int i = 0; i < numTasks; ++i) {
                        System.out.println("  " + Integer.toString(i + 1) + ") " + tasks[i].getStatusIcon() + " " + tasks[i].getDescription());
                    }
                    break;
                case "mark":
                    int toMark = Integer.parseInt(words[1]);
                    tasks[toMark - 1].setMarked(true);
                    System.out.println("> " + tasks[toMark - 1].getDescription() + " has been completed!");
                    break;
                case "unmark":
                    int toUnmark = Integer.parseInt(words[1]);
                    tasks[toUnmark - 1].setMarked(false);
                    System.out.println("> " + tasks[toUnmark - 1].getDescription() + " has been unmarked!");
                    break;
                default:
                    tasks[numTasks] = new Task(line);
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