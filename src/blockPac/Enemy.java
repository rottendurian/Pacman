package blockPac;

import game.Getter;

public class Enemy extends PacFunc{
	

	private int initX;
	private int initY;
	private boolean edible;
	
	
	public Enemy(int x, int y, Getter b) {
		initX = x;
		initY = y;
		init(x,y,b);
		edible = false;
	}
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
	public void toggleEdible(boolean toggle) {
		if (toggle == true) {
			edible = true;
		} else {
			edible = false;
		}
	}
	
	public boolean getEdible() {
		return edible;
	}
	
	public void move() {
		if (alive == false) {
			timespan(false);
			moveX(initX);
			moveY(initY);
		} else {
			setDirection();
			//super.move();
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
		/*
		if (d == 1) {
			moveUp(false);
		} else if (d==2) {
			moveDown(false);
		} else if (d==3) {
			moveLeft(false);
		} else if (d==4) {
			moveRight(false);
		}*/
	}
	
	public void setDirection() {
		if (b.getWeight(getX(),getY()) != 1) {
		int[] array = new int[4]; 
		
		
		
		//up
		if (outOfBounds(getX(),getY()-1) == true) {
			array[0] = b.getWeight(getX(),b.getHeight()-1);
		} else {
			array[0] = b.getWeight(getX(),getY()-1); 
		}
		
		//down
		if (outOfBounds(getX(),getY()+1) == true) {
			array[1] = b.getWeight(getX(),0);
		} else {
			array[1] = b.getWeight(getX(),getY()+1);
		}
		//left
		if (outOfBounds(getX()-1,getY()) == true) {
			array[2] = b.getWeight(b.getWidth()-1,getY());
		} else {
			array[2] = b.getWeight(getX()-1,getY());
		}
		//right
		if (outOfBounds(getX()+1,getY()) == true) {
			array[3] = b.getWeight(0,getY());
		} else {
			array[3] = b.getWeight(getX()+1,getY());
		}
		int i = 0;
		int val = 0;
		int dir = 0;
		if (edible == false) {
			while (i < array.length) {
				if (array[i] != 0) {
					if (val == 0) {
						val = array[i];
						dir = i+1;
					} else if (val > array[i]) {
						val = array[i];
						dir = i+1;
					}
				}
				
				i++;
			}
		} else if (edible == true) {
			while (i < array.length) {
				if (array[i] != 0) {
					
					if (val == 0) {
						val = array[i];
						dir = i+1;
					} else if (val < array[i]) {
						val = array[i];
						dir = i+1;
					}
					
				}
				
				i++;
			}
		}
		if (val > (b.getWeight(getX(),getY())) || edible == false) {
			if (dir == 1) {
				d = 1;
			} else if (dir == 2) {
				d = 2;
			} else if (dir == 3) {
				d = 3;
			} else if (dir == 4) {
				d = 4;
			}
		}
		}
		else {
			d = 0;
		}
	}
	public void moveUp() {
		/*if (outOfBounds(getX(),getY()-1) == false) {
			if(b.isWall(getX(),getY()-1) == true) {
				//changeDirection();
				//move();
			}
		}*/
		super.moveUp();
	}
	public void moveDown() {
		if (outOfBounds(getX(),getY()+1) == false) {
			if(b.isWall(getX(),getY()+1) == true) {
				//changeDirection();
				//move();
			}
		}
		super.moveDown();
	}
	public void moveLeft() {
		if (outOfBounds(getX()-1,getY())== false) {
			if(b.isWall(getX()-1,getY()) == true) {
				//move();
			}
		}
		super.moveLeft();
	}
	public void moveRight() {
		if (outOfBounds(getX()+1,getY())== false) {
			if(b.isWall(getX()+1,getY()) == true) {
				//move();
			}
		}
		super.moveRight();
	}
	/*
	public void checkLR() {
		if (outOfBounds(getX()+1,getY()) == false && outOfBounds(getX()-1,getY()) == false) {
		if (b.isWall(getX()+1,getY()) == false) {
			d = 4;
			moveRight(true);
		} if (b.isWall(getX()-1,getY()) == false) {
			moveLeft(true);
			d = 3;
		}
		} else {
			changeDirection();
		}
	}
	public void checkUD() {
		if (outOfBounds(getX(),getY()+1) == false && outOfBounds(getX(),getY()-1) == false) {
		if (b.isWall(getX(),getY()+1) == false) {
			d = 2;
			moveDown(true);
		}
		if (b.isWall(getX(),getY()-1) == false) {
			d = 1;
			moveUp(true);
		}
		} else {
			changeDirection();
		}
		
	}
	public void moveUp(boolean OG) {
		if (outOfBounds(getX(),getY()-1) == false) {
		if(b.isWall(getX(),getY()-1) == true && OG == false) {
			changeDirection();
			move();
		}
		if (OG == false) {
			if (((int)(Math.random()*6)+1) == 1) {
				checkLR();
			}
			
		}
		}
		super.moveUp();
	}
	public void moveDown(boolean OG) {
		if (outOfBounds(getX(),getY()+1) == false) {
		if(b.isWall(getX(),getY()+1) == true && OG == false) {
			changeDirection();
			move();
		}
		if (OG == false) {
			if (((int)(Math.random()*6)+1) == 1) {
				checkLR();
			}
		}
		}
		super.moveDown();
	}
	public void moveLeft(boolean OG) {
		if (outOfBounds(getX()-1,getY()) == false) {
		if(b.isWall(getX()-1,getY()) == true && OG == false) {
			changeDirection();
			move();
		}
		if (OG == false) {
			if (((int)(Math.random()*6)+1) == 1) {
				checkUD();
			}
		}
		}
		super.moveLeft();
	}
	public void moveRight(boolean OG) {
		if (outOfBounds(getX()+1,getY()) == false) {
		if(b.isWall(getX()+1,getY()) == true && OG == false) {
			changeDirection();
			move();
		}
		if (OG == false) {
			if (((int)(Math.random()*6)+1) == 1) {
				checkUD();
			}
		}
		}
		super.moveRight();
	}
	
	public void changeDirection() {
		int max = 4;
        int min = 1;
        int range = max - min + 1;
        d = (int)(Math.random() * range) + min;
        
	}
	*/
	public static void main(String[] args) {
		
		
	}
}

