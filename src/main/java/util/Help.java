package util;

import java.util.List;
import java.util.Scanner;

public class Help {
    private static final Scanner scanner = new Scanner(System.in);


    public final static List<String> symbols = List.of("USDCHF", "GBPUSD", "EURUSD", "USDJPY",
            "USDCAD", "AUDUSD", "EURGBP", "EURAUD", "EURCHF", "EURJPY", "GBPCHF",
            "CADJPY", "GBPJPY", "AUDNZD", "AUDCAD", "AUDCHF", "AUDJPY", "CHFJPY",
            "EURNZD", "EURCAD", "CADCHF", "NZDJPY", "NZDUSD", "GBPCAD", "AUDSGD",
            "GBPAUD", "GBPNZD", "GBPSEK", "GBPSGD", "NZDCAD", "NZDCHF", "EURSEK",
            "EURSGD", "SGDJPY", "USDDKK", "USDHUF", "USDNOK", "USDSEK", "USDSGD",
            "USDZAR", "EURPLN", "USDTRY", "USDPLN"
    );

    public static Double getPipLocation(String symbol) {
        Double pipLocation = 0.0001, result;
        if (symbol.contains("JPY")) {
            pipLocation = 0.01;
        }
        return pipLocation;
    }

    public static <E> void println(E prompt) {
        System.out.println(prompt);
    }

    public static <E> void print(E prompt) {
        System.out.print(prompt);
    }

    public static String input(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        return input;
    }

    public static Integer intInput(String prompt) {
        System.out.print(prompt);
        Integer input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public static Double doubleInput(String prompt) {
        System.out.print(prompt);
        Double input = scanner.nextDouble();
        scanner.nextLine();
        return input;
    }

    public static String formatSymbol(String prompt) {
        return prompt.substring(0, 3).concat("/").concat(prompt.substring(3));
    }

}
