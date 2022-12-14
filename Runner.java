import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Runner {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Islands");

        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // frame.setResizable(false);

        // Create panel and add it to the frame
        Screen sc = new Screen();

        frame.add(sc);
        frame.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 3 - frame.getSize().height / 2);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                sc.saveOnExit();
            }
        });
        // sc.movement();
        Animate animate = new Animate(sc);
        animate.run();
        Movement mv = new Movement(sc);
        Chase chase = new Chase(sc);
        mv.run();
        chase.run();
        sc.repaint();

    }
}
