import java.util.*;

/**
 * Graph of towns connected by roads
 * 
 * @author Jessica Park
 */

public class Graph implements GraphInterface<Town, Road> {
    private Map<Town, Set<Road>> adjacencyList;

    /**
     * Constructor - initializes an empty graph
     */
    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    /**
     * returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph, otherwise returns null
     * 
     * @param sourceVertex source vertex of the edge
     * @param destinationVertex target vertex of the edge
     * @return an edge connecting source vertex to target vertex
     */
    @Override
    public Road getEdge(Town sourceVertex, Town destinationVertex) {
        if (sourceVertex == null || destinationVertex == null) {
            return null;
        }
        for (Road road : adjacencyList.getOrDefault(sourceVertex, Collections.emptySet())) {
            if (road.contains(destinationVertex)) {
                return road;
            }
        }
        return null;
    }

    /**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge
     *
     * @param sourceVertex source vertex of the edge
     * @param destinationVertex target vertex of the edge
     * @param weight weight of the edge
     * @param description description for edge
     * @return The newly created edge if added to the graph, otherwise null
     * @throws IllegalArgumentException if source or target vertices are not found in the graph
     * @throws NullPointerException if any of the specified vertices is null
     */
    @Override
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        if (sourceVertex == null || destinationVertex == null) {
            throw new NullPointerException("Source or destination cannot be null.");
        }
        if (!containsVertex(sourceVertex) || !containsVertex(destinationVertex)) {
            throw new IllegalArgumentException("Vertices must be in the graph.");
        }
        Road road = new Road(sourceVertex, destinationVertex, weight, description);
        adjacencyList.get(sourceVertex).add(road);
        adjacencyList.get(destinationVertex).add(road);
        return road;
    }

    /**
     * Adds the specified vertex to this graph if not already present
     *
     * @param v vertex to be added to this graph
     * @return true if this graph did not already contain the specified vertex
     * @throws NullPointerException if the specified vertex is null
     */
    @Override
    public boolean addVertex(Town v) {
        if (v == null) {
            throw new NullPointerException("Vertex cannot be null.");
        }
        if (!adjacencyList.containsKey(v)) {
            adjacencyList.put(v, new HashSet<>());
            return true;
        }
        return false;
    }

    /**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex
     *
     * @param sourceVertex      source vertex of the edge
     * @param destinationVertex target vertex of the edge
     * @return true if this graph contains the specified edge
     */
    @Override
    public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
        return getEdge(sourceVertex, destinationVertex) != null;
    }

    /**
     * Returns true if this graph contains the specified vertex
     *
     * @param v vertex whose presence in this graph is to be tested
     * @return true if this graph contains the specified vertex
     */
    @Override
    public boolean containsVertex(Town v) {
        return adjacencyList.containsKey(v);
    }

    /**
     * Returns a set of the edges contained in this graph
     *
     * @return a set of the edges contained in this graph
     */
    @Override
    public Set<Road> edgeSet() {
        Set<Road> allEdges = new HashSet<>();
        for (Set<Road> roads : adjacencyList.values()) {
            allEdges.addAll(roads);
        }
        return allEdges;
    }

    /**
     * Returns a set of all edges touching the specified vertex
     *
     * @param vertex the vertex for which a set of touching edges is to be returned
     * @return a set of all edges touching the specified vertex
     * @throws IllegalArgumentException if vertex is not found in the graph
     * @throws NullPointerException if vertex is null
     */
    @Override
    public Set<Road> edgesOf(Town vertex) {
        if (vertex == null) {
            throw new NullPointerException("Vertex cannot be null.");
        }
        Set<Road> roads = adjacencyList.get(vertex);
        if (roads == null) {
            throw new IllegalArgumentException("Vertex not found in the graph.");
        }
        return roads;
    }

    /**
     * Removes an edge going from source vertex to target vertex
     *
     * @param sourceVertex source vertex of the edge
     * @param destinationVertex target vertex of the edge
     * @param weight weight of the edge
     * @param description description of the edge
     * @return The removed edge, or null if no edge removed
     */
    @Override
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        Road road = getEdge(sourceVertex, destinationVertex);
        if (road != null && road.getWeight() == weight && road.getName().equals(description)) {
            adjacencyList.get(sourceVertex).remove(road);
            adjacencyList.get(destinationVertex).remove(road);
            return road;
        }
        return null;
    }

    /**
     * Removes the specified vertex from this graph including all its touching edges if present
     *
     * @param v vertex to be removed from this graph, if present
     * @return true if the graph contained the specified vertex; false otherwise
     */
    @Override
    public boolean removeVertex(Town v) {
        if (v == null || !adjacencyList.containsKey(v)) {
            return false;
        }
        adjacencyList.remove(v);
        for (Set<Road> roads : adjacencyList.values()) {
            roads.removeIf(road -> road.contains(v));
        }
        return true;
    }

    /**
     * Returns a set of the vertices contained in this graph
     *
     * @return a set view of the vertices contained in this graph
     */
    @Override
    public Set<Town> vertexSet() {
        return adjacencyList.keySet();
    }

    /**
     * Find the shortest path from the sourceVertex to the destinationVertex
     *
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An ArrayList of Strings that describe the path from sourceVertex to destinationVertex
     */
    @Override
    public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
        dijkstraShortestPath(sourceVertex);
        ArrayList<String> path = new ArrayList<>();
        Town current = destinationVertex;

        while (!current.equals(sourceVertex)) {
            Town previous = previousNodes.get(current);
            if (previous == null) {
                return new ArrayList<>(); 
            }
            Road road = getEdge(previous, current);
            path.add(0, previous + " via " + road.getName() + " to " + current + " " + road.getWeight());
            current = previous;
        }
        return path;
    }

    private Map<Town, Integer> distances;
    private Map<Town, Town> previousNodes;

    /**
     * Dijkstra's Shortest Path Method
     *
     * @param sourceVertex the vertex to find shortest path from
     */
    @Override
    public void dijkstraShortestPath(Town sourceVertex) {
        distances = new HashMap<>();
        previousNodes = new HashMap<>();
        PriorityQueue<Town> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (Town town : adjacencyList.keySet()) {
            distances.put(town, Integer.MAX_VALUE);
            previousNodes.put(town, null);
        }
        distances.put(sourceVertex, 0);
        priorityQueue.add(sourceVertex);

        while (!priorityQueue.isEmpty()) {
            Town current = priorityQueue.poll();
            for (Road road : edgesOf(current)) {
                Town neighbor = road.getDestination().equals(current) ? road.getSource() : road.getDestination();
                int newDistance = distances.get(current) + road.getWeight();
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    previousNodes.put(neighbor, current);
                    priorityQueue.add(neighbor);
                }
            }
        }
    }

}
