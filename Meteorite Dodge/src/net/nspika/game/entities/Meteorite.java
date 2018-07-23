package net.nspika.game.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import net.nspika.game.Handler;

public class Meteorite {
	private float x, y;
	private int width, height;
	private int size;
	private float vy;
	private Handler handler;

	public Meteorite(Handler handler, float x, float y, int speed, int size) {
		this.handler = handler;
		width = 20*size;
		height = 20*size;
		vy = speed;
		this.y = y;
		this.x = x;
	}
	
	public void tick() {
		y += vy;
	}

	public void render(Graphics g) {
		
		g.setColor(Color.GRAY);
		g.fillRect(((int) x), ((int) y), width, height);
		
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
}
