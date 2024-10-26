package threads.livePrice;

import main.Main;
import network.Api;
import util.Help;

public class ThreadLivePrice2 implements Runnable{
    @Override
    public void run() {
        for (int i=17; i<34; i++){
            String s = Help.symbols.get(i);
            try {
                System.out.printf("%-9s %-10s\n", Help.formatSymbol(s), Api.getForexPrice(s));
            } catch (Exception ignored) {
            }
        }
    }
}