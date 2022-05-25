package game;

import boardPac.Board;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Scanner;

import blockPac.*;
import dispSwing.*;

public class Getter {
	private Board b;
	private Pacman p;
	private ArrayList<Enemy> enemyArr;
	private GameController gC;
	private int score;
	public boolean gameOver;
	public String currentBoard;
	private final String defaultBoard = "111111110111111111222222222222222112111211211211121121322212122231211212111121111212112221221212212221121212222222121211212121212121212102120212121222120121212121212121211212122222221212112221221212212221121211112111121211213222121222312112111211211211121122222222222222211111111101111111101010806070809080810";
	//private DisplaySwing s;
	public Getter(GameController gC) {
		this.gC = gC;
		gameOver = false;
		score = 0;
		currentBoard = defaultBoard;
		//s = new DisplaySwing(b);
	}
	public String getDefaultBoard() {
		return defaultBoard;
	}
	public String getBoard() {
		return currentBoard;
	}
	public void export(boolean auto) {
    	String export = new String();
    	int i = 0;
    	while (i < b.getWidth()) {
    		int k = 0;
    		while (k < b.getHeight()) {
    			if (b.isWall(i,k) == true) {
    				export = export + "1";
    			} else if (b.isPoint(i,k) == true) {
    				export = export + "2";
    			} else if (b.isPointL(i,k)== true) {
    				export = export+"3";
    			} else {
    				export = export + "0";
    			}
    			k++;
    		}
    		i++;
    	}
    	if (p.getInitX() < 10) {
    		//System.out.println("0" + Integer.toString(p.getX()));
    		export = export + "0" + Integer.toString(p.getInitX());
    	} else {
    		//System.out.println(Integer.toString(p.getX()));
    		export = export + Integer.toString(p.getInitX());
    	}
    	if (p.getInitY() < 10) {
    		export = export + "0" + Integer.toString(p.getInitY());
    	} else {
    		export = export + Integer.toString(p.getInitY());
    	}
    	
    	for (int icounter = 0; icounter<enemyArr.size(); icounter++) {
    		if (enemyArr.get(icounter).getInitX() < 10) {
    			export = export + "0" + Integer.toString(enemyArr.get(icounter).getInitX());
        	} else {
        		export = export + Integer.toString(enemyArr.get(icounter).getInitX());
        	}
    		if (enemyArr.get(icounter).getInitY() < 10) {
    			export = export + "0" + Integer.toString(enemyArr.get(icounter).getInitY());
        	} else {
        		export = export + Integer.toString(enemyArr.get(icounter).getInitY());
        	}
		}
    	if (auto == false) {
    	StringSelection selection = new StringSelection(export);
    	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    	clipboard.setContents(selection, selection);
    	//JOptionPane.showMessageDialog(this, export);
    	System.out.println(export);
    	} else {
    		//g.importBoard(export);
    	}
    }
	public void importBoard(String board) {
		currentBoard = board;
		if (board.length() < 21*21) {
			//board = defaultBoard;
		}
		int boardCount = 0;
		int i = 0;
    	while (i < b.getWidth()) {
    		int k = 0;
    		while (k < b.getHeight()) {
    			if (board.charAt(boardCount) == '1') {
    				b.createWall(i,k);
    				b.removePoint(i,k);
    				b.removePointL(i,k);
    			} else if (board.charAt(boardCount) == '2') {
    				b.addPoint(i,k);
    				b.removePointL(i,k);
    				b.removeWall(i,k);
    			} else if (board.charAt(boardCount) == '3') {
    				b.addPointL(i,k);
    				b.removeWall(i,k);
    				b.removePoint(i,k);
    			} else {
    				b.removeWall(i,k);
    				b.removePoint(i,k);
    				b.removePointL(i,k);
    			}
    			k++;
    			boardCount++;
    		}
    		i++;
    	}
    	/*System.out.println("X");
    	System.out.println(boardCount + " charAt " + board.charAt(boardCount) + " " + board.charAt(boardCount+1));
    	System.out.println((board.charAt(boardCount+2) + "" + board.charAt(boardCount+3)));
    	System.out.println("Y");
    	System.out.println(board.charAt(boardCount+2) + "" + board.charAt(boardCount+3));*/
    	p.changeInitX(Integer.valueOf(board.charAt(boardCount) + "" + board.charAt(boardCount+1)) );
    	p.changeInitY(Integer.valueOf(board.charAt(boardCount+2) + "" + board.charAt(boardCount+3)) );
    	boardCount += 4;
    	p.moveX(p.getInitX());
    	p.moveY(p.getInitY());
    	
    	
    	
    	for (int counter = 0; counter<enemyArr.size(); counter++) {
    		
    		
			enemyArr.get(counter).changeInitX(Integer.valueOf(board.charAt(boardCount)+ "" +board.charAt(boardCount+1)));
			//System.out.println("e x" + board.charAt(boardCount)+ "" +board.charAt(boardCount+1));
			enemyArr.get(counter).changeInitY(Integer.valueOf(board.charAt(boardCount+2)+ "" +board.charAt(boardCount+3)));
			//System.out.println("e y" + board.charAt(boardCount+2)+ "" +board.charAt(boardCount+3));
			enemyArr.get(counter).moveX(enemyArr.get(counter).getInitX());
			enemyArr.get(counter).moveY(enemyArr.get(counter).getInitY());
			boardCount+=4;
		}
    	b.calcMaxScore();
    	score = 0;
    	gC.togglePause(true);
    	//System.out.println(b.getMaxScore());
    	//gC.repaint();
	}
	
