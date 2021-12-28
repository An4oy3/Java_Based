import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        int day = 27;
        int month = 1;
        int year = 2011;

        ICurrentDateResolver dateResolver = new CurrentDateResolver();
        System.out.println(collectBirthdays(year, month, day, dateResolver));

    }

    public static String collectBirthdays(int year, int month, int day, ICurrentDateResolver dateResolver) {

        StringBuilder sb = new StringBuilder("");
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        DateFormat formatDay = new SimpleDateFormat("E", Locale.ENGLISH);
        Calendar birthdayDate = Calendar.getInstance();
        birthdayDate.set(year, month, day, 0, 0, 0);
        birthdayDate.add(Calendar.MONTH,-1);
        Date bd = birthdayDate.getTime();
        int daycount = 0;
        Date cd = dateResolver.getCurrentDate();

        while (bd.compareTo(cd) <= 0){
            sb.append(daycount).append(" - ").append(format.format(bd)).append(" - ").append(formatDay.format(bd)).append(System.lineSeparator());
            birthdayDate.add(Calendar.YEAR, 1);
            bd = birthdayDate.getTime();
            daycount++;
        }
        //TODO реализуйте метод для построения строки в следующем виде
        //0 - 31.12.1990 - Mon
        //1 - 31.12.1991 - Tue
        return sb.toString();
    }
}

/*Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        Date cd = currentDate.getTime();*/
