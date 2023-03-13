package game;

import biuoop.DrawSurface;
/**
 * @author LIOR DRAI
 */
public interface Animation {
    /**
     * Draws the animation.
     *
     * @param d a draw surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return whether the animation is over.
     */
    boolean shouldStop();
}