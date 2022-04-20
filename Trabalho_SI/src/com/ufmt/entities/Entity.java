package com.ufmt.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.ufmt.graphics.Spritesheet;

public class Entity extends Rectangle{

	private static final long serialVersionUID = 1L;
	
	public static BufferedImage PlayerShip = Spritesheet.getSprite(0, 0, 32, 32);
	
	private BufferedImage sprite;

	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		super(x,y,width,height);
		this.sprite = sprite;
	}
	
	public void tick() {}
	
	public void render(Graphics g) {
		g.drawImage(sprite, x, y, width, height, null);
	}
}
