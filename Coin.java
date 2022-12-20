import load.Loadable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.*;

public class Coin extends TileEntity implements Loadable {
    public Coin() {
        super(1);
        load();
    }

    @Override
    public void load() {
        try {
            texture = new ImageIcon(getClass().getResource("/Q4-assets/gfx/entities/coin.gif")).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
