import load.Loadable;

import javax.swing.*;
import java.awt.*;

public class Water extends Tile implements Loadable {

    public Water() {
        super(1);
        passable = false;
        load();
    }

    @Override
    public void load() {
        try {
            texture = new ImageIcon(getClass().getResource("/Q4-assets/gfx/tiles/tile/water/waterAnimation.gif")).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
