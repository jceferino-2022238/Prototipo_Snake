import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemigo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemigo extends Actor
{
    private int dx = 0;
    private int dy = 0;
    private int moveDelay = 10;
    private int moveCounter = 0;

    public Enemigo() {

        // Randomize direction: horizontal or vertical
        if (Greenfoot.getRandomNumber(2) == 0) {
            dx = Greenfoot.getRandomNumber(2) == 0 ? 1 : -1;
        } else {
            dy = Greenfoot.getRandomNumber(2) == 0 ? 1 : -1;
        }
    }

    public void act() {
        if (++moveCounter >= moveDelay) {
            moveCounter = 0;
            moveEnemy();
        }
        checkCollision();
    }

    private void moveEnemy() {
        int nextX = getX() + dx;
        int nextY = getY() + dy;

        // Bounce off world borders
        if (nextX <= 0 || nextX >= getWorld().getWidth() - 1) dx *= -1;
        if (nextY <= 0 || nextY >= getWorld().getHeight() - 1) dy *= -1;

        // Bounce off Barril
        Actor barril = getOneObjectAtOffset(dx, dy, Barril.class);
        if (barril != null) {
            dx *= -1;
            dy *= -1;
        }

        setLocation(getX() + dx, getY() + dy);
    }

    private void checkCollision() {
        Actor snake = getOneObjectAtOffset(0, 0, Serpiente.class);
        if (snake != null) {
            Greenfoot.stop(); // Snake dies
        }
    }
}
