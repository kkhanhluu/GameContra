package gameObjects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import manager.ImageManager;

public class Item extends GameObject {

	public static final int TYPE_1 = 2; 
	public static final int TYPE_2 = 3; 
	public static final int SIZE_1 = 60;
	public static final int SIZE_2 = 30; 
	public static final int HP = 5; 
	private int type; 
	private int line; 
	private int damage; 
	private int height; 
	private int width;
	private ArrayList<Image> images;
	private Rectangle rectangle;
	private int countTime = -1;

	public Item(int type, int x, int line){
		this.setType(type); 
		this.x = x;
		this.setLine(line);
		this.y = line - SIZE_1; 
		this.width = SIZE_1; 
		this.height = SIZE_1; 
		rectangle = new Rectangle (this.x, this.y, width, height);
		loadImages();
	}

	public void loadImages(){
		images = new ArrayList<Image>();
		images.add(ImageManager.getImage("box_item_1.png"));
		images.add(ImageManager.getImage("box_item_2.png"));
		images.add(ImageManager.getImage("box_item_3.png"));
		if (type == TYPE_1)
			images.add(ImageManager.getImage("item_m.png"));
		else {
			images.add(ImageManager.getImage("item_r.png"));
		}
		this.image = images.get(0);
	}

	public void changeImageAction(){
		if (damage > HP){
			setImage (images.get(3));
			width = SIZE_2; 
			height = SIZE_2; 
			return;
		}
		else {
			countTime ++; 
			countTime = countTime % ((images.size() - 1) * 20);
			setImage(images.get(countTime / 20));	
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(image, this.x, this.y, width, height, null);
	}

	public void setImage(Image image){
		this.image = image; 
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}
	
	@Override 
	public void setX(int x){
		this.x = x; 
		rectangle.setLocation(x, this.y);
	}
	
	@Override 
	public void setY(int y){
		this.y = y; 
		rectangle.setLocation(this.x, y);
	}
	
	public boolean isExploded(){
		return damage > HP; 
	}

}
