import java.util.ArrayList;

public class Persoon {
    private String naam;
    private double budget;
    private ArrayList<Game> mijnGames;

    public Persoon(String naam, double budget) {
        this.naam = naam;
        this.budget = budget;
        this.mijnGames = new ArrayList<Game>();
    }

    /**
     * Toont het budget van wat er binnen de parameter van de geïnstantieerde.
     * Object van Class is ingevoerd.
     * @return Huidige budget.
     */
    public double getBudget() {
        return budget;
    }

    /**
     * Biedt de mogelijkheid om te kunnen zien of de game wel of niet is verkocht aan persoon.<br><br>
     * Als het budget van persoon hoger is dan de huidige waarde van de game en de persoon heeft
     * dat game nog niet tot zijn bezit, dan wordt deze aan de persoon verkocht waardoor ook automatisch
     * deze aan het lijst mijnGames wordt toegevoegd en de waarde van budget van persoon waarde afgetrokken
     * met het huidige budget van de game waardoor er een nieuwe waarde in het budget wordt gezet.
     * @param game Huidige waarde van game.
     * @return true als de game wordt verkocht, anders false.
     */
    public boolean koop(Game game) {
        if (getBudget() >= game.huidigeWaarde() && !mijnGames.contains(game)) {
            budget -= game.huidigeWaarde();
            mijnGames.add(game);
            return true;
        }
        return false;
    }

    /**
     * Biedt de mogelijkheid om te kunnen zien dat het game aan een andere persoon wordt verkocht.<br><br>
     * Als eerste persoon de game bevat en de tweede persoon deze niet in bezit heeft en voldoende budget
     * heeft deze te kopen, wordt de game van eerste persoon verkocht aan het tweede persoon waardoor
     * het budget van de eerste persoon stijgt en die van de tweede persoon daalt in huidige waarde.
     * @param game De game zelf, naam en het huidige waarde.
     * @param koper Andere persoon.
     * @return true als de game wordt verkocht, anders false.
     */
    public boolean verkoop(Game game, Persoon koper) {
        if (mijnGames.contains(game)
                && koper.getBudget() >= game.huidigeWaarde()
                && !koper.mijnGames.contains(game)) {
            mijnGames.remove(game);
            koper.budget -= game.huidigeWaarde();
            budget += game.huidigeWaarde();
            koper.mijnGames.add(game);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sGames= new StringBuilder();

        for (Game mijnGame : mijnGames) {
            sGames.append("\n").append(mijnGame);
        }

        return naam + " heeft een budget van \u20AC" + String.format("%.2f", budget) +
                " en bezit de volgende games:" + sGames ;
    }
}
