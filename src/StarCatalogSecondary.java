/**
 * Secondary implementation of {@code StarCatalogKernel} methods.
 *
 * <p>
 * This abstract class provides implementations for secondary
 * operations using only the kernel methods. 
 * </p>
 *
 * <p>
 * Implements {@code getCoordinates} and {@code listStars} by temporarily
 * removing stars from the kernel, processing them, and restoring the catalog.
 * </p>
 */
public abstract class StarCatalogSecondary implements StarCatalogKernel {

    /**
     * Returns the coordinates of the star with the given name in the form
     * "RA: {ra}, Dec: {dec}".
     *
     * @param name
     *            the name of the star to look up
     * @return a formatted string containing the star's coordinates, or
     *         "Star not found" if the name does not exist in the catalog
     * @requires
     *   name != null and
     *   this.size() > 0
     * @ensures
     *   the catalog remains unchanged and
     *   returns a string describing the coordinates of the star with the given name
     */
    @Override
    public String getCoordinates(String name) {
        assert name != null : "Violation: name is null";
        assert this.size() > 0 : "Violation: empty catalog";

        String result = "Star not found";

        int originalSize = this.size();
        Star[] temp = new Star[originalSize];
        Star found = null;

        int index = 0;

        while (this.size() > 0) {
            String key = this.getAnyName();
            Star s = this.removeStar(key);

            if (s.getName().equals(name)) {
                found = s;
            }

            temp[index] = s;
            index++;
        }

        // Restore catalog
        for (int i = 0; i < originalSize; i++) {
            this.addStar(temp[i]);
        }

        if (found != null) {
            result = "RA: " + found.getRa() + ", Dec: " + found.getDec();
        }

        return result;
    }

    /**
     * Returns a formatted string listing all stars in the catalog with their
     * coordinates. Each line is in the format:
     * "name: RA: {ra}, Dec: {dec}".
     *
     * @return a string listing all stars, or "Catalog is empty" if the catalog
     *         contains no stars
     * @ensures
     *   the catalog remains unchanged and
     *   returns a formatted list of all stars in the catalog
     */
    @Override
    public String listStars() {
        if (this.size() == 0) {
            return "Catalog is empty";
        }

        int originalSize = this.size();
        Star[] temp = new Star[originalSize];

        StringBuilder sb = new StringBuilder();
        int index = 0;

        while (this.size() > 0) {
            String name = this.getAnyName();
            Star s = this.removeStar(name);

            sb.append(s.getName())
              .append(": RA: ")
              .append(s.getRa())
              .append(", Dec: ")
              .append(s.getDec())
              .append("\n");

            temp[index++] = s;
        }

        // Restore catalog
        for (int i = 0; i < originalSize; i++) {
            this.addStar(temp[i]);
        }

        return sb.toString();
    }
}
