package ru.nsu.ccfit.matus.task4.threadpool;

import java.util.ArrayDeque;

public class MyThreadPool {

    private final ThreadPoolWorker[] threadPoolWorkers;
    private final ArrayDeque<Runnable> queue;

    public MyThreadPool(int size){
        queue = new ArrayDeque<>();
        threadPoolWorkers = new ThreadPoolWorker[size];
        for(int i = 0; i < size; i++){
            threadPoolWorkers[i] = new ThreadPoolWorker(queue);
            new Thread(threadPoolWorkers[i]).start();
        }
    }

    public void addTask(Runnable task){
       // System.out.println("trying to add task");
        synchronized (queue){
         //   System.out.println("task added");
            queue.add(task);
            queue.notifyAll();
        }
    }

    public void stop() {
        for (ThreadPoolWorker threadPoolWorker : threadPoolWorkers) {
            threadPoolWorker.stop();
        }
    }
}
