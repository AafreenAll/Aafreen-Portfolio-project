public class StarCatalogSample1 {

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