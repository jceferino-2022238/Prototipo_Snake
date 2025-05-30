import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Boss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss extends Actor
{
    private GreenfootImage baseImage = new GreenfootImage("jefe.jpeg");
    private GreenfootImage healthBar = new GreenfootImage(100, 10);
    private int currentHP = 5;

    public Boss() {
        baseImage.scale(150, 150); // 5x5 at 30px each
        setImage(baseImage);
    }

    @Override
    protected void addedToWorld(World world) {
        updateLife(currentHP); // Safe to access getWorld() now
    }

    public void updateLife(int hp) {
        this.currentHP = hp;

        if (getWorld() == null) return; // Defensive: don't crash if called too early

        healthBar.clear();
        healthBar.setColor(Color.RED);
        healthBar.fillRect(0, 0, hp * 20, 10); // Simple red bar

        getWorld().showText("Boss HP: " + hp, getWorld().getWidth()/2, 1);
    }
}
