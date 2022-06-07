package Shooting_Game;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class PosImageIcon extends ImageIcon{
	String img;
	int x;
	int y;
	int width;
	int height;
	double xx, yy;
	
	public PosImageIcon(String img, int x, int y, int width, int height) {
		super(img);
		this.img = img;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.xx = (double)x;
		this.yy=(double)y;
	}
	
	public boolean collide(Rectangle rec) {
		return rec.intersects((double)x,(double)y,(double)width,(double)height);
	}
	
	public void draw(Graphics g) {
		g.drawImage(this.getImage(), x, y, width, height, null);
	}
	

}
