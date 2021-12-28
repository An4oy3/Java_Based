import java.util.*;

public class Company {
    private int income;
    private List<Employee> list = new ArrayList<>();
    private Set<Employee> set = new TreeSet<>();

    public void hire(Employee employee){
        set.add(employee);
        //list.add(employee);
        income += employee.getIncomeForEnterprise();
    }

    public void hireAll(List<Employee> listEmployee){
        for (Employee employee : listEmployee) {
            hire(employee);
        }
    }

    public void fire(Employee employee){
        income -= employee.getIncomeForEnterprise();
        //list.remove(employee);
        set.remove(employee);
    }

    public int getIncome(){
        return income;
    }

    public ArrayList<Employee> getList(){
        return new ArrayList<>(set);
    }

    public List<Employee> getTopSalaryStaff(int count){
        if(count < 0 || count > set.size()){
            count = set.size();
        }
        int result = 0;
        List<Employee> topSalaryStaff = new ArrayList<>();
        for (Employee employee : set) {
            if(result < count) {
                topSalaryStaff.add(employee);
                result++;
            }else
                break;
        }
        return topSalaryStaff;
    }

    public List<Employee> getLowestSalaryStaff(int count){
        if(count < 0 || count > set.size()){
            count = set.size();
        }
        ArrayList<Employee> listLowestSalary = new ArrayList<>(set);
        Collections.reverse(listLowestSalary);
        ArrayList<Employee> lowestSalaryStaff = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lowestSalaryStaff.add(listLowestSalary.get(i));
        }
        return lowestSalaryStaff;
    }
}
