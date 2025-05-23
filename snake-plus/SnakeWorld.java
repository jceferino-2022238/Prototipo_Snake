import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SnakeWorld extends World
{

    private static final int CELL_SIZE = 40;
    private static final int WORLD_WIDTH = 15;
    private static final int WORLD_HEIGHT = 10;
    private ScoreBox scoreBox;
    private int applesEaten = 0;
    
    public SnakeWorld()
    {    
        super(WORLD_WIDTH, WORLD_HEIGHT, CELL_SIZE); // 600x400 px, 20 px cell
        scoreBox = new ScoreBox();
        addObject(scoreBox, getWidth() / 2, -3);
        setBackground("desierto.jpg");
        drawGrid();
        spawnApple();
        prepare();
    }

    private void drawGrid() {
        GreenfootImage bg = getBackground();
        bg.setColor(new Color(255, 255, 255, 80)); // translucent white lines

        for (int x = 0; x <= getWidth() * getCellSize(); x += CELL_SIZE) {
            bg.drawLine(x, 0, x, getHeight() * getCellSize());
        }

        for (int y = 0; y <= getHeight() * getCellSize(); y += CELL_SIZE) {
            bg.drawLine(0, y, getWidth() * getCellSize(), y);
        }
    }
    public void appleEaten() {
        applesEaten++;
        
        if (scoreBox != null) {
            scoreBox.setScore(applesEaten);
        }
        
        if (applesEaten % 3 == 0) {
            spawnEnemy();
        }
    }

    private void spawnEnemy() {
        int x = Greenfoot.getRandomNumber(getWidth());
        int y = Greenfoot.getRandomNumber(getHeight());
        addObject(new Enemigo(), x, y);
        Greenfoot.playSound("aparecer.wav"); // Y
    }
    
    public void spawnApple() {
        int x, y;
    
        do {
            x = Greenfoot.getRandomNumber(getWidth());
            y = Greenfoot.getRandomNumber(getHeight());
        } while (!isCellFree(x, y)); // Try again if spot is taken
    
        addObject(new Manzana(), x, y);
    }
    
    private boolean isCellFree(int x, int y) {
        List<Actor> objects = getObjectsAt(x, y, null);
        return objects.isEmpty(); // No actor in that cell
    }
    
    private void prepare()
    {

        Serpiente serpiente = new Serpiente();
        addObject(serpiente,3,4);
        Manzana manzana = new Manzana();
        addObject(manzana,7,1);
        Manzana manzana2 = new Manzana();
        addObject(manzana2,11,6);
        Manzana manzana3 = new Manzana();
        addObject(manzana3,7,7);
        Manzana manzana4 = new Manzana();
        addObject(manzana4,9,3);
        Manzana manzana5 = new Manzana();
        addObject(manzana5,9,5);
        Manzana manzana6 = new Manzana();
        addObject(manzana6,3,9);
        Manzana manzana7 = new Manzana();
        addObject(manzana7,5,4);
        Manzana manzana8 = new Manzana();
        addObject(manzana8,11,1);
        Barril barril = new Barril();
        addObject(barril,9,8);
        Barril barril2 = new Barril();
        addObject(barril2,7,2);
        removeObject(manzana6);
        removeObject(manzana3);
        removeObject(manzana7);
        removeObject(manzana5);
        removeObject(manzana2);
        removeObject(manzana4);
        removeObject(manzana8);
        manzana.setLocation(7,1);
        removeObject(manzana);
    }
}
