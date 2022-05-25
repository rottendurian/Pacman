package game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import blockPac.Enemy;
import blockPac.Pacman;
import boardPac.Board;
import dispSwing.DisplaySwing;


public class GameController{
	private Getter g;
	private DisplaySwing s;
	private Board b;
	private Pacman p;
	private boolean pause;
	private boolean resetGame;
	private boolean inLoop;
	ArrayList<Enemy> enemyArr;
	
	
	//private Enemy e1;
	//private Enemy e2;
	private int edibleCount;
	private boolean exit;
	public GameController() {
		g = new Getter(this);
		
		b = new Board(17,17);
		p = new Pacman(1,1,g);
		Enemy e = new Enemy(7,6,g);
		Enemy e1 = new Enemy(6,7,g);
		Enemy e2 = new Enemy(8,7,g);
		Enemy e3 = new Enemy(7,8,g);
		
		/*e1.timespan(true,10);
		e2.timespan(true,20);
		e3.timespan(true,30);*/
		enemyArr = new ArrayList<Enemy>();
		enemyArr.add(e);
		enemyArr.add(e1);
		enemyArr.add(e2);
		enemyArr.add(e3);
		for (int counter = 0; counter<enemyArr.size(); counter++) {
			enemyArr.get(counter).timespan(true,counter*10);
		}
		enemyArr.get(0).timespan(true,2);
		g.initObjects(b,p,enemyArr);
		s = new DisplaySwing(g, this);
		s.repaint();
		togglePause(true);
		gameRun();
		//
		
		/*g.initObjects(b,p,enemyArr);
		
		s.repaint();
		gameRun();*/
	}
	public void repaint() {
		
		s.repaint();
		
		
	}
	public void resetGame() {
		p.moveX(p.getInitX());
		p.moveY(p.getInitY());
		p.changeD(2);
		
		for (int counter = 0; counter<enemyArr.size(); counter++) {
			enemyArr.get(counter).timespan(true,counter*10);
			enemyArr.get(counter).moveX(enemyArr.get(counter).getInitX());
			enemyArr.get(counter).moveY(enemyArr.get(counter).getInitY());
		}
		enemyArr.get(0).timespan(true,2);
		for (int counter = 0; counter<enemyArr.size(); counter++) {
			enemyArr.get(counter).toggleEdible(false);
		}
		g.importBoard(g.getBoard());
		if (inLoop == true) {
		//exit = true;
		}
		//System.out.println("resetWaited");
		p.res();
		g.gameOverM(false);
		s.repaint();
		//System.out.println(inLoop);
		/*if (inLoop == false) {
			System.out.println("inLoop if statement run");
			s.repaint();
			gameRun();
		} else {
			
		}*/
		//s = new DisplaySwing(g,this);
		//
		
		
	}
	public Getter getGetter() {
		return g;
	}
	public void gameRun() {
		int i = 0;
		edibleCount = 0;
		exit = false;
		//System.out.println("Exit = false");
		//togglePause(true);
		while (exit != true) {
			
			inLoop = true;
			
			if (pause == true) {
				s.repaint();
			}
			if (pause != true) {
				//System.out.println("Loop started");
				checkConditions();
	            
	            if (p.getAlive() == false) {
					pause = true;
					g.gameOverM(true);
				}
	            if (b.getMaxScore() == g.getScore()) {
	            	pause = true;
	            	g.gameOverM(true);
	            }
	            
	            b.pacWeightClear();
	            b.pacWeightRunner(p.getX(),p.getY());
	            //System.out.println(b.toStringWeight());
	            //System.out.println(pause);
	            p.move();
	            checkConditionsDead();
	            if (i == 2) {
	            	for (int counter = 0; counter<enemyArr.size(); counter++) {
	        			enemyArr.get(counter).move();
	        		}
	            	
	            	i = 0;
	            }
	            
	            if (enemyArr.get(0).getEdible() == true) {
	            	
	            	edibleCount++;
	            	if (edibleCount == 50) {
	            		for (int icount = 0; icount<enemyArr.size(); icount++) {
	            			enemyArr.get(icount).toggleEdible(false);
	            		}
	            		//e.toggleEdible(false);
	            		edibleCount = 0;
	            	}
	            }
	            i++;
	            s.repaint();
			
            
			}
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                
            }
			
        }
		System.out.println("EXITEDLOOP");
		inLoop = false;
		//gameRun();
		
		//s.repaint();
		//togglePause(true);
		/*if (resetGame = true) {
			gameRun();
			g.gameOverM(false);
		} else {
			
		}*/
		
	}
	public void checkConditions() {
		if (b.isPointL(p.getX(), p.getY()) == true) {
			b.removePointL(p.getX(), p.getY());
			g.addScore();
			for (int i = 0; i<enemyArr.size(); i++) {
				enemyArr.get(i).toggleEdible(true);
			}
			//e.toggleEdible(true);
			edibleCount = 0;
		} 
		if (b.isPoint(p.getX(), p.getY()) == true) {
			b.removePoint(p.getX(), p.getY());
			g.addScore();
		}
		
		
		for (int i = 0; i <enemyArr.size(); i++) {
			if(p.getX() == enemyArr.get(i).getX() && p.getY() == enemyArr.get(i).getY()) {
				if (enemyArr.get(i).getEdible() == true) {
					enemyArr.get(i).kill();
					enemyArr.get(i).timespan(true);
				} else {
					p.kill();
				}
			}
		}
		checkConditionsDead();
		
		//System.out.println(g.getScore());
	}
	public void checkConditionsDead() {
		for (int i = 0; i <enemyArr.size(); i++) {
			if(p.getX() == enemyArr.get(i).getX() && p.getY() == enemyArr.get(i).getY()) {
				if (enemyArr.get(i).getEdible() == true) {
					enemyArr.get(i).kill();
					enemyArr.get(i).timespan(true);
				} else {
					p.kill();
				}
			}
		}
	}
	public static void main(String[] args) {
		GameController gc = new GameController();
	}
	public void keyPress(KeyEvent w) {
		// TODO Auto-generated method stub
		if (w.getKeyChar() == 'w') {
			p.changeD(1);
			togglePause(false);
		} else if (w.getKeyChar() == 's') {
            p.changeD(2);
            togglePause(false);
        } else if (w.getKeyChar() == 'a') {
            p.changeD(3);
            togglePause(false);
        } else if (w.getKeyChar() == 'd') {
            p.changeD(4);
            togglePause(false);
        } else if (w.getKeyCode() == KeyEvent.VK_ESCAPE) {
        	togglePause();
        } else if (w.getKeyChar() == 'r') {
        	resetGame();
        	
        }
	}
	
	public void togglePause() {
		if (pause != true) {
			pause = true;
		} else {
			pause = false;
		}
	}
	public void togglePause(boolean p) {
		pause = p;
	}
}
