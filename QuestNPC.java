import load.Loadable;

import javax.imageio.ImageIO;
import java.awt.*;

import java.io.IOException;

public class NPC implements Loadable {

    private Image sprite;
    private Coordinate position;

    public NPC(int x, int y) {
        position = new Coordinate(x, y);
    }

    public NPC(Coordinate position) {
        this.position = position;
    }

    @Override
    public void load() {
        try {
            sprite = ImageIO.read(getClass().getResource("/Q4-assets/gfx/entities/mobs/player/idle.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(sprite, (int) position.getX() * World.TILE_SIZE, (int) position.getY() * World.TILE_SIZE, null);
    }

}
