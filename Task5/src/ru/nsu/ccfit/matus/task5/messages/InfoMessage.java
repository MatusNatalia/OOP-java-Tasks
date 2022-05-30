package ru.nsu.ccfit.matus.task5.messages;

import java.util.Date;

public class InfoMessage implements Message{
    private Integer numLastMessages;

    public InfoMessage(Integer numLastMessages){
        this.numLastMessages = numLastMessages;
    }

    @Override
    public String getText() {
        return numLastMessages.toString();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Date getTime() {
        return null;
    }
}
