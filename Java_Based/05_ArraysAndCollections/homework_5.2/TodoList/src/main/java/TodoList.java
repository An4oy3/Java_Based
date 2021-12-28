import java.util.ArrayList;

public class TodoList {
    ArrayList<String> list = new ArrayList<>();

    public void add(String todo) {
        // TODO: добавьте переданное дело в конец списка
        list.add(todo);
        System.out.println("Добавлено дело \"" + list.get(list.size()-1).trim() + "\"");
    }

    public void add(int index, String todo) {
        // TODO: добавьте дело на указаный индекс,
        //  проверьте возможность добавления
        if(index >= 0 && index < list.size()){
            list.add(index, todo);
            System.out.println("Добавлено дело \"" + list.get(index).trim() + "\" на индекс " + index);
        } else {
            list.add(todo);
            System.out.println("Дело с таким номером не существует. Дело добавлено в конец списка");
        }
    }

    public void edit(String todo, int index) {
        // TODO: заменить дело на index переданным todo индекс,
        //  проверьте возможность изменения
        if(index >= 0 && index < list.size()){
            String s = list.get(index);
            list.set(index, todo);
            System.out.println("Дело \"" + s.trim() + "\"" + " " +
                    "заменено на " + "\"" + list.get(index).trim() + "\"");
        } else {
            System.out.println("Дело с таким номером не существует");
        }
    }

    public void delete(int index) {
        // TODO: удалить дело находящееся по переданному индексу,
        //  проверьте возможность удаления дела
        if(index >= 0 && index < list.size()){
            System.out.println("Дело \""+ list.get(index).trim() + "\" удалено");
            list.remove(index);
        } else {
            System.out.println("Дело с таким номером не существует");
        }
    }

    public ArrayList<String> getTodos() {
        // TODO: вернуть список дел
        return list;
    }

}