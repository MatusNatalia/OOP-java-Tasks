package ru.nsu.ccfit.matus.task2;
import ru.nsu.ccfit.matus.task2.calculator.Calculator;

public class Main {
    public static void main(String[] args) {
        String filename = "C:\\Users\\nemat\\IdeaProjects\\Task2\\src\\ru\\nsu\\ccfit\\Matus\\Task2\\test.txt";
        try {
            Calculator calculator = new Calculator(filename);
            calculator.calculate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
