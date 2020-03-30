import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font smallerF;
	Timer frameDraw;
	Timer alienSpawn;
	Rocketship rocket = new Rocketship(200,700,50,50);
	ObjectManager oj = new ObjectManager(rocket);
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	GamePanel(){
		titleFont = new Font("Arial", Font.PLAIN, 48);
		smallerF = new Font("Arial", Font.PLAIN, 25);
		frameDraw = new Timer(1000/60, this);
		frameDraw.start();
		loadImage("space.png");
	}
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}
	}
	void updateMenuState(){
		
	}
	void updateGameState() {
		oj.update();
	}
	void updateEndState() {
		
	}
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 23, 100);
		g.setFont(smallerF);
		g.drawString("press ENTER to start", 115, 400);
		g.drawString("press SPACE for instructions", 90, 500);
	}
	void drawGameState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
		oj.draw(g);
	}
	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("GAME OVER", 23, 100);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		System.out.println("action");
		repaint();
	}
	void startGame() {
		alienSpawn = new Timer(1000,oj);
		alienSpawn.start();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		    } 
		    else if(currentState == MENU) {
		    	startGame();
		    	currentState = GAME;
		    }
		    else if(currentState == GAME) {
		    	alienSpawn.stop();
		    	currentState++;
		    }
		    else {
		        currentState++;
		    }
		}   
		if(currentState == GAME && e.getKeyCode()==KeyEvent.VK_SPACE) {
			oj.addProjectile(rocket.getProjectile());
		}
		if(currentState == 1) {
		if (e.getKeyCode()==KeyEvent.VK_UP&&rocket.y>1) {
		    rocket.up();
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN&&rocket.y<740) {
			rocket.down();
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT&&rocket.x>1) {
			rocket.left();
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT&&rocket.x<450) {
			rocket.right();
		}
		}

	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	void loadImage(String imageFile) {
		   if (needImage) {
		        try {
		            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
			    gotImage = true;
		        } catch (Exception e) {
		            
		        }
		        needImage = false;
		    }
		}
}

