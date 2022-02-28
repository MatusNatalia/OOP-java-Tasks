package ru.nsu.ccfit.matus.task2.commands;

import ru.nsu.ccfit.matus.task2.calculator.Context;
import ru.nsu.ccfit.matus.task2.exceptions.CommandException;
import ru.nsu.ccfit.matus.task2.exceptions.StackException;

public interface Command {
    void execute(String[] args, Context context) throws CommandException, StackException;
}
