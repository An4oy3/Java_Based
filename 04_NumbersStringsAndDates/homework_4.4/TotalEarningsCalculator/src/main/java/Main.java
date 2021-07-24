public class Main {

  public static void main(String[] args) {

    String text = "Вася заработал 5000/ рублей, Петя - 7563s рубля, а Маша - 30000a ";

    int sum = 0;


    for (int i = 0; i < text.length(); i++) {
        if(text.charAt(i) == '0' || text.charAt(i) == '1' || text.charAt(i) == '2' || text.charAt(i) == '3' || text.charAt(i) == '4' || text.charAt(i) == '5' || text.charAt(i) == '6' || text.charAt(i) == '7' || text.charAt(i) == '8' || text.charAt(i) == '9'){
        text = text.substring(i);
        int delimiter = 0;
        for (int j = 0; j < text.length(); j++) {
          if(text.charAt(j) == '0' || text.charAt(j) == '1' || text.charAt(j) == '2' || text.charAt(j) == '3' || text.charAt(j) == '4' || text.charAt(j) == '5' || text.charAt(j) == '6' || text.charAt(j) == '7' || text.charAt(j) == '8' || text.charAt(j) == '9') {
          } else {
            delimiter = j;
            break;
          }
        }
        String s = text.substring(0, delimiter);
        sum += Integer.parseInt(s);
        i = delimiter;
      }
    }
    System.out.println(sum);

    //TODO: напишите ваш код, результат вывести в консоль
  }

}