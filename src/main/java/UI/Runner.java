package UI;

import calculator.Calculator;
import util.Help;

import java.text.DecimalFormat;

import static util.Help.*;

public class Runner {

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
            println("7 - exit");
            Integer number = intInput("choose a number: ");
            switch (number) {
                case 1 -> livePriceMenu();
                case 2 -> positionSize();
                case 3 -> margin();
                case 4 -> valueOfOnePip();
                case 5 -> CurrencyConverter();
                case 6 -> leverage();
                case 7 -> System.exit(0);
                default -> println("choose a number between 1 and 6");
            }
        }
    }

    private static void leverage() {
        try {
            String symbol = input("enter symbol(EURUSD, USDJPY, ...): ").toUpperCase();
            String accountCurrency = input("enter account currency: ").toUpperCase();
            symbol = symbol.substring(0, 3).concat(accountCurrency);
            Double margin = doubleInput("enter margin: ");
            Double tradeSize = doubleInput("enter trade size(lots): ");
            Double result = Calculator.leverage(symbol,margin,tradeSize);
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.000000");
            println("result: ".concat(decimalFormat.format(result)));
        } catch (Exception e) {
            println(e.getMessage());
        }
    }

    private static void CurrencyConverter() {
        try{
            String firstCurrency = input("enter first currency: ").toUpperCase();
            String secondCurrency = input("enter second currency: ").toUpperCase();
            Double[] result = Calculator.currencyConverter(firstCurrency, secondCurrency);
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00000");
            println("1 ".concat(firstCurrency).concat(" = ") + decimalFormat.format(result[0]) + " ".concat(secondCurrency));
            println("1 ".concat(secondCurrency).concat(" = ") + decimalFormat.format(result[1]) + " ".concat(firstCurrency));

        }catch (Exception e) {
            println(e.getMessage());
        }
    }

    private static void margin() {
        try {
            String symbol = input("enter symbol(EURUSD, USDJPY, ...): ").toUpperCase();
            String accountCurrency = input("enter account currency: ").toUpperCase();
            Integer marginRatio = intInput("enter margin ratio: ");
            Double tradeSize = doubleInput("enter trade size(lots): ");
            Double result = Calculator.margin(tradeSize, symbol, accountCurrency, marginRatio);
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.0000");
            println("result: " + decimalFormat.format(result));
        } catch (Exception e) {
            println(e.getMessage());
        }
    }

    private static void valueOfOnePip() {
        try {
            String symbol = input("enter symbol(EURUSD, USDJPY, ...): ").toUpperCase();
            Double result = Calculator.valueOfOnePip(symbol);
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.0000");
            println("result: " + decimalFormat.format(result));
        } catch (Exception e) {
            println(e.getMessage());
        }
    }

    private static void positionSize() {
        try {
            String symbol = input("enter symbol(EURUSD, USDJPY, ...): ").toUpperCase();
            Double balance = doubleInput("enter balance: ");
            Double amountOfRisk = doubleInput("enter amount of risk: ");
            amountOfRisk /= 100;
            Double stopLoss = doubleInput("enter stop loss: ");
            Double result = Calculator.positionSize(balance, amountOfRisk, symbol, stopLoss);
            DecimalFormat decimalFormat = new DecimalFormat("#,##0");
            println("units: " + decimalFormat.format((result * 10_000)));
            decimalFormat = new DecimalFormat("#,##0.0000");
            println("Lots: " + decimalFormat.format(result));
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
                case 3 -> {
                    return;
                }
                default -> println("choose a number between 1 and 3");
            }
        }
    }

    private static void getAll() {
        try {
            Calculator.getAllLivePrice();
        } catch (Exception e) {
            println(e.getMessage());
        }
    }

    private static void getBySymbol() {
        try {
            String symbol = input("choose a symbol(EURUSD, USDJPY, ...): ").toUpperCase();
            Double result = Calculator.getBySymbol(symbol);
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.0000");
            System.out.printf("%-9s %-10s\n", "symbol", "price");
            System.out.printf("%-9s %-10s\n", Help.formatSymbol(symbol), decimalFormat.format(result));
        } catch (Exception e) {
            println(e.getMessage());
        }
    }
}
