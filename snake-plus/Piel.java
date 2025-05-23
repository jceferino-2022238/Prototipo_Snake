import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Piel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Piel extends Actor
{
    int random = Greenfoot.getRandomNumber(3);
    
    public Piel()
    {
        setRandomImage();
        GreenfootImage image = getImage();
        image.scale(40, 40); // scale to cell size
        setImage(image);  
    }
    private void setRandomImage() {
        int choice = Greenfoot.getRandomNumber(3); // 0 to 3 → 4 options
        switch (choice) {
            case 0:
                setImage("cuerpo_gusano_2.jpg");
                break;
            case 1:
                setImage("franco.jpeg");
                break;
            case 2:
                setImage("altan.png");
                break;
        }
    }
}
