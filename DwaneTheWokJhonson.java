
import javax.imageio.ImageIO;

import load.Loadable;

import java.awt.*;

public class DwaneTheWokJhonson extends Entity implements Loadable {

    private boolean completed = false; // this is used to determine what the npc should do
    public int coinCost; // this is used to store the cost of the quest
    // this is uesd to offset the draw position to make it fit the tile
    private Point drawOffset = new Point(0, 0);
    private Image sprite;

    private static final Point PLAYER_DRAW_OFFSET = new Point(20, -15);

    public DwaneTheWokJhonson(int x, int y) {
        super(new Coordinate(x, y), new Dimension(64, 97));
        System.out.println("Entity created");
        load();
        dialogue = "Hello there, I am Dwane the Wok Jhonson. I am a master of the wok. I can slow him down for 45 seconds, it will cost you 10 coines";
        coinCost = 10;
        npcState = 0;

    }

    public DwaneTheWokJhonson(Coordinate position) {
        super(position, new Dimension(64, 97));
    }

    @Override
    public void interact() {
        if (npcState == 0) {
            System.out.println("asdkfjhlkaslhdf");
            dialogue = "Hello there, I am Dwane the Wok Jhonson. I am a master of the wok. I can slow him down for 45 seconds, it will cost you 10 coines";
            npcState = 1;
        } else if (npcState == 1 && Player.coinage >= 10) {
            Player.removeCoinage(10);
            // give player power up

            npcState = 2;
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
