import javax.swing.JFrame;

public class LeagueInvaders {
JFrame frame;
GamePanel gp;
public static int WIDTH;
public static int HEIGHT;
LeagueInvaders(){
	frame = new JFrame();
	gp = new GamePanel();
	
}
void setup(){
	frame.add(gp);
	frame.addKeyListener(gp);
	WIDTH = 500;
	HEIGHT = 800;
	frame.setSize(WIDTH, HEIGHT);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
	public static void main(String[] args) {
	LeagueInvaders invader = new LeagueInvaders();
	invader.setup();
}
}
