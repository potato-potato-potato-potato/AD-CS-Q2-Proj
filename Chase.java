import java.awt.*;

public class Chase implements Runnable {

    private Screen screen;

    public Chase(Screen screen) {
        this.screen = screen;
    }

    public void run() {
        while (true) {
            // screen.chase();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
