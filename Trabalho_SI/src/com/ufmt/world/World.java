package com.ufmt.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.ufmt.main.Game;

public class World {
	
	private final int maxFrame = 60, maxMapImage = 4;
	private int curFrame = 0, curMapImage = 0;
	private BufferedImage[] maps = new BufferedImage[maxMapImage];
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			maps[0] = map.getSubimage(0, 0, 320, 180);
			maps[1] = map.getSubimage(320, 0, 320, 180);
			maps[2] = map.getSubimage(640, 0, 320, 180);
			maps[3] = map.getSubimage(960, 0, 320, 180);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		curFrame++;
		if(curFrame >= maxFrame) {
			curFrame = 0;
			curMapImage++;
			if(curMapImage >= maxMapImage) {
				curMapImage = 0;
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(maps[curMapImage], 0, 0, Game.WIDTH, Game.HEIGHT, null);
	}
}
