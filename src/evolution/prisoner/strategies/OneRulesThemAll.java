package evolution.prisoner.strategies;

import evolution.prisoner.Result;
import evolution.prisoner.Strategy;

public class OneRulesThemAll extends Strategy {

    Result lastMove = null;
    int lastCoops = 0;
    
    @Override
    public String authorName() {
        return "(╯°□°）╯︵ ┻━┻";
    }

    @Override
    public String getName() {
        return "Rainbow hacker will be back!... someday";
    }

    @Override
    public Move nextMove() {
        if (lastMove == null)
            return Move.COOPERATE;
        
        if (lastCoops == 20) {
            lastCoops = 0;
            return Move.COOPERATE;
        }
        
        return lastMove.getOponentsMove();
    }

    @Override
    public void reward(Result res) {
        lastMove = res;
        
        if (lastMove.getOponentsMove() == Move.DECEIVE) {
            lastCoops++;
        }
    }

    @Override
    public void reset() {
        lastMove = null;
        lastCoops = 0;
    }

}
