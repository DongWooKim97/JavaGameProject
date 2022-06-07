package Shooting_Game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainFrame {

	final String MAINBACK = "src/images/background.PNG";
	final String GAMEBACK = "src/images/gameBackground.jpg";

	public static final int SCREEN_WIDTH = 1440;
	public static final int SCREEN_HEIGHT = 720;

	ImageIcon exitBtnImage = new ImageIcon(MainFrame.class.getResource("../images/quitButtonBasic.png"));
	ImageIcon startBtnImage = new ImageIcon(MainFrame.class.getResource("../images/startButtonBasic.png"));

	ReadyPanel rp = new ReadyPanel();
	GamePanel gp = new GamePanel();

	JFrame mf;

	JButton exitBtn = new JButton(exitBtnImage);
	JButton startBtn = new JButton(startBtnImage);
	Image screenBack;

	PosImageIcon panelBackground;

	public static void main(String[] args) {

		new MainFrame().go();
	}

	public void go() {
		mf = new JFrame();
		mf.setTitle("Guardians Of Galaxy");
		mf.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		mf.setResizable(false);
		mf.setLocationRelativeTo(null);
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ButtonListener buttonLis = new ButtonListener();
		MyKeyListener keyLis = new MyKeyListener();

		startBtn.addActionListener(buttonLis);
		startBtn.setContentAreaFilled(false);
		startBtn.setBorderPainted(false);
		startBtn.setFocusPainted(false);
		startBtn.setBounds(0, 620, 400, 100);
		exitBtn.addActionListener(buttonLis);
		exitBtn.setContentAreaFilled(false);
		exitBtn.setBorderPainted(false);
		exitBtn.setFocusPainted(false);
		exitBtn.setBounds(1040, 620, 400, 100);

		rp.add(startBtn);
		rp.add(exitBtn);

		rp.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		mf.addKeyListener(keyLis);
		mf.add(rp);
		mf.pack();
		mf.setFocusable(true);
		mf.setVisible(true);
	}

	public class ReadyPanel extends JPanel {
		public ReadyPanel() {
			setLayout(null);
			panelBackground = new PosImageIcon(MAINBACK, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		}

		public void paintComponent(Graphics g) {
			panelBackground.draw(g);
		}
	}

	public class ButtonListener implements ActionListener { // before start game, select button.
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == startBtn) { // click startButton -> Game Start ! ! !
				new GameFrame().go();
				mf.dispose();
			} else if (e.getSource() == exitBtn) {
				System.exit(0); // click not startButton(=exitButton) -> Close window ! ! !
			}
		}
	}

	public class MyKeyListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case (KeyEvent.VK_ENTER):
				new GameFrame().go();
				mf.dispose();
				break;
			case (KeyEvent.VK_ESCAPE):
				System.exit(0);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}
	}
}