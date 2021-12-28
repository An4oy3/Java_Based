public class Main {

    public static void main(String[] args) {
        System.out.println(searchAndReplaceDiamonds("Номер кредитной карты <4008> 1234 <5678> 8912", "***"));
    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        // TODO: реализовать метод, если в строке нет <> - вернуть строку без изменений
        text = text.replaceAll("<.+?>", placeholder);
        return text;
    }

}