import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * JUnit tests for the TownGraphManager class
 * 
 * @author Jessica Park
 */
public class TownGraphManager_STUDENT_Test {
    private TownGraphManager manager;

    /**
     * Set up the TownGraphManager with initial data before each test
     */
    @BeforeEach
    public void setUp() {
        manager = new TownGraphManager();
        manager.addTown("TownA");
        manager.addTown("TownB");
        manager.addTown("TownC");
        manager.addRoad("TownA", "TownB", 10, "MainStreet");
        manager.addRoad("TownB", "TownC", 5, "SecondStreet");
    }

    /**
     * Test that towns can be added to the graph manager
     */
    @Test
    public void testAddTown() {
        assertTrue(manager.addTown("TownD"), "TownD should be added successfully.");
        assertTrue(manager.containsTown("TownD"), "TownGraphManager should contain TownD.");
    }

    /**
     * Test that roads can be added to the graph manager
     */
    @Test
    public void testAddRoad() {
        assertTrue(manager.addRoad("TownA", "TownC", 15, "ThirdStreet"), "ThirdStreet should be added between TownA and TownC.");
        assertEquals("ThirdStreet", manager.getRoad("TownA", "TownC"), "Road name between TownA and TownC should be 'ThirdStreet'.");
    }

    /**
     * Test that the graph manager can find the shortest path
     */
    @Test
    public void testShortestPath() {
        ArrayList<String> path = manager.getPath("TownA", "TownC");
        assertNotNull(path, "Path should exist between TownA and TownC.");
        assertEquals(2, path.size(), "Path should consist of two segments.");
        assertEquals("TownA via MainStreet to TownB 10", path.get(0), "First path segment should be TownA via MainStreet to TownB.");
        assertEquals("TownB via SecondStreet to TownC 5", path.get(1), "Second path segment should be TownB via SecondStreet to TownC.");
    }

    /**
     * Test that towns can be retrieved in alphabetical order
     */
    @Test
    public void testAllTowns() {
        ArrayList<String> towns = manager.allTowns();
        assertEquals(3, towns.size(), "There should be three towns in the manager.");
        assertEquals("TownA", towns.get(0), "First town should be TownA.");
        assertEquals("TownB", towns.get(1), "Second town should be TownB.");
        assertEquals("TownC", towns.get(2), "Third town should be TownC.");
    }

    /**
     * Test that roads can be retrieved in sorted order by name
     */
    @Test
    public void testAllRoads() {
        ArrayList<String> roads = manager.allRoads();
        assertEquals(2, roads.size(), "There should be two roads in the manager.");
        assertEquals("MainStreet", roads.get(0), "First road should be MainStreet.");
        assertEquals("SecondStreet", roads.get(1), "Second road should be SecondStreet.");
    }

    /**
     * Test that roads can be deleted from the graph manager
     */
    @Test
    public void testDeleteRoad() {
        assertTrue(manager.deleteRoadConnection("TownA", "TownB", "MainStreet"), "MainStreet should be deleted successfully.");
        assertFalse(manager.containsRoadConnection("TownA", "TownB"), "Graph should not contain MainStreet after deletion.");
    }

    /**
     * Test that towns can be deleted from the graph manager
     */
    @Test
    public void testDeleteTown() {
        assertTrue(manager.deleteTown("TownB"), "TownB should be deleted successfully.");
        assertFalse(manager.containsTown("TownB"), "Graph should not contain TownB after deletion.");
    }

    /**
     * Test that the graph manager correctly identifies road connections
     */
    @Test
    public void testContainsRoadConnection() {
        assertTrue(manager.containsRoadConnection("TownA", "TownB"), "Graph should contain a road connection between TownA and TownB.");
        assertFalse(manager.containsRoadConnection("TownA", "TownC"), "Graph should not contain a direct road connection between TownA and TownC.");
    }
}