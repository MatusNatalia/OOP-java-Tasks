package ru.nsu.ccfit.matus.task5.server;

import ru.nsu.ccfit.matus.task5.messages.InfoMessage;
import ru.nsu.ccfit.matus.task5.messages.Message;
import ru.nsu.ccfit.matus.task5.messages.NameMessage;
import ru.nsu.ccfit.matus.task5.messages.TextMessage;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Connection implements Runnable{

    private String name;
    private final Socket socket;
    private final List<Connection> clients;
    private final List<Message> messages;
    private ObjectInputStream inputStream;
    private volatile boolean stop = false;

    private ConnectionWriter writer;

    public Connection(Socket socket, List<Connection> clients, List<Message> messages){
        this.clients = clients;
        this.messages = messages;
        this.socket = socket;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            writer = new ConnectionWriter(new ObjectOutputStream(socket.getOutputStream()));
            Thread thread = new Thread(writer);
            thread.start();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            this.readName();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.sendLastMessages();
        clients.add(this);
        while(!stop) {
            try {
                Message message = (Message) inputStream.readObject();
                synchronized (messages) {
                    for(Connection client : clients){
                        client.send(message);
                    }
                    messages.add(message);
                }
            } catch (IOException | ClassNotFoundException e) {
                TextMessage closeMessage = new TextMessage(name,"closed connection");
                synchronized (messages) {
                    for(Connection client : clients){
                        if(client == this){
                            continue;
                        }
                        client.send(closeMessage);
                    }
                    messages.add(closeMessage);
                }
                clients.remove(this);
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                writer.stop();
                stop = true;
            }
        }
    }

    private void sendLastMessages() {
        synchronized (messages) {
            int count = messages.size();
            if (count > 50) {
                count = 50;
            }
            writer.sendMessage(new InfoMessage(count));
            for (Message message : messages) {
                writer.sendMessage(message);
            }
        }
    }

    private void readName() throws IOException {
        try {
            NameMessage nameMessage = (NameMessage) inputStream.readObject();
            name = nameMessage.getName();
            for(Connection client : clients){
                if(this == client){
                    continue;
                }
                client.send(nameMessage);
            }
            synchronized (messages) {
                messages.add(nameMessage);
            }
        } catch (IOException | ClassNotFoundException e) {
            clients.remove(this);
            socket.close();
            writer.stop();
            stop = true;
        }
    }

    public void send(Message message) {
        writer.sendMessage(message);
    }

}
