import load.Loadable;
import javax.imageio.ImageIO;
import java.awt.*;

public class CoinCounter implements Loadable {
    private Image texture;
    private Coordinate drawOffset = new Coordinate(100, 80);

    public CoinCounter() {
        load();
    }

    public void draw(Graphics g, int x, int y) {
        if (texture == null) {
            System.out.println("Texture is null");
            return;
        }

        g.drawImage(texture, 0, 0, null);
        g.setFont(new Font("Arial", Font.PLAIN, 50));
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString((Integer) Player.coinage), (int) drawOffset.x, (int) drawOffset.y);
        // System.out.println("Drawing coin counter: " + Player.coinage);
    }

    @Override
    public void load() {
        // TODO Auto-generated method stub
        try {
            texture = ImageIO.read(getClass().getResource("/Q4-assets/gfx/gui/overlay/coin.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
