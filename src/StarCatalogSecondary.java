public abstract class StarCatalogSecondary implements StarCatalogKernel {

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

        // Restore
        for (int i = 0; i < originalSize; i++) {
            this.addStar(temp[i]);
        }

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
