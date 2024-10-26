package calculator;

import network.Api;
import threads.livePrice.*;
import util.Help;

import java.util.List;

import static util.Help.println;
import static util.Help.symbols;

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
        try{
            Thread t1 = new Thread(new ThreadLivePrice1());
            Thread t2 = new Thread(new ThreadLivePrice2());
            Thread t3 = new Thread(new ThreadLivePrice3());
            Thread t4 = new Thread(new ThreadLivePrice4());
            Thread t5 = new Thread(new ThreadLivePrice6());
            Thread t6 = new Thread(new ThreadLivePrice6());
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
            t6.start();
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
        }catch (Exception e){
            println(e.getMessage());
        }
    }

    public static void getBySymbol(String symbol) throws Exception {
        System.out.printf("%-9s %-10s\n", "symbol", "price");
        System.out.printf("%-9s %-10s\n", Help.formatSymbol(symbol), Api.getForexPrice(symbol));
    }
}
