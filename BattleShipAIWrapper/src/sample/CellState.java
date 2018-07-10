package sample;

public enum CellState
{
    Empty,  Hit,  Miss;

    private CellState() {}

    public String toString()
    {
        switch (this)
        {
            case Empty:
                return ".";
            case Hit:
                return "X";
            case Miss:
                return "o";
        }
        return "?";
    }
}
