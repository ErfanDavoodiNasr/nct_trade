package ir.ncttrade.UI;


import ir.ncttrade.main.Calculator;
import ir.ncttrade.main.LivePrice;
import java.text.DecimalFormat;
import static ir.ncttrade.util.Help.*;

public class Runner {

    public static final DecimalFormat DECIMAL_FORMAT_0;
    public static final DecimalFormat DECIMAL_FORMAT_3;
    public static final DecimalFormat DECIMAL_FORMAT_4;
    public static final DecimalFormat DECIMAL_FORMAT_5;
    public static final DecimalFormat DECIMAL_FORMAT_6;

    static {
        DECIMAL_FORMAT_0 = new DecimalFormat("#,##0");
        DECIMAL_FORMAT_3 = new DecimalFormat("#,##0.000");
        DECIMAL_FORMAT_4 = new DecimalFormat("#,##0.0000");
        DECIMAL_FORMAT_5 = new DecimalFormat("#,##0.00000");
        DECIMAL_FORMAT_6 = new DecimalFormat("#,##0.000000");
    }

    public static void run() {
        while (true) {
            println("███╗   ██╗ ██████╗████████╗    ████████╗██████╗  █████╗ ██████╗ ███████╗");
            println("████╗  ██║██╔════╝╚══██╔══╝    ╚══██╔══╝██╔══██╗██╔══██╗██╔══██╗██╔════╝");
            println("██╔██╗ ██║██║        ██║          ██║   ██████╔╝███████║██║  ██║█████╗");
            println("██║╚██╗██║██║        ██║          ██║   ██╔══██╗██╔══██║██║  ██║██╔══╝");
            println("██║ ╚████║╚██████╗   ██║          ██║   ██║  ██║██║  ██║██████╔╝███████╗");
            println("╚═╝  ╚═══╝ ╚═════╝   ╚═╝          ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚═════╝ ╚══════╝");
            println("");
            println("1 - live forex prices");
            println("2 - position size");
            println("3 - margin calculator");
            println("4 - value of one pip");
            println("5 - currency converter");
            println("6 - leverage calculator");
            println("7 - risk of ruin calculator");
            println("8 - exit");
            Integer number = intInput("choose a number: ");
            switch (number) {
                case 1 -> livePriceMenu();
                case 2 -> positionSize();
                case 3 -> margin();
                case 4 -> valueOfOnePip();
                case 5 -> CurrencyConverter();
                case 6 -> leverage();
                case 7 -> riskOfRuin();
                case 8 -> System.exit(0);
                case null -> {
                    // intInput() throws exception
                }
                default -> println("choose a number between 1 and 6");
            }
        }
    }

    private static void riskOfRuin() {
        Double result = Calculator.riskOfRuin(
                doubleInput("enter win rate in percent: "),
                doubleInput("enter average win: "),
                doubleInput("enter average loss: "),
                doubleInput("enter risk per trade in percent: "),
                doubleInput("enter loss level in percent: ")
        );
        println("result: " + DECIMAL_FORMAT_3.format(result).concat("%"));
    }

    private static void leverage() {
        try {
            String symbol = input("enter symbol(EURUSD, USDJPY, ...): ").toUpperCase();
            String accountCurrency = input("enter account currency: ").toUpperCase();
            Double result = Calculator.leverage(
                    formatCurrencyPair(symbol, accountCurrency),
                    doubleInput("enter margin: "),
                    doubleInput("enter trade size(lots): ")
            );
            println("result: ".concat(DECIMAL_FORMAT_6.format(result)));
        } catch (Exception e) {
            println(e.getMessage());
        }
    }

    private static String formatCurrencyPair(String symbol, String accountCurrency) {
        return symbol.substring(0, 3).concat(accountCurrency);
    }

    private static void CurrencyConverter() {
        try{
            String firstCurrency = input("enter first currency: ").toUpperCase();
            String secondCurrency = input("enter second currency: ").toUpperCase();
            Double[] result = Calculator.currencyConverter(firstCurrency, secondCurrency);
            println("1 ".concat(firstCurrency).concat(" = ") + DECIMAL_FORMAT_5.format(result[0]) + " ".concat(secondCurrency));
            println("1 ".concat(secondCurrency).concat(" = ") + DECIMAL_FORMAT_5.format(result[1]) + " ".concat(firstCurrency));

        }catch (Exception e) {
            println(e.getMessage());
        }
    }

    private static void margin() {
        try {
            Double result = Calculator.margin(
                    doubleInput("enter trade size(lots): "),
                    input("enter symbol(EURUSD, USDJPY, ...): ").toUpperCase(),
                    input("enter account currency: ").toUpperCase(),
                    intInput("enter margin ratio: ")
            );
            println("result: " + DECIMAL_FORMAT_4.format(result));
        } catch (Exception e) {
            println(e.getMessage());
        }
    }

    private static void valueOfOnePip() {
        try {
            Double result = Calculator.valueOfOnePip(input(
                    "enter symbol(EURUSD, USDJPY, ...): ").toUpperCase()
            );
            println("result: " + DECIMAL_FORMAT_4.format(result));
        } catch (Exception e) {
            println(e.getMessage());
        }
    }

    private static void positionSize() {
        try {
            Double result = Calculator.positionSize(
                    doubleInput("enter balance: "),
                    doubleInput("enter amount of risk in percent: "),
                    input("enter symbol(EURUSD, USDJPY, ...): ").toUpperCase(),
                    doubleInput("enter stop loss: ")
            );
            println("units: " + DECIMAL_FORMAT_0.format((result * 10_000)));
            println("Lots: " + DECIMAL_FORMAT_4.format(result));
        } catch (Exception e) {
            println(e.getMessage());
        }
    }

    private static void livePriceMenu() {
        while (true) {
            println("1 - live all forex prices");
            println("2 - live price symbol");
            println("3 - return to last page");
            Integer number = intInput("choose a number: ");
            switch (number) {
                case 1 -> getAll();
                case 2 -> getBySymbol();
                case 3 -> {println("exiting...");
                    return;
                }
                case null -> {
                    // intInput() throws exception
                }
                default -> println("choose a number between 1 and 3");
            }
        }
    }

    private static void getAll() {
        try {
            LivePrice.getLivePrice();
        } catch (Exception e) {
            println(e.getMessage());
        }
    }

    private static void getBySymbol() {
        try {
            String symbol = input("choose a symbol(EURUSD, USDJPY, ...): ").toUpperCase();
            Double result = Calculator.getBySymbol(symbol);
            System.out.printf("%-9s %-10s\n", "symbol", "price");
            System.out.printf("%-9s %-10s\n", formatSymbol(symbol), DECIMAL_FORMAT_4.format(result));
        } catch (Exception e) {
            println(e.getMessage());
        }
    }
}
