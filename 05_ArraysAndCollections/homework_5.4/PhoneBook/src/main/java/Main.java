import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PhoneBook phoneBook = new PhoneBook();
        while (true){
            System.out.println("Введите номер, имя или команду");
            String input = reader.readLine();
            if(input.equals("exit")){
                break;
            }
            if(input.equals("list")){
                for(String s : phoneBook.getAllContacts()) System.out.println(s);
            }
            else if(PhoneBook.isValidName(input)){ //Если введенная строка - имя,
                Set<String> contact = phoneBook.getPhonesByName(input); //Запрашиваем это имя в справочнике
                if(contact.isEmpty()){ //Если такого имени нет, пользователь вводит номер телефона
                    System.out.println("Такого имени в телефонной книге нет.");
                        while (true){
                            System.out.println("Введите номер телефона для абонента \"" + input + "\"");
                            String s = reader.readLine();
                            if(PhoneBook.isValidPhone(s)) { //Если введенная строка - номер, то добавляем контакт в справочник.
                               phoneBook.addContact(s, input); //Проверка на наличие такого номера в контактах проводится внутри метода addContact
                                System.out.println("Контакт сохранен!");
                               break;
                            } else {
                                System.out.println("Номер телефона некорректный");
                            }
                        }
                } else { //Если такое имя в справочнике есть, то выводим данные контакта
                    for(String s : contact){
                        System.out.println(s);
                    }
                }
            } else if(PhoneBook.isValidPhone(input)){ //Если введенная строка - телефон,
                String contact = phoneBook.getNameByPhone(input); //Запрашиваем этот телефон в справочнике
                if(contact.isEmpty()){ //Если такого номера нет, пользователь вводит имя
                    System.out.println("Такого номера в справочнике нет.");
                        while (true){
                            System.out.println("Введите имя абонента");
                            String s = reader.readLine();
                            if(PhoneBook.isValidName(s)){ //Если введенная строка - имя,
                                if(phoneBook.getMap().containsKey(s)){//Проверяем есть ли в справочнике такое имя
                                     phoneBook.addPhoneNumber(s, input);  //Если такое имя есть, то добавляем номер в этот контакт
                                     System.out.println("Контакт сохранен!");
                                     break;
                                } else {
                                    phoneBook.addContact(input, s); //Если такого имени нет, то добавляем новый контакт
                                    System.out.println("Контакт сохранен!");
                                    break;
                                }
                            } else {
                                System.out.println("Введенное имя некорректно");
                            }
                        }
                } else {
                    System.out.println(contact + " ");
                }
            } else {
                System.out.println("Неверный формат ввода");
            }
        }
    }

}

