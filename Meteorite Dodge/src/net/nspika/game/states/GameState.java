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
import net.nspika.game.utils.Fonts;

public class GameState extends State {

	private Handler handler;
	private Player player;
	private ArrayList<Bullet> bullets;
	private ArrayList<Meteorite> meteors;
	private Background background;
	private Random random;
	private State deathState;
	public static boolean shootable = true;
	private int secs = 0;
	private int secs2 = 0;
	private int secs3 = 0;

	private int score = 0;

	private int hp = 3;
	private boolean shipHitted = false;
	private int ammo;

	public GameState(Handler handler) {
		super(handler);
		player = new Player(handler);
		background = new Background(handler);
		bullets = new ArrayList<Bullet>();
		meteors = new ArrayList<Meteorite>();
		random = new Random();
		ammo = 5;
	}

	@Override
	public void tick() {
		if (player.getAlive()) {

			player.tick();
			
			background.tick();
			secs++;
			secs2++;
			secs3++;
			
			if (Handler.getKeyManager().space) {
				if (shootable && ammo > 1) {
					generateBullet();
					shootable = false;
				}
			}
			
			for (int i = 0; i < bullets.size(); i++) {
				bullets.get(i).tick();
				if (bullets.get(i).getY() + bullets.get(i).getHeight() < 0) {
					bullets.remove(i);
				}
			}

			for (int i = 0; i < meteors.size(); i++) {
				meteors.get(i).tick();
				if (meteors.get(i).getY() > Handler.getHeight()) {
					meteors.remove(i);
					score++;
				}
			}

			for (int i = 0; i < meteors.size(); i++) {
				for(int j = 0; j < bullets.size(); j++) {
					if(CollisionDetection.testMeteoriteBulletCollision(meteors.get(i), bullets.get(j))){
						meteors.remove(i);
						bullets.remove(j);
					}
				}
			}
			
			
			for (int i = 0; i < meteors.size(); i++) {
				if (CollisionDetection.testMeteoriteShipCollision(meteors.get(i), player)) {
					meteors.remove(i);
					shipHitted = true;
				}
			}

			if (secs - 50 >= 0) {
				secs = 0;
				shootable = true;
			}

			if (secs3 - 200 >= 0 && ammo <= 10) {
				secs3 = 0;
				ammo++;
			}

			if (shipHitted) {
				player.reduceHP(1);
				shipHitted = false;
			}
			
			if (secs2 - 20 >= 0) {
				generateMetoer();
				secs2 = 0;
			}
		}
		
		if (player.getHP() == 0) {
			State.setState(Handler.getGame().deathState);
		}
	}

	@Override
	public void render(Graphics g) {
		background.render(g);
		player.render(g);


		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}

		for (int i = 0; i < meteors.size(); i++) {
			meteors.get(i).render(g);
		}

		g.setColor(Color.WHITE);
		Fonts.drawLeftAllinedText(g, "Ammo", 10, 200, Fonts.playerNameFont);
		
		
		for (int i = 0; i < player.getHP(); i++) {
			g.setColor(Color.ORANGE);
			g.fillRect(9 + 80 * i, 29, 72, 72);
			g.setColor(Color.RED);
			g.fillRect(10 + 80 * i, 30, 70, 70);
		}

		for (int i = 1; i < ammo; i++) {
			g.setColor(Color.ORANGE);
			g.fillRect(17, 200 + 20 * i, 42, 12);
			g.setColor(Color.BLACK);
			g.fillRect(18, 201 + 20 * i, 40, 10);
		}
		
		g.setColor(Color.RED);
		Fonts.drawCenteredText(g, Integer.toString(score), Handler.getWidth()/2, Handler.getHeight()/8, Fonts.scoreFont);
	}

	private void generateBullet() {
		ammo--;
		bullets.add(new Bullet(handler, player.getX() + player.getWidth() / 2 - 32 / 2, player.getY()));

	}

	private void generateMetoer() {
		int randX = random.nextInt(Handler.getWidth() - 20);
		int randSize = 1 + random.nextInt(3);
		int randSpeed = 1 + random.nextInt(5);
		meteors.add(new Meteorite(handler, randX, 0, randSpeed, randSize));
	}

}
