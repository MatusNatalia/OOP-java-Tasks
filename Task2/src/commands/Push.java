package commands;

import exceptions.ConstantNotFound;
import exceptions.WrongArguments;

public class Push implements Command {
    @Override
    public void execute(String[] args, Context context) throws WrongArguments, ConstantNotFound {
        if (args.length != 1) {
            throw new WrongArguments();
        } else {
            if (context.contains(args[0])) {
                context.push(context.get(args[0]));
            } else {
                Double d = null;
                try {
                    d = Double.parseDouble(args[0]);
                }catch (NumberFormatException e) {
                    throw new ConstantNotFound(args[0]);
                }
                finally {
                    if(d != null) {
                        context.push(Double.parseDouble(args[0]));
                    }
                }
            }
        }
    }
}
