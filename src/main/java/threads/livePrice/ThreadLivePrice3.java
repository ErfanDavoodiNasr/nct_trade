package threads.livePrice;


import main.Main;
import network.Api;
import util.Help;

public class ThreadLivePrice3 implements Runnable {
    @Override
    public void run() {
        for (int i=34; i<51; i++){
            String s = Help.symbols.get(i);
            try {
                System.out.printf("%-9s %-10s\n", Help.formatSymbol(s), Api.getForexPrice(s));
            } catch (Exception ignored) {
            }
        }
    }
}
