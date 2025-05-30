import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SecretAgent here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SecretAgent extends Actor
{
    public SecretAgent() {
        String[] variants = {"agente1.jpeg", "agente2.jpeg", "agente3.jpeg"};
        setImage(new GreenfootImage(variants[Greenfoot.getRandomNumber(variants.length)]));
        getImage().scale(30, 30);
    }

    public void act() {
    if (isTouching(Serpiente.class)) {
        BossWorld bw = (BossWorld) getWorld();
        int centerX = bw.getWidth() / 2;
        int centerY = bw.getHeight() / 2;

        Bullet bullet = new Bullet(getX(), getY(), centerX, centerY);
        bw.addObject(bullet, getX(), getY());

        bw.removeObject(this);
    }
}
}
