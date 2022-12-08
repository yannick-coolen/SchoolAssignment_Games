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

    public double getBudget() {
        return budget;
    }

    public boolean koop(Game game) {
        if (getBudget() >= game.huidigeWaarde() && !mijnGames.contains(game)) {
            budget -= game.huidigeWaarde();
            mijnGames.add(game);
            return true;
        }
        return false;
    }

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
