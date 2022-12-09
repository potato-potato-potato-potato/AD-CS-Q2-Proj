
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

    public Point temp = new Point(0, 0);

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

        repaint();
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

            Point movement = new Point(0, 0);

            if (Input.keyboard[87] || Input.keyboard[38]) {
                movement.translate(0, -Player.PLAYER_SPEED); // {W} Key
                System.out.println("w");
            }

            if (Input.keyboard[65] || Input.keyboard[37])
                movement.translate(-Player.PLAYER_SPEED, 0); // {A} Key
            System.out.println();
            if (Input.keyboard[83] || Input.keyboard[40])
                movement.translate(0, Player.PLAYER_SPEED); // {S} Key
            if (Input.keyboard[68] || Input.keyboard[39])
                movement.translate(Player.PLAYER_SPEED, 0); // {D} Key
            // boolean playerMoved = false;
            temp.translate(movement.x, movement.y);
            world.temp(temp);

            repaint();
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
