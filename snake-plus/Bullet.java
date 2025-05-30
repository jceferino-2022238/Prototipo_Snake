import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Actor {
    private double dx, dy;

    public Bullet(int startX, int startY, int targetX, int targetY) {
        setImage(new GreenfootImage("bala.png"));
        getImage().scale(10, 10);

        double distX = targetX - startX;
        double distY = targetY - startY;
        double length = Math.sqrt(distX * distX + distY * distY);

        if (length != 0) {
            dx = distX / length;
            dy = distY / length;
        }
    }

    public void act() {
        setLocation(getX() + (int)Math.round(dx), getY() + (int)Math.round(dy));

        if (isTouching(Boss.class)) {
            ((BossWorld) getWorld()).hitBoss();
            getWorld().removeObject(this);
        } else if (getX() <= 0 || getX() >= getWorld().getWidth() - 1 ||
                   getY() <= 0 || getY() >= getWorld().getHeight() - 1) {
            getWorld().removeObject(this);
        }
    }
}
