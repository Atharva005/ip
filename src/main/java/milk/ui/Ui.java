package milk.ui;

import java.util.Scanner;

public class Ui {
    private final String linePrefix = "> ";
    private final Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printGreeting() {
        String banner = """
                 __  __   _   _   _        _   _
                |  \\/  | (_) | | | | __   | | | |
                | |  | | | | | | |   <    |_| |_|
                |_|  |_| |_| |_| |_|\\_\\   (_) (_)
                """;
        // used https://www.asciiart.eu/text-to-ascii-art for this!
        System.out.println(banner);
        System.out.println(linePrefix + "Milk is here!! What do you need today?");
    }

    public void printResponse(String response) {
        System.out.println(linePrefix + response);
    }

    public void printResponse(String response, boolean includePrefix) {
        if (includePrefix) {
            System.out.println(linePrefix + response);
        } else {
            System.out.println(response);
        }
    }

    public void printGoodbye() {
        System.out.println(linePrefix + "See you next time!~");
    }
}
