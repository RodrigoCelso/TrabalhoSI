package com.ufmt.entities;

import java.awt.image.BufferedImage;

import com.ufmt.main.Game;
import com.ufmt.world.World;

public class Player extends Entity{

	private static final long serialVersionUID = 1L;
	
	public boolean right, left, up, down;
	public boolean isShooting = false;
	
	private int speed;
	public int mx, my;
	
	public Player(int x, int y, int width, int height, int speed, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		this.speed = speed;
	}
	
	public void tick() {
		if(right) {
			x+=speed;
		}else if(left) {
			x-=speed;
		}
		
		if(up) {
			y-=speed;
		}else if(down) {
			y+=speed;
		}
		
		if(isShooting){
			isShooting = false;
			// Se puder atirar
			double angle = Math.atan2(my - (getY() + 32), mx - (getX() + 32));
			double dx = Math.cos(angle);
			double dy = Math.sin(angle);
			// px e py serve pra mudar a posição de spawn do tiro
			int px = 32, py = 32;
			Bullet bullet = new Bullet(x + px, y + py, 5, 5, 4, null,dx,dy);
			Game.bullets.add(bullet);
		}
	}
}
