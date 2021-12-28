public class TopManager extends Company implements Employee, Comparable {
    private Company company;

    public TopManager(Company company){
        this.company = company;
    }


    @Override
    public double getMonthSalary() {
        if(company.getIncome() > 10000000){
            return 90000 + (90000 * 1.5);
        } else {
            return 90000;
        }
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
