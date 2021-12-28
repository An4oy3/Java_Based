import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class RouteCalculatorTest extends TestCase {
    List<Station> route;
    StationIndex stationIndex;
    RouteCalculator calculator;


    @Override //Инициализация каких-либо данных
    protected void setUp() throws Exception {
        stationIndex = new StationIndex();
        route = new ArrayList<>();

        Line line1 = new Line(1, "первая");
        Line line2 = new Line(2, "вторая");
        Line line3 = new Line(3, "третья");

        route.add(new Station("Станция 1", line1));
        route.add(new Station("Станция 2", line1));
        route.add(new Station("Станция 3", line2));
        route.add(new Station("Станция 4", line2));
        route.add(new Station("Станция 5", line2));
        route.add(new Station("Станция 6", line3));
        route.add(new Station("Станция 7", line3));


        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);
        for (Station station : route) {
            stationIndex.addStation(station);
            station.getLine().addStation(station);
        }
        List<Station> connection = new ArrayList<>();
        List<Station> connection2 = new ArrayList<>();

        connection.add(route.get(1));
        connection.add(route.get(2));
        connection2.add(route.get(4));
        connection2.add(route.get(5));
        stationIndex.addConnection(connection);
        stationIndex.addConnection(connection2);
        calculator = new RouteCalculator(stationIndex);

    }

    public void testCalculateDuration(){
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 17.0;
        assertEquals(expected, actual);
    }

    public void testGetShortestRoute(){
        List<Station> actual = calculator.getShortestRoute(stationIndex.getStation("Станция 1"), stationIndex.getStation("Станция 3"));
        List<Station> expected = new ArrayList<>();
        expected.add(route.get(0));
        expected.add(route.get(1));
        expected.add(route.get(2));
        assertEquals(expected, actual);
    }

    public void testGetRouteOnTheLine(){
        List<Station> actual = calculator.getShortestRoute(stationIndex.getStation("Станция 1"), stationIndex.getStation("Станция 2"));
        List<Station> expected = new ArrayList<>();
        expected.add(route.get(0));
        expected.add(route.get(1));
        assertEquals(expected, actual);
    }

    public void testGetRouteWithOneConnection(){
        List<Station> actual = calculator.getShortestRoute(stationIndex.getStation("Станция 1"), stationIndex.getStation("Станция 3"));
        List<Station> expected = new ArrayList<>();
        expected.add(route.get(0));
        expected.add(route.get(1));
        expected.add(route.get(2));
        assertEquals(expected, actual);

    }

    public void testGetRouteWithTwoConnections(){
        List<Station> actual = calculator.getShortestRoute(stationIndex.getStation("Станция 1"), stationIndex.getStation("Станция 6"));
        List<Station> expected = new ArrayList<>();
        expected.add(route.get(0));
        expected.add(route.get(1));
        expected.add(route.get(2));
        expected.add(route.get(3));
        expected.add(route.get(4));
        expected.add(route.get(5));
        assertEquals(expected, actual);
    }

    public void testGetRouteViaConnectedLine(){
        List<Station> actual = calculator.getShortestRoute(stationIndex.getStation("Станция 1"), stationIndex.getStation("Станция 2"));
        List<Station> expected = new ArrayList<>();
        expected.add(route.get(0));
        expected.add(route.get(1));
        assertEquals(expected, actual);
    }

    @Override //Удаляет данные после проведения теста
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
