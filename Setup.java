import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Random;

public class Setup {
    public static int i;
    public static int j;
    public static BFS bfs;

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
                    boolean value = random.nextDouble() < 0.02;
                    if (value == true) {
                        world.placeTileEntity(new Coin(), i, j);
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
        world.placeEntity(46, 32, World.ENTITY, new Player(new Coordinate(46, 32)));
        world.placeEntity(46, 27, World.ENTITY, new DwaneTheWokJhonson(new Coordinate(46, 27)));
        world.placeEntity(0, 0, World.CHASE, new MrNguyen(new Coordinate(0, 0), world));

    }
}
