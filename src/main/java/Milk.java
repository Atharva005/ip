import java.util.Scanner;

public class Milk {

    private static Scanner scanner = new Scanner(System.in);
    private static Task[] tasks = new Task[100];
    private static int numTasks = 0;
    private static Ui ui = new Ui();

    private static void handleCommand(String line) {
        String[] words = line.split(" ");
        String command = words[0];
        try {
            switch (command) {
                case "todo":
                    handleTodo(line);
                    break;
                case "deadline":
                    handleDeadline(line);
                    break;
                case "event":
                    handleEvent(line);
                    break;
                case "list":
                    listTasks();
                    break;
                case "mark":
                    markTask(words[1]);
                    break;
                case "unmark":
                    unmarkTask(words[1]);
                    break;
                case "bye":
                    break;
                default:
                    ui.printResponse("\"" + line + "\"...? I don't know this command!!");
                    break;
            }
        } catch (MilkException e) { }
    }

    private static void handleTodo(String line) throws MilkException {
        if (line.length() == 4 || line.equals("todo ")) {
            throw new MilkException("I don't understand! You should tell me something like \"todo water the plants\"!!");
        }
        tasks[numTasks] = new Todo(line.substring("todo".length()).trim());
        ++numTasks;
        ui.printResponse("Okay!! Added todo: " + line.substring("todo".length()).trim());
    }

    private static void handleDeadline(String line) throws MilkException {
        String deadlineIn = line.substring("deadline".length()).trim();
        String[] deadlineParams = deadlineIn.split(" /by ");
        if (deadlineParams.length != 2) {
            throw new MilkException("This is invalid format!! Say something like \"deadline finish homework /by tomorrow\"!");
        }
        tasks[numTasks] = new Deadline(deadlineParams[0], deadlineParams[1]);
        ++numTasks;
        ui.printResponse("Okay!! Added deadline: " + deadlineParams[0] + " (by " + deadlineParams[1] + ")");
    }

    private static void handleEvent(String line) throws MilkException {
        String eventIn = line.substring("event".length()).trim();
        String[] eventParams = eventIn.split("(?:/from |/to )");
        if (eventParams.length != 3) {
            throw new MilkException("This is invalid format!! Say something like \"event party /from 5 /to 8\"!");
        }
        tasks[numTasks] = new Event(eventParams[0], eventParams[1], eventParams[2]);
        ++numTasks;
        ui.printResponse("Okay!! Added event: " + eventParams[0] + " (from " + eventParams[1] + " to " + eventParams[2] + ")");
    }

    private static void markTask(String toMark) {
        try {
            int indexToMark = Integer.parseInt(toMark);
            tasks[indexToMark - 1].setMarked(true);
            ui.printResponse(tasks[indexToMark - 1].getDescription() + " has been completed!");
        } catch (NumberFormatException e) {
            ui.printResponse("You didn't give me a task to mark!! For example, say \"mark 2\"!");
        }

    }

    private static void unmarkTask(String toUnmark) {
        try {
            int indexToUnmark = Integer.parseInt(toUnmark);
            tasks[indexToUnmark - 1].setMarked(false);
            ui.printResponse(tasks[indexToUnmark - 1].getDescription() + " has been unmarked!");
        } catch (NumberFormatException e) {
            ui.printResponse("You didn't give me a task to unmark!! For example, say \"unmark 2\"!");
        }
    }

    private static void listTasks() {
        ui.printResponse("Here's your tasks!");
        for (int i = 0; i < numTasks; ++i) {
            ui.printResponse("  " + (i + 1) + ") " + tasks[i], false);
        }
    }

    public static void main(String[] args) {
        ui.printGreeting();
        String line;
        do {
            line = ui.readCommand();
            handleCommand(line);
        } while (!line.startsWith("bye"));
        ui.printGoodbye();
    }
}