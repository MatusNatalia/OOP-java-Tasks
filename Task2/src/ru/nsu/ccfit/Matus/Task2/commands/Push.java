package ru.nsu.ccfit.Matus.Task2.commands;

import ru.nsu.ccfit.Matus.Task2.calculator.Context;
import ru.nsu.ccfit.Matus.Task2.exceptions.ConstantNotFound;
import ru.nsu.ccfit.Matus.Task2.exceptions.WrongArguments;

public class Push implements Command {
    @Override
    public void execute(String[] args, Context context) throws WrongArguments, ConstantNotFound {
        if (args.length != 1) {
            throw new WrongArguments();
        }

        if (context.contains(args[0])) {
            context.push(context.get(args[0]));
        } else {
            try {
                var d = Double.parseDouble(args[0]);
                context.push(Double.parseDouble(args[0]));
            } catch (NumberFormatException e) {
                throw new ConstantNotFound(args[0]);
            }
        }
    }
}
