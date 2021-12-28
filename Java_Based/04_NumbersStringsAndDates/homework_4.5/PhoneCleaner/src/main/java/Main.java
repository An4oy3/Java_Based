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
      //TODO:напишите ваш код тут, результат вывести в консоль.
      input = input.replaceAll("[^0-9]", "");
      if(!isValidNumber(input))
        System.out.println("Неверный формат номера");
      else {
        if(input.length() == 11 && input.charAt(0) == '7') {
          System.out.println(input);
        }else if(input.length() == 11 && input.charAt(0) == '8'){
          input = input.replaceFirst("8", "7");
          System.out.println(input);
        }else if(input.length() == 10 && input.charAt(0) == '9'){
          System.out.println(7 + input);
        } else {
          System.out.println("Неверный формат номера");
        }
      }

    }
  }
  public static boolean isValidNumber(String number){
    Pattern pattern;
    if(number.length() < 10  || number.length() > 11){
      return false;
    }

    pattern = Pattern.compile("^[7,8,9]");
    Matcher matcher = pattern.matcher(number);
    return matcher.find();
  }

}
