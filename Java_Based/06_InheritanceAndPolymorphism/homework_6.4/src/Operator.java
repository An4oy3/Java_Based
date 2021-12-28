public class Operator extends Company implements Employee, Comparable {

    @Override
    public double getMonthSalary() {
        return 50000;
    }

    @Override
    public int getIncomeForEnterprise() {
        return 0;
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
