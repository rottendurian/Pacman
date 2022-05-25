package blockPac;

import boardPac.Board;
import game.Getter;

public abstract class PacFunc extends BlockFunc {
	public int d; // 1 up, 2 down, 3 left, 4 right
	public Getter b;
	private int initX;
	private int initY;
	public void changeInitX(int x) {
		initX = x;
	}
	public void changeInitY(int y) {
		initY = y;
	}
	public int getInitX() {
		return initX;
	}
	public int getInitY() {
		return initY;
	}
	public void init(int x, int y, Getter b) {
		d = 1;
		this.b = b;
		initVal(x,y);
		initX = x;
		initY = y;
	}
	public void changeD(int i) {
		d = i; 
	}
	public int getD() {
		return d;
	}
	public void move() {
		if (d == 1) {
			moveUp();
		} else if (d==2) {
			moveDown();
		} else if (d==3) {
			moveLeft();
		} else if (d==4) {
			moveRight();
		}
	}
	public void moveUp() {
		if (outOfBounds(getX(),getY()-1) == true) {
			moveY(b.getHeight()-1);
		} else if(b.isWall(getX(),getY()-1) != true) {
			moveY(getY()-1);
		}
	}
	public void moveDown() {
		if (outOfBounds(getX(),getY()+1) == true) {
			moveY(0);
		} else if(b.isWall(getX(),getY()+1) != true) {
		moveY(getY()+1);
		}
	}
	public void moveLeft() {
		if (outOfBounds(getX()-1,getY()) == true) {
			moveX(b.getWidth()-1);
		} else if(b.isWall(getX()-1,getY()) != true) {
		moveX(getX()-1);
		}
	}
	public void moveRight() {
		if (outOfBounds(getX()+1,getY()) == true) {
			moveX(0);
		} else if(b.isWall(getX()+1,getY()) != true) {
		moveX(getX()+1);
		}
	}
	public boolean outOfBounds(int x, int y) {
		if (x >= b.getWidth() || x < 0) {
			return true;
		} else if (y >= b.getHeight() || y < 0) {
			return true;
		}
		return false;
	}
}
