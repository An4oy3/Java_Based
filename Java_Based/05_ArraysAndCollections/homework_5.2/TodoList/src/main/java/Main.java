import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static TodoList todoList = new TodoList();

    public static void main(String[] args) throws IOException {
        // TODO: написать консольное приложение для работы со списком дел todoList
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (true){
            s = reader.readLine();
            if(s.equals("exit")){
                break;
            }
            String toDo;
            s = s.trim();
            try {
                toDo = s.substring(0, s.indexOf(' '));
                s = s.substring(s.indexOf(' ')+1, s.length());
            }catch (Exception e){
                toDo = s;
            }

            switch (toDo) {
                case "ADD":
                    try {
                       int index = Integer.parseInt(s.substring(0, s.indexOf(' ')));
                       s = s.substring(s.indexOf(' '), s.length());
                       todoList.add(index, s);
                    } catch (Exception e){
                        todoList.add(s);
                    }
                    break;
                case "EDIT":
                    try {
                        int index = Integer.parseInt(s.substring(0, s.indexOf(' ')));
                        s = s.substring(s.indexOf(' '), s.length());
                        todoList.edit(s, index);
                    }catch (Exception e){
                        System.out.println("Дело с таким номером не существует");
                    }
                    break;
                case "DELETE":
                    try {
                        todoList.delete(Integer.parseInt(s));
                    }catch (Exception e){
                        System.out.println("Дело с таким номером не существует");
                    }
                    break;
                case "LIST":
                    for (int i = 0; i < todoList.getTodos().size(); i++) {
                        System.out.println(i + " - " + todoList.getTodos().get(i));
                    }
                    break;
                default:
                    System.out.println("Неизвестная команда");
            }
        }

    }
}
