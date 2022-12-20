import java.util.*;

import HashTable.*;

public class BFS {

    // Given input matrix
    int grid[][];
    int ROW = 0;
    int COL = 0;
    int npc_pos[];
    int player_pos[];
    boolean[][] vis;

    // Direction vectors
    static int dRow[] = { -1, 0, 1, 0 };
    static int dCol[] = { 0, 1, 0, -1 };

    public BFS(int grid[][], boolean[][] vis, int npc_pos[], int player_pos[]) {
        this.grid = grid;
        this.vis = vis;
        this.npc_pos = npc_pos;
        this.player_pos = player_pos;
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

    boolean isValid(boolean vis[][], int row, int col) {

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

    boolean check_bound(boolean vis[][], int row, int col) {

        // If cell lies out of bounds
        if (row < 0 || col < 0 ||
                row >= ROW || col >= COL)
            return false;

        // Otherwise
        return true;
    }

    // find the exact path from npc to player
    public void search_path() {
        // Declare a queue
        Queue<Node> q = new LinkedList<>();
        int dist = 10;

        // Mark the source cell as visited
        vis[npc_pos[0]][npc_pos[1]] = true;

        // Push the source cell
        q.add(new Node(npc_pos[0], npc_pos[1]));

        // Do a BFS starting from source cell
        while (!q.isEmpty()) {
            // Pop front node from queue and process it
            Node node = q.poll();

            // (i, j) represents current cell and
            // dist stores its minimum distance from the source
            int i = node.x;
            int j = node.y;

            // if destination is found, print the distance
            if (i == player_pos[0] && j == player_pos[1]) {
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
    public MyArrayList<Integer[]> get_path() {
        MyArrayList<Integer[]> path = new MyArrayList<>();
        int i = player_pos[0];
        int j = player_pos[1];

        while (i != npc_pos[0] || j != npc_pos[1]) {
            Integer[] pos = new Integer[2];
            pos[0] = i;
            pos[1] = j;
            path.add(pos);
            int min = grid[i][j];
            int min_i = i;
            int min_j = j;
            for (int k = 0; k < 4; k++) {
                if (check_bound(vis, i + dRow[k], j + dCol[k])) {
                    // Skip if location is invalid or blocked
                    if (grid[i + dRow[k]][j + dCol[k]] == 1)
                        continue;

                    if (grid[i + dRow[k]][j + dCol[k]] < min) {
                        min = grid[i + dRow[k]][j + dCol[k]];
                        min_i = i + dRow[k];
                        min_j = j + dCol[k];
                    }
                }
            }
            i = min_i;
            j = min_j;
        }

        for (int x = 0, y = path.size() - 1; x < y; x++, y--) {
            Integer[] temp = path.get(x);
            path.set(x, path.get(y));
            path.set(y, temp);
        }

        return path;
    }

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
