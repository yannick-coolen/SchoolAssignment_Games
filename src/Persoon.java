import java.util.ArrayList;
import java.util.Collections;

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
     * @param game De naam en het huidige waarde van de game.
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
     * @param game De naam en het huidige waarde van de game.
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

    /**
     * Toont of de persoon de game bezit dat hetzelfde naam heeft
     * @param naamVanGame De naam van de game is String waarde
     * @return Toont het resultaat of de persoon de game wel of niet bevat.
     */
    public Game zoekGameOpNaam(String naamVanGame) {
        for (Game mijnGame : mijnGames) {
            if (naamVanGame.equals(mijnGame.getNaam())) {
                return mijnGame;
            }
        }
        return null;
    }

    /**
     * Bij het invoeren van naam en waarde van de game, wordt er gecheckt
     * of deze in bezit is van de persoon.
     * @param teKoop Het object van de ArrayList<Game> waarde
     * @return Als de game niet in bezit is wordt de naam en de waarde van de
     * game getoond en wanneer deze door persoon wel in bezit is wordt er een lege
     * array getoond.
     */
    public ArrayList<Game> bepaalGamesNietInBezit(ArrayList<Game> teKoop) {
        for (Game mijnGame : mijnGames) {
            teKoop.remove(mijnGame);
            if (mijnGames.equals(teKoop)) {
                teKoop.remove(teKoop.get(0));
            }
        }
        return teKoop;
    }

    @Override
    public String toString() {
        StringBuilder sGames = new StringBuilder();

        for (Game mijnGame : mijnGames) {
            sGames.append("\n").append(mijnGame);
        }
        return naam + " heeft een budget van \u20AC" + String.format("%.2f", budget) +
                " en bezit de volgende games:" + sGames ;
    }
}
