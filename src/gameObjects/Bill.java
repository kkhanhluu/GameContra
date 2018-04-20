package gameObjects;

import java.awt.*;
import java.util.ArrayList;

import manager.ImageManager;

public class Bill extends GameObject {

	private static final int JUMP_HEIGHT = 90;
	private int orientation;
	private ArrayList<Image>[] arrImgBillActions;
	private int[] countArr;
	private Rectangle rectangle;
	private boolean isJumpUp, isJumpDown;
	private int line;
	private int countJump;
	private int typeBullet;
	private boolean isDead; 

	public Bill(int x, int y) {
		this.x = x;
		this.y = y;
		this.line = -1;
		this.countJump = 0;
		this.isJumpDown = false;
		this.isJumpUp = false;
		this.setTypeBullet(Bullet.TYPE_0);
		this.orientation = FIRE_RIGHT;
		this.image = ImageManager.getImage("bill_run_fire_right_1.png");
		rectangle = new Rectangle();
		this.isDead = false; 

		loadImages();
	}

	@Override
	public void setX(int x) {
		super.setX(x);
		rectangle.setBounds(x, this.y, ImageManager.getWidthImage(BILL, orientation),
				ImageManager.getHeightImage(BILL, orientation));
	}

	@Override
	public void setY(int y) {
		super.setY(y);
		rectangle.setBounds(this.x, y, ImageManager.getWidthImage(BILL, orientation),
				ImageManager.getHeightImage(BILL, orientation));
	}

	public boolean isJumpUp() {
		return isJumpUp;
	}

	public void setJumpUp(boolean isJumpUp) {
		this.isJumpUp = isJumpUp;
	}

	public boolean isJumpDown() {
		return isJumpDown;
	}

	public void setJumpDown(boolean isJumpDown) {
		this.isJumpDown = isJumpDown;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Rectangle getRectangle() {
		return this.rectangle;
	}

	public void changeImgeAction() {
		countArr[orientation]++;
		countArr[orientation] = countArr[orientation] % (arrImgBillActions[orientation].size() * 10);
		setImage(arrImgBillActions[orientation].get(countArr[orientation] / 10));
	}

	@Override
	public void draw(Graphics2D graphics2D) {
		graphics2D.drawImage(image, x, y, ImageManager.getWidthImage(BILL, orientation),
				ImageManager.getHeightImage(BILL, orientation), null);
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public int getOrientation() {
		return this.orientation;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public void move() {
		switch (orientation) {
		case UP_RIGHT: {
			break;
		}

		case DIAGONAL_UP_RIGHT:
		case RUN_RIGHT:
		case FIRE_RIGHT:
		case DIAGONAL_RIGHT_DOWN: {
			if (x < Frame.WIDTH - ImageManager.getWidthImage(BILL, orientation)) {
				x++;
			}
			break;
		}

		case RIGHT_DOWN: {
			break;
		}
		case DOWN_LEFT: {
			break;
		}

		case DIAGONAL_DOWN_LEFT:
		case RUN_LEFT:
		case FIRE_LEFT:
		case DIAGONAL_LEFT_UP: {
			if (x > 0) {
				x--;
			}
			break;
		}

		case LEFT_UP: {
			break;
		}

		case JUMP_RIGHT:
		case JUMP_LEFT: {
			if (countJump < JUMP_HEIGHT) {
				y--;
				countJump++;
			} else {
				isJumpUp = false;
				isJumpDown = true;
			}
			break;
		}
		default:
		}
		rectangle.setBounds(this.x, this.y, ImageManager.getWidthImage(BILL, orientation),
				ImageManager.getHeightImage(BILL, orientation));
	}

	private void loadImages() {

		arrImgBillActions = new ArrayList[NUMBER_ORIENT];
		for (int i = 0; i < NUMBER_ORIENT; i++) {
			arrImgBillActions[i] = new ArrayList<>();
		}

		arrImgBillActions[UP_RIGHT].add(ImageManager.getImage("bill_fire_up_right.png"));
		arrImgBillActions[LEFT_UP].add(ImageManager.getImage("bill_fire_up_left.png"));
		arrImgBillActions[RIGHT_DOWN].add(ImageManager.getImage("bill_lie_fire_right.png"));
		arrImgBillActions[DOWN_LEFT].add(ImageManager.getImage("bill_lie_fire_left.png"));

		for (int i = 1; i <= 3; i++) {
			arrImgBillActions[DIAGONAL_UP_RIGHT]
					.add(ImageManager.getImage("bill_run_fire_diagonal_up_right_" + i + ".png"));
			arrImgBillActions[FIRE_RIGHT].add(ImageManager.getImage("bill_run_fire_right_" + i + ".png"));
			arrImgBillActions[DIAGONAL_RIGHT_DOWN]
					.add(ImageManager.getImage("bill_run_fire_diagonal_right_down_" + i + ".png"));
			arrImgBillActions[DIAGONAL_DOWN_LEFT]
					.add(ImageManager.getImage("bill_run_fire_diagonal_down_left_" + i + ".png"));
			arrImgBillActions[FIRE_LEFT].add(ImageManager.getImage("bill_run_fire_left_" + i + ".png"));
			arrImgBillActions[DIAGONAL_LEFT_UP]
					.add(ImageManager.getImage("bill_run_fire_diagonal_left_up_" + i + ".png"));
		}

		for (int i = 1; i <= 4; i++) {
			arrImgBillActions[JUMP_RIGHT].add(ImageManager.getImage("bill_jump_right_" + i + ".png"));
			arrImgBillActions[JUMP_LEFT].add(ImageManager.getImage("bill_jump_left_" + i + ".png"));
		}

		for (int i = 1; i <= 5; i++) {
			arrImgBillActions[RUN_RIGHT].add(ImageManager.getImage("bill_run_right_" + i + ".png"));
			arrImgBillActions[RUN_LEFT].add(ImageManager.getImage("bill_run_left_" + i + ".png"));
		}

		countArr = new int[NUMBER_ORIENT];
		for (int i = 0; i < NUMBER_ORIENT; i++) {
			countArr[i] = -1;
		}
	}

	public int getCountJump() {
		return countJump;
	}

	public void setCountJump(int countJump) {
		this.countJump = countJump;
	}

	public int getTypeBullet() {
		return typeBullet;
	}

	public void setTypeBullet(int typeBullet) {
		this.typeBullet = typeBullet;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public boolean isBillMovingLeft() {
		return (orientation == GameObject.DIAGONAL_DOWN_LEFT || orientation == GameObject.DIAGONAL_LEFT_UP
				|| orientation == GameObject.LEFT_UP || orientation == GameObject.FIRE_LEFT
				|| orientation == GameObject.RUN_LEFT || orientation == GameObject.JUMP_LEFT
				|| orientation == GameObject.DOWN_LEFT);
	}

	public boolean isBillMovingRight() {
		return (orientation == GameObject.DIAGONAL_RIGHT_DOWN || orientation == GameObject.DIAGONAL_UP_RIGHT
				|| orientation == GameObject.UP_RIGHT || orientation == GameObject.FIRE_RIGHT
				|| orientation == GameObject.RUN_RIGHT || orientation == GameObject.JUMP_RIGHT
				|| orientation == GameObject.RIGHT_DOWN);
	}

}
