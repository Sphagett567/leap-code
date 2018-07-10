package sample;

import java.awt.Point;
import java.util.ArrayList;

public final class Ship
{
    private boolean isPlaced = false;
    private Point location;
    private ShipOrientation orientation;
    private int length;

    public Ship(int length)
    {
        if (length <= 1) {
            throw new IllegalArgumentException("Invalid length specified: must be >= 1 ");
        }
        this.length = length;
    }

    public boolean getIsPlaced()
    {
        return this.isPlaced;
    }

    public Point getLocation()
    {
        return this.location;
    }

    public ShipOrientation getOrientation()
    {
        return this.orientation;
    }

    public int getLength()
    {
        return this.length;
    }

    public boolean place(int boardSize, Point location, ShipOrientation orientation, ArrayList<Ship> ships)
    {
        this.location = location;
        this.orientation = orientation;
        this.isPlaced = false;
        if (!isValid(boardSize)) {
            return false;
        }
        if (ships != null) {
            for (Ship s : ships)
            {
                if (s.orientation == ShipOrientation.Horizontal)
                {
                    int y = s.getLocation().y;
                    for (int x = s.getLocation().x; x < s.getLocation().x + s.getLength(); x++) {
                        if (isAt(new Point(x, y))) {
                            return false;
                        }
                    }
                }
                if (s.orientation == ShipOrientation.Vertical)
                {
                    int x = s.getLocation().x;
                    for (int y = s.getLocation().y; y < s.getLocation().y + s.getLength(); y++) {
                        if (isAt(new Point(x, y))) {
                            return false;
                        }
                    }
                }
            }
        }
        this.isPlaced = true;
        return true;
    }

    public boolean isAt(Point p)
    {
        if (getOrientation() == ShipOrientation.Horizontal) {
            return (this.location.y == p.y) && (this.location.x <= p.x) && (this.location.x + this.length > p.x);
        }
        return (this.location.x == p.x) && (this.location.y <= p.y) && (this.location.y + this.length > p.y);
    }

    public boolean isValid(int boardSize)
    {
        if ((this.location.x < 0) || (this.location.y < 0)) {
            return false;
        }
        if (this.orientation == ShipOrientation.Horizontal)
        {
            if ((this.location.y >= boardSize) || (this.location.x + this.length > boardSize)) {
                return false;
            }
        }
        else if ((this.location.x >= boardSize) || (this.location.y + this.length > boardSize)) {
            return false;
        }
        return true;
    }
}

