package ru.nsu.ccfit.matus.task5.messages;

import java.util.Date;

public class NameMessage implements Message{
    private String name;
    private String text;
    private Date time;

    public NameMessage(String name){
        this.name = name;
        text = "joined";
        time = new Date();
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Date getTime() {
        return time;
    }
}
