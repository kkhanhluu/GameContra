package manager;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;

import gameObjects.Enemies;
import gameObjects.Explosion;
import GUI.Frame;
import Sound.SoundManager;
import gameObjects.Bill;
import gameObjects.Boss;
import gameObjects.Bullet;
import gameObjects.GameObject;
import gameObjects.Item;
import gameObjects.Life;
import gameObjects.LineMap;
import gameObjects.Map;

public class GameManager {

	private Bill bill;
	private Map map;
	private ArrayList<LineMap> arrLineMap;
	private ArrayList<Bullet> arrBullet;
	private ArrayList<Bullet> arrEnemiesBullet;
	private ArrayList<Enemies> arrEnemies;
	private ArrayList<Item> arrItem;
	private ArrayList<Life> arrLife;
	private ArrayList<Boss> arrBoss;
	private Boss finalBoss = new Boss (8083, 411, 50);
	private ArrayList<Explosion> arrExplosion;
	private int countTimeBullet = -1;
	private int score;
	private int nrOfLife;
	private int[][] dBulletX = { { 0, 25, 56, 0, 62, 55, 89, 0, -10, -9, 0, -10, -10, 5 },
			{ 0, 0, 0, 0, 62, 0, 90, 0, -9, 0, 0, -11, 0, 0 }, { 0, 0, 57, 0, 63, 57, 0, 0, 0, -11, 0, -11, -11, 0 },
			{ 35, 0, 66, 0, 79, 65, 0, 35, 0, 4, 0, -5, 3, 0 }, { 35, 0, 73, 0, 83, 72, 0, 35, 0, -2, 0, -12, -2, 0 } };
	private int[][] dBulletY = { { 0, -9, -4, 0, 20, 41, 12, 0, 12, 42, 0, 20, -5, -10 },
			{ 0, 0, 0, 0, 11, 0, 13, 0, 13, 0, 0, 11, 0, 0 }, { 0, 0, -9, 0, 5, 35, 0, 0, 0, 35, 0, 5, -10, 0 },
			{ -8, 0, 6, 0, 35, 65, 0, 79, 0, 65, 0, 35, 5, 0 }, { -11, 0, -2, 0, 31, 70, 0, 82, 0, 73, 0, 32, -2, 0 } };

	public GameManager() {
		bill = new Bill(114, 100);
		arrLineMap = new ArrayList<LineMap>();
		arrBullet = new ArrayList<Bullet>();
		arrEnemiesBullet = new ArrayList<Bullet>();
		arrEnemies = new ArrayList<Enemies>();
		arrItem = new ArrayList<Item>();
		arrLife = new ArrayList<Life>();
		arrBoss = new ArrayList<Boss>();
		arrExplosion = new ArrayList<Explosion>();
		score = 0;
		nrOfLife = 3;
		for (int i = 0; i < nrOfLife; i++) {
			arrLife.add(new Life(i * Life.WIDTH, 20));
		}
		loadMap();
		loadEnemies();
		loadBoss();
	}

	public void changeBillImageAction() {
		bill.changeImgeAction();
	}

	public void changeBillOrientation(int orientation) {
		bill.setOrientation(orientation);
	}

	public int getBillOrientation() {
		return bill.getOrientation();
	}

	public void changeBillX(int x) {
		bill.setX(x);
	}

	public int getBillX() {
		return bill.getX();
	}

	public int getBillY() {
		return bill.getY();
	}

	public void changeBillY(int y) {
		bill.setY(y);
	}

	public void changeBillLine(int line) {
		bill.setLine(line);
	}

	public int getBillLine() {
		return bill.getLine();
	}

	public boolean isBillJumpingUp() {
		return bill.isJumpUp();
	}

	public void changeBillJumpUp(boolean isJump) {
		bill.setJumpUp(isJump);
	}

	public void changeBillJumpDown(boolean isJumpDown) {
		bill.setJumpDown(isJumpDown);
	}

	public boolean isBillJumpingDown() {
		return bill.isJumpDown();
	}

	public boolean isBillMovingLeft() {
		int currOrient = bill.getOrientation();
		return (currOrient == GameObject.DIAGONAL_DOWN_LEFT || currOrient == GameObject.DIAGONAL_LEFT_UP
				|| currOrient == GameObject.LEFT_UP || currOrient == GameObject.FIRE_LEFT
				|| currOrient == GameObject.RUN_LEFT || currOrient == GameObject.JUMP_LEFT
				|| currOrient == GameObject.DOWN_LEFT);
	}

	public boolean isBillMovingRight() {
		int currOrient = bill.getOrientation();
		return (currOrient == GameObject.DIAGONAL_RIGHT_DOWN || currOrient == GameObject.DIAGONAL_UP_RIGHT
				|| currOrient == GameObject.UP_RIGHT || currOrient == GameObject.FIRE_RIGHT
				|| currOrient == GameObject.RUN_RIGHT || currOrient == GameObject.JUMP_RIGHT
				|| currOrient == GameObject.RIGHT_DOWN);
	}

