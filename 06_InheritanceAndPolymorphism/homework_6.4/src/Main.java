import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Company epam = new Company();
        for (int i = 0; i < 180; i++){
            if(i < 10){
                epam.hire(new TopManager(epam));
            }
            if(i < 80){
                epam.hire(new Manager());
            }
            epam.hire(new Operator());
        }


        List<Employee> listTopSalary = epam.getTopSalaryStaff(15);
        System.out.println("Общий доход компании - " + epam.getIncome());
        System.out.println("Список самых высоких зарплат:");
        for (Employee employee : listTopSalary) {
            System.out.println(employee.getMonthSalary() + " руб. ");
        }

        System.out.println("\n\n\n Список самых низких зарплат:");
        List<Employee> listLowestSalary = epam.getLowestSalaryStaff(30);
        for(Employee employee : listLowestSalary){
            System.out.println(employee.getMonthSalary() + " руб. ");
        }

        System.out.println("\n\n Сокращение на 50%:");
        int listSize = epam.getList().size()/2;
        for (int i = 0; i < listSize ; i++) {
                epam.fire(epam.getList().get(i));
        }

        System.out.println("\n\n Список самых высоких зарплат:");
        listTopSalary = epam.getTopSalaryStaff(15);
        for (Employee employee : listTopSalary) {
            System.out.println(employee.getMonthSalary() + " руб. ");
        }


        System.out.println("\n\n\n Список самых низких зарплат:");
        listLowestSalary = epam.getLowestSalaryStaff(30);
        for(Employee employee : listLowestSalary){
            System.out.println(employee.getMonthSalary() + " руб. ");
        }

        System.out.println("Общий доход компании, после сокращений - " + epam.getIncome());

    }
}
