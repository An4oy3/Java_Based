public class PhysicalPerson extends Client {

    @Override
    public void getInfo() {
        System.out.println("Остаток на счете: " + getAmount() + "\n" +
                "Условия снятия и пополнения: \n" +
                "За пополнение счета и снятия средств со счета комиссия не взимается.");

    }
}
