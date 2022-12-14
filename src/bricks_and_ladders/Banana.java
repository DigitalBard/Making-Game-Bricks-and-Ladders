package bricks_and_ladders;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Banana extends Fruit {
	
	private Image banana = new ImageIcon(Main.class.getResource("../images/bananaItem.png"))
			.getImage();
	
	
	public Banana(int pos_x, int pos_y) {
		super(pos_x, pos_y);
	}
	
	
	public Image getBanana() {
		return banana;
	}
}


