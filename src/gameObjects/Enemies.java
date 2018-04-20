package gameObjects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import manager.ImageManager;

public class Enemies extends GameObject {

	private int type;
	private int line;
	private int orient;
	private ArrayList<Image>[] arrImgEnemiesActions;
	private int[] countArr;
	private int time;
	private int hp;
	private int damage = 0;
	private int countTime = -1;
	private int typeBullet;
	private Rectangle rectangle;

	public Enemies(int type, int orient, int x, int line) {
		this.type = type;
		this.orient = orient;
		this.x = x;
		this.line = line;
		this.y = line - ImageManager.getHeightImage(type, orient);
		initializeComponents();
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
	
	private void initializeComponents() {
		arrImgEnemiesActions = new ArrayList[NUMBER_ORIENT];
		for (int i = 0; i < NUMBER_ORIENT; i++) {
			arrImgEnemiesActions[i] = new ArrayList<>();
		}

		countArr = new int[NUMBER_ORIENT];
		for (int i = 0; i < NUMBER_ORIENT; i++) {
			countArr[i] = -1;
		}

		switch (type) {
		case SOLDIER: {
			initalizesoldier();
			break;
		}
		case SNIPER: {
			initalizeSniper();
			break;
		}
		case WALL_TURRET: {
			initalizeWallTurret();
			break;
		}
		case CANNON: {
			initalizeCannon();
			break;
		}
		default:
		}
		image = arrImgEnemiesActions[orient].get(0);
		rectangle = new Rectangle(this.x, this.y, ImageManager.getWidthImage(type, orient),
				ImageManager.getHeightImage(type, orient));
	}

	public void initalizeWallTurret() {
		arrImgEnemiesActions[DIAGONAL_DOWN_LEFT].add(ImageManager.getImage("wall_turret_fire_diagonal_down_left.png"));
		arrImgEnemiesActions[DIAGONAL_RIGHT_DOWN]
				.add(ImageManager.getImage("wall_turret_fire_diagonal_down_right.png"));
		arrImgEnemiesActions[DIAGONAL_LEFT_UP].add(ImageManager.getImage("wall_turret_fire_diagonal_up_left.png"));
		arrImgEnemiesActions[DIAGONAL_UP_RIGHT].add(ImageManager.getImage("wall_turret_fire_diagonal_up_right.png"));
		arrImgEnemiesActions[DOWN].add(ImageManager.getImage("wall_turret_fire_down.png"));
		arrImgEnemiesActions[FIRE_LEFT].add(ImageManager.getImage("wall_turret_fire_left.png"));
		arrImgEnemiesActions[FIRE_RIGHT].add(ImageManager.getImage("wall_turret_fire_right.png"));
		arrImgEnemiesActions[UP].add(ImageManager.getImage("wall_turret_fire_up.png"));
		setTime(2000);
		hp = 15;
		setTypeBullet(Bullet.TYPE_1);
	}

	public void initalizeCannon() {
		arrImgEnemiesActions[DIAGONAL_DOWN_LEFT].add(ImageManager.getImage("cannon_fire_diagonal_down_left.png"));
		arrImgEnemiesActions[DIAGONAL_RIGHT_DOWN].add(ImageManager.getImage("cannon_fire_diagonal_down_right.png"));
		arrImgEnemiesActions[DIAGONAL_LEFT_UP].add(ImageManager.getImage("cannon_fire_diagonal_up_left.png"));
		arrImgEnemiesActions[DIAGONAL_UP_RIGHT].add(ImageManager.getImage("cannon_fire_diagonal_up_right.png"));
		arrImgEnemiesActions[DOWN].add(ImageManager.getImage("cannon_fire_down.png"));
		arrImgEnemiesActions[FIRE_LEFT].add(ImageManager.getImage("cannon_fire_left.png"));
		arrImgEnemiesActions[FIRE_RIGHT].add(ImageManager.getImage("cannon_fire_right.png"));
		arrImgEnemiesActions[UP].add(ImageManager.getImage("cannon_fire_up.png"));
		setTime(2500);
		hp = 20;
		setTypeBullet(Bullet.TYPE_2);
	}

	public void initalizesoldier() {
		arrImgEnemiesActions[FIRE_RIGHT].add(ImageManager.getImage("soldier_fire_right.png"));
		arrImgEnemiesActions[FIRE_LEFT].add(ImageManager.getImage("soldier_fire_left.png"));
		arrImgEnemiesActions[RIGHT_DOWN].add(ImageManager.getImage("soldier_lie_fire_right.png"));
		arrImgEnemiesActions[DOWN_LEFT].add(ImageManager.getImage("soldier_lie_fire_left.png"));
		for (int i = 1; i <= 4; i++) {
			arrImgEnemiesActions[RUN_RIGHT].add(ImageManager.getImage("soldier_run_right_" + i + ".png"));
			arrImgEnemiesActions[RUN_LEFT].add(ImageManager.getImage("soldier_run_left_" + i + ".png"));
		}
		setTime(1500);
		hp = 2;
		setTypeBullet(Bullet.TYPE_1);
	}

	public void initalizeSniper() {
		arrImgEnemiesActions[DIAGONAL_DOWN_LEFT].add(ImageManager.getImage("sniper_fire_diagonal_down_left.png"));
		arrImgEnemiesActions[DIAGONAL_RIGHT_DOWN].add(ImageManager.getImage("sniper_fire_diagonal_down_right.png"));
		arrImgEnemiesActions[DIAGONAL_LEFT_UP].add(ImageManager.getImage("sniper_fire_diagonal_up_left.png"));
		arrImgEnemiesActions[DIAGONAL_UP_RIGHT].add(ImageManager.getImage("sniper_fire_diagonal_up_right.png"));
		arrImgEnemiesActions[FIRE_LEFT].add(ImageManager.getImage("sniper_fire_left.png"));
		arrImgEnemiesActions[FIRE_RIGHT].add(ImageManager.getImage("sniper_fire_right.png"));
		setTime(1500);
		hp = 5;	
		setTypeBullet(Bullet.TYPE_0);
	}

	public void changeEnemiesAction() {
		countArr[orient]++;
		countArr[orient] = countArr[orient] % (arrImgEnemiesActions[orient].size() * 10);
		setImage(arrImgEnemiesActions[orient].get(countArr[orient] / 10));
	}

	public void move() {
		if (type == SOLDIER) {
			switch (orient) {
			case RUN_RIGHT:
				x += 1;
				break;
			case RUN_LEFT:
				x -= 1;
				break;
			}
		}
		rectangle.setLocation(x, y);
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(image, x, y, ImageManager.getWidthImage(type, orient), ImageManager.getHeightImage(type, orient),
				null);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getorient() {
		return orient;
	}

	public void setorient(int orient) {
		this.orient = orient;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getTypeBullet() {
		return typeBullet;
	}

	public void setTypeBullet(int typeBullet) {
		this.typeBullet = typeBullet;
	}

	public int getCountTime() {
		return countTime;
	}

	public void setCountTime(int countTime) {
		this.countTime = countTime;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	
	public boolean isDied(){
		return damage > hp; 
	}
}
