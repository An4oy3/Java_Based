import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {
    private Map<String, Set<String>> map = new HashMap<>();


    public Map<String, Set<String>> getMap() {
        return map;
    }

    public void addContact(String phone, String name) {
        // проверьте корректность формата имени и телефона
        // если такой номер уже есть в списке, то перезаписать имя абонента
        if(isValidName(name) && isValidPhone(phone)){
            boolean newContact = true;
            Set<String> values = new TreeSet<>();
           for(Map.Entry<String, Set<String>> entry : map.entrySet()){
               if(entry.getValue().contains(phone)){
                   values = entry.getValue();
                   map.remove(entry.getKey());
                   map.put(name, values);
                   newContact = false;
                   break;
               }
           }
           if(newContact)
               values.add(phone);
               map.put(name, values);
        }
    }

    public String getNameByPhone(String phone) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
        StringBuilder contact = new StringBuilder("");
        boolean isFound = false;
        for(Map.Entry<String, Set<String>> entry : map.entrySet()){
            for (String value : entry.getValue()){
                if(value.equals(phone)){
                    contact.append(entry.getKey());
                    isFound = true;
                    break;
                }
            }
        }
        if(isFound){
            Set<String> phones = map.get(contact.toString());
            int size = phones.size();
            for(String s : phones)
                if(size == phones.size()) {
                    contact.append(" - ").append(s);
                    size--;
                } else if(size >= 1){
                    contact.append(", ").append(s);
                    size--;
                } else {
                    contact.append(s);
                }
        }

        return contact.toString();
    }

    public Set<String> getPhonesByName(String name) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        Set<String> result = new TreeSet<>();
        StringBuilder contact;
        if(map.containsKey(name)){
            contact =  new StringBuilder(name + " - ");
            Set<String> value = map.get(name);
            int size = value.size();
                for (String v : value) {
                    if(size > 1) {
                        contact.append(v).append(", ");
                        size--;
                    } else
                        contact.append(v);
                }

            result.add(contact.toString());
        }

        return result;
    }

    public Set<String> getAllContacts() {
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet
        StringBuilder contact;
        Set<String> result = new TreeSet<>();
        for (Map.Entry<String, Set<String>> entry : map.entrySet()){
            int sizeValue = entry.getValue().size();
            contact = new StringBuilder(entry.getKey() + " - ");
            for(String phone : entry.getValue()){
                if(sizeValue > 1) {
                    contact.append(phone).append(", ");
                    sizeValue--;
                }
                else
                    contact.append(phone);
            }
            result.add(contact.toString());
        }

        return result;
    }

    public void addPhoneNumber(String name, String phone){
        Set<String> contact = map.get(name);
        contact.add(phone);
        map.put(name,contact);
    }


    public static boolean isValidName(String name){
        Pattern namePattern = Pattern.compile("^[а-яА-Я]+");
        Matcher matcherName = namePattern.matcher(name);
        return matcherName.matches();
    }

    public static boolean isValidPhone(String phone){
        Pattern phonePattern = Pattern.compile("^[0-9]{11}");
        Matcher matcherPhone = phonePattern.matcher(phone);
        return matcherPhone.matches();
    }
}