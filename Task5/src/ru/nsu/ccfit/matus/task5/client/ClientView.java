package ru.nsu.ccfit.matus.task5.client;

import ru.nsu.ccfit.matus.task5.messages.NameMessage;
import ru.nsu.ccfit.matus.task5.messages.TextMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ClientView{

    private JFrame window;
    private Socket socket;
    ClientReader reader;
    ClientWriter writer;
    private final ArrayList<String> clientMessages = new ArrayList<>();
    private boolean isConnected = true;
    private String name;

    public ClientView() {

        window = new JFrame();
        window.setTitle("Chat");
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setPreferredSize(new Dimension(420, 470));
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(isConnected) {
                    try {
                        socket.close();
                        reader.stop();
                        writer.stop();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                System.out.println("stop");
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        try{
            socket = new Socket("localhost", 4004);
            writer = new ClientWriter(new ObjectOutputStream(socket.getOutputStream()));
            reader = new ClientReader(new ObjectInputStream(socket.getInputStream()));
        } catch (IOException e){
            mainPanel.add(new JLabel("Server is not available now"));
            isConnected = false;
        }

        if(isConnected) {
            JLabel getNameLabel = new JLabel("Please enter your name:");
            JTextField textField = new JTextField();
            textField.setBounds(130,200,150,25);
            JButton button = new JButton("Go");
            mainPanel.add(getNameLabel, BorderLayout.PAGE_START);
            mainPanel.add(textField, BorderLayout.AFTER_LAST_LINE);
            mainPanel.add(button, BorderLayout.AFTER_LAST_LINE);
            button.addActionListener(e -> {
                name = textField.getText();
                window.setTitle(window.getTitle() + " Name: "+ name);
                Thread tr1 = new Thread(writer);
                tr1.start();
                writer.sendMessage(new NameMessage(name));
                Thread tr2 = new Thread(reader);
                tr2.start();
                this.showChat();
            });

        }
        window.add(mainPanel);
        window.pack();
        window.setVisible(true);
    }

    public void showChat() {
        window.getContentPane().removeAll();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JLabel chat = new JLabel();
        chat.setVerticalAlignment(JLabel.BOTTOM);
        chat.setText("");
        JScrollPane scrollPane = new JScrollPane(chat, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel sendPanel = new JPanel(new BorderLayout());
        JTextField textField = new JTextField();
        JButton button = new JButton("send");
        sendPanel.add(textField, BorderLayout.CENTER);
        sendPanel.add(button, BorderLayout.EAST);

        button.addActionListener(e -> {
            if(!textField.getText().isEmpty()){
                writer.sendMessage(new TextMessage(name, textField.getText()));
                textField.setText("");
            }
        });

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(sendPanel, BorderLayout.PAGE_END);

        window.add(mainPanel);
        window.pack();
        window.repaint();

        ActionListener taskPerformer = e -> chat.setText(reader.getNewMessages());
        Timer timer = new Timer(500, taskPerformer);
        timer.start();
    }

}
