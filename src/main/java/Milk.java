import java.util.Scanner;

public class Milk {
    public static void main(String[] args) {
        String banner = " __  __   _   _   _        _   _    \n"
                + "|  \\/  | (_) | | | | __   | | | |\n"
                + "| |  | | | | | | |   <    |_| |_|   \n"
                + "|_|  |_| |_| |_| |_|\\_\\   (_) (_)   \n";
        // used https://www.asciiart.eu/text-to-ascii-art for this!
        System.out.println(banner);
        System.out.println("> Milk is here!! What do you need today?");

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        while (!line.matches("bye")) {
            System.out.println("> " + line);
            line = scanner.nextLine();
        }

        System.out.println("> See you next time!~");
    }
}