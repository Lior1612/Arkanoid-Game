package sprites;

import biuoop.KeyboardSensor;
import configuration.Config;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import logic.Collidable;

import java.awt.Color;

/**
 * @author LIOR DRAI
 */
public class Paddle extends Block implements Sprite, Collidable {
    private final KeyboardSensor keyboard;
    private final int speed;

    /**
     * Constructor.
     *
     * @param keyboard  a keyboard from a {@code GUI}.
     * @param rectangle the rectangle.
     * @param color     the paddle's color.
     * @param speed     the paddle's speed.
     */
    public Paddle(KeyboardSensor keyboard, Rectangle rectangle, Color color, int speed) {
        super(rectangle, color);
        this.keyboard = keyboard;
        this.speed = speed;
    }

    /**
     * Moving the paddle one step right.
     */
    public void moveRight() {
        setCollisionRectangle(new Rectangle(
                Math.min(getCollisionRectangle().getUpperLeft().getX() + speed,
                        Config.WIN_WIDTH - Config.WALLS_SIZE - getCollisionRectangle().getWidth()),
                getCollisionRectangle().getUpperLeft().getY(),
                getCollisionRectangle().getWidth(),
                getCollisionRectangle().getHeight()));
    }

    /**
     * Moving the paddle one step left.
     */
    public void moveLeft() {
        setCollisionRectangle(
                new Rectangle(Math.max(getCollisionRectangle().getUpperLeft().getX() - speed, Config.WALLS_SIZE),
                        getCollisionRectangle().getUpperLeft().getY(),
                        getCollisionRectangle().getWidth(),
                        getCollisionRectangle().getHeight()));
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }

        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));

        double x = collisionPoint.getX();

        if (x <= getCollisionRectangle().getUpperLeft().getX() + getCollisionRectangle().getWidth() / 5) {
            return Velocity.fromAngleAndSpeed(300, speed);
        }
        if (x <= getCollisionRectangle().getUpperLeft().getX() + 2 * getCollisionRectangle().getWidth() / 5) {
            return Velocity.fromAngleAndSpeed(330, speed);
        }
        if (x >= getCollisionRectangle().getUpperLeft().getX() + 4 * getCollisionRectangle().getWidth() / 5) {
            return Velocity.fromAngleAndSpeed(60, speed);
        }
        if (x >= getCollisionRectangle().getUpperLeft().getX() + 3 * getCollisionRectangle().getWidth() / 5) {
            return Velocity.fromAngleAndSpeed(30, speed);
        }

        return Velocity.fromAngleAndSpeed(0, speed);
    }

}