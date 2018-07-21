package net.nspika.game.managers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import net.nspika.game.Handler;

public class KeyManager implements KeyListener {

	public boolean[] keys;
	public static boolean up, down, left, right, space;
	
	private Handler handler;
	
	public KeyManager(Handler handler) {
		this.handler = handler;
		keys = new boolean[1024];
	}
	
    public void tick() {
    //    up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];

    //    down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];

        left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];

        right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
        
        space = keys[KeyEvent.VK_SPACE];

    }
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public boolean isKeyPressed(int id) {
		return keys[id];
	}
	
	public boolean[] getKeys() {
		return keys;
	}

}
