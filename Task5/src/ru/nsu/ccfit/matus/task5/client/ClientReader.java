package ru.nsu.ccfit.matus.task5.client;

import ru.nsu.ccfit.matus.task5.messages.InfoMessage;
import ru.nsu.ccfit.matus.task5.messages.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ClientReader implements Runnable {

    private final ObjectInputStream inputStream;
    private final ArrayList<Message> messages;
    private int newMessages = 0;
    private volatile boolean isStop = false;

    public ClientReader(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
        messages = new ArrayList<>();
    }

    @Override
    public void run() {
        this.readLastMessages();
        while (!isStop) {
            try {
                Message e = (Message) inputStream.readObject();
                synchronized (messages) {
                    messages.add(e);
                    newMessages = newMessages + 1;
                }
            } catch (IOException | ClassNotFoundException e) {
                isStop = true;
            }
        }
    }

    private void readLastMessages() {
        try {
            Message infoMessage = (InfoMessage) inputStream.readObject();
            int count = Integer.parseInt(infoMessage.getText());
            List<Message> newMsg = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                Message message = (Message) inputStream.readObject();
                newMsg.add(message);
            }
            synchronized (messages) {
                messages.addAll(newMsg);
                newMessages = newMessages + count;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getNewMessages() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>");

        synchronized (messages) {
            SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
            for (int i = newMessages; i < messages.size(); i++) {
                stringBuilder.append("[").append(ft.format(messages.get(i).getTime())).append("] ").append(messages.get(i).getName()).append(": ").append(messages.get(i).getText()).append("<br>");
            }
            newMessages = 0;
        }
        stringBuilder.append("</html>");
        return stringBuilder.toString();
    }


    public void stop() {
        isStop = true;
    }

}
