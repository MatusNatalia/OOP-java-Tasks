package commands;

import exceptions.StackException;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;

public class Context {
    private Stack<Double> stack;
    private HashMap<String, Double> constants;

    public Context(){
        stack = new Stack<Double>();
        constants = new HashMap<>();
    }

    public void push(Double d){
        stack.push(d);
    }

    public void pop() throws StackException {
        try {
            stack.pop();
        }
        catch(EmptyStackException e){
            throw new StackException();
        }
    }

    public Double peek() throws StackException {
        Double d;
        try {
            d = stack.peek();
        }
        catch(EmptyStackException e){
            throw new StackException();
        }
        return d;
    }

    public void define(String s, Double d){
        constants.put(s, d);
    }

    public boolean contains(String s){
        return constants.containsKey(s);
    }

    public Double get(String s){
        return constants.get(s);
    }

}
