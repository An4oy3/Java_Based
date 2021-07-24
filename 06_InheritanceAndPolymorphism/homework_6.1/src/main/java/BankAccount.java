public class BankAccount {

  private double amount;

  public BankAccount(){
     amount = 0.0;
  }

  public double getAmount() {
    return amount;
  }

  public void put(double amountToPut) {
    if(amountToPut > 0)
        amount += amountToPut;
  }

  public void take(double amountToTake) {
    if(amountToTake <= amount)
        amount -= amountToTake;
  }

  public boolean send(BankAccount receiver, double amountForSend){
      if(amountForSend <= 0 || amountForSend > this.amount){
          return false;
      }
      this.take(amountForSend);
      receiver.put(amountForSend);
      return true;
  }
}
