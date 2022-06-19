package blockPac;

import game.Getter;

public class Pacman extends PacFunc{
	
	public Pacman (int x, int y, Getter b) {
		init(x,y,b);
	}
	public void changeD(int i) {
		
		if(i == 1 && outOfBounds(getX(),getY()-1) == false) {
			if (b.isWall(getX(),getY()-1) == false) {
				d = i;
			}
		} else if(i == 2 && outOfBounds(getX(),getY()+1) == false) {
			if (b.isWall(getX(),getY()+1) == false) {
				d = i;
			}
		} else if(i == 3 && outOfBounds(getX()-1,getY()) == false) {
			if (b.isWall(getX()-1,getY()) == false) {
				d = i;
			}
		} else if(i == 4 && outOfBounds(getX()+1,getY()) == false) {
			if (b.isWall(getX()+1,getY()) == false) {
				d = i;
			}
		}
	}
}
