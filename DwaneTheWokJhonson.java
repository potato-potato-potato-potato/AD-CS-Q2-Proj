
import javax.imageio.ImageIO;

import load.Loadable;

import java.awt.*;

public class DwaneTheWokJhonson extends Entity implements Loadable {

    public static boolean completed = false; // this is used to determine what the npc should do
    public int coinCost; // this is used to store the cost of the quest
    // this is uesd to offset the draw position to make it fit the tile
    private Point drawOffset = new Point(0, 0);
    private Image sprite;
    public static int npcState; // this is used to determine what the npc should do

    private static final Point PLAYER_DRAW_OFFSET = new Point(20, -15);

    public DwaneTheWokJhonson(int x, int y) {
        super(new Coordinate(x, y), new Dimension(64, 97));
        System.out.println("Entity created");
        load();
        dialogue = "Hello there, I am Dwane the Wok Jhonson. I am a master of the wok. I can slow him down for 45 seconds, it will cost you 10 coines";
        coinCost = 10;
        npcState = 0;

    }

    public DwaneTheWokJhonson(int x, int y, int npcState) {
        super(new Coordinate(x, y), new Dimension(64, 97));
        System.out.println("Entity created");
        load();
        dialogue = "Hello there, I am Dwane the Wok Jhonson. I am a master of the wok. I can slow him down for 45 seconds, it will cost you 10 coines";
        coinCost = 10;
        DwaneTheWokJhonson.npcState = npcState;

    }

    public DwaneTheWokJhonson(Coordinate position) {
        super(position, new Dimension(64, 97));
    }

    public DwaneTheWokJhonson(Coordinate position, int npcState) {
        super(position, new Dimension(64, 97));
        DwaneTheWokJhonson.npcState = npcState;
    }

    @Override
    public void interact() {
        if (npcState == 0) {
            System.out.println("asdkfjhlkaslhdf");
            dialogue = "Hello there, I am Dwane the Wok Jhonson. I am a master of the wok. I can slow him down for 45 seconds, it will cost you 10 coines";
            super.interact();
            npcState = 1;
            setNpcState(npcState);
        } else if (npcState == 1 && Player.coinage >= 10) {
            Player.removeCoinage(10);
            // give player power up
            super.interact();
            npcState = 2;
            setNpcState(npcState);
            completed = true;
        }
    }

    @Override
    public void load() {
        try {
            texture = ImageIO.read(
                    getClass().getResource("/Q4-assets/gfx/entities/mobs/quest-npc/characters/DwaneTheWokJhonson.png"));
            // System.out.println("WHY WHY WHY WHY");
            // System.out.println(texture);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
