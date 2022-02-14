package exceptions;

public class ConstantNotFound extends CommandException{
    public ConstantNotFound(String name){
        super("constant '" + name + "' not found");
    }
}
