import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Parser {

    private ArrayList<Component> dummy_for_sync ;

    private ArrayList<Component> dummy_for_epsilon ;

    private HashMap<String,Terminal> terminal_map ;

    private HashMap<String,NonTerminal> non_terminal_map ;

    private  ArrayList<Component>[][] parsing_table ;

    private HashMap< NonTerminal , Integer > rows_map ;

    private HashMap<Terminal,Integer> columns_map ;

    private NonTerminal Start;

    public Parser() {
        dummy_for_epsilon = new ArrayList<>();
        dummy_for_sync = new ArrayList<>();
        terminal_map = new HashMap<>();
        non_terminal_map = new HashMap<>();
    }

    public static void main(String[] args) throws Exception {
        new Parser().start_parsing();
    }

    public void start_parsing (){
    }

    private void computeFirstSet ( NonTerminal t ){

    }

    private void computeFollowSet ( NonTerminal t ){

    }

    private void computeParseTable (){


    }
    private int ind =0;
    private String st="ceadb$";
//7ms7o b3d m5ls sho8l mkano method marwan mn elexical project
    private String getnexttoken()
    {
        return st.substring(ind++,ind);
    }
    private void process_input ()throws Exception{
        /*
        * 1- push dollar_sign into the stack
        * 2-push start of non terminal
        * 3-while(stack not empty)
        *   -get nexttoken
        *   -create component topOfstack=stack.top();
        *   -check if topOfstack isTerminal
        *     *check whether topOfstack.name==nexttoken then stack.pop();
        *     *else Report Error and in next loop DON'T TAKE NEXT TOKEN
        *   - if topofstack not terminal
        *     *get row index of topofstack
        *     *create terminal=terminalmap.get(nexttoken);
        *     *get column index of nexttoken
        *     *get the component tostack to place into the stack from parsing table
        *        check if :
        *          --if tostack =dummy_sync
        *            >Report Error
        *            >POP stack
        *            >DON'T TAKE THE NEXTTOKEN
        *          --tostack= null
        *            >Report ERROR
        *            >get next token
        *          -- else pop the stack AND take the productions of the component aqnd push from last to first
        * */
        LexicalAnalyzerGenerator Lexicalgenerator=new LexicalAnalyzerGenerator();
        BufferedWriter bw = new BufferedWriter(new FileWriter("output2.txt"));
        boolean takenext=true;
        Stack<Component>stack=new Stack<>();
        stack.add(terminal_map.get("$"));
        stack.add(Start);
        String nexttoken="";
        while (!stack.isEmpty()){
            if (takenext)nexttoken=Lexicalgenerator.getNextToken();
            Component topOfstack=stack.peek();
            if (topOfstack.getClass()==Terminal.class){
                if (topOfstack.name.equals(nexttoken)){
                    stack.pop();
                    takenext=true;
                }
                else {
                    System.out.println("ERROR! Not Matched");
                    takenext=false;
                    stack.pop();
                }
            }
            else {
                Integer row=rows_map.get(topOfstack);
                Terminal terminal=terminal_map.get(nexttoken);
                Integer column=columns_map.get(terminal);
                ArrayList<Component> tostackList=parsing_table[row][column];
                if (tostackList==null){
                    System.out.println("ERROR!");
                    takenext=true;
                }
                else if (tostackList==dummy_for_sync){
                    System.out.println("ERROR!");
                    stack.pop();
                    takenext=false;
                }
                else
                {    takenext=false;
                    stack.pop();
                    StringBuilder st=new StringBuilder();
                    if (tostackList==dummy_for_epsilon){
                        bw.write(topOfstack.name+" -> "+"epsilon");
                        continue;
                    }
                    for (int i=tostackList.size()-1;i>=0;i--){
                        Component component=tostackList.get(i);
                        stack.push(component);
                        st.append(component.name+" ");
                    }
                    bw.write(topOfstack.name+" ->"+st.reverse().toString());
                    bw.newLine();
                }
            }
        }
        bw.close();
    }

    private void error_handler (){


    }

}
