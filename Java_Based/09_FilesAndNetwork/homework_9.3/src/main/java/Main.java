import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {
    private static final String pathMovementsCsv = "data/movementList.csv";
    public static void main(String[] args) {
        Movements movements = new Movements(pathMovementsCsv);
        System.out.println("Сумма расходов: " + movements.getExpenseSum());
        System.out.println("Сумма дохода: " + movements.getIncomeSum());
        List<EntryMove> list = movements.getListEntryMov();
        for (EntryMove entryMove : list) {
            System.out.println(entryMove.getOperationDescription() + ":  " + entryMove.getExpense());
        }
    }
}
