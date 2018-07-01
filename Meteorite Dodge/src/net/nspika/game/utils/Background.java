package net.nspika.game.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import net.nspika.game.Handler;

public class Background {
	
	private Handler handler;
	private Timer timer;
	
	private int x, y1, y2, width, height1;
	
	private BufferedImage img1, img2;
	
	public Background(Handler handler) {
		this.handler = handler;
		width = Handler.getWidth();
		height1 = Handler.getHeight();
		img1 = ImageLoader.loadImage("/Lel.png");
		img2 = ImageLoader.loadImage("/Lel.png");
		y2 = -Handler.getHeight();
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				
				y1 += 2;
				y2 += 2;
				
				if(y1 >= Handler.getHeight()) {
					y1 = 0;
				}
				if(y2 >= 0) {
					y2 = 0 - Handler.getHeight();
				}
				
			}
		}, 50, 50);
	}
	
	
	public void render(Graphics g) {
		g.drawImage(img1, x, y1, width, height1, null);
		g.drawImage(img2, x, y2, width, height1, null);
	}
}
