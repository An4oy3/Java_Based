public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.put(1000);
        BankAccount bankAccount2 = new BankAccount();
        System.out.println(bankAccount1.send(bankAccount2, 300));
        System.out.println("bankAccount1 - " + bankAccount1.getAmount());
        System.out.println("bankAccount2 - " + bankAccount2.getAmount());
        BankAccount deposit = new DepositAccount();
    }
}
