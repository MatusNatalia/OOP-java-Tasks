package ru.nsu.ccfit.matus.task3;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //String gameMode = args[0];
        try {
            Game game = new Game("g");//gameMode);
            game.play();
        } catch (UnknownArgException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
