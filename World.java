import load.Loadable;
import HashTable.*;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.*;

import javax.swing.text.Position;

public class World implements Loadable {
    public static final int X_GRID_MAX = 100, Y_GRID_MAX = 100;
    public static final int TILE_SIZE = 64;
    private boolean canEdit = false;

    // this is used to discided which map to draw
    public DLList<MyHashTable<Coordinate, Object>> map;

    public World() {
        map = new DLList<MyHashTable<Coordinate, Object>>();
        map.add(new MyHashTable<Coordinate, Object>());
        map.add(new MyHashTable<Coordinate, Object>());
        map.add(new MyHashTable<Coordinate, Object>());
    }

    public boolean place(Tile tile, int layer, int x, int y) {
        return place(tile, layer, new Coordinate(x, y));
    }

    public boolean place(Tile tile, int layer, Coordinate p) {
        MyHashTable<Coordinate, Object> grid = map.get(layer);
        grid.put(p, tile);
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

    public Tile getTranslatedTile(int layer, int x, int y, int xTranslate, int yTranslate) {
        return getTranslatedTile(layer, new Coordinate(x, y), xTranslate, yTranslate);
    }

    public Tile getTranslatedTile(int layer, Coordinate p, int xTranslate, int yTranslate) {
        MyHashTable<Coordinate, Object> grid = map.get(layer);
        return (Tile) grid.get(new Coordinate(p.x + xTranslate, p.y + yTranslate)).get(1);
    }

    public void recalculateGrassWater(int layer) {
        MyHashTable<Coordinate, Object> grid = map.get(1);
        for (int i = 0; i < X_GRID_MAX; i++) {
            for (int j = 0; j < Y_GRID_MAX; j++) {
                if ((Tile) grid.get(new Coordinate(i, j)).get(1) instanceof GrassWater)
                    place(new Grass(), layer, i, j);
            }
        }

        for (int i = 0; i < X_GRID_MAX; i++) {
            for (int j = 0; j < Y_GRID_MAX; j++) {
                if (!((Tile) grid.get(new Coordinate(i, j)).get(1) instanceof Grass))
                    continue;
                int dir = 0;
                Coordinate p = new Coordinate(i, j);

                if (getTranslatedTile(layer, p, 1, 0).getId() == 1 && getTranslatedTile(layer, p, 0, 1).getId() == 1)
                    dir = 4;
                else if (getTranslatedTile(layer, p, 1, 0).getId() == 1
                        && getTranslatedTile(layer, p, 0, -1).getId() == 1)
                    dir = 7;
                else if (getTranslatedTile(layer, p, -1, 0).getId() == 1
                        && getTranslatedTile(layer, p, 0, 1).getId() == 1)
                    dir = 5;
                else if (getTranslatedTile(layer, p, -1, 0).getId() == 1
                        && getTranslatedTile(layer, p, 0, -1).getId() == 1)
                    dir = 6;
                else if (getTranslatedTile(layer, p, 1, 0).getId() == 1)
                    dir = 1;
                else if (getTranslatedTile(layer, p, 0, 1).getId() == 1)
                    dir = 0;
                else if (getTranslatedTile(layer, p, -1, 0).getId() == 1)
                    dir = 2;
                else if (getTranslatedTile(layer, p, 0, -1).getId() == 1)
                    dir = 3;
                else if (getTranslatedTile(layer, p, -1, -1).getId() == 1)
                    dir = 8;
                else if (getTranslatedTile(layer, p, 1, -1).getId() == 1)
                    dir = 9;
                else if (getTranslatedTile(layer, p, -1, 1).getId() == 1)
                    dir = 10;
                else if (getTranslatedTile(layer, p, 1, 1).getId() == 1)
                    dir = 11;

                else
                    continue;
                place(new GrassWater(dir), layer, i, j);
            }
        }
    }

    public static Coordinate getChunkCoordinateFromFreeCoordinate(Coordinate coordinate) {
        int chunkX = (int) (coordinate.getX() / TILE_SIZE);
        int chunkY = (int) (coordinate.getY() / TILE_SIZE);
        return new Coordinate(chunkX, chunkY);
    }

    public static Coordinate getChunkCoordinateFromFreeCoordinate(int x, int y) {
        return getChunkCoordinateFromFreeCoordinate(new Coordinate(x, y));
    }

    public DLList<Object> getTile(int layer, int x, int y) {
        MyHashTable<Coordinate, Object> grid = map.get(layer);
        return grid.get(new Coordinate(x, y));
    }

    public DLList<Object> getTile(int layer, Coordinate chunkCoordinate) {
        return getTile(layer, chunkCoordinate.x, chunkCoordinate.y);
    }

    public boolean getTilePassable(int x, int y, int layer) {
        MyHashTable<Coordinate, Object> grid = map.get(layer);

        return ((Tile) grid.get(new Coordinate(x, y)).get(0)).passable;
    }

    public boolean getTilePassable(Coordinate chunk, int layer) {
        return getTilePassable(chunk.x, chunk.y, layer);
    }

    // TODO implement water before using this
    @Deprecated
    public boolean getTileWater(int x, int y, int layer) {
        return true;
    }

    @Deprecated
    public boolean getTileWater(Coordinate chunk, int layer) {
        return getTileWater(chunk.x, chunk.y, layer);
    }

    public Point p = new Point(0, 0);

    public void temp(Point temp) {
        p = temp;
        System.out.println(p);
    }

    public void draw(Graphics g) {
        MyHashTable<Coordinate, Object> grid = map.get(1);
        System.out.println(grid);
        for (int i = 0; i < X_GRID_MAX; i++) {
            for (int j = 0; j < Y_GRID_MAX; j++) {
                if (grid.get(new Coordinate(i, j)) != null) {
                    ((Tile) grid.get(new Coordinate(i, j)).get(0)).draw(g, i * TILE_SIZE - p.x, j * TILE_SIZE - p.y);
                }

            }

        }

    }

    public void load() {
        Setup.setupW(this);
    }

}
