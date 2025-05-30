import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SnakeWorld extends World {

    private static final int CELL_SIZE = 40;
    private static final int WORLD_WIDTH = 15;
    private static final int WORLD_HEIGHT = 10;

    private ScoreBox scoreBox;
    private int applesEaten = 0;
    private int currentRound = 1;
    private int totalRounds = 3;
    private int applesPerRound = 5;
    private boolean randomBarrels = true;
    private boolean gameStarted = false;
    private boolean gameOver = false;
    private int roundDisplayCounter = 0;

    public SnakeWorld() {
        super(WORLD_WIDTH, WORLD_HEIGHT, CELL_SIZE);
        scoreBox = new ScoreBox();
        addObject(scoreBox, getWidth() / 2, -3);
        setBackground("desierto.jpg");
        drawGrid();
    }

    public void act() {
        if (!gameStarted) {
            handleConfigInput();
            return;
        }

        if (roundDisplayCounter > 0) {
            roundDisplayCounter--;
            return; // Delay gameplay while showing "ROUND X"
        }

        if (gameOver) return;

        if (applesEaten >= applesPerRound) {
            nextRound();
        }
    }

    private void drawGrid() {
        GreenfootImage bg = getBackground();
        bg.setColor(new Color(255, 255, 255, 80));

        for (int x = 0; x <= getWidth() * getCellSize(); x += CELL_SIZE) {
            bg.drawLine(x, 0, x, getHeight() * getCellSize());
        }

        for (int y = 0; y <= getHeight() * getCellSize(); y += CELL_SIZE) {
            bg.drawLine(0, y, getWidth() * getCellSize(), y);
        }
    }

    private void showConfigScreen() {
        showText("Rounds: " + totalRounds + " (Q/W)", getWidth() / 2, getHeight() / 2 - 3);
        showText("Apples/Round: " + applesPerRound + " (O/P)", getWidth() / 2, getHeight() / 2 - 2);
        showText("Barrels: " + (randomBarrels ? "ON" : "OFF") + " (B)", getWidth() / 2, getHeight() / 2 - 1);
        showText("Press ENTER to start", getWidth() / 2, getHeight() / 2 + 1);
    }

    private void handleConfigInput() {
        String key = Greenfoot.getKey();
        if (key == null) return;

        switch (key) {
            case "q":
                if (totalRounds > 1) totalRounds--;
                break;
            case "w":
                if (totalRounds < 9) totalRounds++;
                break;
            case "o":
                if (applesPerRound > 1) applesPerRound--;
                break;
            case "p":
                if (applesPerRound < 9) applesPerRound++;
                break;
            case "b":
                randomBarrels = !randomBarrels;
                break;
            case "enter":
                startGame();
                return;
        }

        showConfigScreen();
    }

    private void startGame() {
        gameStarted = true;
        clearText();
        startRound();
    }

    private void startRound() {
        // Reset world
        removeObjects(getObjects(null));

        // Add essential UI
        addObject(scoreBox, getWidth() / 2, -3);
        drawGrid();

        // Reset score
        applesEaten = 0;
        scoreBox.setScore(applesEaten);

        // Add snake
        Serpiente serpiente = new Serpiente();
        addObject(serpiente, getWidth() / 2, getHeight() / 2);

        // Add initial apple
        spawnApple();

        // Barrels
        if (randomBarrels) {
            spawnRandomBarrels();
        }

        // Display round title briefly
        showText("ROUND " + currentRound, getWidth() / 2, getHeight() / 2);
        roundDisplayCounter = 100; // Delay for a moment
    }

    private void nextRound() {
        currentRound++;
        if (currentRound > totalRounds) {
            showEndScreen(true);
            return;
        }

        if (currentRound == totalRounds && totalRounds >= 3) {
            startBossStage(); // special round
        } else {
            startRound();
        }
    }

    private void startBossStage() {
        Greenfoot.setWorld(new BossWorld(this)); // New world with size 30 cell
    }

    public void appleEaten() {
        applesEaten++;
        scoreBox.setScore(applesEaten);

        if (applesEaten < applesPerRound) {
            spawnApple();
        }

        if (applesEaten % 3 == 0) {
            if (Greenfoot.getRandomNumber(2) == 0) {
                spawnEnemy();
            }
        }
    }

    public void spawnApple() {
        int x, y;
        do {
            x = Greenfoot.getRandomNumber(getWidth() - 2) + 1;
            y = Greenfoot.getRandomNumber(getHeight() - 2) + 1;
        } while (!isCellFree(x, y));
        addObject(new Manzana(), x, y);
    }

    private void spawnEnemy() {
        int x = Greenfoot.getRandomNumber(getWidth());
        int y = Greenfoot.getRandomNumber(getHeight());
        addObject(new Enemigo(), x, y);
        Greenfoot.playSound("aparecer.wav");
    }

    private void spawnRandomBarrels() {
        int count = Greenfoot.getRandomNumber(5) + 3;
        for (int i = 0; i < count; i++) {
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            if (isCellFree(x, y)) addObject(new Barril(), x, y);
        }
    }

    private boolean isCellFree(int x, int y) {
        return getObjectsAt(x, y, null).isEmpty();
    }

    public void showEndScreen(boolean win) {
        gameOver = true;
        showText(win ? "YOU WIN!" : "GAME OVER!", getWidth() / 2, getHeight() / 2);
        Greenfoot.stop();
    }

    private void clearText() {
        for (int y = getHeight() / 2 - 3; y <= getHeight() / 2 + 3; y++) {
            showText(null, getWidth() / 2, y);
        }
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
}
