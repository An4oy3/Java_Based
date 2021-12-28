import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class Movements {
    private double expenseSum = 0.0;
    private double incomeSum = 0.0;
    private List<String> movementsCsv;
    private static String dateFormat = "dd.MM.yyyy";
    private List<EntryMove> listEntryMov = new ArrayList<>();

    public Movements(String pathMovementsCsv) {
        try {
            movementsCsv = Files.readAllLines(Paths.get(pathMovementsCsv));
            for(int i = 1; i < movementsCsv.size(); i++) {
                String[] splitText = movementsCsv.get(i).split(",");
                ArrayList<String> entry = new ArrayList<>();
                for (int j = 0; j < splitText.length; j++) {
                    if(isColumnPart(splitText[j])){
                        String lastText = entry.get(entry.size() - 1);
                        entry.set(entry.size()-1, lastText + "," + splitText[j]);
                        entry.set(entry.size()-1, entry.get(entry.size()-1).replaceAll("\"", ""));
                    }
                    else
                        entry.add(splitText[j]);
                }

                if (entry.size() != 8) {
                    System.out.println("Неверный формат строки: " + movementsCsv.get(i));
                    continue;
                }
                entry.set(6, entry.get(6).replaceAll(",", "."));
                entry.set(7, entry.get(7).replaceAll(",", "."));
                listEntryMov.add(new EntryMove(entry.get(0), entry.get(1), entry.get(2),
                        new SimpleDateFormat(dateFormat).parse(entry.get(3)), entry.get(4), entry.get(5),
                        Double.parseDouble(entry.get(6)), Double.parseDouble(entry.get(7))));
                expenseSum += listEntryMov.get(listEntryMov.size()-1).getExpense();
                incomeSum += listEntryMov.get(listEntryMov.size()-1).getIncome();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isColumnPart(String text){
        String trimText = text.trim();
        return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
    }

    public double getExpenseSum() {
        return expenseSum;
    }

    public double getIncomeSum() {
        return incomeSum;
    }
    public List<EntryMove> getListEntryMov() {
        return listEntryMov;
    }
}
