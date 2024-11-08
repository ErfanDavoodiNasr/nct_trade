package ir.ncttrade.util;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Scanner;

@UtilityClass
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
        if (symbol == null || symbol.isEmpty()) {
            throw new IllegalArgumentException("Symbol cannot be null or empty");
        }
        return symbol.contains("JPY") ? 0.01 : 0.0001;
    }

    public static <E> void println(E prompt) {
        System.out.println(prompt);
    }

    public static String input(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static Integer intInput(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            return null;
        }
    }

    public static Double doubleInput(String prompt) {
        System.out.print(prompt);
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return null;
        }
    }

    public static String formatSymbol(String symbol) {
        if (symbol == null || symbol.length() != 6) {
            throw new IllegalArgumentException("Symbol must be exactly 6 characters long.");
        }
        return symbol.substring(0, 3).toUpperCase() + "/" + symbol.substring(3).toUpperCase();
    }
}
