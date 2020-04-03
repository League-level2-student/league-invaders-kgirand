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
	public int currentStatee = MENU;
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
		if(currentStatee == MENU){
		    drawMenuState(g);
		}else if(currentStatee == GAME){
		    drawGameState(g);
		}else if(currentStatee == END){
		    drawEndState(g);
		}
	}
	void updateMenuState(){
		
	}
	void updateGameState() {
		oj.update();
		if(rocket.isActive==false) {
			currentStatee++;
		}
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
		g.setFont(smallerF);
		g.setColor(Color.YELLOW);
		g.drawString(Integer.toString(oj.score), 200, 100);
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
		if(currentStatee == MENU){
		    updateMenuState();
		}else if(currentStatee == GAME){
		    updateGameState();
		}else if(currentStatee == END){
		    updateEndState();
		}
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
		    if (currentStatee == END) {
		        currentStatee = MENU;
		        Rocketship rocketNew;
		        rocketNew = new Rocketship(200, 700, 50, 50);
		        rocket = rocketNew;
		        oj = new ObjectManager(rocketNew);
		    } 
		    else if(currentStatee == MENU) {
		    	startGame();
		    	currentStatee = GAME;
		    }
		    else if(currentStatee == GAME) {
		    	alienSpawn.stop();
		    	currentStatee++;
		    }
		    else {
		        currentStatee++;
		    }
		}   
		if(currentStatee == GAME && e.getKeyCode()==KeyEvent.VK_SPACE) {
			oj.addProjectile(rocket.getProjectile());
		}
		if(currentStatee == 1) {
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

