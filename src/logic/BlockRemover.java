package logic;

import game.GameLevel;
import sprites.Ball;
import sprites.Block;
import utils.Counter;

/**
 * @author LIOR DRAI
 */
// a arkanoidGame.arkanoid.logic.logic.BlockRemover is in charge of removing blocks from the arkanoidGame.arkanoid.game, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener {
    private final GameLevel game;
    private final Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param game            a Game.
     * @param remainingBlocks Counting the remaining blocks.
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        beingHit.removeHitListener(this);

        remainingBlocks.decrease(1);
    }
}