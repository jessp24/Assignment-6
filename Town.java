import java.util.Objects;

/**
 * Town
 * @author Jessica Park
 */

public class Town implements Comparable<Town> {
    private String name;
    
    /**
     * Constructor - initializes a town with the given name
     * 
     * @param name - name of the town
     */
    public Town(String name) {
        this.name = name;
    }

    /**
     * Copy constructor that creates a new Town based on an existing one.
     * 
     * @param templateTown
     */
    public Town(Town templateTown) {
        this.name = templateTown.name;
    }

    /**
     * Returns the name of the town
     * 
     * @return the town's name
     */
    public String getname() {
        return name;
    }

    /**
     * Compares this town with another based on their names
     * 
     * @param o - the other town to compare to
     * @return 0 if the town names are equal, a positive or negative number otherwise
     */
    @Override
    public int compareTo(Town o) {
        return this.name.compareTo(o.name);
    }

    /**
     * Checks if two towns are equal by comparing their names
     * 
     * @param obj - the object to compare
     * @return true if the town names are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Town town = (Town) obj;
        return Objects.equals(name, town.name);
    }

    /**
     * Generates a hash code for the town based on its name
     * 
     * @return the hash code for the town
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Returns the name of the town as its string representation
     * 
     * @return the town's name
     */
    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
    }
}
