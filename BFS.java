import java.util.*;

import HashTable.*;

public class BFS {

    // Given input matrix
    public static int grid[][];
    public int ROW = 100;
    public int COL = 100;
    public static int npc_pos[];
    public static int player_pos[];
    public boolean[][] vis;
    public boolean gridSet = false;
    // Direction vectors
    static int dRow[] = { -1, 0, 1, 0 };
    static int dCol[] = { 0, 1, 0, -1 };

    public BFS(World world, boolean[][] vis, int npc_pos[], int player_pos[]) {
        setUpGrid(world);
        this.vis = vis;
        BFS.npc_pos = npc_pos;
        BFS.player_pos = player_pos;
        this.ROW = grid.length;
        this.COL = grid[0].length;
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void setUpGrid(World world) {
        int ret[][] = new int[ROW][COL];
        for (int i = 0; i < ret.length; i++) {
            for (int j = 0; j < ret[i].length; j++) {
                if (world.getTile(i, j).get(World.TILE) instanceof Grass) {
                    ret[j][i] = 0;
                } else {
                    ret[j][i] = 1;
                }

            }
        }
        gridSet = true;
        grid = ret;
    }

    private boolean isValid(boolean vis[][], int row, int col) {

        // If cell lies out of bounds
        if (row < 0 || col < 0 ||
                row >= ROW || col >= COL)
            return false;

        // If cell is already visited
        if (vis[row][col])
            return false;

        // Otherwise
        return true;
    }

    private boolean check_bound(boolean vis[][], int row, int col) {

        // If cell lies out of bounds
        if (row < 0 || col < 0 ||
                row >= ROW || col >= COL)
            return false;

        // Otherwise
        return true;
    }

    // find the exact path from npc to player
    public void search_path() {
        System.out.println("searching path");
        // Declare a queue
        Queue<Node> q = new LinkedList<>();
        int dist = 10;

        // Mark the source cell as visited
        vis[npc_pos[1]][npc_pos[0]] = true;

        // Push the source cell
        q.add(new Node(npc_pos[1], npc_pos[0]));

        // Do a BFS starting from source cell
        while (!q.isEmpty()) {
            // Pop front node from queue and process it
            Node node = q.poll();

            // (i, j) represents current cell and
            // dist stores its minimum distance from the source
            int i = node.x;
            int j = node.y;

            // if destination is found, print the distance
            if (i == player_pos[1] && j == player_pos[0]) {
                // System.out.println("The distance is " + dist[i][j]);
                System.out.println("The path is " + i + " " + j);
                return;
            }

            // check for all 4 possible movements from current cell
            // and enqueue each valid movement
            for (int k = 0; k < 4; k++) {
                // Skip if location is invalid or blocked
                if (isValid(vis, i + dRow[k], j + dCol[k])) {
                    // Skip if location is invalid or blocked
                    if (grid[i + dRow[k]][j + dCol[k]] == 1)
                        continue;

                    // mark next cell as visited and enqueue it
                    vis[i + dRow[k]][j + dCol[k]] = true;
                    q.add(new Node(i + dRow[k], j + dCol[k]));
                    grid[i + dRow[k]][j + dCol[k]] = dist;
                }
            }
            dist += 1;
        }
    }

    // find the path from npc to player given the grid
    public ArrayList<Integer[]> get_path() {
        System.out.println("Player pos: " + player_pos[1] + " " + player_pos[0]);
        System.out.println("NPC pos: " + npc_pos[1] + " " + npc_pos[0]);
        ArrayList<Integer[]> path = new ArrayList<>();
        int i = player_pos[1];
        int j = player_pos[0];
        System.out.println("alskdjhflkashjdflaksgjdf");
        System.out.println(grid[i][j]);
        while (i != npc_pos[1] || j != npc_pos[0]) {

            Integer[] pos = new Integer[2];
            pos[0] = i;
            pos[1] = j;
            // System.out.println(pos[0] + " " + pos[1]);
            path.add(pos);
            int min = grid[i][j];
            int min_i = i;
            int min_j = j;
            for (int k = 0; k < 4; k++) {
                // System.out.println("mark");
                if (check_bound(vis, i + dRow[k], j + dCol[k])) {
                    // Skip if location is invalid or blocked
                    if (grid[i + dRow[k]][j + dCol[k]] == 1)
                        continue;
                    if (grid[i + dRow[k]][j + dCol[k]] == 0 && (i + dRow[k] != npc_pos[1] || j + dCol[k] != npc_pos[0]))
                        continue;
                    if (grid[i + dRow[k]][j + dCol[k]] <= min) {
                        // System.out.println("i: " + (i + dRow[k]) + " j: " + (j + dCol[k]));
                        min = grid[i + dRow[k]][j + dCol[k]];
                        min_i = i + dRow[k];
                        min_j = j + dCol[k];
                    }
                }
            }
            i = min_i;
            j = min_j;
        }
        return path;
    }

    public void show_path() {
        ArrayList<Integer[]> path = get_path();
        for (int i = 0; i < path.size(); i++) {
            Integer[] pos = path.get(i);
            grid[pos[0]][pos[1]] = 3;
        }
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (grid[i][j] != 0 && grid[i][j] != 1 && grid[i][j] != 3)
                    grid[i][j] = 4;
            }

        }
    }

    // public void updatePlayerPos(int x, int y) {
    // player_pos[1] = x;
    // player_pos[0] = y;
    // }

    // public void updateNPCPos(int x, int y) {
    // npc_pos[1] = x;
    // npc_pos[0] = y;
    // }

    public void bfs() {
        System.out.println("BFS");
        // print the grid
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print(vis[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

    }

}
