import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        System.out.println(searchAndReplaceDiamonds("Номер кредитной карты <4008 1234 5678> 8912>", "***"));
    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        // TODO: реализовать метод, если в строке нет <> - вернуть строку без изменений
        if (text.length() <= 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        Stack<Character> stack = new Stack<>();
        State state = State.OPEN;

        for (int i = 0; i < text.length(); i++) {
            if (state == State.OPEN) {
                if (text.charAt(i) != '<' && text.charAt(i) != '>') {
                    result.append(text.charAt(i));
                }
                if (text.charAt(i) == '<') {
                    state = State.SECRET;
                    stack.push(text.charAt(i));
                    continue;
                }
                if (text.charAt(i) == '>') {
                    state = State.ERROR;
                }

            }
            if (state == State.SECRET) {
                if (text.charAt(i) == '<') {
                    stack.push(text.charAt(i));
                }
                if (text.charAt(i) == '>') {
                    if (stack.empty()) {
                        state = State.ERROR;
                    } else {
                        stack.pop();
                        if (stack.empty()) {
                            state = State.OPEN;
                            result.append(placeholder);
                        }
                    }
                }

            }
            if (state == State.ERROR) {
                break;
            }
            if (state == State.FINISHED) {
                return result.toString();
            }
        }
        if(state == State.ERROR){
            return "Error";
        }
        else
            return result.toString();
    }
}