package calculator;

import network.Api;
import threads.livePrice.*;
import util.Help;

import java.util.List;

import static util.Help.println;
import static util.Help.symbols;

public class Calculator {

    public static Double positionSize(Double balance, Double amountOfRiskInPercent, String symbol, Double stopLoss) throws Exception {
        Double livePrice = Api.getForexPrice(symbol);
        return Math.abs(((balance * amountOfRiskInPercent) / (livePrice - stopLoss))
                / valueOfOnePip(symbol, livePrice));
    }

    public static Double margin(Double tradeSize, String symbol, String accountCurrency, Integer marginRatio) throws Exception {
        String finalSymbol = symbol.substring(0, 3).concat(accountCurrency);
        tradeSize *= 100_000;
        return ((tradeSize * Api.getForexPrice(finalSymbol)) / marginRatio);
    }

    public static Double valueOfOnePip(String symbol, Double livePrice) {
        Double pipLocation = Help.getPipLocation(symbol);
        return (pipLocation / livePrice) * 100_000;
    }

    public static Double valueOfOnePip(String symbol) throws Exception {
        Double livePrice = Api.getForexPrice(symbol);
        Double pipLocation = Help.getPipLocation(symbol);
        return (pipLocation / livePrice) * 100_000;
    }

    public static void getAllLivePrice() throws Exception {
        try {
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
        } catch (Exception e) {
            println(e.getMessage());
        }
    }

    public static Double getBySymbol(String symbol) throws Exception {
        return Api.getForexPrice(symbol);
    }

    public static Double[] currencyConverter(String firstCurrency, String secondCurrency) throws Exception {
        Double[] result = new Double[2];
        // result[0] = firstSecond   &   result[1] = secondFirst
        result[0] = Api.getForexPrice(firstCurrency.concat(secondCurrency));
        result[1] = Api.getForexPrice(secondCurrency.concat(firstCurrency));
        return result;
    }
}
