import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Student tests for the Road class.
 * 
 * @author Jessica Park
 */
public class Road_STUDENT_Test {
    private Town townA;
    private Town townB;
    private Road road;

    /**
     * Set up the road with initial data before each test.
     */
    @BeforeEach
    public void setUp() {
        townA = new Town("TownA");
        townB = new Town("TownB");
        road = new Road(townA, townB, 8, "RoadAB");
    }

    /**
     * Test the creation of a road and its attributes.
     */
    @Test
    public void testRoadCreation() {
        assertEquals("RoadAB", road.getName(), "Road name should be RoadAB.");
        assertEquals(8, road.getWeight(), "Road weight should be 8.");
        assertEquals(townA, road.getSource(), "Source town should be TownA.");
        assertEquals(townB, road.getDestination(), "Destination town should be TownB.");
    }

    /**
     * Test that a road contains a specified town.
     */
    @Test
    public void testContainsTown() {
        assertTrue(road.contains(townA), "Road should contain TownA.");
        assertTrue(road.contains(townB), "Road should contain TownB.");
        Town townC = new Town("TownC");
        assertFalse(road.contains(townC), "Road should not contain TownC.");
    }

    /**
     * Test the comparison of two roads.
     */
    @Test
    public void testCompareTo() {
        Road roadCopy = new Road(townA, townB, 8, "RoadAB");
        assertEquals(0, road.compareTo(roadCopy), "Roads with the same name should be equal.");
        Road roadDifferent = new Road(townA, townB, 8, "DifferentRoad");
        assertTrue(road.compareTo(roadDifferent) > 0, "RoadAB should be greater than DifferentRoad.");
    }

    /**
     * Test the equality of two roads.
     */
    @Test
    public void testEquals() {
        Road roadCopy = new Road(townA, townB, 8, "RoadAB");
        assertEquals(road, roadCopy, "Roads with the same endpoints and name should be equal.");
        Road roadReverse = new Road(townB, townA, 8, "RoadAB");
        assertEquals(road, roadReverse, "Roads with reversed endpoints should be equal.");
        Road roadDifferent = new Road(townA, townB, 8, "DifferentRoad");
        assertNotEquals(road, roadDifferent, "Roads with different names should not be equal.");
    }

    /**
     * Test the string representation of a road.
     */
    @Test
    public void testToString() {
        assertEquals("RoadAB", road.toString(), "Road string representation should be its name.");
    }
}