import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }

      String lastName = "";
      String firstName = "";
      String middleName = "";

      input = input.trim();
      if (isValid(input)) {
      lastName = input.substring(0, input.indexOf(' '));
      input = input.substring(input.indexOf(' ') + 1, input.length());
      input = input.trim();
      firstName = input.substring(0, input.indexOf(' '));
      input = input.substring(input.indexOf(' ') + 1, input.length());
      input = input.trim();
      middleName = input.substring(input.indexOf(' ') + 1, input.length());

        System.out.println("Фамилия: " + lastName + "\n" +
                "Имя: " + firstName + "\n" +
                "Отчество: " + middleName);
      } else {
        System.out.println("Введенная строка не является ФИО");
      }

      //TODO:напишите ваш код тут, результат вывести в консоль.
      //При невалидном ФИО вывести в консоль: Введенная строка не является ФИО
    }
  }

  //а, б, в, г, д, е, ё, ж, з, и, й, к, л, м, н, о, п, р, с, т, у, ф, х, ц, ч, ш, щ, ъ, ы, ь, э, ю, я.
  public static boolean isValid(String s) {
    if (s.length() < 5) {
      return false;
    }
    int spaceCount = 0;
    int hyphenCount = 0;
    int charsCount = 0;

    for (int i = 0; i < s.length(); i++) {
      char c = Character.toLowerCase(s.charAt(i));
      if (c == 'а' || c == 'б' || c == 'в' || c == 'г' || c == 'д' || c == 'е' || c == 'ё' || c == 'ж' || c == 'з' || c == 'и' || c == 'й' || c == 'к' || c == 'л' || c == 'м' || c == 'н' || c == 'о' || c == 'п' || c == 'р' || c == 'с' || c == 'т' || c == 'у' || c == 'ф' || c == 'х' || c == 'ц' || c == 'ч' || c == 'ш' || c == 'щ' || c == 'ъ' || c == 'ы' || c == 'ь' || c == 'э' || c == 'ю' || c == 'я') {
        charsCount++;
      } else if (c == '-' && i > 0 && i < s.length()-1 && s.charAt(i-1) != ' ' && s.charAt(i+1) != ' ') {
        hyphenCount++;
      } else if(c == ' ' && s.charAt(i+1) == ' ' ){
        continue;
      } else if(c == ' ' && s.charAt(i + 1) != ' ') {
        spaceCount++;
      } else
        return false;
    }
    return spaceCount == 2 && hyphenCount <= 3 && charsCount >= 3;

  }
}