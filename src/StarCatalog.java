import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * A catalog for storing and managing stars with their names and coordinates
 * (right ascension and declination in degrees).
 */
public class StarCatalog {
    /**
     * Maximum right ascension in degrees (0 to 360).
     */
    private static final double MAX_RA = 360.0;

    /**
     * Maximum declination in degrees (-90 to 90).
     */
    private static final double MAX_DEC = 90.0;

    /**
     * Maximum number of stars the catalog can hold.
     */
    private static final int MAX_STARS = 10;

    /**
     * Right ascension of Sirius in degrees.
     */
    private static final double SIRIUS_RA = 101.28;

    /**
     * Declination of Sirius in degrees.
     */
    private static final double SIRIUS_DEC = -16.72;

    /**
     * Right ascension of Vega in degrees.
     */
    private static final double VEGA_RA = 279.23;

    /**
     * Declination of Vega in degrees.
     */
    private static final double VEGA_DEC = 38.78;

    /**
     * Inner class representing a star with name and coordinates.
     */
    private static class StarEntry {
        /**
         * Name of the star.
         */
        private String name;

        /**
         * Right ascension in degrees.
         */
        private double ra;

        /**
         * Declination in degrees.
         */
        private double dec;

        /**
         * Constructs a star with the given name and coordinates.
         *
         * @param name
         *            the name of the star
         * @param ra
         *            the right ascension in degrees
         * @param dec
         *            the declination in degrees
         */
        StarEntry(String name, double ra, double dec) {
            this.name = name;
            this.ra = ra;
            this.dec = dec;
        }

        /**
         * Returns the name of the star.
         *
         * @return the name of the star
         */
        public String getName() {
            return this.name;
        }

        /**
         * Returns the right ascension in degrees.
         *
         * @return the right ascension
         */
        public double getRa() {
            return this.ra;
        }

        /**
         * Returns the declination in degrees.
         *
         * @return the declination
         */
        public double getDec() {
            return this.dec;
        }
    }

    /**
     * Array to store stars in the catalog.
     */
    private StarEntry[] stars;

    /**
     * Number of stars currently in the catalog.
     */
    private int count;

    /**
     * Constructs an empty star catalog with a fixed capacity of 10 stars.
     */
    public StarCatalog() {
        this.stars = new StarEntry[MAX_STARS];
        this.count = 0;
    }

    /**
     * Adds a star to the catalog with the given name and coordinates. Does
     * nothing if the catalog is full, the name is invalid, or coordinates are
     * out of range.
     *
     * @param name
     *            the name of the star
     * @param ra
     *            the right ascension in degrees (0 to 360)
     * @param dec
     *            the declination in degrees (-90 to 90)
     */
    public void addStar(String name, double ra, double dec) {
        if (this.count >= MAX_STARS || name == null || name.isEmpty()) {
            return; // Skip if catalog is full or name is invalid
        }
        if (ra < 0 || ra >= MAX_RA || dec < -MAX_DEC || dec > MAX_DEC) {
            return; // Skip if coordinates are invalid
        }
        this.stars[this.count] = new StarEntry(name, ra, dec);
        this.count++;
    }

    /**
     * Removes a star from the catalog by name and returns its name. Returns an
     * empty string if the star is not found.
     *
     * @param name
     *            the name of the star to remove
     * @return the name of the removed star, or an empty string if not found
     */
    public String removeStar(String name) {
        for (int i = 0; i < this.count; i++) {
            if (this.stars[i] != null && this.stars[i].getName().equals(name)) {
                String removedName = this.stars[i].getName();
                this.stars[i] = null; // Clear the slot
                return removedName;
            }
        }
        return ""; // Not found
    }

    /**
     * Checks if a star with the given name exists in the catalog.
     *
     * @param name
     *            the name of the star to check
     * @return true if the star exists, false otherwise
     */
    public boolean hasStar(String name) {
        for (int i = 0; i < this.count; i++) {
            if (this.stars[i] != null && this.stars[i].getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the coordinates of a star as a string in the format "RA: x, Dec:
     * y". Returns "Star not found" if the star does not exist.
     *
     * @param name
     *            the name of the star
     * @return the coordinates as a string, or "Star not found" if the star is
     *         not in the catalog
     */
    public String getCoordinates(String name) {
        if (!this.hasStar(name)) {
            return "Star not found";
        }
        for (int i = 0; i < this.count; i++) {
            if (this.stars[i] != null && this.stars[i].getName().equals(name)) {
                return "RA: " + this.stars[i].getRa() + ", Dec: "
                        + this.stars[i].getDec();
            }
        }
        return "Star not found";
    }

    /**
     * Returns the number of stars in the catalog.
     *
     * @return the number of stars
     */
    public int size() {
        int size = 0;
        for (int i = 0; i < this.count; i++) {
            if (this.stars[i] != null) {
                size++;
            }
        }
        return size;
    }

    /**
     * Returns a string listing all stars and their coordinates. Returns
     * "Catalog is empty" if the catalog has no stars.
     *
     * @return a string with all stars' names and coordinates
     */
    public String listStars() {
        if (this.count == 0) {
            return "Catalog is empty";
        }
        String result = "";
        for (int i = 0; i < this.count; i++) {
            if (this.stars[i] != null) {
                result += this.stars[i].getName() + ": RA: "
                        + this.stars[i].getRa() + ", Dec: "
                        + this.stars[i].getDec() + "\n";
            }
        }
        return result;
    }

    /**
     * Demonstrates the usage of the StarCatalog by adding, removing, and
     * querying stars.
     *
     * @param args
     *            command-line arguments (not used)
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        StarCatalog catalog = new StarCatalog();
        out.println("Star Catalog Demo:");

        // Adding stars
        catalog.addStar("Sirius", SIRIUS_RA, SIRIUS_DEC);
        catalog.addStar("Vega", VEGA_RA, VEGA_DEC);
        out.println("Added Sirius and Vega");

        // Listing stars
        out.println("\nAll Stars:");
        out.println(catalog.listStars());

        // Checking if a star exists
        out.println("Has Vega? " + catalog.hasStar("Vega"));
        out.println("Has Polaris? " + catalog.hasStar("Polaris"));

        // Getting coordinates
        out.println("\nVega coordinates: " + catalog.getCoordinates("Vega"));

        // Removing a star
        out.println("\nRemoved: " + catalog.removeStar("Sirius"));
        out.println("After removal:");
        out.println(catalog.listStars());

        // Checking size
        out.println("Catalog size: " + catalog.size());

        out.close();
    }
}
