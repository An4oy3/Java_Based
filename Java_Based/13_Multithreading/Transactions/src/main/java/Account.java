public class Account {

    private long money;
    private String accNumber;
    private boolean isFraudAcc;

    public Account(long money, String accNumber) {
        this.money = money;
        this.accNumber = accNumber;
        isFraudAcc = false;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        if(isFraudAcc()) {
            System.out.println("Ваш аккаунт временно заблокирован службой безопасности. Пожалуйста ожидайте!");
        } else {
            this.money = money;
        }
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public boolean isFraudAcc() {
        return isFraudAcc;
    }

    public void setFraudAcc(boolean fraudAcc) {
        isFraudAcc = fraudAcc;
    }
}
