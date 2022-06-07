package Shooting_Game;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameFrame {

	Timer goAnime;
	Timer goAttack;
	Timer goCreate;

	JFrame gf;
	JButton btn;

	JPanel cardContent;
	GamePanel gamePanel;
	EndPanel endPanel;
	CardLayout card = new CardLayout(0, 0);

	static String USERNAME = "";
	static int score = 0;

	final String PROFILE = "src/images/profile.PNG";
	final String GAMEBACKGROUND = "src/images/gameBackgroundFinal2.PNG";
	final String ASTRONAUT = "src/images/astronaut.png";
	final String BLUEALIEN = "src/images/blueAlien2.png";
	final String REDALIEN = "src/images/redAlien.png";
	final String GREENALIEN = "src/images/greenAlien2.png";
	final String REDATTACK = "src/images/redAlienAttack.png";
	final String BLUEATTACK = "src/images/blueAlienAttack.png";
	final String GREENATTACK = "src/images/greenAlienAttack.png";
	final String HEROATTACK = "src/images/basic_attack.gif";
	final String ENDBACK = "src/images/endBack.png";
	final String LIFE = "src/images/life.png";
	final String BROKEN = "src/images/life.png";

	PosImageIcon endB;
	int hp = 5;
	int playTime = 0;
	int lifeSize = 50;
	int moveStep = 7;
	int redAlienHp = 3;
	int blueAlienHp = 4;
	int greenAlienHp = 5;
	int backX = 0, backY = 0;
	int heroX = 150, heroY = 360;
	int redX = 1550, redY = 360;
	int greenX = 1700, greenY = 560;
	int blueX = 1600, blueY = 160;
	int bulletX = 100, bulletY = 100;
	int heroSizeX = 120, heroSizeY = 100;
	int alienSizeX = 100, alienSizeY = 120;
	int profileSizeX = 265, profileSizeY = 100;
	int alienBulletSizeX = 51, alienBulletSizeY = 42;
	int heroBulletSizeX = 120, heroBulletSizeY = 50;

	boolean startGame = false;
	boolean keyUp = false;
	boolean keyEsc = false;
	boolean keyDown = false;
	boolean keyLeft = false;
	boolean keyRight = false;
	boolean attack_on = false;
	boolean keyCtrl = false;
//
//	public void GameFrame() {
//		// go();
//	}

	public void go() {
		USERNAME = JOptionPane.showInputDialog(null, "당신의 이름을 입력하세요 ! ");
		gf = new JFrame();
		gf.setTitle("Guardians Of Galaxy");
		gf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gf.setSize(MainFrame.SCREEN_WIDTH, MainFrame.SCREEN_HEIGHT);
		gf.setResizable(false);
		gf.setLocationRelativeTo(null);
		gf.setVisible(true);

		startGame = true;
		gamePanel = new GamePanel();

		endPanel = new EndPanel();
		cardContent = new JPanel();
		cardContent.setLayout(card);

		gf.getContentPane().setLayout(card);
		gf.add(cardContent);

		gamePanel.gameBack = new PosImageIcon(GAMEBACKGROUND, backX, backY, 7648, 1080);

		gamePanel.profile = new PosImageIcon(PROFILE, backX, backY, profileSizeX, profileSizeY);
		gamePanel.redAlien = new Enemy(REDALIEN, redX, redY, alienSizeX, alienSizeY, redAlienHp);
		gamePanel.blueAlien = new Enemy(BLUEALIEN, blueX, blueY, alienSizeX, alienSizeY, blueAlienHp);
		gamePanel.greenAlien = new Enemy(GREENALIEN, greenX, greenY, alienSizeX, alienSizeY, greenAlienHp);
		gamePanel.hero = new Hero(ASTRONAUT, heroX, heroY, heroSizeX, heroSizeY, moveStep, 5, 2);

		gamePanel.lifeList.add(new PosImageIcon(LIFE, 0, 100, lifeSize, lifeSize));
		gamePanel.lifeList.add(new PosImageIcon(LIFE, 50, 100, lifeSize, lifeSize));
		gamePanel.lifeList.add(new PosImageIcon(LIFE, 100, 100, lifeSize, lifeSize));
		gamePanel.lifeList.add(new PosImageIcon(LIFE, 150, 100, lifeSize, lifeSize));
		gamePanel.lifeList.add(new PosImageIcon(LIFE, 200, 100, lifeSize, lifeSize));

		gf.addKeyListener(new MyKeyListener());
		// gf.add(gamePanel);

		goAnime = new Timer(10, new AnimeListener());
		goAnime.start();
		goAttack = new Timer(1000, new AddBulletListener());
		goAttack.start();
		goCreate = new Timer(500, new MakeEnemyListener());
		goCreate.start();

		gf.getContentPane().add(BorderLayout.CENTER, gamePanel);
		gf.getContentPane().setLayout(card);

		cardContent.add("gamePanel", gamePanel);
		cardContent.add("endPanel", endPanel);

		gf.add(cardContent);

		gf.pack();
		gf.setFocusable(true);
		gf.requestFocus();

	}

	public class AnimeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			gamePanel.gameBack.x -= 2;
			if (gamePanel.gameBack.x < -6000)
				gamePanel.gameBack.x = -0;

			collide();
			alienMove();
			keyForMove();
			crash();
			noHit();

			if (gamePanel.hero.hp == 0) {
				goAttack.stop();
				goAnime.stop();
				goCreate.stop();
				card.show(cardContent, "endPanel");
			}

		}
	}

	public void alienMove() {
		for (int i = 0; i < gamePanel.redList.size(); i++) {
			Enemy re = gamePanel.redList.get(i);
		}
		for (Enemy r : gamePanel.redList) {
			r.x -= 4;
		}
		for (Enemy b : gamePanel.blueList) {
			b.x -= 4;
		}
		for (Enemy g : gamePanel.greenList) {
			g.x -= 4;
		}
	}

	public void noHit() { // 각 총알 안부딪힘 -> 범위 벗어남 -> 삭제
		for (Bullet b : gamePanel.heroAtkList) {
			b.x += 25;
			if (b.x > 1500)
				gamePanel.deleteAtkList.add(b);
		}
		for (Bullet b : gamePanel.blueAtkList) {
			b.x -= 7;
			b.y -= 3;
			if (b.x < -100)
				gamePanel.deleteAtkList.add(b);
		}
		for (Bullet b : gamePanel.redAtkList) {
			b.x -= 7;
			b.y += 3;
			if (b.x < -100)
				gamePanel.deleteAtkList.add(b);
		}
		for (Bullet b : gamePanel.greenAtkList) {
			b.x -= 7;
			if (b.x < -100)
				gamePanel.deleteAtkList.add(b);
		}
		for (Bullet b : gamePanel.deleteAtkList) {
			gamePanel.blueAtkList.remove(b);
			gamePanel.heroAtkList.remove(b);
			gamePanel.redAtkList.remove(b);
			gamePanel.greenAtkList.remove(b);
		}

	}

	public void onHit(Enemy e) {
		if (e.hp <= 0) {
			gamePanel.deleteEnList.add(e);
			gamePanel.redList.remove(e);
			gamePanel.blueList.remove(e);
			gamePanel.greenList.remove(e);
			score += 50;
		}
	}

	public void collide() {
		for (int i = 0; i < gamePanel.redList.size(); i++) {
			Enemy re = gamePanel.redList.get(i);
			Hero h = gamePanel.hero;
			int spare = 10;
			if (re.collide(new Rectangle(h.x + spare, h.y + spare, h.width - spare, h.height - spare))) {
				// gamePanel.redList.remove(re);
				gamePanel.hero.x = heroX;
				gamePanel.hero.y = heroY;
				gamePanel.lifeList.remove(gamePanel.hero.hp - 1);
				gamePanel.hero.hp--;
			}
		}

		for (int i = 0; i < gamePanel.blueList.size(); i++) {
			Enemy be = gamePanel.blueList.get(i);
			Hero h = gamePanel.hero;
			int spare = 10;
			if (be.collide(new Rectangle(h.x + spare, h.y + spare, h.width - spare, h.height - spare))) {
				// gamePanel.blueList.remove(be);
				gamePanel.hero.x = heroX;
				gamePanel.hero.y = heroY;
				gamePanel.lifeList.remove(gamePanel.hero.hp - 1);
				gamePanel.hero.hp--;
			}
		}

		for (int i = 0; i < gamePanel.greenList.size(); i++) {
			Enemy ge = gamePanel.greenList.get(i);
			Hero h = gamePanel.hero;
			int spare = 10;
			if (ge.collide(new Rectangle(h.x + spare, h.y + spare, h.width - spare, h.height - spare))) {
				// gamePanel.greenList.remove(ge);
				gamePanel.hero.x = heroX;
				gamePanel.hero.y = heroY;
				gamePanel.lifeList.remove(gamePanel.hero.hp - 1);
				gamePanel.hero.hp--;
			}
		}
	}

	public void crash() { // hero -> enemy //// enemy -> hero
		// Hit hero with red's bullet
		for (int i = 0; i < gamePanel.redAtkList.size(); i++) {
			Bullet rb = gamePanel.redAtkList.get(i);
			Hero h = gamePanel.hero;
			if (h.collide(new Rectangle(rb.x, rb.y, rb.width, rb.height))) {
				gamePanel.redAtkList.remove(rb);
				gamePanel.hero.x = heroX;
				gamePanel.hero.y = heroY;
				gamePanel.lifeList.remove(gamePanel.hero.hp - 1);
				gamePanel.hero.hp--;
			}
		}
		// Hit hero with blue's bullet
		for (int i = 0; i < gamePanel.blueAtkList.size(); i++) {
			Bullet bb = gamePanel.blueAtkList.get(i);
			Hero h = gamePanel.hero;
			if (h.collide(new Rectangle(bb.x, bb.y, bb.width, bb.height))) {
				gamePanel.blueAtkList.remove(bb);
				gamePanel.hero.x = heroX;
				gamePanel.hero.y = heroY;
				gamePanel.lifeList.remove(gamePanel.hero.hp - 1);
				gamePanel.hero.hp--;
			}
		}
		// Hit hero with green's bullet
		for (int i = 0; i < gamePanel.greenAtkList.size(); i++) {
			Bullet gb = gamePanel.greenAtkList.get(i);
			Hero h = gamePanel.hero;
			if (h.collide(new Rectangle(gb.x, gb.y, gb.width, gb.height))) {
				gamePanel.greenAtkList.remove(gb);
				gamePanel.hero.x = heroX;
				gamePanel.hero.y = heroY;
				gamePanel.lifeList.remove(gamePanel.hero.hp - 1);
				gamePanel.hero.hp--;
			}
		}

		// Hit red alien with hero bullet
		for (int i = 0; i < gamePanel.redList.size(); i++) {
			Enemy re = gamePanel.redList.get(i);
			for (int j = 0; j < gamePanel.heroAtkList.size(); j++) {
				Bullet hb = gamePanel.heroAtkList.get(j);
				int spare = 10;
				if (re.collide(new Rectangle(hb.x + spare, hb.y + spare, hb.width - spare, hb.height - spare))) {
					gamePanel.heroAtkList.remove(hb);
					re.hp--;
					onHit(re);
				}
			}
		}
		// Hit blue alien with hero bullet
		for (int i = 0; i < gamePanel.blueList.size(); i++) {
			Enemy be = gamePanel.blueList.get(i);
			for (int j = 0; j < gamePanel.heroAtkList.size(); j++) {
				Bullet hb = gamePanel.heroAtkList.get(j);
				int spare = 10;
				if (be.collide(new Rectangle(hb.x + spare, hb.y + spare, hb.width - spare, hb.height - spare))) {
					gamePanel.heroAtkList.remove(hb);
					be.hp--;
					onHit(be);
				}
			}
		}
		// Hit green alien with hero bullet
		for (int i = 0; i < gamePanel.greenList.size(); i++) {
			Enemy ge = gamePanel.greenList.get(i);
			for (int j = 0; j < gamePanel.heroAtkList.size(); j++) {
				Bullet hb = gamePanel.heroAtkList.get(j);
				int spare = 10;
				if (ge.collide(new Rectangle(hb.x + spare, hb.y + spare, hb.width - spare, hb.height - spare))) {
					gamePanel.heroAtkList.remove(hb);
					ge.hp--;
					onHit(ge);
				}
			}
		}

	}

	public void keyForMove() {
		if (gamePanel.hero.x < 0)
			keyLeft = false;
		if (gamePanel.hero.x > 1360)
			keyRight = false;
		if (gamePanel.hero.y < -10)
			keyUp = false;
		if (gamePanel.hero.y > 860)
			keyDown = false;

		if (keyUp)
			gamePanel.hero.upMoving();
		if (keyDown)
			gamePanel.hero.downMoving();
		if (keyLeft)
			gamePanel.hero.leftMoving();
		if (keyRight)
			gamePanel.hero.rightMoving();
	}

	public class EndPanel extends JPanel {
		public EndPanel() {
//			setLayout(null);
//			setBounds(0, 0, MainFrame.SCREEN_WIDTH, MainFrame.SCREEN_HEIGHT);
//			setOpaque(false);
			endB = new PosImageIcon(ENDBACK, 0, 0, MainFrame.SCREEN_WIDTH, MainFrame.SCREEN_HEIGHT);
		}

		public void paintComponent(Graphics g) {
			endB.draw(g);
			gamePanel.profile.draw(g);
			g.setFont(new Font("Aharoni 굵게", Font.BOLD, 20));
			g.setColor(Color.BLACK);
			g.drawString(GameFrame.USERNAME, 130, 29);
			g.drawString("" + GameFrame.score, 140, 80);

		}
	}

	public class AddBulletListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			for (Enemy e : gamePanel.redList) {
				gamePanel.redAtkList.add(new Bullet(REDATTACK, e.x, e.y, alienBulletSizeX, alienBulletSizeY));
			}
			for (Enemy e : gamePanel.blueList) {
				gamePanel.blueAtkList.add(new Bullet(BLUEATTACK, e.x, e.y, alienBulletSizeX, alienBulletSizeY));
			}
			for (Enemy e : gamePanel.greenList) {
				gamePanel.greenAtkList.add(new Bullet(GREENATTACK, e.x, e.y, alienBulletSizeX, alienBulletSizeY));
			}

		}
	}

	public class MakeEnemyListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			playTime++;

			if (playTime % 5 == 0) {
				gamePanel.redList.add(
						new Enemy(REDALIEN, 1600, (int) (Math.random() * 580), alienSizeX, alienSizeY, redAlienHp));
			}
			if (playTime % 7 == 0) {
				gamePanel.blueList.add(
						new Enemy(BLUEALIEN, 1600, (int) (Math.random() * 580), alienSizeX, alienSizeY, blueAlienHp));
			}
			if (playTime % 9 == 0) {
				gamePanel.greenList.add(
						new Enemy(GREENALIEN, 1600, (int) (Math.random() * 580), alienSizeX, alienSizeY, greenAlienHp));
			}
		}
	}

	public class MyKeyListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case (KeyEvent.VK_ESCAPE):
				gf.dispose();
				new MainFrame().go();
				break;
			case KeyEvent.VK_F1:
				gf.dispose();
				new GameFrame().go();
				score = 0;
				hp = 0;
			case KeyEvent.VK_UP:
				keyUp = true;
				break;
			case KeyEvent.VK_DOWN:
				keyDown = true;
				break;
			case KeyEvent.VK_LEFT:
				keyLeft = true;
				break;
			case KeyEvent.VK_RIGHT:
				keyRight = true;
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {
			case (KeyEvent.VK_ESCAPE):
				break;
			case KeyEvent.VK_UP:
				keyUp = false;
				break;
			case KeyEvent.VK_DOWN:
				keyDown = false;
				break;
			case KeyEvent.VK_LEFT:
				keyLeft = false;
				break;
			case KeyEvent.VK_RIGHT:
				keyRight = false;
				break;
			case KeyEvent.VK_SPACE:
				gamePanel.heroAtkList.add(
						new Bullet(HEROATTACK, gamePanel.hero.x, gamePanel.hero.y, heroBulletSizeX, heroBulletSizeY));
				break;
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}
	}

}
