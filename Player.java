import load.Loadable;

import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import HashTable.*;
import java.awt.Point;

public class Player extends Entity implements Loadable {

	public boolean isInboat = false;
	protected Image sprite;
	private Dimension size = new Dimension(64, 97);
	public static int PLAYER_SPEED = 1;
	private int frames;
	private static final Point PLAYER_DRAW_OFFSET = new Point(20, -15);
	public static Coordinate playerCoordinate = new Coordinate(15, 11);
	private MyHashTable<Integer, Image> animationFrames;
	public static int direction;
	public static int coinage = 9;
	public static final int ID = 0;

	public Player(Coordinate playerCoordinate) {
		super(playerCoordinate, true);
		Player.playerCoordinate = playerCoordinate;
		thread1.start();
		animationFrames = new MyHashTable<Integer, Image>();
		// forwardsWalk = 1
		// leftWalk = 2
		// backwardsWalk = 3
		// rightWalk = 4
		// idle = 5
		animationFrames.put(1, null);
		animationFrames.put(2, null);
		animationFrames.put(3, null);
		animationFrames.put(4, null);
		animationFrames.put(5, null);
		load();
	}

	public void setIsInboat(boolean isInboat) {
		this.isInboat = isInboat;
	}

	Thread thread1 = new Thread(() -> {
		int frame = 0;
		while (true) {
			try {
				Thread.sleep(250);
				if (frame >= 4) {
					frame = 0;
				}
				frame++;

				setframs(frame);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	});

	public void setframs(int f) {
		frames = f;
	}

	@Override
	public void draw(Graphics g, Coordinate p) {
		int currentFrame = frames;

		if (Input.keyboard[87] || Input.keyboard[38]) { // W, Up Arrow
			sprite = animationFrames.get(1).get(currentFrame);
			direction = 1;
		} else if (Input.keyboard[83] || Input.keyboard[40]) { // S, Down Arrow
			sprite = animationFrames.get(3).get(currentFrame);
			direction = 2;
		} else if (Input.keyboard[65] || Input.keyboard[37]) { // A, Left Arrow
			sprite = animationFrames.get(2).get(currentFrame);
			direction = 3;
		} else if (Input.keyboard[68] || Input.keyboard[39]) { // D, Right Arrow
			sprite = animationFrames.get(4).get(currentFrame);
			direction = 4;
		} else { // If nothing's happening, play idle animation
			sprite = animationFrames.get(5).get(direction);
		}
		// System.out.println(coinage);
		g.drawImage(sprite, (400 - size.width / 2) + PLAYER_DRAW_OFFSET.x,
				(300 - size.height / 2) + PLAYER_DRAW_OFFSET.y, size.width, size.height, null);

	}

	public Coordinate getCoordinates() {
		return playerCoordinate;
	}

	public static void translateCoordinates(int dx, int dy) {
		playerCoordinate = new Coordinate(playerCoordinate.x + dx, playerCoordinate.y + dy);
	}

	public static void setCoordinates(int dx, int dy) {
		playerCoordinate = new Coordinate(dx, dy);
	}

	public static Coordinate move(int direction) {
		switch (direction) {
			case 0:
				playerCoordinate = new Coordinate(playerCoordinate.x + 1, playerCoordinate.y - 1);
				break;
			case 1:
				playerCoordinate = new Coordinate(playerCoordinate.x - 1, playerCoordinate.y);
				break;
			case 2:
				playerCoordinate = new Coordinate(playerCoordinate.x, playerCoordinate.y - 1);
				break;
			case 3:
				playerCoordinate = new Coordinate(playerCoordinate.x, playerCoordinate.y + 1);
				break;
		}
		return playerCoordinate;
	}

	public int getID() {
		return ID;
	}

	public static int getCoinage() {
		return coinage;
	}

	public static void addCoinage(int coinage) {
		Player.coinage += coinage;
	}

	public static void removeCoinage(int coinage) {
		Player.coinage -= coinage;
	}

	@Override
	public void load() {
		try {
			String walkingFile = "/Q4-assets/gfx/entities/mobs/player/walking/";
			// * backward frams
			animationFrames.put(3,
					ImageIO.read(getClass().getResource(walkingFile + "forward/frame1.png")));
			animationFrames.put(3,
					ImageIO.read(getClass().getResource(walkingFile + "forward/frame2.png")));
			animationFrames.put(3,
					ImageIO.read(getClass().getResource(walkingFile + "forward/frame1.png")));
			animationFrames.put(3,
					ImageIO.read(getClass().getResource(walkingFile + "forward/frame3.png")));
			// *forward frams
			animationFrames.put(1,
					ImageIO.read(getClass().getResource(walkingFile + "backward/frame1.png")));
			animationFrames.put(1,
					ImageIO.read(getClass().getResource(walkingFile + "backward/frame2.png")));
			animationFrames.put(1,
					ImageIO.read(getClass().getResource(walkingFile + "backward/frame1.png")));
			animationFrames.put(1,
					ImageIO.read(getClass().getResource(walkingFile + "backward/frame3.png")));

			// * left frams
			animationFrames.put(2, ImageIO.read(getClass().getResource(walkingFile + "left/frame1.png")));
			animationFrames.put(2, ImageIO.read(getClass().getResource(walkingFile + "left/frame2.png")));
			animationFrames.put(2, ImageIO.read(getClass().getResource(walkingFile + "left/frame1.png")));
			animationFrames.put(2, ImageIO.read(getClass().getResource(walkingFile + "left/frame3.png")));

			// * right frams
			animationFrames.put(4, ImageIO.read(getClass().getResource(walkingFile + "right/frame1.png")));
			animationFrames.put(4, ImageIO.read(getClass().getResource(walkingFile + "right/frame2.png")));
			animationFrames.put(4, ImageIO.read(getClass().getResource(walkingFile + "right/frame1.png")));
			animationFrames.put(4, ImageIO.read(getClass().getResource(walkingFile + "right/frame3.png")));

			animationFrames.put(5, ImageIO.read(getClass().getResource(walkingFile + "backward/frame1.png")));
			animationFrames.put(5, ImageIO.read(getClass().getResource(walkingFile + "forward/frame1.png")));
			animationFrames.put(5, ImageIO.read(getClass().getResource(walkingFile + "left/frame1.png")));
			animationFrames.put(5, ImageIO.read(getClass().getResource(walkingFile + "right/frame1.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
