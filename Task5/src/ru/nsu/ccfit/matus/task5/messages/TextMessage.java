package ru.nsu.ccfit.matus.task5.messages;

import java.util.Date;

public class TextMessage implements Message{
    private String name;
    private String text;
    private Date time;

    public TextMessage(String name, String text){
        this.name = name;
        this.text = text;
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
