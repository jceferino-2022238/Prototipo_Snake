import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Explosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Explosion extends Actor
{
    private int life = 30;

    public Explosion() {
        GreenfootImage img = new GreenfootImage("explosion.jpg");
        img.scale(90, 90); // 3x3 cell size (30px)
        setImage(img);
    }

    public void act() {
        life--;
        if (life <= 0) {
            getWorld().removeObject(this);
            return;
        }

        if (isTouching(Serpiente.class)) {
            Greenfoot.stop(); // Snake dies
        }
    }
}
