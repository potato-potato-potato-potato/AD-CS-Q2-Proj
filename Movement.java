import java.awt.*;

public class Movement implements Runnable {

    private Screen screen;
    int i = 0;

    public Movement(Screen screen) {
        this.screen = screen;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(4);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            screen.movement();
        }

    }
}
