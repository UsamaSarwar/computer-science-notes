package utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public abstract class LRParser {
    // interger is use because we write state number in goto table
    protected HashMap<String, Integer>[] goToTable;
    // interger is use because we write S,reduce oraccepted in action table
    protected HashMap<String, Action>[] actionTable;
    
    //GRAMMER CLASS OBJECT
    protected Grammar grammar;
    //constructor is call from LR0Parser class in 1st iteration
    /*
    Grammer Rules[S' -> E , E -> E + ( E ) , E -> a ]
    Grammer Terminals[a, (, ), +]
    Grammer Non-Terminals/variables[E]
    */
    public LRParser(Grammar grammar) {
        System.out.println("LRParser Constructor");
        this.grammar = grammar;
        System.out.println("Grammer Rules"+grammar.getRules());
        System.out.println("Grammer Terminals"+grammar.getTerminals());
        System.out.println("Grammer Non-Terminals/variables"+grammar.getVariables());
    }
/*
    grammer for LR0
E -> E + ( E )
E -> a
    Grammer for SLR1
    E -> T + E
E -> T
T -> i
    //
S -> ( L ) | x
L -> L , S | S

    */
    // this method is checked whether the string is accepted or not
    public boolean accept(ArrayList<String> inputs) {
        inputs.add("$");
        int index = 0;
        Stack<String> stack = new Stack<>();
        stack.add("0");
        while (index < inputs.size()) {
            int state = Integer.valueOf(stack.peek());
            String nextInput = inputs.get(index);
            System.out.print(nextInput);
            Action action = actionTable[state].get(nextInput);
            
            if (action == null) {
                return false;
            } else if (action.getType() == ActionType.S) {
                stack.push(nextInput);
                stack.push(action.getOperand() + "");
                index++;
            } else if (action.getType() == ActionType.R) {
                int ruleIndex = action.getOperand();
                Rule rule = grammar.getRules().get(ruleIndex);
                String leftSide = rule.getLeftSide();
                int rightSideLength = rule.getRightSide().length;
                for (int i = 0; i < 2 * rightSideLength; i++) {
                    stack.pop();
                }
                int nextState = Integer.valueOf(stack.peek());
                stack.push(leftSide);
                int variableState = goToTable[nextState].get(leftSide);
                stack.push(variableState + "");
            } else if (action.getType() == ActionType.ACCEPT) {
                return true;
            }
        }
        
        return false;
    }
    //this method is define in LR0Parser Class
    protected abstract void createGoToTable();
    
    public String goToTableStr() {
        String str = "Go TO Table : \n";
        str += "          ";
        for (String variable : grammar.getVariables()) {
            str += String.format("%-6s", variable);
        }
        str += "\n";
        
        for (int i = 0; i < goToTable.length; i++) {
            for (int j = 0; j < (grammar.getVariables().size() + 1) * 6 + 2; j++) {
                str += "-";
            }
            str += "\n";
            str += String.format("|%-6s|", i);
            for (String variable : grammar.getVariables()) {
                str += String.format("%6s", (goToTable[i].get(variable) == null ? "|" : goToTable[i].get(variable) + "|"));
            }
            str += "\n";
        }
        for (int j = 0; j < (grammar.getVariables().size() + 1) * 6 + 2; j++) {
            str += "-";
        }
        return str;
    }
    
    public String actionTableStr() {
        String str = "Action Table : \n";
        HashSet<String> terminals = new HashSet<>(grammar.getTerminals());
        terminals.add("$");
        str += "                ";
        for (String terminal : terminals) {
            str += String.format("%-10s", terminal);
        }
        str += "\n";
        
        for (int i = 0; i < actionTable.length; i++) {
            for (int j = 0; j < (terminals.size() + 1) * 10 + 2; j++) {
                str += "-";
            }
            str += "\n";
            str += String.format("|%-10s|", i);
            for (String terminal : terminals) {
                str += String.format("%10s", (actionTable[i].get(terminal) == null ? "|" : actionTable[i].get(terminal) + "|"));
            }
            str += "\n";
        }
        for (int j = 0; j < (terminals.size() + 1) * 10 + 2; j++) {
            str += "-";
        }
        return str;
    }
    
    public Grammar getGrammar() {
        return grammar;
    }
}
