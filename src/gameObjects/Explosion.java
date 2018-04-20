package gameObjects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import manager.ImageManager;

public class Explosion extends GameObject{
	
	public static final int TYPE_1 = 1; 
	public static final int TYPE_2 = 2; 
	public static final int WIDHT_IMG = 50;
    public static final int HEIGHT_IMG = 40;
	
	private int type; 
	private int countTime = -1; 
	ArrayList<Image> images; 
	public Explosion (int type, int x, int y){
		this.setType(type); 
		this.x = x; 
		this.y = y; 
		images = new ArrayList<Image>();
		loadImages();
	}
	
	public void loadImages(){
		switch(type){
		case TYPE_1: 
			images.add(ImageManager.getImage("burst_type_1_1.png"));
			images.add(ImageManager.getImage("burst_type_1_2.png"));
			break;
		case TYPE_2: 
			images.add(ImageManager.getImage("burst_type_2_1.png"));
			images.add(ImageManager.getImage("burst_type_2_2.png"));
			images.add(ImageManager.getImage("burst_type_2_3.png"));
		}
		image = images.get(0);
	}
	
	public void changeImageAction(){
		countTime ++; 
		countTime = countTime % (images.size() * 20); 
		setImage(images.get(countTime / 20));
	}
	
	@Override
	public void draw(Graphics2D graphics2d) {
		graphics2d.drawImage(image, this.x, this.y, WIDHT_IMG, HEIGHT_IMG, null);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public void setImage(Image image){
		this.image = image; 
	}
	
	public boolean isFinished(){
		return countTime >= (images.size() * 20 - 1);
	}

}
