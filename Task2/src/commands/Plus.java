package commands;

import exceptions.StackException;
import exceptions.WrongArguments;

public class Plus implements Command{
    @Override
    public void execute(String[] args, Context context) throws StackException, WrongArguments {
        if (args.length != 0) {
            throw new WrongArguments();
        } else {
            Double a = context.peek();
            context.pop();
            Double b = context.peek();
            context.pop();
            context.push(a + b);
        }
    }
}
