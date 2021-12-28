public class LegalPerson extends Client {

    @Override
    public void take(double amountToTake) {
        super.take(amountToTake * 1.01);
    }

    @Override
    public void getInfo() {
        System.out.println("Остаток на счете: " + getAmount() + "\n" +
                "Условия снятия и пополнения: \n" +
                "За попослнение счета комиссия не взимается. \n" +
                "За снятие средств - взымается комиссия 1%");

    }
}
