import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailList {
    private HashSet<String> emails = new HashSet<>();

    public void add(String email) {
        // TODO: валидный формат email добавляется
        email = email.toLowerCase();
        if(isVaild(email) && !emails.contains(email)){
            emails.add(email);
            System.out.println("Email успешно добавлен");
        } else if(emails.contains(email)){
            System.out.println("Такой email уже зарегестрирован");
        } else {
            System.out.println(Main.WRONG_EMAIL_ANSWER);
        }
    }

    public List<String> getSortedEmails() {
        // TODO: возвращается список электронных адресов в алфавитном порядке
        TreeSet<String> treeSet = new TreeSet<>(emails);
        return new ArrayList<>(treeSet);
    }

    private static boolean isVaild(String email){
        Pattern pattern = Pattern.compile("(^[\\w\\-]+)\\@(\\w+)\\.(\\w+)");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
