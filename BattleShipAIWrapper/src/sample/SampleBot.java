package sample;

import java.awt.Point;
import java.util.Random;

public class SampleBot
{
    private int gameSize;

    private BattleShip battleShip;

    private Random random;

    // Used to track where you've already shot
    // Suggested:
    //      0 = No shot
    //      1 = Shot - miss
    //      2 = Shot - hit
    private int[][] enemyBoard;

    // Keeping track of whether the last shot was a hit or miss
    private boolean lastShotHit;

    /**
     * Constructor keeps a copy of the BattleShip instance
     * @param b previously created battleship instance - should be a new game
     */
    public SampleBot(BattleShip b)
    {
        battleShip = b;
        gameSize = b.BOARDSIZE;
        random = new Random();
        enemyBoard = new int[gameSize][gameSize];

        for (int i = 0; i < gameSize; i++)
            for (int j = 0; j < gameSize; j++)
                enemyBoard[i][j] = 0;
    }

    /**
     * Create a random shot and calls the battleship shoot method
     * @return true if a Ship is hit, false otherwise
     */

    public boolean fireShot()
    {
        int x = random.nextInt(gameSize);
        int y = random.nextInt(gameSize);

        while (alreadyBeenShot(x, y)) {
            x = random.nextInt(gameSize);
            y = random.nextInt(gameSize);
        }

        lastShotHit = battleShip.shoot(new Point(x, y));

        if (lastShotHit) {
            enemyBoard[x][y] = 2;
        } else {
            enemyBoard[x][y] = 1;
        }

        return lastShotHit;
    }

    public boolean isValidShot(int x, int y) {
        return (x >= 0 && x < 10 && y >= 0 && y < 10);
    }

    public boolean alreadyBeenShot(int x, int y) {
        if (isValidShot(x, y))
            return enemyBoard[x][y] == 1 || enemyBoard[x][y] == 2;
        return false;
    }
}