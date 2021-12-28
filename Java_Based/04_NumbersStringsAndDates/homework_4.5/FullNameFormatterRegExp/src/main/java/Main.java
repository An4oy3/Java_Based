import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }
      String lastName = "";
      String firsetName = "";
      String middleName = "";

      input = input.trim();
      Pattern pattern = Pattern.compile("(^[а-яА-ЯЁё]{1,}[\\-]{0,1}[а-яА-ЯёЁ]{0,})+\\s+([а-яА-ЯЁё]{1,}[\\-]{0,1}[а-яА-ЯёЁ]{0,})+\\s+([а-яА-ЯЁё]{1,}[\\-]{0,1}[а-яА-ЯёЁ]{0,})+");
      Matcher matcher = pattern.matcher(input);
      boolean matches = matcher.matches();
      Pattern patternSplit = Pattern.compile("\\s*(\\s)\\s*");
      if(matches) {
        String[] fullName = patternSplit.split(input);
        for (int i = 0; i < fullName.length; i++) {
          System.out.println(fullName[i]);
        }
      } else
        System.out.println("Введенная строка не является ФИО");

    }
  }
}