package Shooting_Game;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Bullet extends PosImageIcon{
	ImageIcon bulletImg;
	double dX, dY;


	
	public Bullet(String img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		bulletImg = new ImageIcon(this.img);
		
	
	}
	public void heroBulletMoving() {
		this.x +=1;

	}
	public void enemyBulletMoving() {
		this.x-=1.5;		
		
	}
	
	
	public void draw(Graphics g) {
		g.drawImage(bulletImg.getImage(),x,y,width,height,null);
	}

}
