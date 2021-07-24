public class IndividualBusinessman extends Client {

    @Override
    public void put(double amountToPut) {
        if(amountToPut < 1000)
            super.put(amountToPut -= (amountToPut * 0.01));
        else
            super.put(amountToPut -= (amountToPut * 0.005));
    }

    @Override
    public void getInfo() {
        System.out.println("Остаток на счете: " + getAmount() + "\n" +
                "Условия снятия и пополнения: \n" +
                "За снятие средств комиссия не взимается. \n" +
                "При пополнении счета на сумму до 1000 рублей - взимается комиссия 1% \n" +
                "При пополнении счета на сумму свыше 1000 рублей - взимается комиссия 0.5%");

    }
}
