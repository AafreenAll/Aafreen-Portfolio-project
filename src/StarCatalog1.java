import components.map.Map;
import components.map.Map1L;

public class StarCatalog1 extends StarCatalogSecondary {

    private Map<String, Star> catalog;

    /**
     * Default constructor.
     * Initializes an empty map.
     */
    public StarCatalog1() {
        this.catalog = new Map1L<>();
    }

    @Override
    public void addStar(Star star) {
        assert star != null : "Violation: star is null";
        this.catalog.add(star.getName(), star);
    }

    @Override
    public Star removeStar(String name) {
        assert this.catalog.hasKey(name) : "Violation: name not found";
        return this.catalog.remove(name);
    }

    @Override
    public String getAnyName() {
        assert this.catalog.size() > 0 : "Violation: catalog is empty";
        return this.catalog.keyIterator().next();
    }

    @Override
    public boolean hasStar(String name) {
        return this.catalog.hasKey(name);
    }

    @Override
    public int size() {
        return this.catalog.size();
    }
}
