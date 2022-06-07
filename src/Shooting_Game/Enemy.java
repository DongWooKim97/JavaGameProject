package Shooting_Game;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Enemy extends PosImageIcon {
	int hp;
	ImageIcon e;

	public Enemy(String img, int x, int y, int width, int height, int hp) {
		super(img, x, y, width, height);
		this.x=x;
		this.y=y;
		this.xx=(double)x;
		this.yy=(double)y;
		
		this.hp = hp;
		e=new ImageIcon(this.img);

	}

	public void draw(Graphics g) {
		g.drawImage(e.getImage(),x,y,120,100,null);
		
	}

}
