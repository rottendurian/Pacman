package boardMaker;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import boardPac.Board;
import dispSwing.DisplaySwing;
import game.Getter;

public class BoardMaker {
	private SwingDisplay s;
	private Board b;
	private Getter g;
	public BoardMaker(int x, int y, Getter g) {
		b = new Board(x,y);
		
		s = new SwingDisplay(b, g);
		s.repaint();
	}
	public static void main(String[] args) {
		//Getter g = new Getter();
		//BoardMaker boardM = new BoardMaker(17,17, g);
	}
}
