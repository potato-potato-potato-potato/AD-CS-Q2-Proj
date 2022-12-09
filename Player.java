import load.Loadable;

import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Player extends Tile implements Loadable {
	// public static final int INTERACTION_RADIUS = 100;
	public static final int PLAYER_SPEED = 1;

	public Image texture;

	// private Inventory inventory;
	// private List<Quest> quests;
	// private int frames;
	// private Map<String, Image[]> animationFrames;
	// public boolean isInboat = false;
	// private int direction;

	public Player() {
		super(4);
		load();

	}

	// public Player(int x, int y) {
	// this(new Point(x, y));
	// }

	// public void setIsInboat(boolean isInboat) {
	// this.isInboat = isInboat;
	// }

	// public Inventory getInventory() {
	// return inventory;
	// }
	// public List<Quest> getQuests() {
	// return quests;
	// }

	// public Entity getClosestInteractableEntity(Entities entities) {
	// List<Entity> entityList = entities.getEntityList();

	// Entity closest = null;
	// double closestDistance = -1;
	// for(int i = 0; i < entityList.size(); i++) {
	// Entity entity = entityList.get(i);
	// if(entity == this) continue;
	// if((entity instanceof Interactable) == false) continue;

	// Interactable interactableEntity = (Interactable) entityList.get(i);
	// if(interactableEntity.isInteractable() == false) continue;

	// Point position = entity.getCoordinates();
	// double entityDistance = Math.sqrt(Math.pow(coordinates.x - position.x, 2) +
	// Math.pow(coordinates.y - position.y, 2));
	// if(entityDistance > INTERACTION_RADIUS) continue;

	// if(closest == null) {
	// closest = entity;
	// closestDistance = entityDistance;
	// continue;
	// }

	// if(entityDistance < closestDistance) {
	// closest = entity;
	// }
	// }

	// return closest;
	// }

	// Thread thread1 = new Thread(() -> {
	// int frame = -1;
	// while (true) {
	// try {
	// Thread.sleep(250);
	// if (frame >= 3) {
	// frame = -1;
	// }
	// frame++;

	// setframs(frame);
	// } catch (InterruptedException ex) {
	// Thread.currentThread().interrupt();
	// }
	// }
	// });

	// public void setframs(int f) {
	// frames = f;
	// }

	// @Override
	// public void draw(Graphics g, Point screenLocation) {
	// int currentFrame = frames;
	// if (isInboat) {
	// currentFrame = 0;
	// }
	// if (Input.keyboard[87] || Input.keyboard[38]) { // W, Up Arrow
	// sprite = animationFrames.get("forwardsWalk")[currentFrame];
	// direction = 0;
	// } else if (Input.keyboard[83] || Input.keyboard[40]) { // S, Down Arrow
	// sprite = animationFrames.get("backwardsWalk")[currentFrame];
	// direction = 1;
	// } else if (Input.keyboard[65] || Input.keyboard[37]) { // A, Left Arrow
	// sprite = animationFrames.get("leftWalk")[currentFrame];
	// direction = 2;
	// } else if (Input.keyboard[68] || Input.keyboard[39]) { // D, Right Arrow
	// sprite = animationFrames.get("rightWalk")[currentFrame];
	// direction = 3;
	// } else { // If nothing's happening, play idle animation
	// sprite = animationFrames.get("idle")[direction];
	// }

	// super.draw(g, screenLocation);
	// }

	@Override
	public void load() {
		try {
			String walkingFile = "/Q4-assets/gfx/entities/mobs/player/walking/";
			texture = ImageIO.read(getClass().getResource(walkingFile + "forward/frame1.png"));
			// // * backward frams
			// animationFrames.get("backwardsWalk")[0] = ImageIO
			// .read(getClass().getResource(walkingFile + "forward/frame1.png"));
			// animationFrames.get("backwardsWalk")[1] = ImageIO
			// .read(getClass().getResource(walkingFile + "forward/frame2.png"));
			// animationFrames.get("backwardsWalk")[2] = ImageIO
			// .read(getClass().getResource(walkingFile + "forward/frame1.png"));
			// animationFrames.get("backwardsWalk")[3] = ImageIO
			// .read(getClass().getResource(walkingFile + "forward/frame3.png"));

			// // *forward frams
			// animationFrames.get("forwardsWalk")[0] = ImageIO
			// .read(getClass().getResource(walkingFile + "backward/frame1.png"));
			// animationFrames.get("forwardsWalk")[1] = ImageIO
			// .read(getClass().getResource(walkingFile + "backward/frame2.png"));
			// animationFrames.get("forwardsWalk")[2] = ImageIO
			// .read(getClass().getResource(walkingFile + "backward/frame1.png"));
			// animationFrames.get("forwardsWalk")[3] = ImageIO
			// .read(getClass().getResource(walkingFile + "backward/frame3.png"));

			// // * left frams
			// animationFrames.get("leftWalk")[0] =
			// ImageIO.read(getClass().getResource(walkingFile + "left/frame1.png"));
			// animationFrames.get("leftWalk")[1] =
			// ImageIO.read(getClass().getResource(walkingFile + "left/frame2.png"));
			// animationFrames.get("leftWalk")[2] =
			// ImageIO.read(getClass().getResource(walkingFile + "left/frame1.png"));
			// animationFrames.get("leftWalk")[3] =
			// ImageIO.read(getClass().getResource(walkingFile + "left/frame3.png"));

			// // * right frams
			// animationFrames.get("rightWalk")[0] = ImageIO
			// .read(getClass().getResource(walkingFile + "right/frame1.png"));
			// animationFrames.get("rightWalk")[1] = ImageIO
			// .read(getClass().getResource(walkingFile + "right/frame2.png"));
			// animationFrames.get("rightWalk")[2] = ImageIO
			// .read(getClass().getResource(walkingFile + "right/frame1.png"));
			// animationFrames.get("rightWalk")[2] = ImageIO
			// .read(getClass().getResource(walkingFile + "right/frame1.png"));
			// animationFrames.get("rightWalk")[3] = ImageIO
			// .read(getClass().getResource(walkingFile + "right/frame3.png"));

			// animationFrames.get("idle")[0] =
			// ImageIO.read(getClass().getResource(walkingFile + "backward/frame1.png"));
			// animationFrames.get("idle")[1] =
			// ImageIO.read(getClass().getResource(walkingFile + "forward/frame1.png"));
			// animationFrames.get("idle")[2] =
			// ImageIO.read(getClass().getResource(walkingFile + "left/frame1.png"));
			// animationFrames.get("idle")[3] =
			// ImageIO.read(getClass().getResource(walkingFile + "right/frame1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
