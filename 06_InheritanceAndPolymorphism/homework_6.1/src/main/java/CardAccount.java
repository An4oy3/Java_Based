public class CardAccount extends BankAccount {

    public CardAccount() {
        super();
    }


    @Override
    public void put(double amountToPut) {
        super.put(amountToPut);
    }

    @Override
    public void take(double amountToTake) {
        super.take(amountToTake * 1.01);
    }
}
