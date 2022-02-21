package ru.nsu.ccfit.Matus.Task2.Main;
import ru.nsu.ccfit.Matus.Task2.calculator.Calculator;

public class Main {
    public static void main(String[] args) {
        String filename = args[0];
        try {
            Calculator calculator = new Calculator(filename);
            calculator.calculate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
