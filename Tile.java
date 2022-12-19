import load.Loadable;

import javax.imageio.ImageIO;
import java.awt.*;

public abstract class Tile implements Loadable {
    public int id;
    public boolean passable;
    protected Image texture;

    public Tile(int id) {
        this.id = id;
        passable = true;
    }

    public void draw(Graphics g, int x, int y) {
        if (texture == null) {
            System.out.println("Texture is null");
            return;
        }

        g.drawImage(texture, x + 385, y + 285, null);
    }

    public int getId() {
        return id;
    }

    public boolean getPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

}
