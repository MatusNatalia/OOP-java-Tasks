package commands;
import exceptions.StackException;
import exceptions.WrongArguments;

import java.lang.Math;

public class Sqrt implements Command{
    @Override
    public void execute(String[] args, Context context) throws StackException, WrongArguments {
        if (args.length != 0) {
            throw new WrongArguments();
        } else {
            Double a = context.peek();
            context.pop();
            context.push(Math.sqrt(a));
        }
    }
}
