package Sound;

public class SoundManager {
	
	public static boolean isSound = true; 
	
	private static SoundManager instance; 
	
	private WAV soundBeginGame;
	private WAV soundBillBulletIntersectsBox;
	private WAV soundBillDie;
	private WAV soundBillFire;
	private WAV soundBillFireType3;
	private WAV soundBossDie;
	private WAV soundClickButton;
	private WAV soundEndGame;
	private WAV soundGame;
	private WAV soundIntersectsItem;
	private WAV soundMenuAndHelpPanel;
	private WAV soundSniperSoliderFire;
	private WAV soundSoliderSnipperDie;
	private WAV soundWallTurretCannonBossFire;
	private WAV soundWallTurretCannonDie;
	
	public SoundManager(){
		soundMenuAndHelpPanel = new WAV("sound_menu_and_help_panel.wav");
        soundClickButton = new WAV("sound_click_button.wav");
        soundBeginGame = new WAV("sound_begin_game.wav");
        soundGame = new WAV("sound_game.wav");
        soundEndGame = new WAV("sound_end_game.wav");
        soundBillFire = new WAV("sound_bill_fire.wav");
        soundBillFireType3 = new WAV("sound_bill_fire_type_3.wav");
        soundSniperSoliderFire = new WAV("sound_snipper_solider_fire.wav");
        soundWallTurretCannonBossFire = new WAV("sound_wall_turret_cannon_boss_fire.wav");
        soundBillBulletIntersectsBox = new WAV("sound_bill_bullet_intersect_box.wav");
        soundWallTurretCannonDie = new WAV("sound_wall_turret_cannon_die.wav");
        soundSoliderSnipperDie = new WAV("sound_solider_snipper_die.wav");
        soundIntersectsItem = new WAV("sound_intersects_items.wav");
        soundBossDie = new WAV("sound_boss_die.wav");
        soundBillDie = new WAV("sound_bill_die.wav");
	}
	
	public static synchronized SoundManager getInstance(){
		if (instance == null){
			return new SoundManager(); 
		}
		return instance;
	}
	
	public boolean isOnSound(){
		return isSound;
	}

	public WAV getSoundBeginGame() {
		return soundBeginGame;
	}

	public WAV getSoundBillBulletIntersectsBox() {
		return soundBillBulletIntersectsBox;
	}

	public WAV getSoundBillDie() {
		return soundBillDie;
	}

	public WAV getSoundBillFire() {
		return soundBillFire;
	}

	public WAV getSoundBillFireType3() {
		return soundBillFireType3;
	}

	public WAV getSoundBossDie() {
		return soundBossDie;
	}

	public WAV getSoundClickButton() {
		return soundClickButton;
	}

	public WAV getSoundGame() {
		return soundGame;
	}

	public WAV getSoundEndGame() {
		return soundEndGame;
	}

	public WAV getSoundIntersectsItem() {
		return soundIntersectsItem;
	}

	public WAV getSoundMenuAndHelpPanel() {
		return soundMenuAndHelpPanel;
	}

	public WAV getSoundSoliderSnipperDie() {
		return soundSoliderSnipperDie;
	}

	public WAV getSoundSniperSoliderFire() {
		return soundSniperSoliderFire;
	}

	public WAV getSoundWallTurretCannonBossFire() {
		return soundWallTurretCannonBossFire;
	}

	public WAV getSoundWallTurretCannonDie() {
		return soundWallTurretCannonDie;
	}

}	
