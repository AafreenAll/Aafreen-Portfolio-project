import org.junit.Test;

public class StarCatalogTest {

    /**
     * Creates a catalog for testing.
     */
    private static StarCatalog create() {
        return new StarCatalog();
    }

    private static Star createStar(String n, double ra, double dec) {
        return new StarCatalog.Star1(n, ra, dec);
    }

    // newInstance

    @Test
    public void testNewInstanceEmpty() {
        StarCatalog c = create();
        StarCatalog d = c.newInstance();

        assertNotNull(d);
        assertEquals(0, d.size());
    }

    @Test
    public void testNewInstanceIndependentStructure() {
        StarCatalog c = create();
        c.addStar(createStar("A", 1, 1));

        StarCatalog d = c.newInstance();

        // Modify d it shouldn't affect c
        d.addStar(createStar("B", 2, 2));

        assertTrue(c.hasStar("A"));
        assertFalse(c.hasStar("B"));
    }

    // clear


    @Test
    public void testClearOnEmpty() {
        StarCatalog c = create();
        c.clear();

        assertEquals(0, c.size());
    }

    @Test
    public void testClearNonEmpty() {
        StarCatalog c = create();
        c.addStar(createStar("A", 1, 1));
        c.addStar(createStar("B", 2, 2));

        c.clear();

        assertEquals(0, c.size());
        assertFalse(c.hasStar("A"));
        assertFalse(c.hasStar("B"));
    }

    // transferFrom()

    @Test
    public void testTransferFromSimple() {
        StarCatalog source = create();
        StarCatalog destination = create();

        source.addStar(createStar("A", 1, 1));
        source.addStar(createStar("B", 2, 2));

        destination.transferFrom(source);

        // Destination now contains A and B
        assertEquals(2, destination.size());
        assertTrue(destination.hasStar("A"));
        assertTrue(destination.hasStar("B"));

        // Source must now be empty
        assertEquals(0, source.size());
    }

    @Test
    public void testTransferFromOverridesExisting() {
        StarCatalog source = create();
        StarCatalog destination = create();

        source.addStar(createStar("X", 10, 10));
        destination.addStar(createStar("Y", 20, 20));

        destination.transferFrom(source);

        assertEquals(1, destination.size());
        assertTrue(destination.hasStar("X"));
        assertFalse(destination.hasStar("Y"));

        assertEquals(0, source.size());
    }

    @Test
    public void testTransferFromEmptySource() {
        StarCatalog source = create();
        StarCatalog destination = create();
        destination.addStar(createStar("Z", 5, 5));

        destination.transferFrom(source);
        assertEquals(0, destination.size());
        assertEquals(0, source.size());
    }
}