public class ReverseArray {

    //TODO: Напишите код, который меняет порядок расположения элементов внутри массива на обратный.
    public static String[] reverse (String[] strings){
        int j = strings.length-1;
        for (int i = 0; i < strings.length/2; i++) {
                String s = strings[j];
                strings[j] = strings[i];
                strings[i] = s;
                j--;
        }

        return strings;
    }
}
