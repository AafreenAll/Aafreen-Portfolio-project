
/**
 * Star catalog kernel component with primary methods for managing a collection of stars.
 * Each star has a name and coordinates (right ascension and declination in degrees).
 * 
 *   0 <= s.ra < 360 and
 *   -90 <= s.dec <= 90
 * 
 * ensures
 * this = {}
 */
public interface StarCatalogKernel extends Standard<StarCatalog> {

    /**
     * Maximum right ascension in degrees.
     */
    static final double MAX_RA = 360.0;

    /**
     * Maximum declination in degrees.
     */
    static final double MAX_DEC = 90.0;

    /**
     * Nested interface representing a star with name and coordinates.
     */
    interface Star {
        /**
         * Returns the name of the star.
         * 
         * @return the star's name
         * @ensures getName = [name of this star]
         */
        String getName();

        /**
         * Returns the right ascension in degrees.
         * 
         * @return the right ascension
         * @ensures getRa = [right ascension of this star]
         */
        double getRa();

        /**
         * Returns the declination in degrees.
         * 
         * @return the declination
         * @ensures getDec = [declination of this star]
         */
        double getDec();
    }

    /**
     * Adds a star to the catalog.
     * 
     * @param star
     *            the star to add
     * @updates this
     * @requires
     *   star.getName() is not empty and
     *   0 <= star.getRa() < MAX_RA and
     *   -MAX_DEC <= star.getDec() <= MAX_DEC and
     *   star.getName() is not in {s.name | s in this}
     * @ensures
     *   this = #this union {star}
     */
    void addStar(Star star);

    /**
 * Returns the name of some star in the catalog.
 * @requires this.size() > 0
 * @ensures getAnyName is in {s.name | s in this}
 */
String getAnyName();

    /**
     * Removes the star with the given name from the catalog and returns it.
     * 
     * @param name
     *            the name of the star to remove
     * @return the removed star
     * @updates this
     * @requires
     *   name is in {s.name | s in this}
     * @ensures
     *   removeStar is in #this and
     *   removeStar.getName() = name and
     *   this = #this \ {removeStar}
     */
    Star removeStar(String name);

    /**
     * Reports whether a star with the given name exists in the catalog.
     * 
     * @param name
     *            the name to check
     * @return true if a star with the given name exists, false otherwise
     * @ensures
     *   hasStar = (name is in {s.name | s in this})
     */
    boolean hasStar(String name);

    /**
     * Returns the number of stars in the catalog.
     * 
     * @return the number of stars
     * @ensures
     *   size = |this|
     */
    int size();
}