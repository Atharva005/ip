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
                case "todo":
                    tasks[numTasks] = new Todo(line);
                    ++numTasks;
                    System.out.println("> Okay!! Added todo: " + line.substring(5));
                    break;
                case "deadline":
                    String deadlineIn = line.substring(9);
                    String[] deadlineParams = deadlineIn.split(" /by ");
                    tasks[numTasks] = new Deadline(deadlineParams[0], deadlineParams[1]);
                    ++numTasks;
                    System.out.println("> Okay!! Added deadline: " + deadlineParams[0] + " (by " + deadlineParams[1] + ")");
                    break;
                case "event":
                    String eventIn = line.substring(6);
                    String[] eventParams = eventIn.split(" /");
                    tasks[numTasks] = new Event(eventParams[0], eventParams[1].substring(5), eventParams[2].substring(3));
                    ++numTasks;
                    System.out.println("> Okay!! Added deadline: " + eventParams[0] + " (from " + eventParams[1].substring(5) + " to " + eventParams[2].substring(3) + ")");
                    break;
                case "list":
                    System.out.println("> Here's your tasks!");
                    for (int i = 0; i < numTasks; ++i) {
                        System.out.println("  " + Integer.toString(i + 1) + ") " + tasks[i].getTaskIcon() + tasks[i].getStatusIcon() + " " + tasks[i].getInfo());
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
                    System.out.println("> \"" + line + "\"...? I don't know this command!!");
                    break;
            }
            line = scanner.nextLine();
            words = line.split(" ");
        }

        System.out.println("> See you next time!~");
    }
}