import HashTable.*;

public class temp {
    public static void main(String[] args) {

        // Given input matrix
        int grid[][] = { { 1, 0, 0, 0 },
                { 1, 1, 0, 1 },
                { 0, 0, 0, 1 },
                { 0, 1, 0, 1 } };
        int npc_pos[] = { 3, 0 };
        int player_pos[] = { 0, 3 };

        final int ROW = 4;
        final int COL = 4;

        // Declare the visited array
        boolean[][] vis = new boolean[ROW][COL];

        BFS bfs = new BFS(grid, vis, npc_pos, player_pos);
        bfs.bfs();
        bfs.search_path();
        bfs.bfs();
        // this is a path of the npc to player
        MyArrayList<Integer[]> path = bfs.get_path();
        System.out.println("Path: ");
        for (int i = 0; i < path.size(); i++) {
            System.out.println(path.get(i)[0] + " " + path.get(i)[1]);
        }
    }
}
