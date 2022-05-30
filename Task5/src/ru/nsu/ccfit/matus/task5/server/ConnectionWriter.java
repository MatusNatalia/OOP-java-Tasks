package ru.nsu.ccfit.matus.task5.server;

import ru.nsu.ccfit.matus.task5.messages.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ConnectionWriter implements Runnable{

    private final ObjectOutputStream outputStream;
    private final List<Message> writeQueue = new ArrayList<>();
    private volatile boolean isStop = false;

    public ConnectionWriter(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        while (!isStop) {
            List<Message> queue = new ArrayList<>();
            synchronized (writeQueue) {
                while (writeQueue.isEmpty()) {
                    try {
                        writeQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.addAll(writeQueue);
                writeQueue.clear();
            }
            for (Message message : queue) {
                sendMessageImpl(message);
            }
        }
    }

    public void sendMessage(Message message) {
        synchronized (writeQueue) {
            writeQueue.add(message);
            writeQueue.notifyAll();
        }
    }

    private void sendMessageImpl(Message message) {
        try {
            outputStream.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        isStop = true;
    }

}
