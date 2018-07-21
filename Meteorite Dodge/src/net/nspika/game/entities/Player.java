package net.nspika.game.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import net.nspika.game.Handler;
import net.nspika.game.managers.KeyManager;

public class Player {
	
	private float x, y;
	private int width = 40, height = 30;
	private float vx;
	private float ax;
	public static int score = 0;
	Handler handler;
	
	public static Font pixelfont;
	
	public Player(Handler handler) {
		this.handler = handler;
		x = Handler.getWidth() - (Handler.getWidth() + width) / 2 ;
		y = Handler.getHeight() - Handler.getWidth() / 5;
		vx = 8;
	}
	
	public void tick() {
		move();
	}
	
	public void move() {
		Handler.getKeyManager();
		
		if(KeyManager.left) {
			x -= vx;
		}
		if(KeyManager.right) {
			x += vx;
		}
		//vx += ax;
		//x += vx;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) x, (int) y, width, height);
		
	}
	
	//GETTERS AND SETTERS
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
