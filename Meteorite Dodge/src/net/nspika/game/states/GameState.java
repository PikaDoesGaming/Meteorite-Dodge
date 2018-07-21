package net.nspika.game.states;

import java.awt.Graphics;
import java.util.ArrayList;

import net.nspika.game.Handler;
import net.nspika.game.entities.Bullet;
import net.nspika.game.entities.Player;
import net.nspika.game.utils.Background;

public class GameState extends State {

	private Handler handler;
	private Player player;
	private ArrayList<Bullet> bullets;
	private Background background;
	public static boolean shootable = true;
	private int secs = 0;

	public GameState(Handler handler) {
		super(handler);
		player = new Player(handler);
		background = new Background(handler);
		bullets = new ArrayList<Bullet>();
	}

	@Override
	public void tick() {
		player.tick();
		background.tick();

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).tick();
			if (bullets.get(i).getX() + bullets.get(i).getHeight() < 0) {
				bullets.remove(i);
			}
		}

		if (Handler.getKeyManager().space) {
			if(shootable) {
			generateBullet();
			shootable = false;
			}
		}
		
		secs ++;
		
		if(secs - 50 >= 0) {
			secs = 0;
			shootable = true;
		}
	}

	@Override
	public void render(Graphics g) {
		background.render(g);
		player.render(g);

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
	}

	private void generateBullet() {
		bullets.add(new Bullet(handler, player.getX() + player.getWidth()/2 - 32/2, player.getY()));
	}
}
