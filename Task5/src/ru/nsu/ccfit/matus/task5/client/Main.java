package ru.nsu.ccfit.matus.task5.client;

public class Main {
    public static void main(String[] args) {
        Thread tr = new Thread(() -> {
            ClientView clientView = new ClientView();
        });
        tr.start();
    }
}
