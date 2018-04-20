package GUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import manager.ImageManager;

public class MenuPanel extends JPanel {
	
	private JButton btnPlay; 
	private JButton btnExit; 
	private JButton btnHelp; 
	
	public MenuPanel(){
		btnPlay = new JButton(); 
		btnPlay.setIcon(new ImageIcon (ImageManager.getImage("button_play_1.png")));
		btnPlay.setBounds(180, 380, 165, 35);
		add(btnPlay);
		
		btnExit = new JButton(); 
		btnExit.setIcon(new ImageIcon (ImageManager.getImage("button_exit_1.png")));
		btnPlay.setBounds(180, 490, 165, 35);
		add(btnExit);
		
		btnHelp = new JButton(); 
		btnHelp.setIcon(new ImageIcon (ImageManager.getImage("button_help_1.png")));
		btnHelp.setBounds(180, 435, 165, 35);
		add(btnHelp);
	}
}
