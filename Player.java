import load.Loadable;

import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Player implements Loadable {

	public boolean isInboat = false;
	protected Image sprite;
	private Dimension size = new Dimension(64, 97);
	private Coordinate coordinates;

	public Player() {
		// this.coordinates = coordinates;

		load();
	}

	public void setIsInboat(boolean isInboat) {
		this.isInboat = isInboat;
	}

	public void draw(Graphics g) {
		g.drawImage(sprite, (400 - size.width / 2) + 15, (300 - size.height / 2) + 15, size.width, size.height, null);

	}

	public Coordinate getCoordinates() {
		return coordinates;
	}

	@Override
	public void load() {
		try {
			String walkingFile = "/Q4-assets/gfx/entities/mobs/player/walking/";
			sprite = ImageIO.read(getClass().getResource(walkingFile + "forward/frame1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
