package net.nspika.game.states;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import net.nspika.game.Handler;
import net.nspika.game.entities.Bullet;
import net.nspika.game.entities.Meteorite;
import net.nspika.game.entities.Player;
import net.nspika.game.utils.Background;
import net.nspika.game.utils.CollisionDetection;

public class GameState extends State {

	private Handler handler;
	private Player player;
	private ArrayList<Bullet> bullets;
	private ArrayList<Meteorite> meteors;
	private Background background;
	private Random random;
	public static boolean shootable = true;
	private int secs = 0;
	private int secs2 = 0;
	
	private int hp = 3;
	private boolean shipHitted = false;
	

	public GameState(Handler handler) {
		super(handler);
		player = new Player(handler);
		background = new Background(handler);
		bullets = new ArrayList<Bullet>();
		meteors = new ArrayList<Meteorite>();
		random = new Random();
	}

	@Override
	public void tick() {
		
		secs++;
		secs2 ++;
		
		player.tick();
		background.tick();

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).tick();
			if (bullets.get(i).getY() + bullets.get(i).getHeight() < 0) {
				bullets.remove(i);
			}
		}
		
		for(int i = 0; i < meteors.size(); i++) {
			meteors.get(i).tick();
			if(meteors.get(i).getY() + meteors.get(i).getHeight() > Handler.getHeight()) {	
				meteors.remove(i);
			}
		}
		
		for(int i = 0; i < meteors.size(); i++) {
			
			 
			 if (CollisionDetection.testMeteoriteShipCollision(meteors.get(i), player)) {
				 meteors.remove(i);
				 shipHitted = true;
			 }
		}
		

		if (Handler.getKeyManager().space) {
			if (shootable) {
				generateBullet();
				shootable = false;
			}
		}
		
		if (secs2 - 20 >= 0) {
			generateMetoer();
			secs2 = 0;
		}


		if (secs - 50 >= 0) {
			secs = 0;
			shootable = true;
		}
		
		reduceHP();
	}

	@Override
	public void render(Graphics g) {
		background.render(g);
		player.render(g);
		
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
		
		for(int i = 0; i < meteors.size(); i++) {
			meteors.get(i).render(g);
		}
		
		g.setColor(Color.BLACK);
		g.drawString("Life:", 10, 80);
		g.setColor(Color.RED);
		for(int i = 0; i < hp; i++) {
			g.fillRect(60 + 50*i, 40, 40, 40);
			
		}
	}

	private void generateBullet() {
		bullets.add(new Bullet(handler, player.getX() + player.getWidth() / 2 - 32 / 2, player.getY()));
	}
	
	private void generateMetoer() {
		int randX = 50 + random.nextInt(Handler.getWidth()); 
		int randSize = 1 + random.nextInt(3);
		int randSpeed = 1 + random.nextInt(5);
		meteors.add(new Meteorite(handler, randX, 0, randSpeed, randSize));
	}
	
	
	
	private void reduceHP() {
		if(shipHitted) {
			hp--;
			shipHitted = false;
		}
	}
}
