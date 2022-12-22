import load.Loadable;

import java.awt.*;

import java.awt.image.ImageObserver;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public abstract class Entity implements Loadable {

    protected Point coordinateOffset;
    public Dimension size;
    protected Image texture;
    protected final Point coordinates;
    protected String dialogue = "if you see this something is wrong"; // this is used to store the dialogue for the npc
    private int npcState;

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

    public int getNpcState() {
        return npcState;
    }

    public void setNpcState(int npcState) {
        this.npcState = npcState;
    }

    public void interact() {
        if (npcState == 0) {
            try {
                AudioInputStream audioInputStream = AudioSystem
                        .getAudioInputStream(getClass().getResourceAsStream("/Q4-assets/audio/se1.wav"));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                System.out.println("Playing sound file");
            } catch (Exception e) {
                System.out.println("Error playing sound file: " + e);
            }
        } else {
            try {
                AudioInputStream audioInputStream = AudioSystem
                        .getAudioInputStream(getClass().getResourceAsStream("/Q4-assets/audio/beep-2.wav"));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                System.out.println("Playing sound file");
            } catch (Exception e) {
                System.out.println("Error playing sound file: " + e);
            }
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
