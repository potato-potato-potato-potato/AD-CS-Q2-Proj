import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GrassWater extends Tile {
    private int dir;

    public GrassWater(int dir) {
        super(3);
        this.dir = dir;
        load();
    }

    @Override
    public void load() {
        try {
            switch (dir) {
                case 0:
                    texture = ImageIO.read(getClass().getResource("/Q4-assets/gfx/tiles/tile/water-grass/bottom.png"));
                    break;
                case 1:
                    texture = ImageIO.read(getClass().getResource("/Q4-assets/gfx/tiles/tile/water-grass/right.png"));
                    break;
                case 2:
                    texture = ImageIO.read(getClass().getResource("/Q4-assets/gfx/tiles/tile/water-grass/left.png"));
                    break;
                case 3:
                    texture = ImageIO.read(getClass().getResource("/Q4-assets/gfx/tiles/tile/water-grass/top.png"));
                    break;
                case 4:
                    texture = ImageIO
                            .read(getClass().getResource("/Q4-assets/gfx/tiles/tile/water-grass/bottom-right.png"));
                    break;
                case 5:
                    texture = ImageIO
                            .read(getClass().getResource("/Q4-assets/gfx/tiles/tile/water-grass/bottom-left.png"));
                    break;
                case 6:
                    texture = ImageIO
                            .read(getClass().getResource("/Q4-assets/gfx/tiles/tile/water-grass/top-left.png"));
                    break;
                case 7:
                    texture = ImageIO
                            .read(getClass().getResource("/Q4-assets/gfx/tiles/tile/water-grass/top-right.png"));
                    break;
                case 8:
                    texture = ImageIO
                            .read(getClass().getResource("/Q4-assets/gfx/tiles/tile/water-grass/in-top-leftpng.png"));
                    break;
                case 9:
                    texture = ImageIO
                            .read(getClass().getResource("/Q4-assets/gfx/tiles/tile/water-grass/in-top-right.png"));
                    break;
                case 10:
                    texture = ImageIO
                            .read(getClass().getResource("/Q4-assets/gfx/tiles/tile/water-grass/in-bottom-left.png"));
                    break;
                case 11:
                    texture = ImageIO
                            .read(getClass().getResource("/Q4-assets/gfx/tiles/tile/water-grass/in-bottom-right.png"));
                    break;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
