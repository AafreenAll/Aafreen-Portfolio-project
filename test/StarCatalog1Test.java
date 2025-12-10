import static org.junit.Assert;

import org.junit.Test;

public class StarCatalog1Test {

    /**
     * Creates an empty catalog.
     */
    private StarCatalog1 create() {
        return new StarCatalog1();
    }

    /**
     * Creates a star.
     */
    private StarCatalogKernel.Star s(String n, double ra, double dec) {
        return new StarCatalog.Star1(n, ra, dec);
    }

    // size()

    @Test
    public void testSizeEmpty() {
        StarCatalog1 c = create();
        assertEquals(0, c.size());
    }

    @Test
    public void testSizeAfterAdd() {
        StarCatalog1 c = create();
        c.addStar(s("A", 1, 1));
        c.addStar(s("B", 2, 2));
        assertEquals(2, c.size());
    }

    // hasStar()

    @Test
    public void testHasStarEmpty() {
        StarCatalog1 c = create();
        assertFalse(c.hasStar("A"));
    }

    @Test
    public void testHasStarAfterAdd() {
        StarCatalog1 c = create();
        c.addStar(s("A", 1, 1));
        assertTrue(c.hasStar("A"));
    }

    @Test
    public void testHasStarAfterRemove() {
        StarCatalog1 c = create();
        c.addStar(s("A", 1, 1));
        c.removeStar("A");
        assertFalse(c.hasStar("A"));
    }

    // addStar()

    @Test
    public void testAddSingle() {
        StarCatalog1 c = create();
        StarCatalogKernel.Star a = s("A", 1, 1);

        c.addStar(a);
        assertTrue(c.hasStar("A"));
        assertEquals(1, c.size());
    }

    @Test
    public void testAddTwo() {
        StarCatalog1 c = create();
        c.addStar(s("A", 1, 1));
        c.addStar(s("B", 2, 2));
        assertTrue(c.hasStar("A"));
        assertTrue(c.hasStar("B"));
        assertEquals(2, c.size());
    }

    // removeStar()

    @Test
    public void testRemoveSingle() {
        StarCatalog1 c = create();
        StarCatalogKernel.Star a = s("A", 1, 1);
        c.addStar(a);

        StarCatalogKernel.Star removed = c.removeStar("A");

        assertEquals("A", removed.getName());
        assertEquals(0, c.size());
        assertFalse(c.hasStar("A"));
    }

    @Test
    public void testRemoveOneOfTwo() {
        StarCatalog1 c = create();
        StarCatalogKernel.Star a = s("A", 1, 1);
        StarCatalogKernel.Star b = s("B", 2, 2);

        c.addStar(a);
        c.addStar(b);

        c.removeStar("A");

        assertFalse(c.hasStar("A"));
        assertTrue(c.hasStar("B"));
        assertEquals(1, c.size());
    }

    // getAnyName()

    @Test
    public void testGetAnyNameSingle() {
        StarCatalog1 c = create();
        c.addStar(s("A", 1, 1));

        String name = c.getAnyName();

        assertEquals("A", name);
    }

    @Test
    public void testGetAnyNameMultiple() {
        StarCatalog1 c = create();
        c.addStar(s("A", 1, 1));
        c.addStar(s("B", 2, 2));

        String name = c.getAnyName();

        assertTrue(name.equals("A") || name.equals("B"));
    }

    // getAnyName + removeStar interaction 

    @Test
    public void testGetAnyNameRemoveDoesNotCorrupt() {
        StarCatalog1 c = create();
        StarCatalogKernel.Star a = s("A", 1, 1);
        StarCatalogKernel.Star b = s("B", 2, 2);

        c.addStar(a);
        c.addStar(b);

        String any = c.getAnyName();
        StarCatalogKernel.Star removed = c.removeStar(any);

        // Confirm removed star matches the name returned
        assertEquals(any, removed.getName());

        // Confirm the other star is still present
        if (any.equals("A")) {
            assertTrue(c.hasStar("B"));
        } else {
            assertTrue(c.hasStar("A"));
        }

        assertEquals(1, c.size());
    }
}