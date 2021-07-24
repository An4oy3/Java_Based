import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    /*
    TODO:
     - реализовать методы класса CoolNumbers
     - посчитать время поиска введимого номера в консоль в каждой из структуры данных
     - проанализоровать полученные данные
     */

    public static void main(String[] args) throws IOException {
        List<String> list = CoolNumbers.generateCoolNumbers();
        HashSet<String> hashSet = new HashSet<>(list);
        TreeSet<String> treeSet = new TreeSet<>(list);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            String input = reader.readLine();
            if(input.equals("exit")){
                break;
            }

            Collections.sort(list);
            long start = System.nanoTime();
            boolean searchInList = CoolNumbers.binarySearchInList(list, input);
            long finish = System.nanoTime();
            if(searchInList)
                System.out.println("Бинарный поиск: номер найден, время поиска составило " + (finish - start) + "нс");
            else
                System.out.println("Бинарный поиск: номер не найден, время поиска составило " + (finish - start) + "нс");

            long start2 = System.nanoTime();
            boolean bruteForce = CoolNumbers.bruteForceSearchInList(list, input);
            long finish2 = System.nanoTime();
            if(bruteForce)
                System.out.println("Поиск перебором: номер найден, время поиска составило " + (finish2 - start2) + "нс");
            else
                System.out.println("Поиск перебором: номер не найден, время поиска составило " + (finish2 - start2) + "нс");

            long start3 = System.nanoTime();
            boolean hashSetSearch = CoolNumbers.searchInHashSet(hashSet, input);
            long finish3 = System.nanoTime();
            if(hashSetSearch)
                System.out.println("Поиск в hashSet: номер найден, время поиска составило " + (finish3 - start3) + "нс");
            else
                System.out.println("Поиск в hashSet: номер не найден, время поиска составило " + (finish3 - start3) + "нс");

            long start4 = System.nanoTime();
            boolean treeSetSearch = CoolNumbers.searchInTreeSet(treeSet, input);
            long finish4 = System.nanoTime();
            if(treeSetSearch)
                System.out.println("Поиск в treeSet: номер найден, время поиска составило " + (finish4 - start4) + "нс");
            else
                System.out.println("Поиск в treeSet: номер не найден, время поиска составило " + (finish4 - start4) + "нс");
        }

    }
}
