import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) {
        Bank bank = new Bank();
        ArrayList<Thread> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    synchronized (bank) {
                        bank.addAccount(new Account(100000, Integer.toString(bank.getAccounts().size() + 1)));
                        System.out.println(bank.getSumAllAccounts());
                    }
                }
            }));
        }
        list.forEach(Thread::start);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Сумма средств всех аккаунтов - " + bank.getSumAllAccounts());


        ArrayList<Thread> transfers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            transfers.add(new Thread(() -> {
                for (int j = 1; j < 10; j++) {
                    synchronized (bank) {
                        bank.transfer(Integer.toString(j), Integer.toString(j + 50), 50001);
                    }
                }
            }));
        }
        transfers.forEach(Thread::start);
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Сумма средств всех аккаунтов - " + bank.getSumAllAccounts());


    }
}
