package calculator;

import network.Api;
import util.Help;

import java.util.List;

public class Calculator {

    public static double positionSize(double balance, double amountOfRiskInPercent, String symbol, double stopLoss) throws Exception {
        double livePrice = Api.getForexPrice(symbol);
        return Math.abs(((balance * amountOfRiskInPercent) / (livePrice - stopLoss))
                / valueOfOnePip(symbol, livePrice));
    }

    public static double margin(double tradeSize, String symbol, String accountCurrency, int marginRatio) throws Exception {
        String finalSymbol = symbol.substring(0, 3).concat(accountCurrency);
        tradeSize *= 100_000;
        return ((tradeSize * Api.getForexPrice(finalSymbol)) / marginRatio);
    }

    public static double valueOfOnePip(String symbol, double livePrice) {
        Double pipLocation = Help.getPipLocation(symbol);
        return (pipLocation / livePrice) * 100_000;
    }

    public static double valueOfOnePip(String symbol) throws Exception {
        double livePrice = Api.getForexPrice(symbol);
        Double pipLocation = Help.getPipLocation(symbol);
        return (pipLocation / livePrice) * 100_000;
    }

    public static void getAllLivePrice() throws Exception {
        List<String> symbols = Help.symbols;
        System.out.printf("%-9s %-10s\n", "symbol", "price");
        for (String symbol : symbols) {
            String formatSymbol = Help.formatSymbol(symbol);
            System.out.printf("%-9s %-10s\n", formatSymbol, Api.getForexPrice(symbol));
        }
    }

    public static void getBySymbol(String symbol) throws Exception {
        System.out.printf("%-9s %-10s\n", "symbol", "price");
        System.out.printf("%-9s %-10s\n", Help.formatSymbol(symbol), Api.getForexPrice(symbol));
    }
}
