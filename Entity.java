import load.Loadable;

import java.awt.*;
import java.awt.image.ImageObserver;

public abstract class Entity implements Loadable {

    protected Point coordinateOffset;
    public Dimension size;
    protected Image texture;
    protected final Point coordinates;
    protected String dialogue = "if you see this something is wrong"; // this is used to store the dialogue for the npc
    public int npcState; // this is used to determine what the npc should do

    public Entity(Point coordinates, Dimension size) {
        this.coordinates = coordinates;
        this.size = size;
        System.out.println("Entity created at " + coordinates.x + ", " + coordinates.y);
        load();

    }

    public static void drawWrappedString(Graphics g, String text, int x, int y, int maxWidth) {
        String[] words = text.split(" ");
        String line = "";
        for (String word : words) {
            if (g.getFontMetrics().stringWidth(line + word) < maxWidth) {
                line += word + " ";
            } else {
                g.drawString(line, x, y);
                line = word + " ";
                y += g.getFontMetrics().getHeight();
            }
        }
        g.drawString(line, x, y);
    }

    public Entity(Point coordinates, boolean thisissobad) {
        this.coordinates = coordinates;
        System.out.println("Entity created at " + coordinates.x + ", " + coordinates.y);

    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void interact() {
        if (npcState == 0) {
            npcState = 1;
        }
        if (npcState == 1) {
            // remove coine from player
            // give player power up

            npcState = 2;
        }
    }

    public Point getOffsetCoordinates() {
        if (coordinateOffset == null)
            return coordinates;

        Point offsetCoordinate = new Point(coordinates);
        offsetCoordinate.translate(coordinateOffset.x, coordinateOffset.y);

        return offsetCoordinate;
    }

    public void draw(Graphics g, Coordinate screenLocation) {
        if (texture != null && size != null) {
            // System.out.println(npcState);
            g.drawImage(texture, screenLocation.x + 385, screenLocation.y + 285,
                    (int) size.getWidth(), (int) size.getHeight(),
                    null);
            if (npcState == 1) {
                System.out.println("Drawing dialogue");
                System.out.println(dialogue);
                g.setColor(Color.RED);
                drawWrappedString(g, dialogue, screenLocation.x + 285, screenLocation.y + 285, 100);
            }
        } else {
            System.out.println("Texture is null" + texture);
        }

    }
}
