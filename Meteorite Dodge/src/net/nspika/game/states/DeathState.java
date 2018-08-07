package net.nspika.game.states;

import java.awt.Color;
import java.awt.Graphics;

import net.nspika.game.Handler;

public class DeathState extends State {

	public DeathState(Handler handler) {
		super(handler);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Handler.getWidth(), Handler.getHeight());
		g.setColor(Color.CYAN);
		g.drawString("You died lol", Handler.getWidth()/2, Handler.getHeight() /2);
	}

}
