import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Student tests for the Town class
 * 
 * @author Jessica Park
 */
public class Town_STUDENT_Test {
    private Town townA;
    private Town townB;
    private Town townACopy;

    /**
     * Set up the towns with initial data before each test
     */
    @BeforeEach
    public void setUp() {
        townA = new Town("TownA");
        townB = new Town("TownB");
        townACopy = new Town("TownA");
    }

    /**
     * Test the creation of a town and its name
     */
    @Test
    public void testTownCreation() {
        assertEquals("TownA", townA.getName(), "Town name should be TownA.");
        assertEquals("TownB", townB.getName(), "Town name should be TownB.");
    }

    /**
     * Test the comparison of two towns
     */
    @Test
    public void testCompareTo() {
        assertEquals(0, townA.compareTo(townACopy), "Towns with the same name should be equal.");
        assertTrue(townA.compareTo(townB) < 0, "TownA should be less than TownB.");
        assertTrue(townB.compareTo(townA) > 0, "TownB should be greater than TownA.");
    }

    /**
     * Test the equality of two towns
     */
    @Test
    public void testEquals() {
        assertEquals(townA, townACopy, "Towns with the same name should be equal.");
        assertNotEquals(townA, townB, "Towns with different names should not be equal.");
    }

    /**
     * Test the hash code of a town
     */
    @Test
    public void testHashCode() {
        assertEquals(townA.hashCode(), townACopy.hashCode(), "Towns with the same name should have the same hash code.");
        assertNotEquals(townA.hashCode(), townB.hashCode(), "Towns with different names should have different hash codes.");
    }

    /**
     * Test the string representation of a town
     */
    @Test
    public void testToString() {
        assertEquals("TownA", townA.toString(), "Town string representation should be its name.");
    }
}