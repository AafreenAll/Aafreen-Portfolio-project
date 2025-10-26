/**
 * {@code StarCatalogKernel} enhanced with secondary methods.
 */
public interface StarCatalog extends StarCatalogKernel {

    /**
     * Returns the coordinates of the star with the given name as a string.
     * 
     * @param name
     *            the name of the star
     * @return a string with the star's coordinates, or "Star not found" if not present
     * @requires
     *   name is not null
     * @ensures
     *   if name is in {s.name | s in this} then
     *     getCoordinates = "RA: " + [s.ra] + ", Dec: " + [s.dec] where s.name = name
     *   else
     *     getCoordinates = "Star not found"
     */
    String getCoordinates(String name);

    /**
     * Returns a string listing all stars and their coordinates.
     * 
     * @return a string with all stars' names and coordinates, or "Catalog is empty" if empty
     * @ensures
     *   if this = {} then
     *     listStars = "Catalog is empty"
     *   else
     *     listStars = [string with each s in this as "s.name: RA: s.ra, Dec: s.dec\n"]
     */
    String listStars();

    /**
     * Reports whether the catalog is empty.
     * 
     * @return true if the catalog is empty, false otherwise
     * @ensures
     *   isEmpty = (this = {})
     */
    boolean isEmpty();
}

