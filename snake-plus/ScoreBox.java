import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Score here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreBox extends Actor {
    private int score = 0;

    public ScoreBox() {
        updateImage();
    }

    public void setScore(int score) {
        this.score = score;
        updateImage();
    }

    private void updateImage() {
        GreenfootImage img = new GreenfootImage(200, 40);
        img.setColor(new Color(0, 0, 0, 160)); // translucent background
        img.fillRect(0, 0, 200, 40);
        img.setColor(Color.WHITE);
        img.setFont(new Font("Arial", true, false, 24));
        img.drawString("Manzanas: " + score, 10, 28);
        setImage(img);
    }
}