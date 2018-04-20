package gameObjects;

import java.awt.Graphics2D;

import GUI.Frame;
import manager.ImageManager;

public class Map extends GameObject {
	
	public static final int WIDTH_MAP = 8330; 
	public static final int HEIGHT_MAP = Frame.HEIGHT - 28;
	public static final int X_LAST_MAP = -7537;
	
	public Map(){
		image = ImageManager.getImage("map_1.png");
		x = 0; 
		y = 0; 
	}
	
	public void move(){
		x --;
	}
	@Override
	public void draw(Graphics2D graphics2d) {
		graphics2d.drawImage(image, x, y, WIDTH_MAP, HEIGHT_MAP, null);
	}

}
