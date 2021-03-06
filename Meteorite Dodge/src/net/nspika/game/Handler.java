package net.nspika.game;

import net.nspika.game.entities.Bullet;
import net.nspika.game.entities.Player;
import net.nspika.game.gfx.Display;
import net.nspika.game.managers.KeyManager;
import net.nspika.game.managers.MouseManager;

public class Handler {
	
	public static Game game;
	public static Player player;
	public static Display display;
	public static KeyManager keyManager;
	public static MouseManager mouseManager;
	public static Bullet bullet;
	
	public Handler(Game game) {
		Handler.game = game;
	}
	
	public static Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		Handler.game = game;
	}
	
	public static Display getDisplay() {
		return display;
	}
	
	public static KeyManager getKeyManager() {
		return keyManager;
	}
	
	public static Player getPlayer() {
		return player;
	}
	
	public static int getWidth() {
		return Game.width;
	}
	
	public static int getHeight() {
		return Game.height;
	}
	
	public static Bullet getBullet() {
		return bullet;
	}
	
	public static MouseManager getMouseManager() {
		return mouseManager;
	}

}
