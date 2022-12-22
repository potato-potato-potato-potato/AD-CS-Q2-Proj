import load.Loadable;

import HashTable.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.util.ArrayList;

public class MrNguyen extends Entity implements Loadable {
    public BFS bfs;
    final int ROW = 100;
    final int COL = 100;
    private static int npc_pos[];
    private static int player_pos[];

    // Declare the visited array
    private boolean[][] vis = new boolean[ROW][COL];

    public MrNguyen(int x, int y, World world) {
        super(new Coordinate(x, y), new Dimension(64, 97));
        bfs = new BFS(world, vis, npc_pos, player_pos);

        load();
        npc_pos = new int[2];
        player_pos = new int[2];
        npc_pos[0] = x;
        npc_pos[1] = y;
        player_pos[0] = Player.playerCoordinate.x;
        player_pos[1] = Player.playerCoordinate.x;

    }

    public MrNguyen(Coordinate position, World world) {
        super(position, new Dimension(64, 97));

        load();
        npc_pos = new int[2];
        player_pos = new int[2];
        npc_pos[0] = position.x;
        npc_pos[1] = position.y;
        player_pos[0] = Player.playerCoordinate.x;
        player_pos[1] = Player.playerCoordinate.x;
        bfs = new BFS(world, vis, npc_pos, player_pos);

    }

    public MyArrayList<Integer[]> chasePath() {
        System.out.println("BFS PATH FINDING");
        System.out.println("+++++++++++++++++++++++++++++++======================");

        bfs.bfs();
        bfs.search_path();
        bfs.bfs();
        // ArrayList<Integer[]> path = bfs.get_path();
        // System.out.println(path);
        // for (int x = 0, y = path.size() - 1; x < y; x++, y--) {
        // Integer[] temp = path.get(x);
        // path.set(x, path.get(y));
        // path.set(y, temp);
        // }
        ArrayList<Integer[]> path = bfs.get_path();
        for (int i = 0; i < path.size(); i++) {
            System.out.println(path.get(i)[0] + " " + path.get(i)[1]);
        }
        return null;
    }

    public void setCoordinates(int x, int y) {
        coordinates.x = x;
        coordinates.y = y;
    }

    public void setCoordinates(Coordinate co) {
        bfs.updatePlayerPos(Player.playerCoordinate.x, Player.playerCoordinate.y);
        bfs.updateNPCPos(co.x, co.y);
        coordinates.x = co.x;
        coordinates.y = co.y;
    }

    @Override
    public void load() {

        try {
            texture = ImageIO.read(
                    getClass().getResource("/Q4-assets/gfx/entities/mobs/quest-npc/characters/MrNguyen.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}