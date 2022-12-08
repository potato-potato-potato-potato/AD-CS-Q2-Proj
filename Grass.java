import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Grass extends Tile {
    public Grass() {
        super(0);
        load();
    }

    @Override
    public void load() {
        try {
            texture = ImageIO.read(getClass().getResource("/Q4-assets/gfx/tiles/tile/grass/grass.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
