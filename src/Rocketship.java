import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject{

	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 8;
	}
	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}
	public void down() {
		y+=speed;
	}
	public void up() {
		y-=speed;
	}
	public void right() {
		x+=speed;
	}
	public void left() {
		x-=speed;
	}
}
