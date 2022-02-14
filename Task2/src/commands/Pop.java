package commands;

import exceptions.StackException;
import exceptions.WrongArguments;

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
