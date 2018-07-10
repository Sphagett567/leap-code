package sample;

import java.awt.Point;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

public class BattleShip
{
    private final boolean DEBUGMODE = false;
    public final int BOARDSIZE = 10;
    public final int[] SHIPSIZES = { 2, 3, 3, 4, 5 };
    public final CellState[][] board;
    private final ArrayList<Ship> ships;
    private final ArrayList<Point> hits;
    private final ArrayList<Point> misses;
    private final Random random = new Random();

    public static String version()
    {
        return "Version 1.02 [Nov 26,2014]";
    }

    public BattleShip()
    {
        this.board = new CellState[10][10];
        this.ships = new ArrayList();
        for (int i = 0; i < this.SHIPSIZES.length; i++)
        {
            Ship testShip = new Ship(this.SHIPSIZES[i]);
            while (!testShip.getIsPlaced())
            {
                Point location = new Point(this.random.nextInt(10), this.random.nextInt(10));
                ShipOrientation orientation = ShipOrientation.values()[this.random.nextInt(ShipOrientation.values().length)];
                boolean placed = testShip.place(10, location, orientation, this.ships);
                if (!placed) {}
            }
            this.ships.add(testShip);
        }
        this.hits = new ArrayList();
        this.misses = new ArrayList();
    }

    private boolean shipAt(Point p)
    {
        for (Ship s : this.ships) {
            if (s.isAt(p)) {
                return true;
            }
        }
        return false;
    }

    private void printBoard()
    {
        System.out.print("\n. 0 1 2 3 4 5 6 7 8 9");
        for (int y = 0; y < 10; y++)
        {
            System.out.printf("\n%d", new Object[] { Integer.valueOf(y) });
            for (int x = 0; x < 10; x++) {
                if (this.board[x][y] == CellState.Empty) {
                    System.out.printf(" %c", new Object[] { Character.valueOf('.') });
                } else if (this.board[x][y] == CellState.Hit) {
                    System.out.printf(" %c", new Object[] { Character.valueOf('X') });
                } else if (this.board[x][y] == CellState.Miss) {
                    System.out.printf(" %c", new Object[] { Character.valueOf('o') });
                } else {
                    System.out.printf(" %c", new Object[] { Character.valueOf('?') });
                }
            }
        }
    }

    public boolean shoot(Point shot)
    {
        boolean hit = shipAt(shot);
        if (hit)
        {
            this.board[shot.x][shot.y] = CellState.Hit;
            this.hits.add(shot);
        }
        else
        {
            this.board[shot.x][shot.y] = CellState.Miss;
            this.misses.add(shot);
        }
        return shipAt(shot);
    }

    public int numberOfShipsSunk()
    {
        int num = 0;
        for (Ship s : this.ships)
        {
            int length = s.getLength();
            Point pos = s.getLocation();
            boolean sunk = true;
            if (s.getOrientation() == ShipOrientation.Horizontal) {
                for (int x = 0; x < length; x++) {
                    sunk &= this.board[(pos.x + x)][pos.y] == CellState.Hit;
                }
            } else {
                for (int y = 0; y < length; y++) {
                    sunk &= this.board[pos.x][(pos.y + y)] == CellState.Hit;
                }
            }
            if (sunk) {
                num++;
            }
        }
        return num;
    }

    private int totalShipLengths()
    {
        int length = 0;
        for (Ship s : this.ships) {
            length += s.getLength();
        }
        return length;
    }

    public boolean allSunk()
    {
        int numberOfHitCells = 0;
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                if (this.board[x][y] == CellState.Hit) {
                    numberOfHitCells++;
                }
            }
        }
        return numberOfHitCells == totalShipLengths();
    }

    public int totalShotsTaken()
    {
        return this.hits.size() + this.misses.size();
    }

    public int[] shipSizes()
    {
        return this.SHIPSIZES;
    }
}

