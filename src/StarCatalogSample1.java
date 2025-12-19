/**
 * Sample client program that represents a single constellation using a
 * {@code StarCatalog}.
 *
 * <p>
 * In this program, the {@code StarCatalog} is used to model one constellation
 * by storing the stars that belong to it. The sample demonstrates adding
 * stars to the constellation, listing its stars, querying coordinates of
 * individual stars, removing a star, and checking whether a star is part
 * of the constellation.
 * </p>
 */
public class StarCatalogSample1 {

    /**
     * Demonstrates basic usage of {@code StarCatalog} to model a constellation.
     *
     * @param args
     *            command-line arguments (not used)
     */
    public static void main(String[] args) {

        // Create a new star catalog
        StarCatalog catalog = new StarCatalog();

        // Add stars
        catalog.addStar(new StarCatalog.Star1("Sirius", 101.28, -16.72));
        catalog.addStar(new StarCatalog.Star1("Vega", 279.23, 38.78));
        catalog.addStar(new StarCatalog.Star1("Betelgeuse", 88.79, 7.41));

        // List all stars
        System.out.println("Stars in the catalog:");
        System.out.println(catalog.listStars());

        // Query coordinates
        System.out.println("Coordinates of Vega:");
        System.out.println(catalog.getCoordinates("Vega"));

        // Remove a star
        System.out.println("Removing Sirius...");
        catalog.removeStar("Sirius");

        // Check if it's removed
        System.out.println("Has Sirius? " + catalog.hasStar("Sirius"));

        // Final catalog list
        System.out.println("\nFinal catalog:");
        System.out.println(catalog.listStars());
    }
}