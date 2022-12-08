
//
//credit to Michail Poskatcheev Class of 2015
//

import javax.swing.*;
import java.awt.event.*;

public final class Input {
    public static int x, y;
    public static boolean clicked;
    public static boolean entered;
    public static int mouseWheel;
    public static boolean[] mouseButtons = new boolean[3];
    public static boolean[] keyboard = new boolean[250];

    public static final KeyListener KEY_LISTENER = new KeyListener() {

        @Override
        public void keyPressed(KeyEvent key) {
            // System.out.println("type: " + (int) key.getKeyCode());
            keyboard[(int) key.getKeyCode()] = true;
        }

        @Override
        public void keyReleased(KeyEvent key) {
            keyboard[(int) key.getKeyCode()] = false;
        }

        @Override
        public void keyTyped(KeyEvent key) {

        }
    };

    public static final MouseListener MOUSE_LISTENER = new MouseListener() {
        @Override
        public void mousePressed(MouseEvent e) {
            clicked = true;
            switch (e.getButton()) {
                case MouseEvent.BUTTON1:
                    mouseButtons[0] = true;
                    break;
                case MouseEvent.BUTTON2:
                    mouseButtons[1] = true;
                    break;
                case MouseEvent.BUTTON3:
                    mouseButtons[2] = true;
                    break;
            }

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            clicked = false;
            switch (e.getButton()) {
                case MouseEvent.BUTTON1:
                    mouseButtons[0] = false;
                    break;
                case MouseEvent.BUTTON2:
                    mouseButtons[1] = false;
                    break;
                case MouseEvent.BUTTON3:
                    mouseButtons[2] = false;
                    break;
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            entered = true;
        }

        @Override
        public void mouseExited(MouseEvent e) {
            entered = false;
        }
    };

    public static final MouseMotionListener MOUSE_MOTION_LISTENER = new MouseMotionListener() {
        @Override
        public void mouseMoved(MouseEvent e) {
            x = e.getX();
            y = e.getY();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            x = e.getX();
            y = e.getY();
        }
    };

    public static final MouseWheelListener MOUSE_WHEEL_LISTENER = new MouseWheelListener() {
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            mouseWheel += e.getWheelRotation();
        }
    };

    public static void addListeners(JPanel jpanel) {
        jpanel.setFocusable(true);
        jpanel.addKeyListener(KEY_LISTENER);
        jpanel.addMouseListener(MOUSE_LISTENER);
        jpanel.addMouseMotionListener(MOUSE_MOTION_LISTENER);
        jpanel.addMouseWheelListener(MOUSE_WHEEL_LISTENER);
    }

}
