import java.awt.*;

public class Animate implements Runnable {
    private Screen screen;

    public Animate(Screen screen) {
        this.screen = screen;
    }

    public void run() {
        while (true) {
            screen.repaint();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
