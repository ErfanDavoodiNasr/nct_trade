package ir.ncttrade.util;

import java.util.List;
import java.util.Scanner;

public class Help {
    private static final Scanner scanner = new Scanner(System.in);


    public static List<String> symbols = List.of(
            "AUDCAD", "AUDCHF", "AUDJPY", "AUDNZD", "AUDSGD", "AUDUSD",
            "CADAUD", "CADCHF", "CADHKD", "CADJPY", "CADPLN", "CADSGD", "CADUSD",
            "CHFHKD", "CHFJPY", "CHFPLN", "CHFUSD", "CHFSAR", "CHFTWD", "CADINR",
            "DKKHKD", "DKKJPY", "DKKNOK", "DKKPLN", "DKKSEK", "DKKUSD",
            "EURAUD", "EURCAD", "EURCHF", "EURDKK", "EURGBP", "EURHKD", "EURHUF",
            "EURINR", "EURJPY", "EURNOK", "EURNZD", "EURPLN", "EURRON", "EURRUB",
            "EURSEK", "EURSGD", "EURTRY", "EURUSD", "GBPCHF", "GBPCAD", "GBPAUD",
            "GBPNOK", "GBPNZD", "GBPPLN", "GBPSEK", "GBPUSD", "GBPJPY", "GBPZAR",
            "JPYPLN", "JPYSGD", "JPYTWD", "JPYUSD", "NOKJPY", "NOKPLN", "NOKSEK",
            "NOKUSD", "NZDAUD", "NZDCAD", "NZDCHF", "NZDJPY", "NZDPLN", "NZDSGD",
            "NZDUSD", "PLNJPY", "PLNTWD", "PLNUSD", "SEKJPY", "SEKPLN",
            "SEKUSD", "SGDCHF", "SGDHKD", "SGDINR", "SGDJPY", "SGDPLN", "SGDTWD",
            "SGDUSD", "USDARS", "USDAUD", "USDCAD", "USDCHF", "USDHKD", "USDHUF",
            "USDILS", "USDINR", "USDMXN", "USDNOK", "USDPLN", "USDRON", "USDRUB",
            "USDSEK", "USDSGD", "USDTRY", "USDZAR", "USDJPY", "AUDHKD", "AUDINR",
            "AUDPLN"
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
        return scanner.nextLine();
    }

    public static Integer intInput(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine());
    }

    public static Double doubleInput(String prompt) {
        System.out.print(prompt);
        return Double.parseDouble(scanner.nextLine());
    }

    public static String formatSymbol(String prompt) {
        return prompt.substring(0, 3).concat("/").concat(prompt.substring(3));
    }

}
