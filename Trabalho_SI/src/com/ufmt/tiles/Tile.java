package com.ufmt.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.ufmt.main.Game;

public class Tile {
	
	public static final int TILE_SIZE = 16;
	
	// Declarar sprites dos tiles aqui
	public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(0, 0, TILE_SIZE, TILE_SIZE);
	
	private BufferedImage sprite;
	private int x, y;
	
	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, x, y, null);
	}
}