	public void initObjects(Board b, Pacman p, ArrayList<Enemy> enemyArr) {
		this.b = b;
		this.p = p;
		this.enemyArr = enemyArr;
		importBoard(defaultBoard);
		//wallTest();
		b.calcMaxScore();
        //System.out.println(b.getMaxScore());
	}
	public void wallTest() {
		score = 0;
		corner(0,0);
		corner(9,0);
		corner(0,9);
		corner(9,9);
		cornerWall();
		innerWall();
		
		int i = 0;
		while (i < getWidth()) {
			b.createWall(0,i);
			b.createWall(getHeight()-1,i);
			b.createWall(i,0);
			b.createWall(i,getWidth()-1);
			i++;
		}
		addPoints();
		b.removePoint(7,6);
		b.removePoint(6,7);
		b.removePoint(8,7);
		b.removePoint(7,8);
		b.removeWall((getWidth()-1)/2,0);
		b.removeWall((getWidth()-1)/2,getHeight()-1);
		b.removeWall(0,(getWidth()-1)/2);
		b.removeWall(getHeight()-1,(getWidth()-1)/2);
		b.createWall(7,6);
		b.createWall(6,7);
		b.createWall(7,8);
		b.createWall(8,7);
	}
	public void corner(int x, int y) {
		b.createWall(2+x,2+y);
		b.createWall(2+x,3+y);
		b.createWall(3+x,2+y);
		b.createWall(3+x,3+y);
	}
	public void cornerWall() {
		//upleft
		b.createWall(5,2);
		b.createWall(5,3);
		b.createWall(5,4);
		b.createWall(5,5);
		b.createWall(4,5);
		b.createWall(3,5);
		b.createWall(2,5);
		//upright
		b.createWall(9,2);
		b.createWall(9,3);
		b.createWall(9,4);
		b.createWall(9,5);
		b.createWall(10,5);
		b.createWall(11,5);
		b.createWall(12,5);
		//downleft
		b.createWall(5,12);
		b.createWall(5,11);
		b.createWall(5,10);
		b.createWall(5,9);
		b.createWall(4,9);
		b.createWall(3,9);
		b.createWall(2,9);
		//downright
		b.createWall(9,12);
		b.createWall(9,11);
		b.createWall(9,10);
		b.createWall(9,9);
		b.createWall(10,9);
		b.createWall(11,9);
		b.createWall(12,9);
	}
	public void innerWall() {
		b.createWall((getHeight()-1)/2,2);
		b.createWall((getHeight()-1)/2,3);
		b.createWall((getHeight()-1)/2,4);
		b.createWall((getHeight()-1)/2,10);
		b.createWall((getHeight()-1)/2,11);
		b.createWall((getHeight()-1)/2,12);
		b.createWall(2,(getHeight()-1)/2);
		b.createWall(3,(getHeight()-1)/2);
		b.createWall(4,(getHeight()-1)/2);
		b.createWall(10,(getHeight()-1)/2);
		b.createWall(11,(getHeight()-1)/2);
		b.createWall(12,(getHeight()-1)/2);
		
		b.createWall(8,8);
		b.createWall(6,8);
		b.createWall(8,6);
		b.createWall(6,6);
		b.createWall(7,7);
	}
	public void addPoints() {
		int i = 0;
        while (i < getHeight()) {
            int k = 0;
            while (k < getWidth()) {
                if (isWall(i,k) == false) {
                	//System.out.println("added point to " + i + ", " + k);
                	b.addPoint(i,k);
                	//System.out.println(b.isPoint(i,k));
                }
                k+=1;
            }
            i+=1;
        }
        b.removePoint(4,4);
        b.addPointL(4,4);
        b.removePoint(10,4);
        b.addPointL(10,4);
        b.removePoint(4,10);
        b.addPointL(4,10);
        b.removePoint(10,10);
        b.addPointL(10,10);
        
    }
	
	public void gameOverM(boolean game) {
		gameOver = game;
		
	}
	public boolean gameOver() {
		return gameOver;
	}
	//score
	public void addScore() {
		score++;
	}
	public int getScore() {
		return score;
	}
	public int getMaxScore() {
		return b.getMaxScore();
	}
	//Board Functions
	public int getHeight() {
		return b.getHeight();
	}
	public int getWidth() {
		return b.getWidth();
	}
	public boolean isWall(int x, int y) {
		return b.isWall(x,y);
	}
	public boolean isPoint(int x, int y) {
		return b.isPoint(x,y);
	}
	public boolean isPointL(int x, int y) {
		return b.isPointL(x,y);
	}
	public int getWeight(int x, int y) {
		return b.getWeight(x,y);
	}
	//
	//Pacman Functions
	public int pgetX() {
		return p.getX();
	}
	public int pgetY() {
		return p.getY();
	}
	//
	
	//Enemy Functions
	public int egetSize() {
		return enemyArr.size();
	}
	public int egetX(int index) {
		return enemyArr.get(index).getX();
	}
	public int egetY(int index) {
		return enemyArr.get(index).getY();
	}
	public boolean getEdible(int index) {
		return enemyArr.get(index).getEdible();
	}
	//
	
}
