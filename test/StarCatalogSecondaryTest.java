import static org.junit.Assert;

import org.junit.Test;

public class StarCatalogSecondaryTest {

    private static Star create(String name, double ra, double dec) {
        return new StarCatalogTest.Star1(name, ra, dec);
    }

    private static StarCatalog1 createCatalog() {
        return new StarCatalog1();
    }

    @Test
    public void testGetCoordinatesSimple() {
        StarCatalog1 c = createCatalog();
        c.addStar(create("Sirius", 101.28, -16.72));

        String coords = c.getCoordinates("Sirius");
        assertEquals("RA: 101.28, Dec: -16.72", coords);
    }

    @Test
    public void testGetCoordinatesNegative() {
        StarCatalog1 c = createCatalog();
        c.addStar(create("Vega", -10.5, 20.3));

        String coords = c.getCoordinates("Vega");
        assertEquals("RA: -10.5, Dec: 20.3", coords);
    }

    @Test
    public void testGetCoordinatesDoesNotMutateCatalog() {
        StarCatalog1 c = createCatalog();
        c.addStar(create("Aldebaran", 68.98, 16.51));
        int sizeBefore = c.size();

        c.getCoordinates("Aldebaran");

        assertEquals(sizeBefore, c.size());
    }

    @Test
    public void testListStarsOneStar() {
        StarCatalog1 c = createCatalog();
        c.addStar(create("Sirius", 101, -16));

        String list = c.listStars();
        assertEquals("Sirius\n", list);
    }

    @Test
    public void testListStarsTwoStars() {
        StarCatalog1 c = createCatalog();
        c.addStar(create("Sirius", 101, -16));
        c.addStar(create("Vega", 279, 38));

        String list = c.listStars();

        assertTrue(list.contains("Sirius"));
        assertTrue(list.contains("Vega"));
        assertTrue(list.endsWith("\n"));
    }

    @Test
    public void testListStarsEmpty() {
        StarCatalog1 c = createCatalog();
        assertEquals("", c.listStars());
    }

    @Test
    public void testIsEmptyOnEmptyCatalog() {
        StarCatalog1 c = createCatalog();
        assertTrue(c.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        StarCatalog1 c = createCatalog();
        c.addStar(create("Sirius", 1, 1));
        assertFalse(c.isEmpty());
    }

    @Test
    public void testIsEmptyDoesNotMutateCatalog() {
        StarCatalog1 c = createCatalog();
        c.addStar(create("Sirius", 1, 1));
        int before = c.size();

        c.isEmpty();

        assertEquals(before, c.size());
    }
}