/**
 * Road - represents a road (edge) in a graph of towns
 * 
 * @author Jessica Park
 */

 public class Road implements Comparable<Road> {
    private Town source;
    private Town destination;
    private int weight;
    private String name;

    /**
     * Constructor - initializes a road with the given source, destination, weight, and name
     * 
     * @param source one town on the road
     * @param destination another town on the road
     * @param weight the distance of the road
     * @param name the name of the road
     */
    public Road(Town source, Town destination, int weight, String name) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        this.name = name;
    }

    /**
     * Constructor - initializes a road with the given source, destination, and name
     * The weight is preset to 1
     * 
     * @param source one town on the road
     * @param destination another town on the road
     * @param name the name of the road
     */
    public Road(Town source, Town destination, String name) {
        this(source, destination, 1, name);
    }

    /**
     * Compares road with another based on their names
     * 
     * @param o - the other road to compare to
     * @return 0 if the road names are the name, a positive or negative number if not
     */
    @Override
    public int compareTo(Road o) {
        return this.name.compareTo(o.name);
    }

    /**
     * Returns true if the edge contains the given town
     *
     * @param town a vertex of the graph
     * @return true if the edge is connected to the given vertex
     */
    public boolean contains(Town town) {
        return source.equals(town) || destination.equals(town);
    }

    /**
     * Checks if two roads are equal by comparing their endpoints
     * A road from A to B is considered the same as a road from B to A
     *
     * @param obj the road object to compare
     * @return true if each of the ends of the road is the same as the ends of this road
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Road road = (Road) obj;
        return (source.equals(road.source) && destination.equals(road.destination)) ||
               (source.equals(road.destination) && destination.equals(road.source));
    }

    /**
     * Returns the second town on the road
     *
     * @return the destination town on the road
     */
    public Town getDestination() {
        return destination;
    }

    /**
     * Returns the road name
     *
     * @return the name of the road
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the first town on the road
     *
     * @return the source town on the road
     */
    public Town getSource() {
        return source;
    }

    /**
     * Returns the distance of the road
     *
     * @return the distance of the road
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Returns the name of the road as its string representation
     *
     * @return the road's name
     */
    @Override
    public String toString() {
        return name;
    }
 }
