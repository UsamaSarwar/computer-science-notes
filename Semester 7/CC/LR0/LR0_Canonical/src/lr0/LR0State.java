package lr0;

import utilities.Grammar;
import utilities.Rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;

public class LR0State {
    
    LinkedHashSet<LR0Item> items;
    HashMap<String, LR0State> transition;
    // this constructor is call in LR0Parser class 1st iteration
    public LR0State(Grammar grammar, HashSet<LR0Item> coreItems) {
        // 1st iteration LR0State core itemes [S' -> .E]
        System.out.println("LR0State core itemes "+coreItems);
        // 1st iteration LR0State itemes [S' -> .E]
        items = new LinkedHashSet<>(coreItems);
        System.out.println("LR0State itemes "+items);
        transition = new HashMap<>();
        closure(grammar);
    }
    
    private void closure(Grammar grammar) {
        boolean changeFlag = false;
        do {
            changeFlag = false;
            HashSet<LR0Item> temp = new HashSet<>();
            for (LR0Item item : items) {
                //1st iteration Closure method current terminal E
                System.out.println("Closure method current terminal "+item.getCurrentTerminal());
                // 1st iteration condition is true
                if (item.getCurrentTerminal()!=null && grammar.isVariable(item.getCurrentTerminal())) {
                    //1st iteration call getRuledByLeftVariable function 
                    HashSet<Rule> rules = grammar.getRuledByLeftVariable(item.getCurrentTerminal());
                    //Closure method current rules fetch from rules [E -> E + ( E ) , E -> a ]
                    System.out.println("Closure method current rules fetch from rules "+rules);
                    //1st iteration call createLR0Item constructor 
                    temp.addAll(createLR0Item(rules));
                }
            }
            if(!items.containsAll(temp)){
                items.addAll(temp);
                changeFlag = true;
            }
        } while (changeFlag);
    }
    
    private HashSet<LR0Item> createLR0Item(HashSet<Rule> rules) {
        // rules contain E -> E + ( E ) ,E -> a 
        HashSet<LR0Item> results = new HashSet<>();
        for (Rule rule : rules) {
            /*1st iteration rule contain E -> E + ( E )
            call LR0Item constructor it will add . at the start
            2nd iteration rule contain E -> a 
            call LR0Item constructor it will add . at the start
            */
            results.add(new LR0Item(rule));
        }
        //Closure 2nd and third rule add . operator [E -> .E + ( E ), E -> .a]
        System.out.println("Closure 2nd and third rule add . operator "+results);
        return results;
    }
    // add value in tansition hashmap
    public void addTransition(String s, LR0State state){
        transition.put(s, state);
                            // Print keys and values
                            /*
 transition key: a transition value: E -> a.
 transition key: E transition value: E -> E .+ ( E )
                                     S' -> E.
 transition key: + transition value: E -> E + .( E )
 transition key: ( transition value: E -> E + ( .E )
                                     E -> .E + ( E )
                                     E -> .a
 transition key: a transition value: E -> a.
 transition key: E transition value: E -> E .+ ( E )
                                     E -> E + ( E .)
 transition key: ) transition value: E -> E + ( E ).
 transition key: + transition value: E -> E + .( E )
                            
                            */
for (String i : transition.keySet()) {
  System.out.println(" transition key: " + i + " transition value: " + transition.get(i));
}
    }

    public HashSet<LR0Item> getItems() {
        return items;
    }

    public HashMap<String, LR0State> getTransition() {
        return transition;
    }
   
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.items);
        hash = 83 * hash + Objects.hashCode(this.transition);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LR0State other = (LR0State) obj;
        if (!(this.items.containsAll(other.items) && other.items.containsAll(this.items))) {
            return false;
        }
        if (!Objects.equals(this.transition, other.transition)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String s = "";
        for(LR0Item item:items){
            s += item + "\n";
        }
        return s;
    }
    
    
    
}
