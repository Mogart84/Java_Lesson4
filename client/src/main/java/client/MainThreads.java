package client;

public class MainThreads {

    static final int numRepeats = 5;
    static String arr[] = {"A","B","C"};
    static volatile String currLetter = arr[0];
    static Object monitor = new Object();

    public static void main(String[] args) {

        new Thread(()->{
            try {
                for (int i = 0; i < numRepeats; i++) {
                    synchronized (monitor) {
                        while (!arr[0].equals(currLetter)) {
                                monitor.wait();
                        }
                        System.out.println(arr[0]);
                        currLetter = arr[1];
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                for (int i = 0; i < numRepeats; i++) {
                    synchronized (monitor) {
                        while (!arr[1].equals(currLetter)) {
                            monitor.wait();
                        }
                        System.out.println(arr[1]);
                        currLetter = arr[2];
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                for (int i = 0; i < numRepeats; i++) {
                    synchronized (monitor) {
                        while (!arr[2].equals(currLetter)) {
                            monitor.wait();
                        }
                        System.out.println(arr[2]);
                        currLetter = arr[0];
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }

}
