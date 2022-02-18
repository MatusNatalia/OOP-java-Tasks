package ru.nsu.ccfit.Matus.Task2.commands;

import ru.nsu.ccfit.Matus.Task2.calculator.Context;
import ru.nsu.ccfit.Matus.Task2.exceptions.*;

public class Define implements Command{
    @Override
    public void execute(String[] args, Context context) throws CommandException {
        if (args.length != 2) {
            throw new WrongArguments();
        }
        else {
            try {
                context.define(args[0], Double.parseDouble(args[1]));
            } catch (NumberFormatException e) {
                throw new WrongConstant(args[0], args[1]);
            }
        }
    }
}
