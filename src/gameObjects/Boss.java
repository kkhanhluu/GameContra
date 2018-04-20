package gameObjects;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Boss extends GameObject{
	
	public static final int MAX_TIME = 2000;	
	private int maxValue; 
	private int countTime; 
	private int damage; 
	private Rectangle rectangle;
	public Boss (int x, int y, int maxValue){
		this.x = x; 
		this.y = y; 
		this.maxValue = maxValue; 
		rectangle = new Rectangle(x, y, 15, 15);
		damage = 0; 
	}
	
	@Override
	public void draw(Graphics2D graphics2d) {
	}
	
	public int getCountTime() {
		return countTime;
	}
	
	public void setCountTime(int countTime) {
		this.countTime = countTime;
	}
	
	public int getMaxValue() {
		return maxValue;
	}
	
	public Rectangle getRectangle(){
		return rectangle; 
	}
	
	public boolean isExploded(){
		return damage > maxValue; 
	}
	
	public void setDamage(int damage){
		this.damage = damage;
	}
	
	public int getDamage(){
		return this.damage;
	}
	
	@Override
	public void setX(int x){
		this.x = x; 
		rectangle.setLocation(x, this.y);
	}

}
