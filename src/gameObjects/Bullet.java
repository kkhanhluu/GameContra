package gameObjects;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import manager.ImageManager;

public class Bullet extends GameObject {
	
	public static final int SIZE_1 = 10; 
	public static final int SIZE_3 = 15; 
	public static final int TYPE_0 = 0;
	public static final int TYPE_1 = 1;
	public static final int TYPE_2 = 2;
	public static final int TYPE_3 = 3;
	
	private int orientation; 
	private Rectangle rectangle;
	private int ID; 
	private int size;
	private int damage; 
	
	public Bullet (int ID, int x, int y, int orientation){
		this.setID(ID);
		this.x = x; 
		this.y = y; 
		this.setOrientation(orientation); 
		this.image = ImageManager.getImage("bullet_" + ID + ".png");
		if (ID == 3){
			size = SIZE_3; 
			setDamage(3); 
		}
		else if (ID == 2){
			setDamage(2); 
			size = SIZE_1;
		}
		else {
			setDamage(1); 
			size = SIZE_1; 
		}
		this.rectangle = new Rectangle(x, y, size, size);
	}
	
	public int getOrientation() {
		return orientation;
	}


	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	
	public Rectangle getRectangle(){
		return rectangle;
	}
	
	@Override
	public void draw(Graphics2D graphics2d){
		graphics2d.drawImage(image, x, y, size, size, null);
	}

	public void move() {
		switch(getOrientation()){
			case GameObject.UP:
				y --; 
				break;
			case GameObject.LEFT_UP:
				x -= 0.5; 
				y ++; 
				break;
			case GameObject.UP_RIGHT:
				y --; 
				break;
			case GameObject.DIAGONAL_UP_RIGHT: 
				x ++; 
				y --; 
				break; 
			case GameObject.RUN_RIGHT: 
				x ++; 
				break;
			case GameObject.FIRE_RIGHT: 
				x ++; 
				break;
			case GameObject.DIAGONAL_RIGHT_DOWN: 
				x ++; 
				y ++; 
				break;
			case GameObject.RIGHT_DOWN: 
				x ++; 
				break;
			case GameObject.DOWN_LEFT: 
				x --; 
				break;
			case GameObject.DOWN: 
				y ++; 
				break;
			case GameObject.DIAGONAL_DOWN_LEFT: 
				x --; 
				y ++; 
				break;
			case GameObject.FIRE_LEFT:
				x --; 
				break; 
			case GameObject.RUN_LEFT:
				x --; 
				break;
			case GameObject.DIAGONAL_LEFT_UP: 
				x --; 
				y --; 
				break;
			case GameObject.JUMP_RIGHT: 
				x ++; 
				break;
			case GameObject.JUMP_LEFT: 
				x --; 
				break;
				
		}
		rectangle.setLocation(x, y);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
}
