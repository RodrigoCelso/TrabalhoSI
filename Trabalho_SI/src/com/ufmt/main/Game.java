package com.ufmt.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.ufmt.entities.Bullet;
import com.ufmt.entities.Entity;
import com.ufmt.entities.Player;
import com.ufmt.graphics.Spritesheet;
import com.ufmt.world.World;

public class Game extends Canvas implements Runnable,KeyListener,MouseListener,MouseMotionListener{

	private static final long serialVersionUID = 1L;
	
	private static JFrame frame;
	private static Thread thread;
	private static BufferedImage image;
	private static World world;
	public static Spritesheet spritesheet;
	private static Player player;
	private static List<Entity> entities;
	public static List<Bullet> bullets;
	
	public static final int WIDTH = 720, HEIGHT = 460;
	
	private boolean isRunning = false;
	
	public Game() {
		addKeyListener(this);
		addMouseListener(this);
		initFrame();
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		spritesheet = new Spritesheet("/spritesheet.png");
		world = new World("/map.png");
		entities = new ArrayList<Entity>();
		bullets = new ArrayList<Bullet>();
		player = new Player((WIDTH/2)-32,(HEIGHT/2)-32,32*2,32*2,3,Entity.PlayerShip);
		entities.add(player);
	}
	
	private void initFrame() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame = new JFrame("Computação Gráfica");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.run();
	}
	
	private void stop() {
		try {
			thread.join(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	private void tick() {
		world.tick();
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).tick();
		}
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = image.getGraphics();
		
		/*Renderizar objetos na tela*/
		world.render(g);
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).render(g);
		}
		
		/*==========================*/
		
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ms = 1000000000 / amountOfTicks;
		double delta = 0;
		int frame = 0;
		double timer = System.currentTimeMillis();
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ms;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frame++;
				delta = 0;
			}
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frame);
				timer += 1000;
				frame = 0;
			}
		}
		stop();
	}



	@Override
	public void keyTyped(KeyEvent e) {
	}



	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
		}else if(e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W) {
			player.up = true;
		}else if(e.getKeyCode() == KeyEvent.VK_S) {
			player.down = true;
		}
	}



	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		}else if(e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;
		}else if(e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		player.isShooting = true;
		player.mx = e.getX();
		player.my = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
}