public class Main {

  public static void main(String[] args) {
    calculateSalarySum("Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей");
  }

  public static int calculateSalarySum(String text){
    text = text.replaceAll("[^0-9\\s]", "");
    int sum = 0;
    String[] arr = text.split("[^0-9]+");
    for (int i = 0; i < arr.length; i++) {
      if(!arr[i].isEmpty())
        sum += Integer.parseInt(arr[i]);
    }

    //TODO: реализуйте метод
    System.out.println(sum);
    return sum;
  }

}