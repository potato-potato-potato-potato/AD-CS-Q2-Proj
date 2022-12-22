import java.awt.Point;
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Random;
import java.io.FileWriter;
import java.io.FileReader;

public class Setup {
    public static int i;
    public static int j;
    public static BFS bfs;
    public static FileReader reader;
    public static FileWriter writer;
    public static File file = new File("Data.txt");
    private static boolean saveDetected = file.exists();
    public static int dwane = 0;
    public static int todd = 0;

    private Setup() {

    }

    private static void setupLandscape(World world) {
        Random random = new Random();

        i = 1;
        j = 1;
        world.placeL(0, new Water());
        try {
            File myObj = new File("MapExportFile.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextInt()) {
                int num = myReader.nextInt();
                if (num == 0) {
                    world.place(new Water(), i, j);
                    i++;
                } else if (num == 1) {
                    if (true) {
                        boolean value = random.nextDouble() < 0.02;
                        if (value == true) {
                            world.placeTileEntity(new Coin(), i, j);
                        }
                    }
                    world.place(new Grass(), i, j);
                    i++;

                }
                System.out.println(num + " " + i + " " + j);
                System.out.println(world.getTile(i, j));
                if (i == 100) {
                    i = 0;
                    j++;
                }
            }

            File save = new File("Data.txt");
            if (save.exists()) {
                Scanner saveReader = new Scanner(save);
                int x = saveReader.nextInt();
                int y = saveReader.nextInt();
                int coinage = saveReader.nextInt();
                Player.playerCoordinate = new Coordinate(x, y);
                Player.coinage = coinage;
                world.placeEntity(x, y, World.ENTITY, new Player(new Coordinate(x, y)));
                Screen.playerPos = new Point(x * World.TILE_SIZE, y * World.TILE_SIZE);
                dwane = saveReader.nextInt();

                todd = saveReader.nextInt();

                // for (int i = 0; i < 100; i++) {
                // for (int j = 0; j < 100; j++) {
                // if (saveReader.nextInt() == 1) {

                // System.out.println("Coin placed at " + i + " " + j);
                // world.placeTileEntity(new Coin(), i, j);
                // } else {
                // System.out.println("No coin placed at " + i + " " + j);
                // }
                // }
                // }

                saveReader.close();
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        world.recalculateGrassWater();

        world.placeTileEntity(new Coin(), 11, 14);

        // Add after second world is done
        // world.recalculateGrassWater(2);

    }

    public static void setupW(World world) {
        setupLandscape(world);
        if (saveDetected == false) {
            world.placeEntity(46, 32, World.ENTITY, new Player(new Coordinate(46, 32)));
            world.placeEntity(46, 27, World.ENTITY, new DwaneTheWokJhonson(new Coordinate(46, 27)));
            world.placeEntity(20, 42, World.ENTITY, new toddHoward(new Coordinate(20, 32)));
        }
        world.placeEntity(46, 27, World.ENTITY, new DwaneTheWokJhonson(new Coordinate(46, 27), dwane));
        world.placeEntity(20, 42, World.ENTITY, new toddHoward(new Coordinate(20, 32), todd));
        // world.placeEntity(0, 0, World.CHASE, new MrNguyen(new Coordinate(0, 0),
        // world));

    }

    public static void saveGame(World world) {
        try {
            writer = new FileWriter(file);
            writer.write(Player.playerCoordinate.x + " " + Player.playerCoordinate.y + " " + Player.coinage + "\n");
            writer.write(DwaneTheWokJhonson.npcState + "\n");
            writer.write(toddHoward.npcState + "\n");

            // for (int i = 0; i < 100; i++) {
            // for (int j = 0; j < 100; j++) {
            // if (world.getTile(i, j).get(World.MAP_ENTITY) instanceof Coin) {
            // writer.write("1 ");
            // } else {
            // writer.write("0 ");
            // }
            // }
            // writer.write("\n");
            // }

            writer.flush();
            writer.close(); // Make sure to close when done reading
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
