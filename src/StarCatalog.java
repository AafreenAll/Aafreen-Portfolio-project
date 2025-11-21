public class StarCatalog extends StarCatalog1 {

    /**
     * Implementation of Star interface.
     */
    public static class Star1 implements Star {
        private final String name;
        private final double ra;
        private final double dec;

        public Star1(String name, double ra, double dec) {
            this.name = name;
            this.ra = ra;
            this.dec = dec;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public double getRa() {
            return this.ra;
        }

        @Override
        public double getDec() {
            return this.dec;
        }
    }
}
