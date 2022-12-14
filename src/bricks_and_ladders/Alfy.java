package bricks_and_ladders;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Alfy {
	// 이미지 파일
	private Image alfyLeftMove = new ImageIcon(Main.class.getResource("../images/alfyLeftMove.png"))
			.getImage();
	private Image alfyLeftStop = new ImageIcon(Main.class.getResource("../images/alfyLeftStop.png"))
			.getImage();
	private Image alfyLeftJump = new ImageIcon(Main.class.getResource("../images/alfyLeftJump.png"))
			.getImage();
	private Image alfyRightMove = new ImageIcon(Main.class.getResource("../images/alfyRightMove.png"))
			.getImage();
	private Image alfyRightStop = new ImageIcon(Main.class.getResource("../images/alfyRightStop.png"))
			.getImage();
	private Image alfyRightJump = new ImageIcon(Main.class.getResource("../images/alfyRightJump.png"))
			.getImage();
	
	// 위치
	private int pos_X, pos_Y;
	
	// 보고 있는 방향
	private String direction;
	
	// 사다리를 탔는지 여부
	private boolean laddering = false;
	
	// 표시할 이미지
	private Image state;
	
	public Image getState() {
		return state;
	}
	
	public void setState(Image image) {
		this.state = image;
	}

	public String getDirection() {
		return direction;
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getPos_X() {
		return pos_X;
	}
	
	public void setPos_X(int pos_X) {
		this.pos_X = pos_X;
	}

	public int getPos_Y() {
		return pos_Y;
	}
	
	public void setPos_Y(int pos_Y) {
		this.pos_Y = pos_Y;
	}
	
	public void setPosition(int pos_X, int pos_Y) {
		this.pos_X = pos_X;
		this.pos_Y = pos_Y;
	}
	
	public Image getAlfyLeftMove() {
		return alfyLeftMove;
	}
	
	public Image getAlfyRightMove() {
		return alfyRightMove;
	}

	public void moveToRight() {
		if (direction.equals("left")) {
			direction = "right";
		}
		
		if (pos_X <= 1216)
			pos_X += 30;
		
		state = alfyRightMove;
	}
	
	public void moveToLeft() {
		if (direction.equals("right")) {
			direction = "left";
		}
		
		if (pos_X >= 0)
			pos_X -= 30;
		state = alfyLeftMove;
	}
	
	public void upper() {
		if (laddering && pos_Y > 116)
			pos_Y -= 55;
	}
	
	public void lower() {
		if (laddering && pos_Y <= 422)
			pos_Y += 55;
	}
	
	public void jump() {
		
		if (direction.equals("left") && KeyListener.pressedLeftKey) {
			pos_X -= 100;
			pos_Y -= 60;
			state = alfyLeftJump;
		}
		else if (direction.equals("right") && KeyListener.pressedRightKey) {
			pos_X += 100;
			pos_Y -= 60;
			state = alfyRightJump;
		}	
		else if (direction.equals("left") && !KeyListener.pressedLeftKey) {
			pos_Y -= 60;
			state = alfyLeftJump;
		}
		else if (direction.equals("right")) {
			pos_Y -= 60;
			state = alfyRightJump;
		}
	}
	
	public void descend() {
		
		if (direction.equals("left") && KeyListener.pressedLeftKey) {
			pos_X -= 100;
			pos_Y += 60;
			state = alfyLeftStop;
		}
		else if (direction.equals("right") && KeyListener.pressedRightKey) {
			pos_X += 100;
			pos_Y += 60;
			state = alfyRightStop;
		}	
		else if (direction.equals("left") && !KeyListener.pressedLeftKey) {
			pos_Y += 60;
			state = alfyLeftStop;
		}
		else if (direction.equals("right")) {
			pos_Y += 60;
			state = alfyRightStop;
		}
	}
	
	public void keyRelease() {
		if (direction.equals("left"))
			state = alfyLeftStop;
		else if (direction.equals("right"))
			state = alfyRightStop;
	}
	
	public void checkLaddering() {
		if (300 <= getPos_X() && getPos_X() <= 360)
			laddering = true;
		else
			laddering = false;
	}
	
	public void dropAlfy() {
		if (528 <= getPos_X() && getPos_X()+64 <= 854 && 500 <= getPos_Y() + 120 
				&& getPos_Y() + 120 <= 550) 
		{
			setPos_Y(552);
			
			new Music("died.mp3", false).start();
			
			for (int i=0; i < BricksAndLadders.stage.getMonList().size(); i++) {
				BricksAndLadders.stage.getMonList().get(i).close();
			}
			
			try {
				Thread.sleep(2000);
			} catch (Exception e) {}
			
			BricksAndLadders.stage.close();
			Main.bal.restartStage();
			
		}
		else if (523 <= getPos_X() && getPos_X()+64 <= 642 && 180 <= getPos_Y() + 110 
				&& getPos_Y() + 110 <= 220) 
		{
			setPos_Y(552);
			
			new Music("died.mp3", false).start();
			
			for (int i=0; i < BricksAndLadders.stage.getMonList().size(); i++) {
				BricksAndLadders.stage.getMonList().get(i).close();
			}
			
			try {
				Thread.sleep(2000);
			} catch (Exception e) {}
			
			BricksAndLadders.stage.close();
			Main.bal.restartStage();
		}
		else if (1064 <= getPos_X() && 180 <= getPos_Y() + 120 && getPos_Y() + 120 <= 220)
			setPos_Y(252);
		else if (getPos_X() + 64 <= 1063 && 250 <= getPos_Y() && getPos_Y() <= 252)
			setPos_Y(422);
	}
}


