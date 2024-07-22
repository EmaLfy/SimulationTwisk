package twisk.outils;

import javafx.concurrent.Task;

import java.util.ArrayList;

public class ThreadsManager {
    private ArrayList<Thread> threads;
    private static ThreadsManager instance = null;

    private ThreadsManager() {
        this.threads = new ArrayList<>();
    }

    public static ThreadsManager getInstance() {
        if (instance == null) {
            instance = new ThreadsManager();
        }
        return instance;
    }

    public void lancer(Runnable task) {
        Thread t = new Thread(task);
        threads.add(t);
        t.start();
    }

    public int getSize() {
        return threads.size();
    }

    public void detruireTout() {
        for (Thread t : threads) {
            t.interrupt();
        }
        threads.clear();
    }
}
