package ru.nsu.ccfit.matus.task3;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class CellButton extends JButton {
    String text;
    boolean opened;
    boolean flag;

    public CellButton(String str){
        text = str;
        opened = false;
        flag = false;
        this.setPreferredSize(new Dimension(33, 33));
        this.setFont(new Font("Arial", Font.BOLD, 18));
        this.setBackground(new Color(126, 152, 61));
        this.setBorder(new LineBorder(new Color(12, 80, 4)));
        this.setForeground(new Color(0, 0, 0));
        this.setOpaque(true);
    }

    public void setOpened(boolean opened) { this.opened = opened; }

    public void setFlag(boolean flag) { this.flag = flag; }

    public boolean isOpened() { return opened; }

    public boolean isFlag() { return flag; }

    public String getVal(){
        return text;
    }

    public void setVal(String str){
        text = str;
    }

}
