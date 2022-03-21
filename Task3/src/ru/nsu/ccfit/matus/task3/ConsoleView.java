package ru.nsu.ccfit.matus.task3;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleView implements View{

    @Override
    public void start(){
        System.out.println("Welcome to minesweeper!\nTo start new game type 'new'\nTo quit game type 'quit'\nTo see scores type 'score'\nTo see more type 'about'");
    }

    @Override
    public void showAbout(){
        this.start();
    }

    @Override
    public void showScore(Model model){
        ArrayList<Boolean> score = model.getScore();
        if(score.size() == 0){
            System.out.println("No games played yet");
        }
        for(int i = 0; i < score.size(); i++){
            if(score.get(i)){
                System.out.println("Game " + i + ": win");
            }
            else{
                System.out.println("Game " + i + ": lose");
            }
        }
    }

    @Override
    public String[] getNextStep() {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        String str = scanner.next();
        String[] step = str.split(" ");
        return step;
    }

    @Override
    public void update(Model model){
        Cell cell;
        if(model.isEndGame() && model.isWin()){
            System.out.println("You win!");
            System.out.println("      0    1    2    3    4    5    6    7    8\n");
            for(int i = 0; i < 9;i++) {
                System.out.print(i + "   ");
                for(int j = 0; j < 9; j++){
                    cell = model.getCell(i, j);
                    if(!cell.isMine()){
                        System.out.print("  " + cell.getNum() + "  ");
                    }
                    else {
                        System.out.print(" |*| ");
                    }
                }
                System.out.println("\n");
            }
        }
        else if(model.isEndGame() && !model.isWin()){
            System.out.println("Boom! You lose!");
            System.out.println("      0    1    2    3    4    5    6    7    8\n");
            for(int i = 0; i < 9;i++) {
                System.out.print(i + "   ");
                for(int j = 0; j< 9; j++){
                    cell = model.getCell(i, j);
                    if(!cell.isMine()){
                        System.out.print("  " + cell.getNum() + "  ");
                    }
                    else {
                        System.out.print(" |*| ");
                    }
                }
                System.out.println("\n");
            }
        }
        else {
            System.out.println("      0    1    2    3    4    5    6    7    8\n");
            for (int i = 0; i < 9; i++) {
                System.out.print(i + "   ");
                for (int j = 0; j < 9; j++) {
                    cell = model.getCell(i, j);
                    if (cell.isOpen() && !cell.isMine()) {
                        System.out.print("  " + cell.getNum() + "  ");
                    } else if (cell.isOpen() && cell.isMine()) {
                        System.out.print(" |*| ");
                    } else {
                        System.out.print(" |?| ");
                    }
                }
                System.out.println("\n");
            }
        }
    }

    @Override
    public void clear(Model model){
        System.out.println("      0    1    2    3    4    5    6    7    8\n");
        for(int i = 0; i < 9;i++) {
            System.out.print(i + "   ");
            for (int j = 0; j < 9; j++) {
                    System.out.print(" |?| ");
                }
            System.out.println("\n");
        }
    }

    @Override
    public void showException(Exception e){
        System.out.println(e.getMessage());
    }

    @Override
    public void changeSize(Model model) {

    }

    @Override
    public void quit(){
        System.out.println("Bye!");
    }

}
