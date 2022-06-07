package Shooting_Game;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Hero extends PosImageIcon{
	
	int hp;
	int damage;
	int moveStep;
	ImageIcon astronaut;

	public Hero(String img, int x, int y, int width, int height, int moveStep, int hp, int damage) {
		super(img, x, y, width, height);
		this.moveStep = moveStep;
		this.hp=hp;
		this.damage = damage;
		astronaut = new ImageIcon(this.img);
		
	}
	public void upMoving() {
		this.y -=moveStep;
	}
	public void downMoving() {
		this.y +=moveStep;
	}
	public void leftMoving() {
		this.x -=moveStep;
	}
	public void rightMoving() {
		this.x +=moveStep;
	}
	
	public void draw(Graphics g) {
		g.drawImage(astronaut.getImage(),x,y,120,100,null);
		
	}
	
}
