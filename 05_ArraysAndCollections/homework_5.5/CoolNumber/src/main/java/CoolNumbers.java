import java.util.*;
import java.util.regex.Matcher;

public class CoolNumbers {

    public static List<String> generateCoolNumbers() {
        List<String> coolNumbers = new ArrayList<>();
        char[] letters = new char[] {'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'};

        for(int i = 0; i < 2000001; i++) {
            char firstChar = letters[(int) (Math.random() * 12)];
            char secondChar = letters[(int) (Math.random() * 12)];
            char threeChar = letters[(int) (Math.random() * 12)];

            int num = (int) (Math.random() * 8) + 1;
            int region = (int) (Math.random() * 199) + 1;
            String reg = "";
            if(region > 10) {
                reg += region;
            } else {
                reg += "0" + region;
            }
            StringBuilder result = new StringBuilder();
            result.append(firstChar).append(num).append(num).append(num).append(secondChar).append(threeChar).append(reg);
            if(binarySearchInList(coolNumbers, result.toString()))
                i--;
            else
                coolNumbers.add(result.toString());
        }
        return coolNumbers;
    }

    public static boolean bruteForceSearchInList(List<String> list, String number) {
        boolean b = false;
        for (String s : list) {
            if(s.equals(number))
               b = true;
        }
        return b;
    }

    public static boolean binarySearchInList(List<String> sortedList, String number) {
        return Collections.binarySearch(sortedList,number) >= 0;
    }


    public static boolean searchInHashSet(HashSet<String> hashSet, String number) {
        return hashSet.contains(number);
    }

    public static boolean searchInTreeSet(TreeSet<String> treeSet, String number) {
        return treeSet.contains(number);
    }

}
