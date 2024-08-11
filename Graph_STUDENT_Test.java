import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import java.util.HashSet;

/**
 * JUnit tests for the Graph class
 * 
 * @author Jessica Park
 */
public class Graph_STUDENT_Test {
    private Graph graph;
    private Town town1;
    private Town town2;
    private Town town3;
    private Road road1;
    private Road road2;

    /**
     * Set up the graph with initial data before each test
     */
    @BeforeEach
    public void setUp() {
        graph = new Graph();
        town1 = new Town("Town1");
        town2 = new Town("Town2");
        town3 = new Town("Town3");
        road1 = new Road(town1, town2, 5, "Road1");
        road2 = new Road(town2, town3, 10, "Road2");

        graph.addVertex(town1);
        graph.addVertex(town2);
        graph.addVertex(town3);
        graph.addEdge(town1, town2, 5, "Road1");
        graph.addEdge(town2, town3, 10, "Road2");
    }

    /**
     * Test that vertices can be added to the graph
     */
    @Test
    public void testAddVertex() {
        Town town4 = new Town("Town4");
        assertTrue(graph.addVertex(town4), "Town4 should be added to the graph.");
        assertTrue(graph.containsVertex(town4), "Graph should contain Town4.");
    }

    /**
     * Test that an edge can be retrieved from the graph
     */
    @Test
    public void testGetEdge() {
        Road retrievedRoad = graph.getEdge(town1, town2);
        assertNotNull(retrievedRoad, "Road1 should exist between Town1 and Town2.");
        assertEquals("Road1", retrievedRoad.getName(), "Retrieved road name should be 'Road1'.");
    }

    /**
     * Test that the graph correctly identifies existing edges
     */
    @Test
    public void testContainsEdge() {
        assertTrue(graph.containsEdge(town1, town2), "Graph should contain an edge between Town1 and Town2.");
        assertFalse(graph.containsEdge(town1, town3), "Graph should not contain an edge between Town1 and Town3.");
    }

    /**
     * Test that edges can be removed from the graph
     */
    @Test
    public void testRemoveEdge() {
        Road removedRoad = graph.removeEdge(town1, town2, 5, "Road1");
        assertNotNull(removedRoad, "Road1 should be removed.");
        assertFalse(graph.containsEdge(town1, town2), "Graph should not contain Road1 after removal.");
    }

    /**
     * Test that the graph correctly identifies existing vertices
     */
    @Test
    public void testContainsVertex() {
        assertTrue(graph.containsVertex(town1), "Graph should contain Town1.");
        Town town4 = new Town("Town4");
        assertFalse(graph.containsVertex(town4), "Graph should not contain Town4.");
    }

    /**
     * Test that vertices can be removed from the graph
     */
    @Test
    public void testRemoveVertex() {
        assertTrue(graph.removeVertex(town1), "Town1 should be removed from the graph.");
        assertFalse(graph.containsVertex(town1), "Graph should not contain Town1 after removal.");
        assertTrue(graph.edgeSet().stream().noneMatch(road -> road.contains(town1)), "No edges should contain Town1.");
    }

    /**
     * Test that the edge set returns all edges in the graph
     */
    @Test
    public void testEdgeSet() {
        Set<Road> edges = graph.edgeSet();
        assertEquals(2, edges.size(), "There should be two roads in the graph.");
        Set<String> edgeNames = new HashSet<>();
        for (Road road : edges) {
            edgeNames.add(road.getName());
        }
        assertTrue(edgeNames.contains("Road1"), "Edge set should contain Road1.");
        assertTrue(edgeNames.contains("Road2"), "Edge set should contain Road2.");
    }

    /**
     * Test that edges touching a specified vertex are returned correctly
     */
    @Test
    public void testEdgesOf() {
        Set<Road> roadsOfTown2 = graph.edgesOf(town2);
        assertEquals(2, roadsOfTown2.size(), "There should be two roads touching Town2.");
        assertTrue(roadsOfTown2.contains(road1), "Road1 should touch Town2.");
        assertTrue(roadsOfTown2.contains(road2), "Road2 should touch Town2.");
    }

    /**
     * Test the shortest path between two vertices using Dijkstra's algorithm
     */
    @Test
    public void testShortestPath() {
        ArrayList<String> path = graph.shortestPath(town1, town3);
        assertEquals(2, path.size(), "The shortest path should consist of two segments.");
        assertEquals("Town1 via Road1 to Town2 5", path.get(0), "First path segment should be Town1 via Road1 to Town2.");
        assertEquals("Town2 via Road2 to Town3 10", path.get(1), "Second path segment should be Town2 via Road2 to Town3.");
    }
}