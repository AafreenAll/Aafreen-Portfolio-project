public class StarCatalogSecondary implements StarCatalog {

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public String getCoordinates(String name) {
        assert name != null : "Violation: name is null";

        // Temporary holding variables
        String result = "Star not found";

        int originalSize = this.size();
        Star found = null;

        // Temporary storage of removed stars
        Star[] stars = new Star[originalSize];
        int index = 0;

        /*
         * Remove everything one by one
         */
        while (this.size() > 0) {
            String n = this.getAnyName();
            Star s = this.removeStar(n);

            if (n.equals(name)) {
                found = s;
            }

            stars[index++] = s;
        }

        /*
         * Restore 
         */
        for (int i = 0; i < originalSize; i++) {
            this.addStar(stars[i]);
        }

        /*
         *  return value
         */
        if (found != null) {
            result = "RA: " + found.getRa() + ", Dec: " + found.getDec();
        }

        return result;
    }

    @Override
    public String listStars() {
        if (this.size() == 0) {
            return "Catalog is empty";
        }

        int originalSize = this.size();
        Star[] stars = new Star[originalSize];
        int index = 0;

        StringBuilder sb = new StringBuilder();

        /*
         * Remove all stars and build output
         */
        while (this.size() > 0) {
            String name = this.getAnyName();
            Star s = this.removeStar(name);

            sb.append(s.getName())
              .append(": RA: ")
              .append(s.getRa())
              .append(", Dec: ")
              .append(s.getDec())
              .append("\n");

            stars[index++] = s;
        }

        /*
         * Restore catalog
         */
        for (int i = 0; i < originalSize; i++) {
            this.addStar(stars[i]);
        }

        return sb.toString();
    }
}
