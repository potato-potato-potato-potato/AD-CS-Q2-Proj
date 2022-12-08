import load.Loadable;
import HashTable.*;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.*;

public class World implements Loadable {
    public static final int X_GRID_MAX = 50, Y_GRID_MAX = 50;
    public static final int TILE_SIZE = 64;
    private boolean canEdit = false;

    // this is used to discided which map to draw
    public DLList<MyHashTable<Coordinates, Tile>> map;

    public World() {
        map = new DLList<MyHashTable<Coordinates, Tile>>();
    }

    public boolean place(Tile tile, int layer, int x, int y) {
        MyHashTable<Coordinates, Tile> grid = map.get(layer);
        grid.put(new Coordinates(x, y), tile);
        return true;
    }

    // TODO implement tile groups and tile memeber
    // public boolean place(TileGroup tileGroup, int layer, int x, int y) {
    // for (int xOffset = 0; xOffset < tileGroup.getSize().width; xOffset++) {
    // for (int yOffset = 0; yOffset < tileGroup.getSize().height; yOffset++) {
    // place(tileGroup.getTile(xOffset, yOffset), layer, x + xOffset, y + yOffset);
    // }
    // }

    // return true;
    // }

    public boolean getEditStatus() {
        return canEdit;
    }

    public void setEditStatus(boolean editStatus) {
        this.canEdit = editStatus;
    }

    public static Point getChunkCoordinateFromFreeCoordinate(Point coordinate) {
        int chunkX = (int) (coordinate.getX() / TILE_SIZE);
        int chunkY = (int) (coordinate.getY() / TILE_SIZE);
        return new Point(chunkX, chunkY);
    }

    public static Point getChunkCoordinateFromFreeCoordinate(int x, int y) {
        return getChunkCoordinateFromFreeCoordinate(new Point(x, y));
    }

    public DLList<Tile> getTile(int layer, int x, int y) {
        MyHashTable<Coordinates, Tile> grid = map.get(layer);
        return grid.get(new Coordinates(x, y));
    }

    public DLList<Tile> getTile(int layer, Point chunkCoordinate) {
        return getTile(layer, chunkCoordinate.x, chunkCoordinate.y);
    }

    public boolean getTilePassable(int x, int y, int layer) {
        MyHashTable<Coordinates, Tile> grid = map.get(layer);
        return grid.get(new Coordinates(x, y)).get(0).passable;
    }

    public boolean getTilePassable(Point chunk, int layer) {
        return getTilePassable(chunk.x, chunk.y, layer);
    }

    // TODO implement water before using this
    @Deprecated
    public boolean getTileWater(int x, int y, int layer) {
        return true;
    }

    @Deprecated
    public boolean getTileWater(Point chunk, int layer) {
        return getTileWater(chunk.x, chunk.y, layer);
    }

    public void load() {
        Setup.setupW(this);
    }

}
