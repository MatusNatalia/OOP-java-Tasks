package ru.nsu.ccfit.matus.task3;

import java.io.IOException;

public class Game {
    private String gameMode;
    private Controller controller;
    private Model model;
    private View view;

    public Game(String gameMode) throws UnknownArgException, IOException {
        this.gameMode = gameMode;
        controller = new Controller();
        model = new Model(9);
        switch (gameMode) {
            case "t" -> view = new ConsoleView();
            case "g" -> view = new GraphicView(model, 9);
            default -> throw new UnknownArgException(gameMode);
        }
    }

    public void play() {
        String[] step={""};
        view.start();
        do {
            try {
                step = view.getNextStep();
                controller.execute(step, model, view);
            }catch (InterruptedException | UnknownCommandException | OutOfFieldException e){
                view.showException(e);
            }
        } while (!"quit".equals(step[0]));
    }
}
