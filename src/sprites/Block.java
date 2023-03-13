package sprites;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import logic.Collidable;
import logic.HitListener;
import logic.HitNotifier;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

/**
 * @author LIOR DRAI
 */
public class Block implements Collidable, Sprite, HitNotifier {

   private final List<HitListener> hitListeners = new ArrayList<>();
    private Rectangle rectangle;
    private Color color;
    private String hits = "X";
    private boolean border = true;

    /**
     * Constructor.
     *
     * @param rectangle the collision rectangle.
     * @param color     the block's color.
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    public void setBorder(boolean b) {
        this.border = b;
    }

    /**
     * @return the color.
     */
    public Color getColor() {
        return color;
    }

    public void setHits(String num) {
        this.hits = num;
    }

    /**
     * Sets the color.
     *
     * @param c a {@link Color}.
     */
    public void setColor(Color c) {
        this.color = c;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    /**
     * Sets the rectangle field.
     *
     * @param rect a {@link Rectangle}.
     */
    public void setCollisionRectangle(Rectangle rect) {
        this.rectangle = rect;
    }

    @Override
   public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        this.notifyHit(hitter);

        if (Math.abs(collisionPoint.getX() - rectangle.getUpperLeft().getX()) < Math.pow(10, -5)
                || (Math.abs(collisionPoint.getX() - rectangle.getUpperLeft().getX() - rectangle.getWidth()))
                < Math.pow(10, -5)) {
            dx *= -1;
        }
        if (Math.abs(collisionPoint.getY() - rectangle.getUpperLeft().getY()) < Math.pow(10, -5)
                || (Math.abs(collisionPoint.getY() - rectangle.getUpperLeft().getY() - rectangle.getHeight()))
                < Math.pow(10, -5)) {
            dy *= -1;
        }

        return new Velocity(dx, dy);

    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle(((int) rectangle.getUpperLeft().getX()), ((int) rectangle.getUpperLeft().getY()),
                ((int) rectangle.getWidth()), ((int) rectangle.getHeight()));
        if (border) {
            surface.setColor(Color.GRAY);
            surface.drawRectangle(((int) rectangle.getUpperLeft().getX()), ((int) rectangle.getUpperLeft().getY()),
                    ((int) rectangle.getWidth()), ((int) rectangle.getHeight()));
        }
    }

    @Override
    public void timePassed() {

    }

    /**
     * Adds the block to the arkanoidGame.arkanoid.collections of collidables and arkanoidGame.arkanoid.sprites in the arkanoidGame.arkanoid.game.
     *
     * @param game a
     */
    public void addToGame(GameLevel game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * removes the block from the arkanoidGame.arkanoid.collections of collidables and arkanoidGame.arkanoid.sprites in the arkanoidGame.arkanoid.game.
     *
     * @param game a
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    // ... implementation

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    // Add hl as a listener to hit events.
    public void addHitListener(HitListener hl){
        this.hitListeners.add(hl);
    }
    // Remove hl from the list of listeners to hit events.
    public void removeHitListener(HitListener hl){
        this.hitListeners.remove(hl);
    }

}