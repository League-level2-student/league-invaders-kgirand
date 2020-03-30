import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
Rocketship rocket;
ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
ArrayList<Alien> aliens = new ArrayList<Alien>();
Random randy = new Random();
ObjectManager(Rocketship rocket){
	this.rocket = rocket;
}
void addProjectile(Projectile Object){
projectiles.add(Object);	
}
void addAlien() {
aliens.add(new Alien(randy.nextInt(LeagueInvaders.WIDTH),0,50,50));
}
void checkCollision() {
	for(int i = 0; i<aliens.size();i++) {
		if(rocket.collisionBox.intersects(aliens.get(i).collisionBox)) {
			aliens.get(i).isActive = false;
			rocket.isActive=false;
		
		}
		for(int j =0; i<projectiles.size(); j++) {
			if(projectiles.get(j).collisionBox.intersects(aliens.get(j).collisionBox)) {
				aliens.get(1).isActive = false;
			}
		}
	}
}

void update() {
	for(int i = 1; i<aliens.size(); i++) {
		aliens.get(i).update();
		if(aliens.get(i).y>LeagueInvaders.HEIGHT) {
			aliens.get(i).isActive = false;
		}
	}
	for(int p = 1; p < projectiles.size(); p++) {
		projectiles.get(p).update();
		if(projectiles.get(p).y>LeagueInvaders.HEIGHT) {
			aliens.get(p).isActive = false;
		}
	}
	checkCollision();
	purgeObjects();
	
}
void draw(Graphics g) {
	rocket.draw(g);
	for(int i=1;i<aliens.size();i++) {
		aliens.get(i).draw(g);
	}
	for(int i = 1; i<projectiles.size();i++) {
		projectiles.get(i).draw(g);
	}
}
void purgeObjects() {
	for(int i = 1; i<aliens.size(); i++) {
		if(aliens.get(i).isActive == false) {
			aliens.remove(aliens.get(i));
		}
	}
	for(int p = 1; p < projectiles.size(); p++) {
		if(projectiles.get(p).isActive == false) {
			projectiles.remove(projectiles.get(p));
		}
	}
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	addAlien();
}
}
