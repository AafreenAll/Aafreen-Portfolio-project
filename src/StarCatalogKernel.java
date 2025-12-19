import components.standard.Standard;

/**
 * Kernel interface for a star catalog, providing primary methods
 * for managing a collection of stars.
 *
 * <p>
 * Each star has a name and coordinates (right ascension and declination in degrees):
 * <ul>
 *   <li>0 <= star.ra < 360</li>
 *   <li>-90 <= star.dec <= 90</li>
 * </ul>
 * </p>
 *
 * <p>
 * This interface specifies the kernel operations only; secondary methods
 * are implemented elsewhere.
 * </p>
 *
 * @convention
 * <pre>
 * All star names in the catalog are unique
 * All stars have valid coordinates: 0 <= ra < MAX_RA and -MAX_DEC <= dec <= MAX_DEC
 * </pre>
 *
 * @correspondence
 * <pre>
 * this = {s | s is a star stored in the catalog}
 * </pre>
 *
 * @ensures
 *   this = {}   (initially empty)
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
         * @ensures getName() = [name of this star]
         */
        String getName();

        /**
         * Returns the right ascension in degrees.
         * 
         * @return the right ascension
         * @ensures getRa() = [right ascension of this star]
         */
        double getRa();

        /**
         * Returns the declination in degrees.
         * 
         * @return the declination
         * @ensures getDec() = [declination of this star]
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
     *   hasStar() = (name is in {s.name | s in this})
     */
    boolean hasStar(String name);

    /**
     * Returns the number of stars in the catalog.
     * 
     * @return the number of stars
     * @ensures
     *   size() = |this|
     */
    int size();
}
