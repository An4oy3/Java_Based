import java.util.Calendar;

public class DepositAccount extends BankAccount {
    private Calendar lastIncome;

    public DepositAccount() {
        super();
    }

    @Override
    public void put(double amountToPut) {
        Calendar currentDay = Calendar.getInstance();
        super.put(amountToPut);
        lastIncome = currentDay;
    }

    @Override
    public void take(double amountToTake) {
        Calendar finalData = lastIncome;
        finalData.add(Calendar.MONTH, 1);
        if(Calendar.getInstance().compareTo(finalData) >= 0) {
            super.take(amountToTake);
        }
    }
}
