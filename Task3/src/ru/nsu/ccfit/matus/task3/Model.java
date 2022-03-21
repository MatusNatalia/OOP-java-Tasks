package ru.nsu.ccfit.matus.task3;

import java.util.ArrayList;
import java.util.Random;

public class Model {
    private Cell[] field;
    private int opened;
    private boolean endGame;
    private boolean win;
    private int N;
    private int bombs;
    private ArrayList<Boolean> score;

    public Model(int N){
        this.N = N;
        bombs = 10;
        opened = 0;
        win = false;
        endGame = false;
        score = new ArrayList<Boolean>();
        field = new Cell[N*N];
        for(int i = 0; i < N*N; i++){
            field[i] = new Cell();
        }
        Random rand = new Random();
        int x, y;
        for(int i = 0; i < bombs; i++){
            do{
                x = rand.nextInt(N);
                y = rand.nextInt(N);
                if(!field[N*x + y].isMine()){
                    field[N*x + y].setMine();
                    break;
                }
            }while(true);
        }
        int idx;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int k = -1; k < 2; k++){
                    for(int l = -1; l < 2; l++){
                        idx = N * (i + k) + (j + l);
                        if (k != 0 || l != 0) {
                            if (i + k >= 0 && j + l >= 0 && i + k < N && j + l < N) {
                                if (field[idx].isMine()) {
                                    field[N * i + j].addNum();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void openNeigh(int x, int y){
        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                int idx = N * (x + i) + (y + j);
                if((i != 0 || j != 0) && x + i >= 0 && x + i < N && y + j >= 0 && y + j < N) {
                    if(field[N * x + y].getNum() != 0) {
                        if(!field[idx].isOpen() && !field[idx].isMine() && field[idx].getNum() == 0 ){//&& !field[idx].isChecked()){
                            field[idx].open();
                            opened++;
                            openNeigh(x + i, y + j);
                        }
                    }
                    else{
                        if(!field[idx].isOpen()) {
                            field[idx].open();
                            opened++;
                            openNeigh(x + i, y + j);
                        }
                    }
                }
            }
        }
    }

    public void update(int x, int y) throws OutOfFieldException {
        if (x < 0 || x > N - 1 || y < 0 || y > N - 1){
            throw new OutOfFieldException();
        }
        int idx = N*x + y;
        if(field[idx].isMine()){
            field[idx].open();
            opened++;
            /*if(opened == 71) {
                win = true;
                endGame = true;
                score.add(true);
            }
            else{*/
                win = false;
                endGame = true;
                score.add(false);
            //}
        }
        else{
            field[idx].open();
            opened++;
            openNeigh(x, y);
            if(opened == N * N - (N + 1)){
                win = true;
                endGame = true;
                score.add(true);
            }
            else {
                endGame = false;
            }
        }
    }

    public void clear(){
        opened = 0;
        win = false;
        endGame = false;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                field[i*N + j].removeMine();
                field[i*N + j].removeNum();
                field[i*N + j].close();
            }
        }
        Random rand = new Random();
        int x, y;
        for(int i = 0; i < N + 1; i++){
            do{
                x = rand.nextInt(N);
                y = rand.nextInt(N);
                if(!field[N*x + y].isMine()){
                    field[N*x + y].setMine();
                    break;
                }
            }while(true);
        }
        int idx;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int k = -1; k < 2; k++){
                    for(int l = -1; l < 2; l++){
                        idx = N * (i + k) + (j + l);
                        if (k != 0 || l != 0) {
                            if (i + k >= 0 && j + l >= 0 && i + k < N && j + l < N) {
                                if (field[idx].isMine()) {
                                    field[N * i + j].addNum();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean isEndGame(){
        return endGame;
    }

    public boolean isWin(){
        return win;
    }

    public Cell getCell(int x, int y){
        return field[N*x+y];
    }

    public ArrayList<Boolean> getScore(){
        return score;
    }

    public void changeSize(int N){

        this.N = N;
        if(N == 9){
            bombs = 10;
        }
        else{
            bombs = 30;
        }
        opened = 0;
        win = false;
        endGame = false;
        //score = new ArrayList<Boolean>();
        field = new Cell[N*N];
        for(int i = 0; i < N*N; i++){
            field[i] = new Cell();
        }
        Random rand = new Random();
        int x, y;
        for(int i = 0; i < bombs; i++){
            do{
                x = rand.nextInt(N);
                y = rand.nextInt(N);
                if(!field[N*x + y].isMine()){
                    field[N*x + y].setMine();
                    break;
                }
            }while(true);
        }
        int idx;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int k = -1; k < 2; k++){
                    for(int l = -1; l < 2; l++){
                        idx = N * (i + k) + (j + l);
                        if (k != 0 || l != 0) {
                            if (i + k >= 0 && j + l >= 0 && i + k < N && j + l < N) {
                                if (field[idx].isMine()) {
                                    field[N * i + j].addNum();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
