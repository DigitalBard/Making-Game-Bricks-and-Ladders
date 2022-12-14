package bricks_and_ladders;

import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class Monster extends Thread {
	// 사용할 이미지
	private Image monsterBasic = new ImageIcon(Main.class.getResource("../images/monsterBasic.png"))
			.getImage();
	private Image monsterGreen = new ImageIcon(Main.class.getResource("../images/monsterGreen.png"))
			.getImage();
	
	// 위치
	private int x, y;
	
	// 현재 상태
	private Image nowState = monsterBasic;
	
	// 한계 위치
	private int limitLeft_X, limitRight_X;
	
	// 방향
	private String direction = "right";
	
	// 루프 탈출 변수
	private boolean stop = false;
	
	// 생성자
	public Monster(int x, int y, int limitLeft_X, int limitRight_X) {
		this.x = x;
		this.y = y;
		this.limitLeft_X = limitLeft_X;
		this.limitRight_X = limitRight_X;
	}

	public Image getNowState() {
		return nowState;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void turnGreen() {
		if (Stage.eatGrape) {
			nowState = monsterGreen;
			
			// 타이머 생성
			Timer timer = new Timer();
			TimerTask timerTask = new TimerTask() {
				
				@Override
				public void run() {
					nowState = monsterBasic;
					Stage.eatGrape = false;
				}
			};
			
			timer.schedule(timerTask, 2000);
		}
	}
	
	
	@Override
	public void run() {
		while (!Stage.stageClear && !stop) {
			turnGreen();
			
			if (direction.equals("left")) {
				x -= 10;
				
				if (x <= limitLeft_X)
					direction = "right";
				
				try {
					Thread.sleep(20);
				} catch (Exception e) {
					
				}
			}
			
			else if (direction.equals("right")) {
				x += 10;
				
				if (x >= limitRight_X)
					direction = "left";
				
				try {
					Thread.sleep(20);
				} catch (Exception e) {
					
				}
			}
		}
	}
	
	public void close() {
		this.stop = true;
	}
}
