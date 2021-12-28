import java.util.Calendar;
import java.util.Date;

public class CurrentDateResolver implements ICurrentDateResolver {
    @Override
    public Date getCurrentDate() {
        Calendar currentDate = Calendar.getInstance();
        //currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        Date cd = currentDate.getTime();
        return cd;
    }
}
