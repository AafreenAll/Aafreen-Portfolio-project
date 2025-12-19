import components.map.Map;
import components.map.Map1L;

/**
 * {@code StarCatalog} represented using a {@code Map} from star names
 * to {@code Star} objects.
 *
 * <p>
 * This class implements the kernel methods of {@code StarCatalog} by
 * managing storage and sendfing to a {@code Map} whose keys are
 * star names and whose values are the corresponding {@code Star}
 * instances.
 * </p>
 *
 * @convention
 * <pre>
 * catalog != null
 * </pre>
 *
 * @correspondence
 * <pre>
 * this = { (name, star) | catalog maps name to star }
 * </pre>
 */
public class StarCatalog1 extends StarCatalogSecondary {

    /**
     * Map that stores the stars in the catalog, keyed by star name.
     */
    private Map<String, Star> catalog;

    /**
     * Constructs an empty {@code StarCatalog}.
     */
    public StarCatalog1() {
        this.catalog = new Map1L<>();
    }

    /**
     * Adds the given {@code Star} to this catalog.
     *
     * @param star
     *            the {@code Star} to be added
     * @requires
     * <pre>
     * star != null
     * </pre>
     * @ensures
     * <pre>
     * this = #this union {(star.name, star)}
     * </pre>
     */
    @Override
    public void addStar(Star star) {
        assert star != null : "Violation: star is null";
        this.catalog.add(star.getName(), star);
    }

    /**
     * Removes and returns the {@code Star} with the given name from
     * this catalog.
     *
     * @param name
     *            the name of the star to remove
     * @return the {@code Star} associated with {@code name}
     * @requires
     * <pre>
     * this has a star with key = name
     * </pre>
     * @ensures
     * <pre>
     * removeStar = star associated with name and
     * this = #this \ {(name, removeStar)}
     * </pre>
     */
    @Override
    public Star removeStar(String name) {
        assert this.catalog.hasKey(name) : "Violation: name not found";
        return this.catalog.remove(name);
    }

    /**
     * Returns the name of an arbitrary star in this catalog.
     *
     * @return the name of some star in the catalog
     * @requires
     * <pre>
     * this.size() > 0
     * </pre>
     * @ensures
     * <pre>
     * getAnyName is a key in this
     * </pre>
     */
    @Override
    public String getAnyName() {
        assert this.catalog.size() > 0 : "Violation: catalog is empty";
        return this.catalog.keyIterator().next();
    }

    /**
     * Reports whether this catalog contains a star with the given name.
     *
     * @param name
     *            the star name to check
     * @return {@code true} if this catalog contains a star with the given
     *         name; {@code false} otherwise
     * @ensures
     * <pre>
     * hasStar = (name is a key in this)
     * </pre>
     */
    @Override
    public boolean hasStar(String name) {
        return this.catalog.hasKey(name);
    }

    /**
     * Returns the number of stars in this catalog.
     *
     * @return the number of stars in the catalog
     * @ensures
     * <pre>
     * size = |this|
     * </pre>
     */
    @Override
    public int size() {
        return this.catalog.size();
    }
}
