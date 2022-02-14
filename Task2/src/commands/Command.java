package commands;

import exceptions.ConstantNotFound;
import exceptions.StackException;
import exceptions.WrongArguments;
import exceptions.WrongConstant;

public interface Command {
    public void execute(String[] args, Context context) throws WrongConstant, WrongArguments, StackException, ConstantNotFound;
}
