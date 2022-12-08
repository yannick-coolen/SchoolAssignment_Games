import java.time.LocalDate;

public class Game {
    private String naam;
    private int releaseJaar;
    private double nieuwprijs;

    public Game(String naam, int releaseJaar, double nieuwprijs) {
        this.naam = naam;
        this.releaseJaar = releaseJaar;
        this.nieuwprijs = nieuwprijs;
    }

    public String getNaam() {
        return naam;
    }

    public double huidigeWaarde() {
        if (LocalDate.now().getYear() - releaseJaar > 0) {
            for (int i = 0; i < LocalDate.now().getYear() - releaseJaar; i++) {
                return nieuwprijs - (nieuwprijs / 100 * 30);
            }
        }
        return nieuwprijs;
    }

    @Override
    public boolean equals(Object andereObject) {
        boolean gelijkeObjecten = false;

        if (andereObject instanceof Game andereGame) {
            if (this.naam.equals(andereGame.naam) &&
                this.releaseJaar == andereGame.releaseJaar &&
                this.nieuwprijs == andereGame.nieuwprijs) {
                gelijkeObjecten = true;
            }
        }
        return gelijkeObjecten;
    }

    @Override
    public String toString() {
        return naam + ", uitgegeven in " + releaseJaar + "; nieuwprijs: \u20AC" +
                String.format("%.2f", nieuwprijs) + " nu voor: \u20AC" +
                String.format("%.2f", huidigeWaarde());
    }
}
