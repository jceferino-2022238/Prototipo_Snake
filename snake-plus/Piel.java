import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Piel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Piel extends Actor
{
    /**
     * Act - do whatever the Piel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Piel()
    {
        GreenfootImage image = getImage();
        image.scale(40, 40); // scale to cell size
        setImage(image);  
    }
}
