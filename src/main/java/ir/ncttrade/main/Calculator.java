package ir.ncttrade.main;

import ir.ncttrade.network.Api;
import ir.ncttrade.util.Help;

public class Calculator {

    public static Double positionSize(Double balance, Double amountOfRiskInPercent, String symbol, Double stopLoss) throws Exception {
        amountOfRiskInPercent /= 100;
        Double livePrice = Api.getForexPrice(symbol);
        return Math.abs(((balance * amountOfRiskInPercent) / (livePrice - stopLoss))
                / valueOfOnePip(symbol, livePrice));
    }

    public static Double margin(Double tradeSize, String symbol, String accountCurrency, Integer marginRatio) throws Exception {
        String finalSymbol = symbol.substring(0, 3).concat(accountCurrency.substring(3));
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

    public static Double getBySymbol(String symbol) throws Exception {
        return Api.getForexPrice(symbol);
    }

    public static Double[] currencyConverter(String firstCurrency, String secondCurrency) throws Exception {
        Double[] result = new Double[2];
        // result[0] = firstSecond   &   result[1] = secondFirst
        result[0] = Api.getForexPrice(firstCurrency.substring(0,3).concat(secondCurrency.substring(3)));
        result[1] = Api.getForexPrice(secondCurrency.substring(0,3).concat(firstCurrency.substring(3)));
        return result;
    }

    public static Double leverage(String symbol, Double margin, Double tradeSize) throws Exception {
        Double livePrice = Api.getForexPrice(symbol);
        return (livePrice * tradeSize * 100_000) / margin;
    }

    public static Double riskOfRuin(Double winRateInPercent, Double averageWin, Double averageLoss, Double riskPerTradeInPercent, Double lossLevelInPercent) {
        winRateInPercent /= 100;
        riskPerTradeInPercent /= 100;
        lossLevelInPercent /= 100;
        Double expectedValue = (winRateInPercent * averageWin) - ((1 - winRateInPercent) * (averageLoss));
        return (Math.pow(((averageLoss - expectedValue) / (averageLoss + expectedValue)), (lossLevelInPercent / riskPerTradeInPercent))) * 100;
    }
    
}
