package Passes.client;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {
    public static boolean validateRegex(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(text);
        return match.matches();
    }

    /**
     * To prompt and get input from user with {@code Scanner} object
     *
     * @author Lee Khoon Hong
     *
     * @param promptMessage Text to be displayed while prompting user to input
     * @return Inputted {@code String}
     */
    public static String promptString(String promptMessage) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print(promptMessage);
            String string = input.nextLine();
            if (string.isBlank()) {
                System.out.println("  No input detected...");
                continue;
            }
            return string.trim();
        }
    }

    /**
     * To prompt and get input from user with {@code Scanner} object
     *
     * @author Lee Khoon Hong
     *
     * @param promptMessage Text to be displayed while prompting user to input
     * @return Inputted integer value
     */
    public static int promptInt(String promptMessage) {
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(promptMessage);
                int value = input.nextInt();
                input.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("  Invalid value entered...");
                input.nextLine();
            }
        }
    }

    /**
     * To prompt and get input from user with {@code Scanner} object
     *
     * @author Lee Khoon Hong
     *
     * @param promptMessage Text to be displayed while prompting user to input
     * @return Inputted double value
     */
    public static double promptDouble(String promptMessage) {
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(promptMessage);
                double value = input.nextDouble();
                input.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("  Invalid value entered...");
                input.nextLine();
            }
        }
    }

    /**
     * To prompt and get input from user with {@code Scanner} object
     *
     * @author Lee Khoon Hong
     *
     * @param promptMessage Text to be displayed while prompting user to input
     * @return Inputted Character
     */
    public static char promptChar(String promptMessage) {
        Scanner input = new Scanner(System.in);
        System.out.print(promptMessage);
        return input.nextLine().charAt(0);
    }

    /**
     * Uses {@code Scanner} object to simulate system pause
     *
     * @author Lee Khoon Hong
     */
    public static void pressAnyKeyToContinue() {
        System.out.println("Press Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception ignored) {
        }
    }
}
