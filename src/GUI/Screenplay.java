package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.BitSet;

import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import Sound.SoundManager;
import gameObjects.Bill;
import manager.GameManager;

public class Screenplay extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private Timer timer = new Timer(1, this);
	GameManager gameManager;
	BitSet bitset;
	int countTimeBill = -1; 
	int countTimeMoveBullet = -1;

	public Screenplay() {
		gameManager = new GameManager();
		bitset = new BitSet(256);
		KeyListener listener = new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				bitset.set(e.getKeyCode(), true);
				if (e.getKeyCode() == KeyEvent.VK_L && !gameManager.isBillJumpingDown()
						&& !gameManager.isBillJumpingUp()) {
					gameManager.changeBillJumpUp(true);
					gameManager.changeBillJumpDown(false);
					gameManager.setBillCountJump(0);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				bitset.set(e.getKeyCode(), false);
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}

		};
		addKeyListener(listener);
		setFocusable(true);
		timer.start();
		intializeSound();
	}

	public void intializeSound(){
		SoundManager.getInstance().getSoundBeginGame().play(); 
		SoundManager.getInstance().getSoundGame().loop(Clip.LOOP_CONTINUOUSLY);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		gameManager.drawMap(g2);
		gameManager.drawBill(g2);
		gameManager.drawEnemies(g2);
		gameManager.drawBoss(g2);
		gameManager.drawItem(g2);
		gameManager.drawBullet(g2);
		gameManager.drawLife(g2);
		gameManager.drawExposion(g2);
		g2.setFont(new Font("Tahoma", Font.BOLD, 16));
		g2.setColor(Color.WHITE);
		g2.drawString("Score: " + gameManager.getScore(), 200, 32);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		countTimeBill ++; 
		if (countTimeBill == 1){
			gameManager.handleMoveBill(bitset);
			gameManager.handleBillFire(bitset);
			gameManager.moveEnemies();
			gameManager.handleItemAction();
			gameManager.handleExplosionAction();
			repaint();
			countTimeBill = -1;
		}
		countTimeMoveBullet ++; 
		if (countTimeMoveBullet == 0){
			gameManager.moveBullet();
			gameManager.addEnemiesBullet();
			gameManager.addBossBullet();
			repaint();
			countTimeMoveBullet = -1;
		}
		gameManager.checkIntersects();
		repaint();
		if (gameManager.checkWin()){
			SoundManager.getInstance().getSoundBeginGame().stop();
			SoundManager.getInstance().getSoundGame().stop();
			SoundManager.getInstance().getSoundEndGame().play();
			JOptionPane.showMessageDialog(null, "You have won the game");
		}
		else {
			if (!gameManager.checkIsBillAlive()){
				repaint();
				if (!gameManager.gameOver()) {
					int count = -1;
					while (count < 100001) {
						count++;
						if (count == 10000)
							gameManager.newBill();
					} 
				}
				else {
					SoundManager.getInstance().getSoundBeginGame().stop();
					SoundManager.getInstance().getSoundGame().stop();
					SoundManager.getInstance().getSoundEndGame().play();
					JOptionPane.showMessageDialog(null, "Game Over");
					System.exit(0);
				}
			}
		}
		this.requestFocusInWindow(true);
	}

}
