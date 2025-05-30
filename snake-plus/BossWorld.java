import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BossWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BossWorld extends World
{

   private static final int CELL_SIZE = 30;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 15;

    private Boss boss;
    private ScoreBox scoreBox;
    private int bossHP = 5;
    private int agentsUsed = 0;
    private boolean agentActive = false;
    private int spawnCounter = 0;
    private SnakeWorld parent;

    public BossWorld(SnakeWorld parent) {
        super(WIDTH, HEIGHT, CELL_SIZE);
        this.parent = parent;
        setBackground("desierto.jpg");
        prepare();
    }

    public void act() {
        spawnCounter++;
        if (spawnCounter >= 100) {
            spawnExplosion();
            spawnCounter = 0;
        }

        if (bossHP <= 0) {
            parent.showEndScreen(true);
            Greenfoot.setWorld(parent); // Optional return
        }
    }

    private void prepare() {
        drawGrid();

        // Add Score UI
        scoreBox = new ScoreBox();
        addObject(scoreBox, getWidth()/2, -3);

        // Add Snake away from center
        Serpiente snake = new Serpiente();
        addObject(snake, 3, getHeight()/2);

        // Add Boss at center (5x5)
        boss = new Boss();
        addObject(boss, getWidth()/2, getHeight()/2);

        // Spawn Secret Agents
        spawnSecretAgent();
    }

    private void drawGrid() {
        GreenfootImage bg = getBackground();
        bg.setColor(new Color(255, 255, 255, 60));
        for (int x = 0; x <= getWidth() * getCellSize(); x += CELL_SIZE) {
            bg.drawLine(x, 0, x, getHeight() * getCellSize());
        }
        for (int y = 0; y <= getHeight() * getCellSize(); y += CELL_SIZE) {
            bg.drawLine(0, y, getWidth() * getCellSize(), y);
        }
    }

    private void spawnExplosion() {
        int x, y;
        do {
            x = Greenfoot.getRandomNumber(getWidth());
            y = Greenfoot.getRandomNumber(getHeight());
        } while (Math.abs(x - getWidth()/2) <= 2 && Math.abs(y - getHeight()/2) <= 2); // Avoid boss area

        Explosion e = new Explosion();
        addObject(e, x, y);
        Greenfoot.playSound("TNT.mp3");
    }

    public void hitBoss() {
        bossHP--;
        boss.updateLife(bossHP);
        agentsUsed++;
        agentActive = false;
    
        if (bossHP > 0 && agentsUsed < 5) {
            spawnSecretAgent();
        }
    }

    private void spawnSecretAgent() {
        if (agentActive) return;
    
        int x, y;
        do {
            x = Greenfoot.getRandomNumber(getWidth() - 2) + 1; // Avoid borders
            y = Greenfoot.getRandomNumber(getHeight() - 2) + 1;
        } while (
            !getObjectsAt(x, y, null).isEmpty() ||
            (Math.abs(x - getWidth() / 2) <= 2 && Math.abs(y - getHeight() / 2) <= 2)
        );
    
        addObject(new SecretAgent(), x, y);
        agentActive = true;
    }
}
