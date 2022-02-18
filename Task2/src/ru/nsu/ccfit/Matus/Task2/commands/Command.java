package ru.nsu.ccfit.Matus.Task2.commands;

import ru.nsu.ccfit.Matus.Task2.calculator.Context;
import ru.nsu.ccfit.Matus.Task2.exceptions.CommandException;
import ru.nsu.ccfit.Matus.Task2.exceptions.StackException;

public interface Command {
    void execute(String[] args, Context context) throws CommandException, StackException;
}
