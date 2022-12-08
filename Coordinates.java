public class Coordinates {
    public int x;
    public int y;

    // set the x and the y coordinates
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int hashcode() {
        return x * 1000 + y;
    }

    public boolean equals(Object o) {
        if (o instanceof Point) {
            Point p = (Point) o;
            return p.x == x && p.y == y;
        }
        return false;
    }

}
