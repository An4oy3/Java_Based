import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        List<Aircraft> aircrafts = airport.getAllAircrafts();
        int aircraftsCount = aircrafts.size();
        System.out.println(aircraftsCount);

    }
}
