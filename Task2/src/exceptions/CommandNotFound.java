package exceptions;

public class CommandNotFound extends CommandException{
    public CommandNotFound(String name){
        super("command '" + name + "' not found");
    }
}
