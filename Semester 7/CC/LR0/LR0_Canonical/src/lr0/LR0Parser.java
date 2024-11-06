package lr0;

import utilities.LRParser;
import utilities.ActionType;
import utilities.Action;
import utilities.Rule;
import utilities.Grammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class LR0Parser extends LRParser {
    
    private ArrayList<LR0State> canonicalCollection;
    //constructor is call in 1st iteration
    public LR0Parser(Grammar grammar) {
        //super keyword is call LRParser Constructor
        super(grammar);
        System.out.println("LR0Parser Constructor");
    }
    
 /*
    it is call in 1st iteration from main.java class
    */  
// it will return true or false
    public boolean parserLR0() {
        System.out.println("parserLR0 boolean method");
       createStates();
        createGoToTable();
        return createActionTableForLR0();
    }
    
    protected void createStates() {
        canonicalCollection = new ArrayList<>();
        HashSet<LR0Item> start = new HashSet<>();
        //1st iteration LR0 Rule S' -> E 
        System.out.println("LR0 Rule "+grammar.getRules().get(0));
        //1st iteration call lroitem class constructor and pass S' -> E 
        start.add(new LR0Item(grammar.getRules().get(0)));
        //1st iteration start contains S' -> .E 
        System.out.println("LR0 Start "+start);
        //1st iteration call LR0 Class constructor pass grammer and start
        LR0State startState = new LR0State(grammar, start);
        canonicalCollection.add(startState);
        /*
        cannonical collection
        [S' -> .E
        E -> .E + ( E )
        E -> .a]
        */
        for (int i = 0; i < canonicalCollection.size(); i++) {
            System.out.println("cannonical collection"+canonicalCollection);
            HashSet<String> stringWithDot = new HashSet<>();
            for (LR0Item item : canonicalCollection.get(i).getItems()) {
                /*
                1st iteration cannonical item S' -> .E
                2nd iteration cannonical item E -> .E + ( E )
                
                */
                System.out.println("cannonical item "+item);
                //it get the current terminal
                //1st iteration cannonical current terminal E
                //2nd iteration cannonical current terminal E
                //3rd iteration cannonical item E -> .a
                if (item.getCurrentTerminal() != null) {
                    System.out.println("cannonical current terminal "+item.getCurrentTerminal());
                    stringWithDot.add(item.getCurrentTerminal());
                    // 1st iteration string with dot..... [E]
                    //2nd iteration string with dot..... [E]
                    //3rd iteration string with dot..... [a, E]
                    System.out.println("string with dot..... "+stringWithDot);
                }
            }
            /*
            Stringwithdot contains [a, E]
            */
            System.out.println("Stringwithdot contains "+stringWithDot);
            for (String str : stringWithDot) {
                //1st iteration cannonical first string a
                System.out.println("cannonical first string "+str);
                HashSet<LR0Item> nextStateItems = new HashSet<>();
                for (LR0Item item : canonicalCollection.get(i).getItems()) {
                    for(int j=0;j<1;j++)
                    {
                        System.out.println("cannonical collection item ---- "+item);
                    }/*
                     1st iteration
                    item contains cannonical collection item  ---- S' -> .E
                    str contains a condition is false
                    item.getCurrentTerminal() contains E
                    */
                    /*
                     2nd iteration
                    item contains cannonical collection item  ---- E -> .E + ( E )
                    str contains a condition is false
                    item.getCurrentTerminal() contains E
                    */
                    /*
                     3rd iteration
                    item contains cannonical collection item  ---- E -> .a
                    str contains a condition is true
                    item.getCurrentTerminal() contains a
                    */
                    
                    
                    if (item.getCurrentTerminal() != null && item.getCurrentTerminal().equals(str)) {
                        //item contains cannonical collection item  ---- E -> .a
                        LR0Item temp = new LR0Item(item);
                        System.out.println("cannonical collection item temp before goto ---- "+temp);
                        //goto function is used to move dot on right side
                        temp.goTo();
                        System.out.println("cannonical collection item temp after goto ---- "+temp);
                        //nextStateItems contains [E -> a.]
                        nextStateItems.add(temp);
                        System.out.println("cannonical collection item temp before goto ---- "+nextStateItems);
                        //cannonical collection item temp before goto ---- [E -> a.]
                    }
                }
                LR0State nextState = new LR0State(grammar, nextStateItems);
                //cannonical collection next state E -> a.
                System.out.println("cannonical collection next state "+nextState);
                boolean isExist = false;
                for (int j = 0; j < canonicalCollection.size(); j++) {
                     System.out.println("cannonical collection get item "+j+"="+canonicalCollection.get(j).getItems()+"next state get items"+nextState.getItems());
                    if (canonicalCollection.get(j).getItems().containsAll(nextState.getItems())
                            && nextState.getItems().containsAll(canonicalCollection.get(j).getItems())) {
                        System.out.println("cannonical collection item ++++++++ ");
                        isExist = true;
                        System.out.println("cannonical collection string "+str+" cannonical collection "+canonicalCollection.get(j));
                        canonicalCollection.get(i).addTransition(str, canonicalCollection.get(j));
                    }
                }
                if (!isExist) {
                    canonicalCollection.add(nextState);
                    System.out.println("cannonical collection isExist condition true?????"+canonicalCollection);
                    System.out.println("cannonical collection string that is passed in transition="+str);
                    System.out.println("cannonical collection that is passed in transition="+nextState);
                    canonicalCollection.get(i).addTransition(str, nextState);

                }
            }
        }
        
    }
    
    @Override
    protected void createGoToTable() {
        System.out.println("createGoToTable ");
        // go to table hashmap define in LRParser class
        // create empty hashmap called gototable according to the size of canonicalcollection
        /*
        createGoToTable 
key: 0 value: {}
key: 1 value: {}
key: 2 value: {}
key: 3 value: {}
key: 4 value: {}
key: 5 value: {}
key: 6 value: {}
        */
        goToTable = new HashMap[canonicalCollection.size()];
        for (int i = 0; i < goToTable.length; i++) {
            goToTable[i] = new HashMap<>();
            System.out.println("key: " + i + " value: " + goToTable[i]);
        }
        /*
Canonical collection s  = 0  =a
Canonical collection s  = 0  =E
Canonical collection s  = 2  =+
Canonical collection s  = 3  =(
Canonical collection s  = 4  =a
Canonical collection s  = 4  =E
Canonical collection s  = 5  =)
Canonical collection s  = 5  =+
        */
        for (int i = 0; i < canonicalCollection.size(); i++) {
        for (String s :canonicalCollection.get(i).getTransition().keySet()) {
            System.out.println("Canonical collection s  = "+i+"  ="+s);
        }
        }
        for (int i = 0; i < canonicalCollection.size(); i++) {
            for (String s : canonicalCollection.get(i).getTransition().keySet()) {
                for(int j=0;j<1;j++)
                {
                    System.out.println("Canonical collection s contains = "+s);
                }
                //this is check if canonical collection contain non terminal then it will go to Gototable and add index number
                if (grammar.getVariables().contains(s)) {
                    System.out.println("grammer contains or not?  = "+grammar.getVariables().contains(s)+"inex is "+canonicalCollection.get(i).getTransition().get(s));
                    goToTable[i].put(s, findStateIndex(canonicalCollection.get(i).getTransition().get(s)));
                }
            }
        }
    }
/* this method is used to create action table if table is created successfully 
  it will return true or false    
 */   private boolean createActionTableForLR0() {
         // action table hashmap define in LRParser class
        // create empty hashmap called actiontable according to the size of canonicalcollection
        
        actionTable = new HashMap[canonicalCollection.size()];
        
        for (int i = 0; i < goToTable.length; i++) {
            actionTable[i] = new HashMap<>();
        }
        // this method is used to check action type is Shift or not
        for (int i = 0; i < canonicalCollection.size(); i++) {
            for (String s : canonicalCollection.get(i).getTransition().keySet()) {
                if (grammar.getTerminals().contains(s)) {
                    actionTable[i].put(s, new Action(ActionType.S, findStateIndex(canonicalCollection.get(i).getTransition().get(s))));
                  
                }
            }
        }
          // this method is used to check action type is accept or not
        for (int i = 0; i < canonicalCollection.size(); i++) {
            for (LR0Item item : canonicalCollection.get(i).getItems()) {
                if (item.getDotPointer() == item.getRightSide().length) {
                    if (item.getLeftSide().equals("S'")) {
                        actionTable[i].put("$", new Action(ActionType.ACCEPT, 0));
                    } else {
                          // this method is used to check action type is reduce or not
                        HashSet<String> terminals = grammar.getTerminals();
                        terminals.add("$");
                        Rule rule = new Rule(item.getLeftSide(), item.getRightSide().clone());
                        int index = grammar.findRuleIndex(rule);
                        Action action = new Action(ActionType.R, index);
                        for (String str : terminals) {
                            if (actionTable[i].get(str) != null) {
                                System.out.println("it has a REDUCE-" + actionTable[i].get(str).getType() + " confilct in state " + i);
                                return false;
                            } else {
                                actionTable[i].put(str, action);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    // this method is used to find index for goto table
    private int findStateIndex(LR0State state) {
        for (int i = 0; i < canonicalCollection.size(); i++) {
            if (canonicalCollection.get(i).equals(state)) {
                return i;
            }
        }
        return -1;
    }
// this method is for canonical collection
    public String canonicalCollectionStr() {
        String str = "Canonical Collection : \n";
        for (int i = 0; i < canonicalCollection.size(); i++) {
            str += "State " + i + " : \n";
            str += canonicalCollection.get(i)+"\n";
        }
        return str;
    }
    
}
