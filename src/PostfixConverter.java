import java.util.*;

public class PostfixConverter {
    private String infix;
    private Deque<Character> stack = new ArrayDeque<Character>();
    private List<String> postfix = new ArrayList<String>();

    public PostfixConverter(String formula){
        infix = formula;
        convertExpression();
    }
    private void convertExpression(){
        // 숫자 저장
        StringBuilder temp = new StringBuilder();
        for(int i = 0; i != infix.length(); ++i){
            if(Character.isDigit(infix.charAt(i))){
                // 숫자면 그 옆에있는 숫자를 모두 읽고 temp에 저장
                temp.append(infix.charAt(i));
                while((i+1) != infix.length()
                        && (Character.isDigit(infix.charAt(i+1))
                        || infix.charAt(i+1) == '.')){
                    temp.append(infix.charAt(++i));
                }
                // 루프 종료, 후위표현식에 저장하고 temp 삭제
                postfix.add(temp.toString());
                temp.delete(0, temp.length());
                // 숫자가 아니면 inputToStack에 저장
            }else inputToStack(infix.charAt(i));
        }
        clearStack();
    }
    private void inputToStack(char input){
        if(stack.isEmpty() || input == '(')
            stack.addLast(input);
        else {
            if(input == ')') {
                while(!stack.getLast().equals('('))
                {
                    postfix.add(stack.removeLast().toString());
                }
                stack.removeLast();
            }
            else {
                if(stack.getLast().equals('(')) {
                } else {
                    while(!stack.isEmpty() && !stack.getLast().equals('(') &&
                            getPrecedence(input) <= getPrecedence(stack.getLast())) {
                        postfix.add(stack.removeLast().toString());
                    }
                }
                stack.addLast(input);
            }
        }
    }
    private int getPrecedence(char op){
        if(op == '+' || op == '-') return 1;
        else if(op == '*' || op == '/') return 2;
        else if(op == '^') return 3;
        else return 0;
    }
    private void clearStack(){
        while(!stack.isEmpty()){
            postfix.add(stack.removeLast().toString());
        }
    }
    public void printExpression(){
        for(String str : postfix){
            System.out.print(str + ' ');
        }
    }
    public List<String> getPostfixAsList(){
        return postfix;
    }
}
