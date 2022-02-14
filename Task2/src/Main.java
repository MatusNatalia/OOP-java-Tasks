import calculator.*;

public class Main {
    public static void main(String[] args) {
        String filename = "C:\\Users\\nemat\\IdeaProjects\\Task2\\src\\calculator\\test.txt";
        try {
            Calculator calculator = new Calculator(filename);
            calculator.calculate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
