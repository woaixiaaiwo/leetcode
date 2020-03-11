package codes.normal;

import java.util.ArrayList;
import java.util.LinkedList;


/**
 * 有效的括号字符串
 *
 * 给定一个只包含三种类型字符的字符串：'（'，'）'和 '*'， 编写一个函数来检查该字符串是否有效。 我们通过以下规则定义字符串的有效性：
 *
 * 1.任何左括号 '('必须有一个相应的右括号')'。
 * 2.任何右括号 ')' 必须有一个相应的左括号'('。
 * 3.左括号'(' 必须在相应的右括号 ')' 之前。
 * 4.*可以被视为单个右括号'）'或单个左括号'（'或空字符串。
 * 5.空字符串也有效。
 */
public class ValidParenthesisString {


    public boolean checkValidString(String s) {
        //先把字符串中，左右括号匹配的数据移除
        ArrayList<Character> list = new ArrayList();
        LinkedList<Integer> stack = new LinkedList<>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c == '(' || c == '*'){
                list.add(c);
                if(c == '('){
                    stack.push(list.size() - 1);
                }
                continue;
            }
            if(stack.size() > 0){
                int index = stack.pop();
                list.remove(index);
            }else {
                list.add(c);
            }
        }
        //经过上面的步骤，字符串中只剩不匹配的括号了，此时根据*好判决即可
        //比如剩下)**(*这种数据，直接使用栈进行匹配
        LinkedList<Character> cStack = new LinkedList<>();
        for(int i=0;i<list.size();i++){
            char c = list.get(i);
            if(c == '('){
                cStack.push(c);
                continue;
            }
            if(c == '*'){
                if(cStack.size() > 0 && cStack.peek() == '('){
                    cStack.pop();
                    continue;
                }
                cStack.push(c);
            }
            if(c == ')'){
                if(cStack.size() > 0 && cStack.peek() == '*'){
                    cStack.pop();
                    continue;
                }
                return false;
            }
        }
        while (cStack.size() > 0){
            Character c = cStack.pop();
            if(c == '(' || c == ')'){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new ValidParenthesisString().checkValidString("(*)))"));
    }

}
