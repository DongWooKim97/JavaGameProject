package Shooting_Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	Hero hero;
	JLabel heroName;

	PosImageIcon gameBack, profile;
	Enemy redAlien, blueAlien, greenAlien;

	
	ArrayList<PosImageIcon>lifeList = new ArrayList<>();
	ArrayList<PosImageIcon>deleteLifeList = new ArrayList<>();
	ArrayList<Bullet> heroAtkList = new ArrayList<>();
	ArrayList<Bullet> redAtkList = new ArrayList<>();
	ArrayList<Bullet> blueAtkList = new ArrayList<>();
	ArrayList<Bullet> greenAtkList = new ArrayList<>();
	ArrayList<Bullet>deleteAtkList = new ArrayList<>();
	ArrayList<Enemy>deleteEnList = new ArrayList<>();
	ArrayList<Enemy> redList = new ArrayList<>();
	ArrayList<Enemy> blueList = new ArrayList<>();
	ArrayList<Enemy> greenList = new ArrayList<>();

	public GamePanel() {
		setFocusable(true);
		requestFocus();
		setPreferredSize(new Dimension(MainFrame.SCREEN_WIDTH, MainFrame.SCREEN_HEIGHT));
		setLayout(null);
		heroName = new JLabel(GameFrame.USERNAME);
		add(heroName);
		setVisible(true);

	}

	public void paintComponent(Graphics g) {
		gameBack.draw(g);
		profile.draw(g);
		hero.draw(g);
		for (Enemy re : redList) {
			re.draw(g);
		}
		for (Enemy be : blueList) {
			be.draw(g);
		}
		for (Enemy ge : greenList) {
			ge.draw(g);
		}

		for (Bullet hb : heroAtkList) {
			hb.draw(g);
		}
		for (Bullet rb : redAtkList) {
			rb.draw(g);
		}
		for (Bullet gb : greenAtkList) {
			gb.draw(g);
		}
		for (Bullet bb : blueAtkList) {
			bb.draw(g);
		}
		for(PosImageIcon p : lifeList) {
			p.draw(g);
		}
		g.setFont(new Font("Aharoni ±½°Ô", Font.BOLD, 20));
		g.setColor(Color.BLACK);
		g.drawString(GameFrame.USERNAME, 130, 29);
		g.drawString(""+GameFrame.score, 130, 77);

		repaint();
	}

}
