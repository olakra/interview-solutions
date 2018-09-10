/*
implement a function that determines if a given string is balanced

this (is [a {balanced}] string)
(this (is) (a) [balanced string])

(this is [not {a balanced}] string
this {is [not}] a (balanced string)

-----------
*/
import java.util.Stack;

public class BalanceString {
    
public static void main(String[] args) {
    String [] input = {
        "this (is [a {balanced}] string)",
        "(this (is) (a) [balanced string])",
        "(this is [not {a balanced}] string",
        "this {is [not}] a (balanced string)"
    };
    
    for (String text : input) {
        System.out.println("Evaluation = " + evaluate(text));
    }
}

public static boolean evaluate(final String text) {
    System.out.println(String.format("input -> %s", text));
    
    boolean result = true; 
    Stack stack = new Stack<String>();
    
    for (int idx = 0; idx < text.length(); idx++) {
        
        char character = text.charAt(idx);
        
        switch(character) {
            case '{':
            case '(':
            case '[':
                stack.push("" + character);
                // System.out.println(String.format("> Pushed: %s", character));
                break;
            case '}': 
                if (!stack.isEmpty() && stack.peek().equals("{")){
                    // System.out.println(String.format("> Stack.peek : %s", stack.peek()));
                    stack.pop();
                    result = true;
                } else {
                    result = false;
                }
                break;
            case ')': 
                if (!stack.isEmpty() && stack.peek().equals("(")){
                    // System.out.println(String.format("> Stack.peek : %s", stack.peek()));
                    stack.pop();
                    result = true;
                } else {
                    result = false;
                }
                break;
            case ']': 
                if (!stack.isEmpty() && stack.peek().equals("[")){
                    // System.out.println(String.format("> Stack.peek : %s", stack.peek()));
                    stack.pop();
                    result = true;
                } else {
                    result = false;
                }
                break;
            default:
        }
        
        // break out of the for loop if result is ever false
        if (!result) break;
    }
    
    if (!stack.isEmpty()) 
        result = false;
    
    return result;
}

}
