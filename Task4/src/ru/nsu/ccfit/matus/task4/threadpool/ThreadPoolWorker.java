package ru.nsu.ccfit.matus.task4.threadpool;

import java.util.ArrayDeque;

public class ThreadPoolWorker implements Runnable{

    private final ArrayDeque<Runnable> queue;
    private boolean stop = false;

    public ThreadPoolWorker(ArrayDeque<Runnable> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        Runnable t = null;
        while(!stop) {
            synchronized (queue) {
                if (queue.isEmpty()) {
                    //System.out.println("i am waiting for task");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        //System.out.println("running worker");
                        t = queue.pollFirst();
                        //System.out.println("threadpool worker done");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if(t != null) {
                t.run();
            }
        }
    }

    public void stop() {
        stop = true;
        Thread.currentThread().interrupt();
    }
}
