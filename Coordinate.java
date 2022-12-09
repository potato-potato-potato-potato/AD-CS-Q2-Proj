import java.awt.Point;

public class Coordinate extends Point {

    public Coordinate(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            Coordinate c = (Coordinate) obj;
            return (c.x == x && c.y == y);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return x * 100 + y;
    }

}
