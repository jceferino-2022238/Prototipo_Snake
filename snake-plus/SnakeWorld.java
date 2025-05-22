import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    public SnakeWorld()
    {    
        super(WORLD_WIDTH, WORLD_HEIGHT, CELL_SIZE); // 600x400 px, 20 px cell

        setBackground("desierto.jpg");
        drawGrid();
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
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
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
    }
}