	public void moveBill() {
		bill.move();
	}

	public void drawBill(Graphics2D g) {
		bill.draw(g);
	}

	public void changeBillImage(Image image) {
		bill.setImage(image);
	}

	public void setBillCountJump(int countJump) {
		bill.setCountJump(countJump);
	}

	public void loadMap() {
		map = new Map();
		arrLineMap.clear();
		arrEnemies.clear();
		arrBoss.clear(); 
		arrBullet.clear(); 
		arrEnemiesBullet.clear();
		java.io.File map = new java.io.File("F:\\Informatik\\Java\\Java Workspace\\Contra\\src\\res\\maps\\map_1.txt");
		try {
			Scanner input = new Scanner(map);
			while (input.hasNext()) {
				String s = input.nextLine();
				int vt1 = s.indexOf(" ");
				int y = Integer.parseInt(s.substring(0, vt1));
				int vt2 = s.lastIndexOf(" ");
				int xLeft = Integer.parseInt(s.substring(vt1 + 1, vt2));
				int xRight = Integer.parseInt(s.substring(vt2 + 1));
				LineMap linemap = new LineMap(xLeft, xRight, y);
				arrLineMap.add(linemap);
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void loadEnemies() {
		java.io.File file = new java.io.File(
				"F:\\Informatik\\Java\\Java Workspace\\Contra\\src\\res\\enemies\\enemies_1.txt");
		try {
			Scanner input = new Scanner(file);
			while (input.hasNext()) {
				String str = input.nextLine();
				int type = Integer.parseInt(str.substring(0, 1));
				int vt1 = str.indexOf(" ");
				int vt3 = str.lastIndexOf(" ");
				int vt2 = 0;
				for (int i = vt1 + 1; i <= vt3 - 1; i++) {
					if (str.charAt(i) == ' ') {
						vt2 = i;
						break;
					}
				}
				int orientation = Integer.parseInt(str.substring(vt1 + 1, vt2));
				int x = Integer.parseInt(str.substring(vt2 + 1, vt3));
				int line = Integer.parseInt(str.substring(vt3 + 1));
				if (type == GameObject.ITEM) {
					arrItem.add(new Item(orientation, x, line));
				} else {
					arrEnemies.add(new Enemies(type, orientation, x, line));
				}
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void loadBoss() {
		arrBoss.add(new Boss(8050, 307, 30));
		arrBoss.add(new Boss(8110, 307, 30));
		arrBoss.add(finalBoss);
	}

	public int checkBillLine() {
		for (int i = 0; i < arrLineMap.size(); i++) {
			LineMap lineMap = arrLineMap.get(i);
			if (lineMap.getY() == bill.getY() + ImageManager.getHeightImage(GameObject.BILL, bill.getOrientation())
			&& lineMap.getxLeft() <= bill.getX() && bill.getX() <= lineMap.getxRight()) {
				return lineMap.getY();
			}
		}
		return -1;
	}

	private boolean checkEnemiesLine(Enemies enemies) {
		for (int i = 0; i < arrLineMap.size(); i++) {
			LineMap lineMap = arrLineMap.get(i);
			if (lineMap.getY() == enemies.getLine() && lineMap.getxLeft() <= enemies.getX()
					&& enemies.getX() <= lineMap.getxRight()) {
				return true;
			}
		}
		return false;
	}

	public void setBillDefault() {
		if (isBillMovingRight()) {
			changeBillOrientation(GameObject.FIRE_RIGHT);
			changeBillImage(ImageManager.getImage("bill_run_fire_right_1.png"));
		}
		if (isBillMovingLeft()) {
			changeBillOrientation(GameObject.FIRE_LEFT);
			changeBillImage(ImageManager.getImage("bill_run_fire_left_1.png"));
		}
		changeBillY(getBillLine() - ImageManager.getHeightImage(GameObject.BILL, getBillOrientation()));
	}

	public void handleMoveBill(BitSet bitset) {
		if (getBillLine() == -1) {
			changeBillY(getBillY() + 1);
			if (bitset.get(KeyEvent.VK_D)) {
				moveBillOrMapInJump();
			}
			if (bitset.get(KeyEvent.VK_A) && getBillX() > 0) {
				changeBillX(getBillX() - 1);
			}
			if (isBillJumpingDown()) {
				changeBillImageAction();
			}
			changeBillLine(checkBillLine());
			if (getBillLine() > -1) {
				changeBillJumpDown(false);
				setBillDefault();
			}
		} else if (isBillJumpingUp()) {
			if (isBillMovingRight()) {
				changeBillOrientation(Bill.JUMP_RIGHT);
			} else {
				changeBillOrientation(Bill.JUMP_LEFT);
			}
			moveBill();
			if (bitset.get(KeyEvent.VK_D)) {
				moveBillOrMapInJump();
			}
			if (bitset.get(KeyEvent.VK_A) && getBillX() > 0) {
				changeBillX(getBillX() - 1);
			}
			changeBillImageAction();
			if (isBillJumpingDown()) {
				changeBillLine(checkBillLine());
			}
		} else if (bitset.get(KeyEvent.VK_W) && bitset.get(KeyEvent.VK_D)) {
			changeBillOrientation(Bill.DIAGONAL_UP_RIGHT);
			changeBillY(getBillLine() - ImageManager.getHeightImage(GameObject.BILL, getBillOrientation()));
			moveBillOrMap();
			changeBillImageAction();
			changeBillLine(checkBillLine());
		} else if (bitset.get(KeyEvent.VK_D) && bitset.get(KeyEvent.VK_S)) {
			changeBillOrientation(Bill.DIAGONAL_RIGHT_DOWN);
			changeBillY(getBillLine() - ImageManager.getHeightImage(GameObject.BILL, getBillOrientation()));
			moveBillOrMap();
			changeBillImageAction();
			changeBillLine(checkBillLine());
		} else if (bitset.get(KeyEvent.VK_S) && bitset.get(KeyEvent.VK_A)) {
			changeBillOrientation(Bill.DIAGONAL_DOWN_LEFT);
			changeBillY(getBillLine() - ImageManager.getHeightImage(GameObject.BILL, getBillOrientation()));
			moveBill();
			changeBillImageAction();
			changeBillLine(checkBillLine());
		} else if (bitset.get(KeyEvent.VK_A) && bitset.get(KeyEvent.VK_W)) {
			changeBillOrientation(Bill.DIAGONAL_LEFT_UP);
			changeBillY(getBillLine() - ImageManager.getHeightImage(GameObject.BILL, getBillOrientation()));
			moveBill();
			changeBillImageAction();
			changeBillLine(checkBillLine());

		} else if (bitset.get(KeyEvent.VK_W)) {
			if (isBillMovingRight()) {
				changeBillOrientation(Bill.UP_RIGHT);
			} else {
				changeBillOrientation(Bill.LEFT_UP);
			}
			changeBillY(getBillLine() - ImageManager.getHeightImage(GameObject.BILL, getBillOrientation()));
			moveBill();
			changeBillImageAction();
			changeBillLine(checkBillLine());
		} else if (bitset.get(KeyEvent.VK_D)) {
			if (bitset.get(KeyEvent.VK_K)) {
				changeBillOrientation(Bill.FIRE_RIGHT);
			} else {
				changeBillOrientation(Bill.RUN_RIGHT);
			}
			changeBillY(getBillLine() - ImageManager.getHeightImage(GameObject.BILL, getBillOrientation()));
			moveBillOrMap();
			changeBillImageAction();
			changeBillLine(checkBillLine());
		} else if (bitset.get(KeyEvent.VK_S)) {
			if (isBillMovingRight()) {
				changeBillOrientation(Bill.RIGHT_DOWN);
			} else {
				changeBillOrientation(Bill.DOWN_LEFT);
			}
			changeBillY(getBillLine() - ImageManager.getHeightImage(GameObject.BILL, getBillOrientation()));
			moveBill();
			changeBillImageAction();
		} else if (bitset.get(KeyEvent.VK_A)) {
			if (bitset.get(KeyEvent.VK_K)) {
				changeBillOrientation(Bill.FIRE_LEFT);
			} else {
				changeBillOrientation(Bill.RUN_LEFT);
			}
			changeBillY(getBillLine() - ImageManager.getHeightImage(GameObject.BILL, getBillOrientation()));
			moveBill();
			changeBillImageAction();
			changeBillLine(checkBillLine());
		} else {
			setBillDefault();
		}

	}

	public void moveMap() {
		map.move();
		for (int i = 0; i < arrLineMap.size(); i++) {
			LineMap lineMap = arrLineMap.get(i);
			lineMap.setxLeft(lineMap.getxLeft() - 1);
			lineMap.setxRight(lineMap.getxRight() - 1);
			arrLineMap.set(i, lineMap);
		}
		if (!arrEnemies.isEmpty()) {
			for (int i = 0; i < arrEnemies.size(); i++) {
				Enemies e = arrEnemies.get(i);
				e.setX(e.getX() - 1);
				arrEnemies.set(i, e);
			}
		}
		if (!arrItem.isEmpty()) {
			for (int i = 0; i < arrItem.size(); i++) {
				Item e = arrItem.get(i);
				e.setX(e.getX() - 1);
				arrItem.set(i, e);
			}
		}
		if (!arrBoss.isEmpty()) {
			for (int i = 0; i < arrBoss.size(); i++) {
				Boss boss = arrBoss.get(i);
				boss.setX(boss.getX() - 1);
				arrBoss.set(i, boss);
			}
		}
	}

	private void moveBillOrMap() {
		if (map.getX() > Map.X_LAST_MAP) {
			if (bill.getX() < Frame.MID) {
				bill.setX(bill.getX() + 1);
			} else {
				moveMap();
			}
		} else {
			bill.setX(bill.getX() + 1);
		}
	}

	private void moveBillOrMapInJump() {
		if (map.getX() > Map.X_LAST_MAP) {
			if (bill.getX() > Frame.MID) {
				moveBill();
			} else {
				moveMap();
			}
		} else {
			bill.setX(bill.getX() + 1);
		}
	}

	public void drawMap(Graphics2D g) {
		map.draw(g);
	}

	public void addBullet() {
		countTimeBullet++;
		if (countTimeBullet == 12) {
			int xBullet = bill.getX() + dBulletX[GameObject.BILL][bill.getOrientation()];
			int yBullet = bill.getY() + dBulletY[GameObject.BILL][bill.getOrientation()];
			if (isOnFrame(xBullet, yBullet)) {
				arrBullet.add(new Bullet(bill.getTypeBullet(), xBullet, yBullet, bill.getOrientation()));
				if (bill.getTypeBullet() == Bullet.TYPE_1){
					SoundManager.getInstance().getSoundBillFire().play();
				}
				else {
					SoundManager.getInstance().getSoundBillFireType3().play();
				}
				if (bill.getTypeBullet() == Bullet.TYPE_3) {
					switch (bill.getOrientation()) {
					case GameObject.UP:
						arrBullet.add(new Bullet(Bullet.TYPE_3, xBullet, yBullet, GameObject.DIAGONAL_LEFT_UP));
						arrBullet.add(new Bullet(Bullet.TYPE_3, xBullet, yBullet, GameObject.DIAGONAL_UP_RIGHT));
						break;
					case GameObject.FIRE_RIGHT:
						arrBullet.add(new Bullet(Bullet.TYPE_3, xBullet, yBullet, GameObject.DIAGONAL_RIGHT_DOWN));
						arrBullet.add(new Bullet(Bullet.TYPE_3, xBullet, yBullet, GameObject.DIAGONAL_UP_RIGHT));
						break;
					case GameObject.RIGHT_DOWN:
						arrBullet.add(new Bullet(Bullet.TYPE_3, xBullet, yBullet, GameObject.DIAGONAL_RIGHT_DOWN));
						arrBullet.add(new Bullet(Bullet.TYPE_3, xBullet, yBullet, GameObject.DIAGONAL_UP_RIGHT));
						break;
					case GameObject.FIRE_LEFT:
						arrBullet.add(new Bullet(Bullet.TYPE_3, xBullet, yBullet, GameObject.DIAGONAL_LEFT_UP));
						arrBullet.add(new Bullet(Bullet.TYPE_3, xBullet, yBullet, GameObject.DIAGONAL_DOWN_LEFT));
						break;
					case GameObject.DOWN_LEFT:
						arrBullet.add(new Bullet(Bullet.TYPE_3, xBullet, yBullet, GameObject.DIAGONAL_LEFT_UP));
						arrBullet.add(new Bullet(Bullet.TYPE_3, xBullet, yBullet, GameObject.DIAGONAL_DOWN_LEFT));
						break;
					case GameObject.DOWN:
						arrBullet.add(new Bullet(Bullet.TYPE_3, xBullet, yBullet, GameObject.DIAGONAL_LEFT_UP));
						arrBullet.add(new Bullet(Bullet.TYPE_3, xBullet, yBullet, GameObject.DIAGONAL_RIGHT_DOWN));
						break;
					case GameObject.DIAGONAL_DOWN_LEFT:
						arrBullet.add(new Bullet(Bullet.TYPE_3, xBullet, yBullet, GameObject.FIRE_LEFT));
						arrBullet.add(new Bullet(Bullet.TYPE_3, xBullet, yBullet, GameObject.DOWN));
						break;
					case GameObject.DIAGONAL_LEFT_UP:
						arrBullet.add(new Bullet(Bullet.TYPE_3, xBullet, yBullet, GameObject.UP));
						arrBullet.add(new Bullet(Bullet.TYPE_3, xBullet, yBullet, GameObject.FIRE_LEFT));
						break;
					case GameObject.DIAGONAL_RIGHT_DOWN:
						arrBullet.add(new Bullet(Bullet.TYPE_3, xBullet, yBullet, GameObject.FIRE_RIGHT));
						arrBullet.add(new Bullet(Bullet.TYPE_3, xBullet, yBullet, GameObject.DOWN));
						break;
					case GameObject.DIAGONAL_UP_RIGHT:
						arrBullet.add(new Bullet(Bullet.TYPE_3, xBullet, yBullet, GameObject.UP));
						arrBullet.add(new Bullet(Bullet.TYPE_3, xBullet, yBullet, GameObject.FIRE_RIGHT));
						break;
					case GameObject.LEFT_UP:
						arrBullet.add(new Bullet(Bullet.TYPE_3, xBullet, yBullet, GameObject.DIAGONAL_LEFT_UP));
						arrBullet.add(new Bullet(Bullet.TYPE_3, xBullet, yBullet, GameObject.DIAGONAL_UP_RIGHT));
						break;
					case GameObject.UP_RIGHT:
						arrBullet.add(new Bullet(Bullet.TYPE_3, xBullet, yBullet, GameObject.DIAGONAL_LEFT_UP));
						arrBullet.add(new Bullet(Bullet.TYPE_3, xBullet, yBullet, GameObject.DIAGONAL_UP_RIGHT));
						break;
					}
				}
			}
			countTimeBullet = -1;
		}
	}

	public void addEnemiesBullet() {
		if (!arrEnemies.isEmpty()) {
			for (int i = 0; i < arrEnemies.size(); i++) {
				Enemies enemies = arrEnemies.get(i);
				if (enemies.getType() == GameObject.SOLDIER && (enemies.getorient() == GameObject.RUN_RIGHT
						|| enemies.getorient() == GameObject.RUN_LEFT)) {
					continue;
				}
				enemies.setCountTime(enemies.getCountTime() + 1);
				if (enemies.getCountTime() == enemies.getTime()) {
					int xBullet = enemies.getX() + dBulletX[enemies.getType()][enemies.getorient()];
					int yBullet = enemies.getY() + dBulletY[enemies.getType()][enemies.getorient()];
					if (isOnFrame(xBullet, yBullet)) {
						arrEnemiesBullet
						.add(new Bullet(enemies.getTypeBullet(), xBullet, yBullet, enemies.getorient()));
						if (enemies.getType() == GameObject.CANNON || enemies.getType() == GameObject.WALL_TURRET){
							SoundManager.getInstance().getSoundWallTurretCannonBossFire().play();
						}
						else {
							SoundManager.getInstance().getSoundSniperSoliderFire().play();
						}
					}
					enemies.setCountTime(-1);
				}
				arrEnemies.set(i, enemies);
			}
		}
	}

	public void addBossBullet() {
		if (!arrBoss.isEmpty() && arrBoss != null) {
			for (int i = 0; i <= arrBoss.size(); i++) {
				Boss boss = arrBoss.get(i);
				if (boss.equals(finalBoss)){
					return;
				}
				boss.setCountTime(boss.getCountTime() + 1);
				if (boss.getCountTime() == Boss.MAX_TIME) {
					if (isOnFrame(boss.getX(), boss.getY())) {
						arrEnemiesBullet.add(new Bullet(Bullet.TYPE_3, boss.getX(), boss.getY(), GameObject.LEFT_UP));
						SoundManager.getInstance().getSoundWallTurretCannonBossFire().play();
					}
					boss.setCountTime(-1);
				}
				arrBoss.set(i, boss);
			}
		}
	}

	public void addBulletWhileBillJump(int orient) {
		countTimeBullet++;
		if (countTimeBullet == 12) {
			int x = bill.getX();
			int y = bill.getY();
			int xBullet, yBullet;
			int size = ImageManager.getHeightImage(GameObject.BILL, GameObject.JUMP_RIGHT);
			switch (orient) {
			case GameObject.UP:
				xBullet = x + size / 2;
				yBullet = y;
				break;
			case GameObject.DIAGONAL_UP_RIGHT:
				xBullet = x + size;
				yBullet = y;
				break;
			case GameObject.FIRE_RIGHT:
				xBullet = x + size;
				yBullet = y + size / 2;
				break;
			case GameObject.DIAGONAL_RIGHT_DOWN:
				xBullet = x + size;
				yBullet = y + size;
				break;
			case GameObject.DOWN:
				xBullet = x + size / 2;
				yBullet = y + size;
				break;
			case GameObject.DIAGONAL_DOWN_LEFT:
				xBullet = x - size;
				yBullet = y + size;
				break;
			case GameObject.DIAGONAL_LEFT_UP:
				xBullet = x - size;
				yBullet = y;
				break;
			default:
				xBullet = x;
				yBullet = y;
				break;
			}
			if (isOnFrame(xBullet, yBullet)) {
				arrBullet.add(new Bullet(bill.getTypeBullet(), xBullet, yBullet, orient));
				if (bill.getTypeBullet() == Bullet.TYPE_3){
					SoundManager.getInstance().getSoundBillFireType3().play();
				}
				else {
					SoundManager.getInstance().getSoundBillFire().play();
				}
			}
			countTimeBullet = -1;
		}
	}

	public void handleBillFire(BitSet bitset) {
		if (bill.isJumpDown() || bill.isJumpUp()) {
			if (bitset.get(KeyEvent.VK_K)) {
				if (bitset.get(KeyEvent.VK_W) && bitset.get(KeyEvent.VK_D)) {
					addBulletWhileBillJump(GameObject.DIAGONAL_UP_RIGHT);
				} else if (bitset.get(KeyEvent.VK_W) && bitset.get(KeyEvent.VK_A)) {
					addBulletWhileBillJump(GameObject.DIAGONAL_LEFT_UP);
				} else if (bitset.get(KeyEvent.VK_S) && bitset.get(KeyEvent.VK_D)) {
					addBulletWhileBillJump(GameObject.DIAGONAL_RIGHT_DOWN);
				} else if (bitset.get(KeyEvent.VK_S) && bitset.get(KeyEvent.VK_A)) {
					addBulletWhileBillJump(GameObject.DIAGONAL_DOWN_LEFT);
				} else if (bitset.get(KeyEvent.VK_W)) {
					addBulletWhileBillJump(GameObject.UP);
				} else if (bitset.get(KeyEvent.VK_D)) {
					addBulletWhileBillJump(GameObject.FIRE_RIGHT);
				} else if (bitset.get(KeyEvent.VK_A)) {
					addBulletWhileBillJump(GameObject.FIRE_LEFT);
				} else if (bitset.get(KeyEvent.VK_S)) {
					addBulletWhileBillJump(GameObject.DOWN);
				}
			}
		} else if (bitset.get(KeyEvent.VK_K)) {
			addBullet();
		}
		removeBullet();
	}

	public void moveBullet() {
		if (!arrBullet.isEmpty() && arrBullet != null) {
			for (int i = 0; i < arrBullet.size(); i++) {
				arrBullet.get(i).move();
			}
		}
		if (!arrEnemiesBullet.isEmpty() && arrEnemiesBullet != null) {
			for (int i = 0; i < arrEnemiesBullet.size(); i++) {
				arrEnemiesBullet.get(i).move();
			}
		}
	}

	public void drawBullet(Graphics2D graphics2d) {
		if (!arrBullet.isEmpty()) {
			for (int i = 0; i < arrBullet.size(); i++) {
				try {
					arrBullet.get(i).draw(graphics2d);
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			}
		}
		if (!arrEnemiesBullet.isEmpty()) {
			for (int i = 0; i < arrEnemiesBullet.size(); i++) {
				try {
					arrEnemiesBullet.get(i).draw(graphics2d);
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public boolean isOnFrame(int x, int y) {
		if (x > Frame.WIDTH || x < 0 || y > Frame.HEIGHT || y < 0)
			return false;
		return true;
	}

	public void removeBullet() {
		if (!arrBullet.isEmpty() || arrBullet != null) {
			for (int i = 0; i < arrBullet.size(); i++) {
				Bullet bullet = arrBullet.get(i);
				if (!isOnFrame(bullet.getX(), bullet.getY())) {
					arrBullet.remove(i);
					i--;
				}
			}
		}
		if (!arrEnemiesBullet.isEmpty() || arrEnemiesBullet != null) {
			for (int i = 0; i < arrEnemiesBullet.size(); i++) {
				Bullet bullet = arrEnemiesBullet.get(i);
				if (!isOnFrame(bullet.getX(), bullet.getY())) {
					arrEnemiesBullet.remove(i);
					i--;
				}
			}
		}
	}

	public void drawEnemies(Graphics2D g) {
		if (!arrEnemies.isEmpty() || arrEnemies != null) {
			for (int i = 0; i < arrEnemies.size(); i++) {
				try {
					arrEnemies.get(i).draw(g);
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int nextOrientOfSoldier(Enemies e) {
		switch (e.getorient()) {
		case GameObject.FIRE_LEFT:
			if (bill.getX() <= e.getX())
				return GameObject.FIRE_LEFT;
			else
				return GameObject.FIRE_RIGHT;
		case GameObject.FIRE_RIGHT:
			if (bill.getX() > e.getX())
				return GameObject.FIRE_RIGHT;
			else
				return GameObject.FIRE_LEFT;
		case GameObject.RUN_LEFT:
			return GameObject.RUN_RIGHT;
		case GameObject.RUN_RIGHT:
			return GameObject.RUN_LEFT;
		case GameObject.DOWN_LEFT:
			if (bill.getX() <= e.getX())
				return GameObject.DOWN_LEFT;
			else
				return GameObject.RIGHT_DOWN;
		case GameObject.RIGHT_DOWN:
			if (bill.getX() > e.getX())
				return GameObject.RIGHT_DOWN;
			else
				return GameObject.DOWN_LEFT;
		}
		return 0;
	}

	public int nextOrientOfSniper(Enemies enemies) {
		if (bill.getY() + ImageManager.getHeightImage(GameObject.BILL, bill.getOrientation()) < enemies.getY()) {
			if (bill.getX() > enemies.getX())
				return GameObject.DIAGONAL_UP_RIGHT;
			else
				return GameObject.DIAGONAL_LEFT_UP;
		}
		if (enemies.getY() + ImageManager.getHeightImage(enemies.getType(), enemies.getorient()) < bill.getY()) {
			if (bill.getX() > enemies.getX())
				return GameObject.DIAGONAL_RIGHT_DOWN;
			else
				return GameObject.DIAGONAL_DOWN_LEFT;
		}
		if (bill.getX() > enemies.getX()) {
			return GameObject.FIRE_RIGHT;
		}
		return GameObject.FIRE_LEFT;
	}

	public int nextOrientOfWallTurretOrCannon(Enemies enemies) {
		if (bill.getY() + ImageManager.getHeightImage(GameObject.BILL, bill.getOrientation()) < enemies.getY()) {
			if (bill.getX() > enemies.getX() + ImageManager.getWidthImage(enemies.getType(), enemies.getorient()))
				return GameObject.DIAGONAL_UP_RIGHT;
			if (bill.getX() < enemies.getX())
				return GameObject.DIAGONAL_LEFT_UP;
			return GameObject.UP;
		}
		if (enemies.getY() + ImageManager.getHeightImage(enemies.getType(), enemies.getorient()) < bill.getY()) {
			if (bill.getX() > +ImageManager.getWidthImage(enemies.getType(), enemies.getorient()))
				return GameObject.DIAGONAL_RIGHT_DOWN;
			else if (bill.getX() < enemies.getX())
				return GameObject.DIAGONAL_DOWN_LEFT;
			else
				return GameObject.DOWN;
		}
		if (bill.getX() > enemies.getX()) {
			return GameObject.FIRE_RIGHT;
		}
		return GameObject.FIRE_LEFT;
	}

	public void moveEnemies() {
		if (!arrEnemies.isEmpty()) {
			for (int i = 0; i < arrEnemies.size(); i++) {
				Enemies e = arrEnemies.get(i);
				if (arrEnemies.get(i).getX() < -ImageManager.getWidthImage(e.getType(), e.getorient())) {
					arrEnemies.remove(i);
					i--;
				}
			}
		}
		if (!arrEnemies.isEmpty()) {
			for (int i = 0; i < arrEnemies.size(); i++) {
				Enemies e = arrEnemies.get(i);
				if (e.getType() == GameObject.SOLDIER) {
					e.move();
					e.changeEnemiesAction();
					int orientation = e.getorient();
					if (orientation == GameObject.FIRE_LEFT || orientation == GameObject.FIRE_RIGHT
							|| orientation == GameObject.DOWN_LEFT || orientation == GameObject.RIGHT_DOWN) {
						e.setorient(nextOrientOfSoldier(e));
					} else if (!checkEnemiesLine(e)) {
						e.setorient(nextOrientOfSoldier(e));
					}
					arrEnemies.set(i, e);
				}
				if (e.getType() == GameObject.SNIPER) {
					e.setorient(nextOrientOfSniper(e));
					e.changeEnemiesAction();
				}
				if (e.getType() == GameObject.CANNON || e.getType() == GameObject.WALL_TURRET) {
					e.setorient(nextOrientOfWallTurretOrCannon(e));
					e.changeEnemiesAction();
				}
			}
		}
	}

	public boolean checkBillBulletIntersectsEnemies(Bullet bullet) {
		if (arrEnemies.isEmpty())
			return false;
		else {
			for (int i = 0; i < arrEnemies.size(); i++) {
				Enemies enemies = arrEnemies.get(i);
				if (bullet.getRectangle().intersects(enemies.getRectangle())) {
					if (enemies.getType() == GameObject.WALL_TURRET || enemies.getType() == GameObject.CANNON){
						SoundManager.getInstance().getSoundBillBulletIntersectsBox().play();
					}
					enemies.setDamage(enemies.getDamage() + bullet.getDamage());
					if (enemies.isDied()) {
						int type = enemies.getType();
						if (type == GameObject.SOLDIER || type == GameObject.SNIPER) {
							SoundManager.getInstance().getSoundSoliderSnipperDie().play();
							Explosion expl = new Explosion(1, enemies.getX(), enemies.getY());
							arrExplosion.add(expl);
							score += 5;
						}
						if (type == GameObject.CANNON || type == GameObject.WALL_TURRET) {
							SoundManager.getInstance().getSoundWallTurretCannonDie().play();
							Explosion expl = new Explosion(2, enemies.getX(), enemies.getY());
							arrExplosion.add(expl);
							score += 10;
						}
						arrEnemies.remove(i);
						i--;
					}
					return true;
				}
			}
			return false;
		}
	}

	public boolean checkBillBulletIntersectsBoss(Bullet bullet){
		if (arrBoss.isEmpty())
			return false;
		else {
			for (int i = 0; i < arrBoss.size(); i ++){
				Boss boss = arrBoss.get(i);
				if (bullet.getRectangle().intersects(boss.getRectangle())){
					boss.setDamage(boss.getDamage() + bullet.getDamage());
					if (boss.isExploded()){
						SoundManager.getInstance().getSoundBossDie().play();
						arrExplosion.add(new Explosion (Explosion.TYPE_2, boss.getX(), boss.getY())); 
						arrBoss.remove(i); 
						i --;
					}
					else{
						arrBoss.set(i, boss);
					}
					return true;
				}
			}
			return false; 
		}
	}
	public boolean checkBillBulletIntersectsItem(Bullet bullet) {
		if (arrItem.isEmpty())
			return false;
		else {
			for (int i = 0; i < arrItem.size(); i++) {
				Item item = arrItem.get(i);
				if (bullet.getRectangle().intersects(item.getRectangle()) && !item.isExploded()) {
					SoundManager.getInstance().getSoundBillBulletIntersectsBox().play();
					item.setDamage(item.getDamage() + bullet.getDamage());
					if (item.isExploded()) {
						arrExplosion.add(new Explosion(Explosion.TYPE_1, item.getX(), item.getY()));
						return true;
					}
				}
			}
			return false;
		}
	}

	public void checkBillIntersectsItem() {
		if (arrItem.isEmpty()) {
			return;
		} else {
			for (int i = 0; i < arrItem.size(); i++) {
				Item item = arrItem.get(i);
				if (item.getRectangle().intersects(bill.getRectangle())) {
					SoundManager.getInstance().getSoundIntersectsItem().play();
					bill.setTypeBullet(item.getType());
					arrItem.remove(i);
					i--;
					return;
				}
			}
		}
	}

	public void checkIntersects() {
		for (int i = 0; i < arrBullet.size(); i++) {
			if (checkBillBulletIntersectsEnemies(arrBullet.get(i)) || checkBillBulletIntersectsItem(arrBullet.get(i))
					|| checkBillBulletIntersectsBoss(arrBullet.get(i))) {
				arrBullet.remove(i);
				i--;
			}
		}

		if (!arrEnemies.isEmpty()) {
			for (int i = 0; i < arrEnemies.size(); i++) {
				Enemies e = arrEnemies.get(i);
				if (e.getRectangle().intersects(bill.getRectangle())) {
					SoundManager.getInstance().getSoundBillDie().play();
					arrExplosion.add(new Explosion(Explosion.TYPE_1, bill.getX(), bill.getY()));
					bill.setDead(true);
					arrLife.remove(arrLife.size() - 1);
					return;
				}
			}
		}

		if (!arrEnemiesBullet.isEmpty()) {
			for (int i = 0; i < arrEnemiesBullet.size(); i++) {
				Bullet bullet = arrEnemiesBullet.get(i);
				if (bill.getRectangle().intersects(bullet.getRectangle())) {
					SoundManager.getInstance().getSoundBillDie().play();
					arrExplosion.add(new Explosion(Explosion.TYPE_1, bill.getX(), bill.getY()));
					arrEnemiesBullet.remove(i);
					bill.setDead(true);
					arrLife.remove(arrLife.size() - 1);
					return;
				}
			}
		}
		checkBillIntersectsItem();
	}

	public void handleExplosionAction() {
		if (!arrExplosion.isEmpty()) {
			for (int i = 0; i < arrExplosion.size(); i++) {
				Explosion e = arrExplosion.get(i);
				e.changeImageAction();
				if (e.isFinished()) {
					arrExplosion.remove(i);
					if (arrExplosion.isEmpty()) {
						return;
					}
				}
			}
		}
	}

	public void newBill() {
		bill = new Bill(114, 100);
	}

	public boolean checkIsBillAlive() {
		if (!isOnFrame(bill.getX(), bill.getY()) || bill.isDead())
			return false;
		return true;
	}

	public void drawExposion(Graphics2D g) {
		if (!arrExplosion.isEmpty()) {
			for (int i = 0; i < arrExplosion.size(); i++) {
				arrExplosion.get(i).draw(g);
			}
		}
	}

	public void drawItem(Graphics2D g) {
		if (!arrItem.isEmpty()) {
			for (int i = 0; i < arrItem.size(); i++) {
				arrItem.get(i).draw(g);
			}
		}
	}

	public void drawBoss(Graphics2D g) {
		if (!arrBoss.isEmpty()) {
			for (int i = 0; i < arrBoss.size(); i++) {
				arrBoss.get(i).draw(g);
			}
		}
	}

	public void handleItemAction() {
		if (!arrExplosion.isEmpty()) {
			for (int i = 0; i < arrItem.size(); i++) {
				Item e = arrItem.get(i);
				e.changeImageAction();
			}
		}
	}

	public int getScore() {
		return score;
	}

	public int getNrOfLife() {
		return nrOfLife;
	}

	public void drawLife(Graphics2D g) {
		if (!arrLife.isEmpty() && arrLife != null) {
			for (int i = 0; i < arrLife.size(); i++) {
				arrLife.get(i).draw(g);
			}
		}
	}

	public boolean checkWin(){
		return arrBoss.isEmpty(); 
	}
	
	public boolean gameOver(){
		return arrLife.isEmpty(); 
	}
}