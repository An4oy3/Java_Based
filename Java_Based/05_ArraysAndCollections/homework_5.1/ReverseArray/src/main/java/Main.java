public class Main {

    public static void main(String[] args) {
        String line = "Каждый aef awdawd охотник желает знать, где сидит фазан";
        String[] strings = line.split(",?\\s+");
        ReverseArray.reverse(strings);
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }
    }
}
