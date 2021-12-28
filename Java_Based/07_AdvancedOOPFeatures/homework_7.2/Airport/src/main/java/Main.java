import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {


    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        //TODO Метод должден вернуть список рейсов вылетающих в ближайшие два часа.
        List<Terminal> terminals = airport.getTerminals();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, +2);
        List<Flight> result = new ArrayList<>();
        terminals.forEach(terminal -> terminal.getFlights().stream()
                .filter(flight -> flight.getType().equals(Flight.Type.DEPARTURE))
                .filter(flight -> flight.getDate().before(calendar.getTime()) && flight.getDate().after(Calendar.getInstance().getTime()))
                .forEach(result::add));
        return result;
    }

}