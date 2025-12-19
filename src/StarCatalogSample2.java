/**
 * Sample client program that demonstrates the use of {@code StarCatalog}.
 *
 * <p>
 * This class has multiple {@code StarCatalog} operations, including
 * adding stars, listing contents, creating new instances, transferring
 * ownership of entries, clearing catalogs, and querying star coordinates.
 * </p>
 */
public class StarCatalogSample2 {

    /**
     * Demonstrates basic usage of the {@code StarCatalog} ADT.
     *
     * @param args
     *            command-line arguments (not used)
     */
    public static void main(String[] args) {

        // Create two separate catalogs representing different constellations
        StarCatalog orion = new StarCatalog();
        StarCatalog bigDipper = new StarCatalog();

        // Add stars to Orion
        orion.addStar(new StarCatalog.Star1("Betelgeuse", 88.79, 7.41));
        orion.addStar(new StarCatalog.Star1("Rigel", 78.63, -8.20));
        orion.addStar(new StarCatalog.Star1("Bellatrix", 81.28, 6.35));

        // Add stars to Big Dipper
        bigDipper.addStar(new StarCatalog.Star1("Alioth", 193.51, 55.96));
        bigDipper.addStar(new StarCatalog.Star1("Dubhe", 165.93, 61.75));
        bigDipper.addStar(new StarCatalog.Star1("Merak", 165.46, 56.38));

        System.out.println("=== Orion Constellation ===");
        System.out.println(orion.listStars());

        System.out.println("=== Big Dipper Constellation ===");
        System.out.println(bigDipper.listStars());

        // Clone the Orion catalog before modifying it
        StarCatalog orionCopy = orion.newInstance();

        System.out.println("=== Copy of Orion (using newInstance) ===");
        System.out.println(orionCopy.listStars());

        // Merge the two catalogs into a new catalog
        StarCatalog allStars = new StarCatalog();
        allStars.transferFrom(orion);         // moves Orion stars into allStars
        allStars.transferFrom(bigDipper);     // then moves Big Dipper stars as well

        System.out.println("=== Combined Star Catalog ===");
        System.out.println(allStars.listStars());

        // Now orion and bigDipper should be empty after transferFrom
        System.out.println("Orion size after transferFrom: " + orion.size());
        System.out.println("Big Dipper size after transferFrom: " + bigDipper.size());

        // Clear the Orion copy
        orionCopy.clear();
        System.out.println("Orion copy cleared. Size = " + orionCopy.size());

        // Demonstrate a coordinate lookup in the merged catalog
        System.out.println("Coordinates of Dubhe:");
        System.out.println(allStars.getCoordinates("Dubhe"));
    }
}