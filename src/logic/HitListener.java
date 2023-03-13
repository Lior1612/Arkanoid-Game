package logic;

import sprites.Ball;
import sprites.Block;

/**
 * @author LIOR DRAI
 */
public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the arkanoidGame.arkanoid.sprites.sprites.Ball that's doing the hitting.
    void hitEvent(Block beingHit, Ball hitter);
}
