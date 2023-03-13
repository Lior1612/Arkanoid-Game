package logic;

import sprites.Ball;
import sprites.Block;
import utils.Counter;

/**
 * @author LIOR DRAI
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * Constructor.
     *
     * @param scoreCounter the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);

    }
}