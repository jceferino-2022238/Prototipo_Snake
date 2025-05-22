import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Serpiente here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Serpiente extends Actor
{
    private int dx = 40;
    private int dy = 0;
    private int moveDelay = 10;
    private int moveCounter = 0;

    private List<int[]> tailPositions = new ArrayList<>();
    private List<Piel> tailSegments = new ArrayList<>();
    
    public Serpiente() {
        GreenfootImage image = getImage();
        image.scale(40, 40); // scale to cell size
        setImage(image);
    }

    public void act() {
        handleInput();

        if (++moveCounter >= moveDelay) {
            moveCounter = 0;
            moveSnake();
            checkCollisions();
        }
    }
    private void handleInput() {
        if (Greenfoot.isKeyDown("up") && dy == 0) {
            dx = 0; dy = -40;
        } else if (Greenfoot.isKeyDown("down") && dy == 0) {
            dx = 0; dy = 40;
        } else if (Greenfoot.isKeyDown("left") && dx == 0) {
            dx = -40; dy = 0;
        } else if (Greenfoot.isKeyDown("right") && dx == 0) {
            dx = 40; dy = 0;
        }
    }
    private void moveSnake() {
        // Save current position
        tailPositions.add(0, new int[]{getX(), getY()});

        // Move head
        setLocation(getX() + dx / 40, getY() + dy / 40);

        // Move tail
        for (int i = 0; i < tailSegments.size(); i++) {
            int[] pos = tailPositions.get(i);
            tailSegments.get(i).setLocation(pos[0], pos[1]);
        }

        // Keep positions list trimmed
        while (tailPositions.size() > tailSegments.size() + 1) {
            tailPositions.remove(tailPositions.size() - 1);
        }
    }
    private void checkCollisions() {
        // Check wall collision
        if (getX() <= 0 || getX() >= getWorld().getWidth() - 1 ||
            getY() <= 0 || getY() >= getWorld().getHeight() - 1) {
            Greenfoot.stop();
        }
        if (getOneObjectAtOffset(0, 0, Barril.class) != null) {
            Greenfoot.stop(); // Game over
        }
        // Check self-collision
        for (Piel segment : tailSegments) {
            if (this.getX() == segment.getX() && this.getY() == segment.getY()) {
                Greenfoot.stop();
            }
        }

        // Check for apple
        Actor apple = getOneObjectAtOffset(0, 0, Manzana.class);
        if (apple != null) {
            getWorld().removeObject(apple);
            grow();
            ((SnakeWorld)getWorld()).appleEaten();
        }
    }
    private void grow() {
        Piel newSegment = new Piel();
        getWorld().addObject(newSegment, getX(), getY());
        tailSegments.add(newSegment);
    }
}
