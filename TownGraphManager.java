import java.util.*;

/**
 * Town Graph Manager - manages a graph of towns and roads
 * Provides methods to interact with the graph
 * 
 * @author Jessica Park
 */

 public class TownGraphManager implements TownGraphManagerInterface {
    private Graph graph;

    /**
     * Constructor to initialize the TownGraphManager with a new graph
     */
    public TownGraphManager() {
        this.graph = new Graph();
    }

    /**
     * Adds a road with 2 towns and a road name
     *
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @param weight the distance between the two towns
     * @param roadName name of road
     * @return true if the road was added successfully
     */
    @Override
    public boolean addRoad(String town1, String town2, int weight, String roadName) {
        Town t1 = getOrCreateTown(town1);
        Town t2 = getOrCreateTown(town2);
        if (graph.containsEdge(t1, t2)) {
            return false;
        }
        graph.addEdge(t1, t2, weight, roadName);
        return true;
    }

    /**
     * Returns the name of the road that both towns are connected through
     *
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return name of road if town 1 and town2 are on the same road, returns null if not
     */
    @Override
    public String getRoad(String town1, String town2) {
        Town t1 = new Town(town1);
        Town t2 = new Town(town2);
        Road road = graph.getEdge(t1, t2);
        return road != null ? road.getName() : null;
    }

    /**
     * Adds a town to the graph
     *
     * @param v the town's name (lastname, firstname)
     * @return true if the town was successfully added, false if not
     */
    @Override
    public boolean addTown(String v) {
        Town town = new Town(v);
        return graph.addVertex(town);
    }

    /**
     * Gets a town with a given name
     *
     * @param name the town's name
     * @return the Town specified by the name, or null if town does not exist
     */
    @Override
    public Town getTown(String name) {
        for (Town town : graph.vertexSet()) {
            if (town.getName().equals(name)) {
                return town;
            }
        }
        return null;
    }

    /**
     * Determines if a town is already in the graph
     *
     * @param v the town's name
     * @return true if the town is in the graph, false if not
     */
    @Override
    public boolean containsTown(String v) {
        return getTown(v) != null;
    }

    /**
     * Determines if a road is in the graph.
     *
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return true if the road is in the graph, false if not
     */
    @Override
    public boolean containsRoadConnection(String town1, String town2) {
        Town t1 = new Town(town1);
        Town t2 = new Town(town2);
        return graph.containsEdge(t1, t2);
    }

    /**
     * Creates an ArrayList of all road titles in sorted order by road name
     *
     * @return an ArrayList of all road titles in sorted order by road name
     */
    @Override
    public ArrayList<String> allRoads() {
        Set<Road> roads = graph.edgeSet();
        ArrayList<String> roadNames = new ArrayList<>();
        for (Road road : roads) {
            roadNames.add(road.getName());
        }
        Collections.sort(roadNames);
        return roadNames;
    }

    /**
     * Deletes a road from the graph
     *
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @param roadName the road name
     * @return true if the road was successfully deleted, false if not
     */
    @Override
    public boolean deleteRoadConnection(String town1, String town2, String roadName) {
        Town t1 = new Town(town1);
        Town t2 = new Town(town2);
        Road road = graph.getEdge(t1, t2);
        if (road != null && road.getName().equals(roadName)) {
            graph.removeEdge(t1, t2, road.getWeight(), roadName);
            return true;
        }
        return false;
    }

    /**
     * Deletes a town from the graph
     *
     * @param v name of town (lastname, firstname)
     * @return true if the town was successfully deleted, false if not
     */
    @Override
    public boolean deleteTown(String v) {
        Town town = new Town(v);
        return graph.removeVertex(town);
    }

    /**
     * Creates an ArrayList of all towns in alphabetical order (last name, first name)
     *
     * @return an ArrayList of all towns in alphabetical order (last name, first name)
     */
    @Override
    public ArrayList<String> allTowns() {
        Set<Town> towns = graph.vertexSet();
        ArrayList<String> townNames = new ArrayList<>();
        for (Town town : towns) {
            townNames.add(town.getName());
        }
        Collections.sort(townNames);
        return townNames;
    }

    /**
     * Returns the shortest path from town 1 to town 2
     *
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return an ArrayList of roads connecting the two towns together, null if the
     * towns have no path to connect them
     */
    @Override
    public ArrayList<String> getPath(String town1, String town2) {
        Town t1 = getTown(town1);
        Town t2 = getTown(town2);
        if (t1 == null || t2 == null) {
            return null;
        }
        return graph.shortestPath(t1, t2);
    }

}