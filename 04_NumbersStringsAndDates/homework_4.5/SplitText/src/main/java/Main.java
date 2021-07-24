public class Main {

  public static void main(String[] args) {

  }

  public static String splitTextIntoWords(String text) {
    //TODO реализуйте метод
    String[] arr = text.split("[^a-zA-Z’]+");
    StringBuilder sb = new StringBuilder(arr[0]);
    for (int i = 1; i < arr.length; i++) {
      sb.append("\n").append(arr[i]);
    }
    String result = sb.toString();
    return result;
  }

}