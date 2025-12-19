import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit test suite for {@code StarCatalog1}.
 *
 * <p>
 * These tests verify my kernel methods implemented
 * in {@code StarCatalog1}, including size, membership queries, addition,
 * removal, and interaction between {@code getAnyName} and
 * {@code removeStar}.
 * </p>
 */
public class StarCatalog1Test {

    /**
     * Constructs and returns an empty {@code StarCatalog1}.
     *
     * @return a new, empty {@code StarCatalog1}
     */
    private StarCatalog1 create() {
        return new StarCatalog1();
    }

    /**
     * Constructs and returns a {@code Star} with the given attributes.
     *
     * @param n
     *            the name of the star
     * @param ra
     *            the right ascension of the star
     * @param dec
     *            the declination of the star
     * @return a {@code Star} with the specified name and coordinates
     */
    private StarCatalogKernel.Star s(String n, double ra, double dec) {
        return new StarCatalog.Star1(n, ra, dec);
    }

    /*
     * Tests for size()
     */

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

    /*
     * Tests for hasStar()
     */

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

    /*
     * Tests for addStar()
     */

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

    /*
     * Tests for removeStar()
     */

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

    /*
     * Tests for getAnyName()
     */

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

    /*
     * Tests for interaction between getAnyName() and removeStar()
     */

    @Test
    public void testGetAnyNameRemoveDoesNotCorrupt() {
        StarCatalog1 c = create();
        StarCatalogKernel.Star a = s("A", 1, 1);
        StarCatalogKernel.Star b = s("B", 2, 2);

        c.addStar(a);
        c.addStar(b);

        String any = c.getAnyName();
        StarCatalogKernel.Star removed = c.removeStar(any);

        /*
         * Confirm removed star matches the returned name.
         */
        assertEquals(any, removed.getName());

        /*
         * Confirm the remaining star is still present.
         */
        if (any.equals("A")) {
            assertTrue(c.hasStar("B"));
        } else {
            assertTrue(c.hasStar("A"));
        }

        assertEquals(1, c.size());
    }
}