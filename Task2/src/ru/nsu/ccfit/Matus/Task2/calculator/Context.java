package ru.nsu.ccfit.matus.task2.calculator;

import ru.nsu.ccfit.matus.task2.exceptions.StackException;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Context {
    private Stack<Double> stack;
    private Map<String, Double> constants;

    public Context(){
        stack = new Stack<>();
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
        constants.put(s.trim(), d);
    }

    public boolean contains(String s){
        return constants.containsKey(s.trim());
    }

    public Double get(String s){
        return constants.get(s.trim());
    }

}
