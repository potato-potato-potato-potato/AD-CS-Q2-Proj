
//import UserInterface.UI;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.PublicKey;
import java.util.List;
import java.lang.Exception;

import java.awt.*;

public class Screen extends JPanel implements KeyListener, MouseListener {

    public static final Dimension SCREEN_SIZE = new Dimension(800, 600);

    World world;
    // private UI ui;
    private int currentState = 1;

    private Image startScreen;

    private Image controls;

    private boolean debug = false;

    public Point temp = new Point(300, 300);

    private boolean cooldown = false;

    private int COOLDOWN_TIME = 256;

    private int direction = 4;

    public Screen() {
        this.setLayout(null);

        // ui = new UI();
        // ui.setSize(SCREEN_SIZE);
        // ui.setVisible(true);
        // ui.setFocusable(false);
        // add(ui);
        // addKeyListener(ui);

        world = new World();
        world.load();

        loadStart();

        addMouseListener(this);
        addKeyListener(this);
        Input.addListeners(this); // Mouse/Keyboard listener

        move.start();

        // Thread t1 = new Thread(new Animate(this));
        // t1.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return SCREEN_SIZE;
    }

    public Screen getScreen() {
        return this;
    }

    public Point temp() {
        return temp;
    }

    // load all start
    public void loadStart() {
        // define buttton
        JButton start = new JButton("start");
        start.setBounds(250, 450, 100, 100);
        JButton controls = new JButton("controls");
        controls.setBounds(350, 450, 100, 100);
        // add buttton
        controls.setFocusable(false);
        controls.setVisible(true);
        this.add(controls);

        start.setFocusable(false);
        start.setVisible(true);
        this.add(start);
        // load title page
        try {
            startScreen = ImageIO.read(getClass().getResource("/Q4-assets/gfx/gui/main/title thing yep.jpeg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // button action listener
        start.addActionListener((ActionEvent e) -> {
            currentState = 2;
            start.setVisible(false);
            this.remove(start);
            controls.setVisible(false);
            this.remove(controls);

            repaint();
        });

        controls.addActionListener((ActionEvent e) -> {
            currentState = 3;

            start.setVisible(false);
            this.remove(start);
            controls.setVisible(false);
            this.remove(controls);
            ;
            loadcontrols();
            repaint();
        });

    }

    //
    public void loadcontrols() {
        JButton back = new JButton("back");
        back.setBounds(0, 0, 100, 100);
        try {
            controls = ImageIO.read(getClass().getResource("/Q4-assets/gfx/gui/main/controls.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        back.addActionListener((ActionEvent e) -> {
            currentState = 1;
            back.setVisible(false);
            this.remove(back);
            loadStart();

            repaint();
        });
        ;
        back.setFocusable(false);
        back.setVisible(true);
        this.add(back);

    }

    public void drawCurrent(Graphics g) {
        if (currentState == 1) {
            g.drawImage(startScreen, 0, 0, 800, 600, null);
        } else if (currentState == 2) {
            world.draw(g);
        } else if (currentState == 3) {
            g.drawImage(controls, 0, 0, 800, 600, null);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draws background
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 800, 600);
        drawCurrent(g);
    }

    public void animate() {
        while (true) {
            try {
                Thread.sleep(6);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            if (Input.keyboard[87] || Input.keyboard[38])
                moveUp();// {W} Key
            if (Input.keyboard[65] || Input.keyboard[37])
                moveLeft(); // {A} Key
            if (Input.keyboard[83] || Input.keyboard[40])
                moveDown(); // {S} Key
            if (Input.keyboard[68] || Input.keyboard[39])
                moveRight(); // {D} Key
            // boolean playerMoved = false;

            // System.out.println(coolDowntThread.isAlive());
            // System.out.println(move.isAlive());

            repaint();
        }

    }

    // Thread coolDowntThread = new Thread(() -> {
    // try {
    // while (true) {
    // Thread.sleep(COOLDOWN_TIME);
    // direction = 4;
    // }
    // } catch (InterruptedException ex) {
    // Thread.currentThread().interrupt();
    // }
    // });

    Thread move = new Thread(() -> {
        try {
            while (true) {
                Thread.sleep(4);
                if (direction == 0) {
                    temp.translate(1, 0);
                } else if (direction == 1) {
                    temp.translate(-1, 0);
                } else if (direction == 2) {
                    temp.translate(0, -1);
                } else if (direction == 3) {
                    temp.translate(0, 1);
                } else if (direction == 4) {
                    temp.translate(0, 0);
                }
                world.temp(temp);
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    });

    public void moveUp() {

        if (!cooldown) {
            cooldown = true;

            direction = 0;

            try {
                Thread.sleep(COOLDOWN_TIME);
                direction = 4;
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

        }
    }

    public void moveDown() {
        if (!cooldown) {
            cooldown = true;

            direction = 1;
            try {
                Thread.sleep(COOLDOWN_TIME);
                direction = 4;
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void moveLeft() {
        if (!cooldown) {
            cooldown = true;

            direction = 2;
            try {
                Thread.sleep(COOLDOWN_TIME);
                direction = 4;
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

    }

    public void moveRight() {
        if (!cooldown) {
            cooldown = true;

            direction = 3;
            try {
                Thread.sleep(COOLDOWN_TIME);
                direction = 4;
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    //
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        // if (debug) {
        // if (keyEvent.getKeyCode() == 69) {
        // Tile[][] grid = world.gridLayers.get(0);
        // for (int i = 0; i < grid.length; i++) {
        // for (int j = 0; j < grid[i].length; j++) {
        // if (grid[i][j] instanceof Grass || grid[i][j] instanceof GrassWater) {
        // System.out.println("world.place(new Grass(), " + i + ", " + j + ");");
        // }
        // }
        // }
        // }
        // }

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        // Entity closestInteractableEntity =
        // world.getPlayer().getClosestInteractableEntity(world.getEntities());
        // if (closestInteractableEntity != null && keyEvent.getKeyCode() == 69) {
        // Interactable closestInteractable = (Interactable) closestInteractableEntity;
        // closestInteractable.interact(ui, world, world.getPlayer(),
        // world.getEntities());
        // }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

        // if (world.getEditStatus() == true) {
        // Point location = mouseEvent.getPoint();

        // Point chunk = World.getChunkCoordinateFromFreeCoordinate(location.x +
        // world.getWorldOffset().x,
        // location.y + world.getWorldOffset().y);
        // if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
        // world.place(new Grass(), chunk.x, chunk.y);
        // } else {
        // world.place(new Water(), chunk.x, chunk.y);
        // }

        // world.recalculateGrassWater();
        // }

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
