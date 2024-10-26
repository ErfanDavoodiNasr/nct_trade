package threads.livePrice;

import main.Main;
import network.Api;
import util.Help;

public class ThreadLivePrice6 implements Runnable{
    @Override
    public void run() {
        for (int i=85; i<Help.symbols.size(); i++){
            String s = Help.symbols.get(i);
            try {
                System.out.printf("%-9s %-10s\n", Help.formatSymbol(s), Api.getForexPrice(s));
            } catch (Exception ignored) {
            }
        }
    }
}
