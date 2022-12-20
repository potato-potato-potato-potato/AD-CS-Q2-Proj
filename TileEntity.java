
import load.Loadable;

import javax.imageio.ImageIO;
import java.awt.*;

public abstract class TileEntity implements Loadable {
    public int id;
    protected Image texture;

    public TileEntity(int id) {
        this.id = id;

    }

    public void draw(Graphics g, int x, int y) {
        draw(g, new Coordinate(x, y));
    }

    public void draw(Graphics g, Coordinate screenLocation) {
        if (texture == null) {
            System.out.println("Texture is null");
            return;
        }

        g.drawImage(texture, screenLocation.x + 385, screenLocation.y + 285, null);
    }

    public int getId() {
        return id;
    }

}
