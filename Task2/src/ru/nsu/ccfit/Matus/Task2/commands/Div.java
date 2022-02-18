package ru.nsu.ccfit.Matus.Task2.commands;

import ru.nsu.ccfit.Matus.Task2.calculator.Context;
import ru.nsu.ccfit.Matus.Task2.exceptions.StackException;
import ru.nsu.ccfit.Matus.Task2.exceptions.WrongArguments;

public class Div implements Command {
    @Override
    public void execute(String[] args, Context context) throws WrongArguments, StackException {
        if (args.length != 0) {
            throw new WrongArguments();
        } else {
            Double a = context.peek();
            context.pop();
            Double b = context.peek();
            context.pop();
            context.push(a / b);
        }
    }
}
