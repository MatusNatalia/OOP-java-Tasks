package ru.nsu.ccfit.matus.task5.messages;

import java.io.Serializable;
import java.util.Date;

public interface Message extends Serializable {

    String getText();

    String getName();

    Date getTime();

}
