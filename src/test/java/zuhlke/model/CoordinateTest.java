package zuhlke.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {

    Coordinate start;
    Coordinate end;

    @Before
    public void setUp() {
        start = new Coordinate(2,3);
        end = new Coordinate(5,12);
    }

    @Test
    public void ExpectGetDistanceOf3and9() {
        Coordinate answer = new Coordinate(5-2,12-3);
        assertEquals(answer.x,end.getDistance(start).x);
        assertEquals(answer.y,end.getDistance(start).y);
    }

    @Test
    public void ExpectStartLinearDistance5() {
        int ans = start.x + start.y;
        assertEquals(ans,start.linearDistance());
    }

    @Test
    public void ExpectLinearDistance10() {
        Coordinate point = new Coordinate(3,7);
        assertEquals(10,point.linearDistance());
    }

    @Test
    public void ExpectTestEqualsStart() {
        Coordinate test = new Coordinate(2,3);
        assertEquals(true,test.equals(start));
    }

}