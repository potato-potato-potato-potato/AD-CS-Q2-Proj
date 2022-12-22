// def
// levle means the index on my arraylist
// levle 0: tile
// levle 1: map based tile object(trees and stuff)
// levle 2: map based entity like conines
// levle 3: entity like player and NPC

//layer means the index on the current map 
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
    private Coordinate player;
    public static final int TILE = 0, MAP_TILE = 1, MAP_ENTITY = 2, ENTITY = 3, CHASE = 4;
    public CoinCounter coinCounter;
    public Coordinate chase = new Coordinate(0, 0);
    // this is used to discided which map to draw
    public static MyHashTable<Coordinate, Object> map;

    public World() {
        map = new MyHashTable<Coordinate, Object>();
        coinCounter = new CoinCounter();
    }

    public boolean place(Tile tile, int x, int y) {
        return place(tile, new Coordinate(x, y));
    }

    public boolean place(Tile tile, Coordinate p) {
        MyHashTable<Coordinate, Object> grid = map;
        grid.put(p, tile, World.TILE);
        return true;
    }

    public boolean placeTileEntity(TileEntity te, int x, int y) {
        return placeTileEntity(te, new Coordinate(x, y));
    }

    public boolean placeTileEntity(TileEntity te, Coordinate p) {
        MyHashTable<Coordinate, Object> grid = map;
        grid.put(p, te, World.MAP_ENTITY);
        return true;
    }

    public boolean placeL(int levle, Object obj) {
        MyHashTable<Coordinate, Object> grid = map;
        for (int i = 0; i < X_GRID_MAX; i++) {
            for (int j = 0; j < Y_GRID_MAX; j++) {
                grid.put(new Coordinate(i, j), obj, levle);
            }
        }
        return true;
    }

    public void placeEntity(int x, int y, int levle, Object entity) {
        placeEntity(new Coordinate(x, y), levle, entity);
    }

    public void placeEntity(Coordinate pos, int index, Object entity) {
        MyHashTable<Coordinate, Object> grid = map;
        grid.put(pos, entity, index);
        System.out.println(
                "placed entity" + entity + " at index: " + index + " at pos: " + pos);

    }

    // TODO implement tile groups and tile memeber
    // public boolean place(TileGroup tileGroup, int x, int y) {a
    // for (int xOffset = 0; xOffset < tileGroup.getSize().width; xOffset++) {
    // for (int yOffset = 0; yOffset < tileGroup.getSize().height; yOffset++) {
    // place(tileGroup.getTile(xOffset, yOffset), x + xOffset, y + yOffset);
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

    public Tile getTranslatedTile(int x, int y, int xTranslate, int yTranslate) {
        return getTranslatedTile(new Coordinate(x, y), xTranslate, yTranslate);
    }

    public Tile getTranslatedTile(Coordinate p, int xTranslate, int yTranslate) {
        MyHashTable<Coordinate, Object> grid = map;
        // System.out.println("x: " + p.x + " y: " + p.y + " xT: " + xTranslate + " yT:
        // " + yTranslate);
        return (Tile) grid.get(new Coordinate(p.x + xTranslate, p.y + yTranslate)).get(World.TILE);
    }

    public void recalculateGrassWater() {
        System.out.println("recalculating grass water");
        MyHashTable<Coordinate, Object> grid = map;
        for (int i = 0; i < X_GRID_MAX; i++) {
            for (int j = 0; j < Y_GRID_MAX; j++) {
                if ((Tile) grid.get(new Coordinate(i, j)).get(World.TILE) instanceof GrassWater)
                    place(new Grass(), i, j);
            }
        }

        for (int i = 2; i < X_GRID_MAX; i++) {
            for (int j = 2; j < Y_GRID_MAX; j++) {
                if (!((Tile) grid.get(new Coordinate(i, j)).get(World.TILE) instanceof Grass))
                    continue;
                // System.out.println("grass found at " + i + " " + j);
                int dir = 0;
                Coordinate p = new Coordinate(i, j);
                // System.out.println("grass found at " + p.x + " " + p.y);

                if (getTranslatedTile(p, 1, 0).getId() == 1 && getTranslatedTile(p, 0, 1).getId() == 1)
                    dir = 4;
                else if (getTranslatedTile(p, 1, 0).getId() == 1
                        && getTranslatedTile(p, 0, -1).getId() == 1)
                    dir = 7;
                else if (getTranslatedTile(p, -1, 0).getId() == 1
                        && getTranslatedTile(p, 0, 1).getId() == 1)
                    dir = 5;
                else if (getTranslatedTile(p, -1, 0).getId() == 1
                        && getTranslatedTile(p, 0, -1).getId() == 1)
                    dir = 6;
                else if (getTranslatedTile(p, 1, 0).getId() == 1)
                    dir = 1;
                else if (getTranslatedTile(p, 0, 1).getId() == 1)
                    dir = 0;
                else if (getTranslatedTile(p, -1, 0).getId() == 1)
                    dir = 2;
                else if (getTranslatedTile(p, 0, -1).getId() == 1)
                    dir = 3;
                else if (getTranslatedTile(p, -1, -1).getId() == 1)
                    dir = 8;
                else if (getTranslatedTile(p, 1, -1).getId() == 1)
                    dir = 9;
                else if (getTranslatedTile(p, -1, 1).getId() == 1)
                    dir = 10;
                else if (getTranslatedTile(p, 1, 1).getId() == 1)
                    dir = 11;

                else
                    continue;
                place(new GrassWater(dir), i, j);
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

    public MyArrayList<Object> getTile(int x, int y) {
        MyHashTable<Coordinate, Object> grid = map;
        return grid.get(new Coordinate(x, y));
    }

    public MyArrayList<Object> getTile(Coordinate chunkCoordinate) {
        return getTile(chunkCoordinate.x, chunkCoordinate.y);
    }

    public boolean getTilePassable(int x, int y) {
        MyHashTable<Coordinate, Object> grid = map;
        if (grid.get(new Coordinate(x, y)) == null) {
        }
        return ((Tile) grid.get(new Coordinate(x, y)).get(0)).passable;
    }

    public boolean getTilePassable(Coordinate chunk) {
        return getTilePassable(chunk.x, chunk.y);
    }

    public MyArrayList<Object> getAdjacentTile(int direction, int x, int y) {
        return getAdjacentTile(direction, new Coordinate(x, y));
    }

    // documentation
    // 0 = right
    // 1 = left
    // 2 = up
    // 3 = down
    // 4 = none

    public MyArrayList<Object> getAdjacentTile(int direction, Coordinate pos) {
        switch (direction) {
            case 0:
                System.out.println(pos.x + 1 + " " + pos.y);
                return getTile(pos.x + 1, pos.y);
            case 1:
                System.out.println(pos.x - 1 + " " + pos.y);
                return getTile(pos.x - 1, pos.y);
            case 2:
                System.out.println(pos.x + " " + (pos.y - 1));
                return getTile(pos.x, pos.y - 1);
            case 3:
                System.out.println(pos.x + " " + (pos.y + 1));
                return getTile(pos.x, pos.y + 1);
            default:
                return null;
        }
    }

    public void entityMove(int direction, int x, int y) {
        entityMove(direction, new Coordinate(x, y));
    }

    public void setChaseLocation(Coordinate to) {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++=");
        MyArrayList<Object> current = getTile(chase);
        MyArrayList<Object> tile = getTile(to);

        if (tile.get(World.ENTITY) instanceof Player) {
            Screen.gameOver = true;
        }
        tile.set(World.CHASE, current.get(World.CHASE));
        current.remove(World.CHASE);

        chase = to;
    }

    public void entityMove(int direction, Coordinate pos) {
        MyArrayList<Object> tile = getAdjacentTile(direction, pos);
        // System.out.println(pos + " " + tile);
        Tile t = (Tile) tile.get(World.TILE);
        if (t.passable) {
            MyArrayList<Object> movingToTile = getTile(pos);
            if (tile.get(World.MAP_ENTITY) != null && movingToTile.get(World.ENTITY) instanceof Player) {
                tile.remove(World.MAP_ENTITY);
                ((Player) movingToTile.get(World.ENTITY)).addCoinage(1);
            }
            tile.set(World.ENTITY, movingToTile.get(World.ENTITY));
            movingToTile.remove(World.ENTITY);
            System.out.println(pos + " " + movingToTile);
            System.out.println(tile);

        }
    }

    public void entityinteract(int x, int y) {
        entityinteract(new Coordinate(x, y));
    }

    public void entityinteract(Coordinate pos) {
        System.out.println("intact at: " + pos + " dir: " + Player.direction + "");
        Entity e;
        if (Player.direction == 1 && getAdjacentTile(2, pos).get(World.ENTITY) != null) {
            e = (Entity) getAdjacentTile(2, pos).get(World.ENTITY);
        } else if (Player.direction == 2 && getAdjacentTile(3, pos).get(World.ENTITY) != null) {
            e = (Entity) getAdjacentTile(3, pos).get(World.ENTITY);
        } else if (Player.direction == 3 && getAdjacentTile(1, pos).get(World.ENTITY) != null) {
            e = (Entity) getAdjacentTile(1, pos).get(World.ENTITY);
        } else if (Player.direction == 4 && getAdjacentTile(0, pos).get(World.ENTITY) != null) {
            e = (Entity) getAdjacentTile(0, pos).get(World.ENTITY);
        } else {
            return;
        }
        e.interact();

    }

    // TODO implement water before using this
    @Deprecated
    public boolean getTileWater(int x, int y) {
        return true;
    }

    @Deprecated
    public boolean getTileWater(Coordinate chunk) {
        return getTileWater(chunk.x, chunk.y);
    }

    public Coordinate getPlayerLocation() {
        return new Coordinate(5, 5);
    }

    public static Point playerPos = new Point(0, 0);

    public void playerPos(Point playerPos) {
        this.playerPos = playerPos;
        // System.out.println(p);
    }

    public void draw(Graphics g) {
        drawLand(g);
        drawEntity(g);
        drawTileEntity(g);
        // drawChace(g);
        drawOvelay(g);
    }

    public void drawLand(Graphics g) {
        MyHashTable<Coordinate, Object> grid = map;
        for (int i = 0; i < X_GRID_MAX; i++) {
            for (int j = 0; j < Y_GRID_MAX; j++) {
                if (grid.get(new Coordinate(i, j)) != null) {
                    try {
                        ((Tile) grid.get(new Coordinate(i, j)).get(World.TILE)).draw(g, i * TILE_SIZE - playerPos.x,
                                j * TILE_SIZE - playerPos.y);
                    } catch (Exception e) {
                        System.out.println("Error drawing tile at " + i + ", " + j);
                        System.out.println(
                                "tile at " + i + ", " + j + " is " + grid.get(new Coordinate(i, j)).get(World.TILE));
                    }

                }

            }

        }
    }

    public void drawEntity(Graphics g) {
        MyHashTable<Coordinate, Object> grid = map;
        for (int i = 0; i < X_GRID_MAX; i++) {
            for (int j = 0; j < Y_GRID_MAX; j++) {
                if (grid.get(new Coordinate(i, j)).get(World.ENTITY) != null) {
                    try {
                        //
                        // System.out.println(grid.get(getChunkCoordinateFromFreeCoordinate(playerPos.x,
                        // playerPos.y)));

                        ((Entity) grid.get(new Coordinate(i, j)).get(World.ENTITY)).draw(g,
                                new Coordinate(i * TILE_SIZE - playerPos.x, j * TILE_SIZE - playerPos.y));
                        // System.out.println("calling drawing entity at " + (i * TILE_SIZE -
                        // playerPos.x) + ", "
                        // + (j * TILE_SIZE - playerPos.y));
                    } catch (Exception e) {
                        System.out.println("Error drawing Entity at " + i + ", " + j);
                        System.out.println(e);
                    }

                }

            }

        }

    }

    private void drawTileEntity(Graphics g) {
        MyHashTable<Coordinate, Object> grid = map;
        for (int i = 0; i < X_GRID_MAX; i++) {
            for (int j = 0; j < Y_GRID_MAX; j++) {
                if (grid.get(new Coordinate(i, j)).get(World.MAP_ENTITY) != null) {
                    try {

                        ((TileEntity) grid.get(new Coordinate(i, j)).get(World.MAP_ENTITY)).draw(g,
                                new Coordinate(i * TILE_SIZE - playerPos.x, j * TILE_SIZE - playerPos.y));
                        // System.out.println("calling drawing entity at " + (i * TILE_SIZE -
                        // playerPos.x) + ", "
                        // + (j * TILE_SIZE - playerPos.y));
                    } catch (Exception e) {
                        System.out.println("Error drawing Entity at " + i + ", " + j);
                        System.out.println(e);
                    }

                }

            }

        }
    }

    private void drawOvelay(Graphics g) {
        coinCounter.draw(g, X_GRID_MAX, ENTITY);
    }

    // private void drawChace(Graphics g) {
    // MyHashTable<Coordinate, Object> grid = map;

    // try {
    // // System.out.println(grid.get(chase));
    // // System.out.println(chase);
    // ((Entity) grid.get(chase).get(World.CHASE)).draw(g,
    // new Coordinate(chase.x * TILE_SIZE - playerPos.x, chase.y * TILE_SIZE -
    // playerPos.y));
    // // System.out.println("calling drawing entity at " + (i * TILE_SIZE -
    // // playerPos.x) + ", "
    // // + (j * TILE_SIZE - playerPos.y));
    // } catch (Exception e) {
    // System.out.println("Error drawing chaser at " + chase.x + ", " + chase.y);
    // System.out.println(e);
    // }

    // }

    public void load() {
        Setup.setupW(this);
    }

    // public void chase() {
    // System.out.println("chase");
    // System.out.println(chase);

    // MyArrayList<Object> current = getTile(chase);
    // Coordinate temp = new Coordinate(chase.y, chase.x);
    // ArrayList<Integer[]> path = ((MrNguyen)
    // current.get(CHASE)).reCalculatePath(Player.playerCoordinate, temp);

    // Coordinate to = new Coordinate(path.get(1)[0], path.get(0)[1]);
    // System.out.println(to);
    // setChaseLocation(to);
    // }

}
