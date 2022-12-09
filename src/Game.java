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

    /**
     * Toont de naam van het geïnstantieerde object van de klasse.
     * @return Naam van geïnstantieerde object.
     */
    public String getNaam() {
        return naam;
    }

    /**
     * Berekend het huidige prijs op basis hoe oud het game is.
     * Per jaar wordt moet het huidige prijs worden gereduceerd met 30%.
     * @return Nieuwe prijs op basis van percentage aftrek per jaar.
     */
    public double huidigeWaarde() {
        if (releaseJaar < LocalDate.now().getYear()) {
            return nieuwprijs * Math.pow(0.7,
                    LocalDate.now().getYear() - releaseJaar);
        }
        return nieuwprijs;
    }

    @Override
    public boolean equals(Object andereObject) {
        boolean gelijkeObjecten = false;

        if (andereObject instanceof Game andereGame) {
            if (this.naam.equals(andereGame.naam) &&
                this.releaseJaar == andereGame.releaseJaar) {
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
