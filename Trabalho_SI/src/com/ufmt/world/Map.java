package com.ufmt.world;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Map {
	
	private BufferedImage map;
	
	public Map(String path) {
		try {
			map = ImageIO.read(getClass().getResource(path));	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getMap(int x, int y, int width, int height) {
		return map.getSubimage(x, y, width, height);
	}
}
