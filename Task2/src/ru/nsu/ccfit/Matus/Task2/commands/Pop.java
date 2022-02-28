package ru.nsu.ccfit.matus.task2.commands;

import ru.nsu.ccfit.matus.task2.calculator.Context;
import ru.nsu.ccfit.matus.task2.exceptions.StackException;
import ru.nsu.ccfit.matus.task2.exceptions.WrongArguments;

public class Pop implements Command{
    @Override
    public void execute(String[] args, Context context) throws StackException, WrongArguments {
        if (args.length != 0) {
            throw new WrongArguments();
        } else {
            context.pop();
        }
    }
}
