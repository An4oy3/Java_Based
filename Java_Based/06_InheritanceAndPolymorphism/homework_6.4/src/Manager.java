public class Manager extends Company implements Employee, Comparable {
    private int incomeForEnterprise;

    public Manager(){
        incomeForEnterprise = (int) (Math.random() * (140000 - 115000 + 1) + 115000);
    }

    public int getIncomeForEnterprise() {
        return incomeForEnterprise;
    }

    @Override
    public double getMonthSalary() {
        return 70000 + (incomeForEnterprise * 0.05);
    }

    @Override
    public int compareTo(Object o) {
        if(getMonthSalary() > ((Employee) o).getMonthSalary())
            return -1;
        else if(getMonthSalary() < ((Employee) o).getMonthSalary())
            return 1;
        else
            return 0;
    }
}
