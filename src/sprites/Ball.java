package sprites;

import biuoop.DrawSurface;
import collections.GameEnvironment;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;
import logic.CollisionInfo;

import java.awt.Color;

/**
 * @author LIOR DRAI
 *
 */
public class Ball implements Sprite {
    private final int size;
    private final Color color;
    private Point point;
    private Velocity velocity = new Velocity(0, 0);
    private GameEnvironment gameEnvironment;

    /**
     * Constructor.
     *
     * @param point the center of the ball.
     * @param r     the size of the ball.
     * @param color the color of the ball.
     */
    public Ball(Point point, int r, Color color) {
        this.point = point;
        this.size = r;
        this.color = color;
    }

    /**
     * Constructor that works just like {@link Ball#Ball(Point, int, Color)}
     * but receives the coordinates of the center.
     *
     * @param x     the center's x coordinate.
     * @param y     the center's y coordinate.
     * @param r     the size of the ball.
     * @param color the color of the ball.
     */
    public Ball(double x, double y, int r, Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * @return the ball center's x coordinate.
     */
    public int getX() {
        return ((int) point.getX());
    }

    /**
     * @return the ball center's y coordinate.
     */
    public int getY() {
        return ((int) point.getY());
    }

    /**
     * @return the ball's color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return the ball's velocity.
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * Updates the ball's velocity.
     *
     * @param v the new velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }


    /**
     * Sets the arkanoidGame.arkanoid.game environment.
     *
     * @param environment arkanoidGame.arkanoid.collections.collections.GameEnvironment.
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    /**
     * Draw the ball on the given DrawSurface.
     *
     * @param surface a surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(getX(), getY(), size);
    }

    public void timePassed() {
        moveOneStep();
    }

    /**
     * Moving the point one step according to the velocity.
     * If the ball hits a collidable, changing the velocity.
     */
    public void moveOneStep() {
        Line trajectory = trajectory();
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory.extendBy(size));

        if (collisionInfo == null) {
            this.point = trajectory.end();
        } else {

            this.velocity = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), velocity);

            while (true) {
                CollisionInfo futureCollisionInfo = gameEnvironment.getClosestCollision(trajectory().extendBy(size));

                if ((futureCollisionInfo == null)
                        || (futureCollisionInfo.collisionObject() != collisionInfo.collisionObject())) {
                    break;
                }

                this.point = trajectory().end();
            }
        }
    }

    private Line trajectory() {
        return new Line(point, velocity.applyToPoint(point));
    }

    /**
     * Adds the ball to the arkanoidGame.arkanoid.sprites collection in the arkanoidGame.arkanoid.game.
     *
     * @param game a arkanoidGame.arkanoid.game.game.Game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}