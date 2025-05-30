import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Kamikaze here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Kamikaze extends Actor {
    private int timer = 0;
    private final int SPEED = 1;
    private final int EXPLODE_TIME = 360; // approx 6 seconds at 60 FPS

    public Kamikaze() {
        getImage().scale(40, 40);
    }

    public void act() {
        followSnake();
        timer++;

        if (timer >= EXPLODE_TIME) {
            explode();
            getWorld().removeObject(this);
        }
    }

    private void followSnake() {
        Serpiente snake = (Serpiente) getWorld().getObjects(Serpiente.class).get(0);
        if (snake == null) return;

        int dx = snake.getX() - getX();
        int dy = snake.getY() - getY();

        int moveX = 0;
        int moveY = 0;

        // Move in a single axis toward snake, 1 cell at a time
        if (Math.abs(dx) > Math.abs(dy)) {
            moveX = dx > 0 ? SPEED : -SPEED;
        } else if (dy != 0) {
            moveY = dy > 0 ? SPEED : -SPEED;
        }

        setLocation(getX() + moveX, getY() + moveY);
    }

    private void explode() {
        Greenfoot.playSound("explode.mp3");

        int cx = getX();
        int cy = getY();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int ex = cx + dx;
                int ey = cy + dy;

                if (ex >= 0 && ex < getWorld().getWidth() && ey >= 0 && ey < getWorld().getHeight()) {
                    getWorld().addObject(new Explosion(), ex, ey);
                }
            }
        }
    }
}
