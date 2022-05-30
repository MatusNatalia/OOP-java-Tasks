package ru.nsu.ccfit.matus.task5.server;

import ru.nsu.ccfit.matus.task5.messages.Message;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Server implements Runnable{
    private List<Connection> clients;
    private ServerSocket serverSocket;
    private List<Message> messages;

    public Server() {
        InputStream stream = Server.class.getResourceAsStream("Config.txt");
        Properties config = new Properties();
        if (stream == null) {
            System.out.println("Configuration file not found");
        } else {
            try {
                config.load(stream);
                serverSocket = new ServerSocket(Integer.parseInt(config.getProperty("Port")), 2, InetAddress.getByName("localhost"));
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        clients = new ArrayList<>();
        messages = new ArrayList<>();
    }


    @Override
    public void run() {
        while(true){
            try {
                Connection connection = new Connection(serverSocket.accept(), clients, messages);
                Thread thread = new Thread(connection);
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
