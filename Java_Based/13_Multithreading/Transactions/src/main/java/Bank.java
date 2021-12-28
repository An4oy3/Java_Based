import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class Bank {

    private volatile Map<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public synchronized void transfer(String fromAccountNum, String toAccountNum, long amount) {
        if(accounts.get(fromAccountNum).isFraudAcc() || accounts.get(toAccountNum).isFraudAcc()){
            System.out.println("Перевод невозможен, т.к. один или оба аккаунта заблокированы службой безопасности");
        } else if (amount > 50000) {
                try {
                    if(isFraud(fromAccountNum, toAccountNum, amount)){
                        System.out.println("Подозрительная транзакция. Блокировка счетов №" + fromAccountNum + ", " + toAccountNum);
                        accounts.get(fromAccountNum).setFraudAcc(true);
                        accounts.get(toAccountNum).setFraudAcc(true);
                    } else {
                        accounts.get(fromAccountNum).setMoney(accounts.get(fromAccountNum).getMoney() - amount);
                        accounts.get(toAccountNum).setMoney(accounts.get(toAccountNum).getMoney() + amount);
                        System.out.println("Выполнен перевод со счета " + fromAccountNum + ", на счет " + toAccountNum + " на сумму - " + amount + "\n" +
                                "Баланс аккаунта №" + fromAccountNum + " = " + accounts.get(fromAccountNum).getMoney() + "\n" +
                                "Баланс аккаунта №" + toAccountNum + " = " + accounts.get(toAccountNum).getMoney());

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                accounts.get(fromAccountNum).setMoney(accounts.get(fromAccountNum).getMoney() - amount);
                accounts.get(toAccountNum).setMoney(accounts.get(toAccountNum).getMoney() + amount);
                System.out.println("Выполнен перевод со счета " + fromAccountNum + ", на счет " + toAccountNum + " на сумму - " + amount + "\n" +
                        "Баланс аккаунта №" + fromAccountNum + " = " + accounts.get(fromAccountNum).getMoney() + "\n" +
                        "Баланс аккаунта №" + toAccountNum + " = " + accounts.get(toAccountNum).getMoney());
        }
        }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public synchronized long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public synchronized long getSumAllAccounts() {
        AtomicLong sum = new AtomicLong();
        accounts.forEach((s, account) -> sum.addAndGet(account.getMoney()));
        return sum.get();
    }

    public void addAccount(Account account){
        accounts.put(account.getAccNumber(), account);
    }

    public Map<String, Account> getAccounts(){
        return accounts;
    }


}
