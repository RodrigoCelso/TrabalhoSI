package com.ufmt.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.ufmt.main.Game;

public class Bullet extends Entity{
	
	private int speed;
	private double dx, dy;
	private int range = 125, curRange = 0;
	
	public Bullet(int x, int y, int width, int height, int speed, BufferedImage sprite, double dx, double dy) {
		super(x, y, width, height, sprite);
		this.dx = dx;
		this.dy = dy;
		this.speed = speed;
	}
	
	public void tick() {
		x += (int)(dx*speed);
		y += (int)(dy*speed);
		
		curRange++;
		if(curRange >= range) {
			Game.bullets.remove(this);
			//Fazer a bala explodindo
			
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillOval(x, y, width, height);
	}
}
