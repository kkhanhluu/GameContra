package gameObjects;

import java.awt.Graphics2D;

import manager.ImageManager;

public class Life extends GameObject {
	
	public static final int WIDTH = 25; 
	public static final int HEIGHT = 32;
	public Life(int x, int y){
		this.x = x; 
		this.y = y; 
		this.image = ImageManager.getImage("icon_bill_life.png"); 
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(image, x, y, WIDTH, HEIGHT, null);
	}

}
