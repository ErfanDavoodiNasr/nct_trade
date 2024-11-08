package ir.ncttrade.main;


import ir.ncttrade.UI.Runner;
import ir.ncttrade.network.Api;
import ir.ncttrade.util.Help;

public class LivePrice {

    public static void getLivePrice() throws InterruptedException {
        Thread thread = thread1();
        Thread thread1 = thread2();
        Thread thread2 = thread3();
        Thread thread3 = thread4();
        Thread thread4 = thread5();
        Thread thread5 = thread6();
        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread.join();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
    }

    private static Thread thread1() throws InterruptedException {
        return new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 17; i++) {
                    showLivePrice(i);
                }
            }
        });
    }
    private static Thread thread2() throws InterruptedException {
        return new Thread(new Runnable() {
            public void run() {
                for (int i = 17; i < 34; i++) {
                    showLivePrice(i);
                }
            }
        });
    }

    private static void showLivePrice(int i) {
        String s = Help.symbols.get(i);
        try {
            System.out.printf("%-9s %-10s\n", Help.formatSymbol(s), Runner.DECIMAL_FORMAT_6.format(Api.getForexPrice(s)));
        } catch (Exception ignored) {
        }
    }

    private static Thread thread3() throws InterruptedException {
        return new Thread(new Runnable() {
            public void run() {
                for (int i = 34; i < 51; i++) {
                    showLivePrice(i);
                }
            }
        });
    }
    private static Thread thread4() throws InterruptedException {
        return new Thread(new Runnable() {
            public void run() {
                for (int i = 51; i < 68; i++) {
                    showLivePrice(i);
                }
            }
        });
    }
    private static Thread thread5() throws InterruptedException {
        return new Thread(new Runnable() {
            public void run() {
                for (int i = 68; i < 85; i++) {
                    showLivePrice(i);
                }
            }
        });
    }
    private static Thread thread6() throws InterruptedException {
        return new Thread(new Runnable() {
            public void run() {
                for (int i = 85; i < Help.symbols.size(); i++) {
                    showLivePrice(i);
                }
            }
        });
    }

}
