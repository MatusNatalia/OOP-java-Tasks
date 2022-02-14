package exceptions;

public class WrongArguments extends CommandException{
    public WrongArguments(){
        super("wrong number of arguments");
    }
}
