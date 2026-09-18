package milk.main;

import milk.exception.MilkException;
import milk.task.Deadline;
import milk.task.Event;
import milk.task.Task;
import milk.task.Todo;
import milk.ui.Ui;

import java.text.NumberFormat;
import java.util.Scanner;
import java.util.ArrayList;

public class Milk {

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Task> tasks = new ArrayList<>();
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
                case "delete":
                    deleteTask(words[1]);
                    break;
                default:
                    ui.printResponse("\"" + line + "\"...? I don't know this command!!");
                    break;
            }
        } catch (MilkException e) { }
    }

    private static void deleteTask(String taskToDelete) throws MilkException {
        try {
            int indexOfTaskToDelete = Integer.parseInt(taskToDelete) - 1;
            if (indexOfTaskToDelete > numTasks - 1) {
                throw new MilkException("But you don't even have that many tasks!");
            }
            if (indexOfTaskToDelete <= -1) {
                throw new MilkException("The index should at least be 1!!");
            }
            ui.printResponse("Okay... deleted task:\n  " + tasks.get(indexOfTaskToDelete).toString());
            tasks.remove(indexOfTaskToDelete);
            numTasks--;
            if (numTasks < 0) {
                numTasks = 0;
            }
            ui.printResponse("Now you only have " + numTasks + " tasks! Congrats...?");
        } catch (NumberFormatException e) {
            ui.printResponse("You need to give me an index!! Like \"delete 1\"!");
        }
    }

    private static void handleTodo(String line) throws MilkException {
        if (line.length() == 4 || line.equals("todo ")) {
            throw new MilkException("I don't understand! You should tell me something like \"todo water the plants\"!!");
        }
        tasks.add(new Todo(line.substring("todo".length()).trim()));
        ++numTasks;
        ui.printResponse("Okay!! Added todo: " + line.substring("todo".length()).trim());
    }

    private static void handleDeadline(String line) throws MilkException {
        String deadlineIn = line.substring("deadline".length()).trim();
        String[] deadlineParams = deadlineIn.split(" /by ");
        if (deadlineParams.length != 2) {
            throw new MilkException("This is invalid format!! Say something like \"deadline finish homework /by tomorrow\"!");
        }
        tasks.add(new Deadline(deadlineParams[0], deadlineParams[1]));
        ++numTasks;
        ui.printResponse("Okay!! Added deadline: " + deadlineParams[0] + " (by " + deadlineParams[1] + ")");
    }

    private static void handleEvent(String line) throws MilkException {
        String eventIn = line.substring("event".length()).trim();
        String[] eventParams = eventIn.split("(?: /from | /to )");
        if (eventParams.length != 3) {
            throw new MilkException("This is invalid format!! Say something like \"event party /from 5 /to 8\"!");
        }
        tasks.add(new Event(eventParams[0], eventParams[1], eventParams[2]));
        ++numTasks;
        ui.printResponse("Okay!! Added event: " + eventParams[0] + " (from " + eventParams[1] + " to " + eventParams[2] + ")");
    }

    private static void markTask(String toMark) {
        try {
            int indexToMark = Integer.parseInt(toMark);
            tasks.get(indexToMark - 1).setMarked(true);
            ui.printResponse(tasks.get(indexToMark - 1).getDescription() + " has been completed!");
        } catch (NumberFormatException e) {
            ui.printResponse("You didn't give me a task to mark!! For example, say \"mark 2\"!");
        }

    }

    private static void unmarkTask(String toUnmark) {
        try {
            int indexToUnmark = Integer.parseInt(toUnmark);
            tasks.get(indexToUnmark - 1).setMarked(false);
            ui.printResponse(tasks.get(indexToUnmark - 1).getDescription() + " has been unmarked!");
        } catch (NumberFormatException e) {
            ui.printResponse("You didn't give me a task to unmark!! For example, say \"unmark 2\"!");
        }
    }

    private static void listTasks() {
        ui.printResponse("Here's your tasks!");
        for (int i = 0; i < numTasks; ++i) {
            ui.printResponse("  " + (i + 1) + ") " + tasks.get(i), false);
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