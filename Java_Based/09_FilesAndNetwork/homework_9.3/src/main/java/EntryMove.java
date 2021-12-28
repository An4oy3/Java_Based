import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntryMove {
    private String type;
    private String cardNumber;
    private String currency;
    private Date dateOperation;
    private String reference;
    private String operationDescription;
    private double income;
    private double expense;


    public EntryMove(String type, String cardNumber, String currency, Date dateOperation, String reference, String operationDescription, double income, double expense) {
        this.type = type;
        this.cardNumber = cardNumber;
        this.currency = currency;
        this.dateOperation = dateOperation;
        this.reference = reference;
        this.operationDescription = operationDescription;
        this.income = income;
        this.expense = expense;
    }

    public double getIncome() {
        return income;
    }

    public double getExpense() {
        return expense;
    }
    public String getOperationDescription() {
        return operationDescription;
    }


}
