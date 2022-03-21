package ru.nsu.ccfit.matus.task3;

public class Cell {
    private boolean isMine;
    private int num;
    private int open;

    public Cell(){
        isMine = false;
        num = 0;
        open = 0;
    }

    public boolean isMine(){
        return isMine;
    }

    public void setMine(){
        isMine = true;
    }

    public void removeMine(){
        isMine = false;
    }

    public void addNum(){
        num++;
    }

    public void removeNum(){
        num = 0;
    }

    public int getNum(){
        return num;
    }

    public void open(){
        open = 1;
    }

    public void close(){
        open = 0;
    }

    public boolean isOpen(){
        return open == 1;
    }

}
