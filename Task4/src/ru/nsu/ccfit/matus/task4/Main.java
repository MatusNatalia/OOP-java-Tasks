package ru.nsu.ccfit.matus.task4;

import ru.nsu.ccfit.matus.task4.factory.Factory;
import ru.nsu.ccfit.matus.task4.view.GraphicView;

public class Main {
    public static void main(String[] args){
        try {
            Factory factory = new Factory();
            GraphicView view = new GraphicView(factory);
            factory.work();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
