package boardPac;
import java.util.*;
import blockPac.Block;
import blockPac.WeightOb;
public class BoardRules {
	Block[][] boardArr;
	public int maxScore;
	ArrayList <WeightOb> newWeight = new ArrayList<WeightOb>();
	private int zeroCheck;
    public void createBoard(int xLen, int yLen) {
        boardArr = new Block[xLen][yLen];
        int i = 0;
        while (i < boardArr.length) {
            int k = 0;
            while (k < boardArr[i].length) {
                boardArr[i][k] = new Block(i,k);
                k+=1;
            }
            i+=1;
        }
    }
    public void calcMaxScore() {
    	maxScore = 0;
    	int i = 0;
    	while (i < getWidth()) {
    		int k = 0;
    		while (k < getHeight()) {
    			if (isPoint(i,k) == true && isWall(i,k) == false) {
    				maxScore++;
    			}
    			if (isPointL(i,k) == true && isWall(i,k) == false) {
    				maxScore++;
    			}
    			k++;
    		}
    		i++;
    	}
    }
    public int getMaxScore() {
    	return maxScore;
    }
    public void pacWeightClear() {
    	int x = 0;
    	while (x < getWidth()) {
    		int y = 0;
    		while (y < getHeight()) {
    			boardArr[x][y].setWeight(0);
    			y++;
    		}
    		x++;
    	}
    		
    }
    //potential optimization, to no longer call this and figure out how many zeros there are once then remove a number from that each time a weight is added
    public int checkZero() {
    	int i = 0;
    	int val = 0;
    	while (i < getWidth()) {
    		int k = 0;
    		while (k < getHeight()) {
    			if (isWall(i,k) == false && getWeight(i,k) == 0) {
    				val++;
    			}
    			k++;
    		
    		}
    		i++;
    	}
    	return val;
    }
    public void pacWeightRunner(int i,int k) {
    	newWeight.clear();
    	pacWeight(i,k,1);
    	int val = 1;
    	/*System.out.println("New Start");
    	for (int x = 0; x < newWeight.size(); x++) {
    		System.out.println(newWeight.get(x).getW());
    	}*/
    	zeroCheck = checkZero();
    	while (zeroCheck > 0) {
    		for(int arr = 0; arr < newWeight.size(); arr++) {
    			if(newWeight.get(arr).getW() == val-1) {
    				newWeight.remove(arr);
    			}
    		}
    		for(int arr = 0; arr < newWeight.size(); arr++) {
    			pacWeight(newWeight.get(arr).getX(),newWeight.get(arr).getY(),newWeight.get(arr).getW());
    		}
    		val++;
    		
    		
    	}
    	
    	
    	
    	//this is the 2nd iteration
    	/*
    	pacWeight(i,k,1);
    	if (i > getWidth()/2 && k < getHeight()/2) {
    		pacWeightRunnerTR(i,k);
    	} else if (i <= getWidth()/2 && k <= getHeight()/2) {
    		pacWeightRunnerTL(i,k);
    	} else if (i < getWidth()/2 && k > getHeight()/2) {
    		pacWeightRunnerBL(i,k);
    	} else if (i >= getWidth()/2 && k >= getHeight()/2) {
    		pacWeightRunnerBR(i,k);
    	}*/
    	
    	
    	/*
    	if (!((i > getWidth()/2 && k < getHeight()/2) || (i < getWidth()/2 && k > getHeight()/2))) {
    	int x = 0;
    	while (x < getWidth()) {
    		int y = 0;
    		while (y < getHeight()) {
    			if (boardArr[x][y].getWeight() > 0) {
    				pacWeight(x,y,(boardArr[x][y].getWeight()));
    			}
    			y++;
    		}
    		x++;
    	}
    	//unforunate circumstances
    	x = getWidth()-1;
    	while (x >= 0) {
    		int y = getHeight()-1;
    		while (y >= 0) {
    			if (boardArr[x][y].getWeight() > 0) {
    				pacWeight(x,y,(boardArr[x][y].getWeight()));
    			}
    			y--;
    		}
    		x--;
    	}
    	if (checkZero() > 0) {
    		pacWeightRunner(i,k);
    	}
    	} else {
    		int x = getWidth()-1;
        	while (x >= 0) {
        		int y = 0;
        		while (y < getHeight()) {
        			if (boardArr[x][y].getWeight() > 0) {
        				pacWeight(x,y,(boardArr[x][y].getWeight()));
        			}
        			y++;
        		}
        		x--;
        	}
        	//unforunate circumstances
        	x = 0;
        	while (x < getWidth()) {
        		int y = getHeight()-1;
        		while (y >= 0) {
        			if (boardArr[x][y].getWeight() > 0) {
        				pacWeight(x,y,(boardArr[x][y].getWeight()));
        			}
        			y--;
        		}
        		x++;
        	}
        	if (checkZero() > 0) {
        		pacWeightRunner(i,k);
        	}
    	}*/
    }
    
    public void pacWeightRunnerTL(int i, int k) {
    	int x = 0;
    	while (x < getWidth()) {
    		int y = 0;
    		while (y < getHeight()) {
    			if (boardArr[x][y].getWeight() > 0) {
    				pacWeight(x,y,(boardArr[x][y].getWeight()));
    			}
    			y++;
    		}
    		x++;
    	}
    	if (checkZero() > 0) {
    		pacWeightRunner(i,k);
    	}
    }
    
    public void pacWeightRunnerTR(int i, int k) {
    	int x = getWidth()-1;
    	while (x >= 0) {
    		int y = 0;
    		while (y < getHeight()) {
    			if (boardArr[x][y].getWeight() > 0) {
    				pacWeight(x,y,(boardArr[x][y].getWeight()));
    			}
    			y++;
    		}
    		x--;
    	}
    	if (checkZero() > 0) {
    		pacWeightRunner(i,k);
    	}
    	
    	
    }
    
    public void pacWeightRunnerBL(int i, int k) {
    	int x = 0;
    	while (x < getWidth()) {
    		int y = getHeight()-1;
    		while (y >= 0) {
    			if (boardArr[x][y].getWeight() > 0) {
    				pacWeight(x,y,(boardArr[x][y].getWeight()));
    			}
    			y--;
    		}
    		x++;
    	}
    	if (checkZero() > 0) {
    		pacWeightRunner(i,k);
    	}
    }
    
    public void pacWeightRunnerBR(int i, int k) {
    	int x = getWidth()-1;
    	while (x >= 0) {
    		int y = getHeight()-1;
    		while (y >= 0) {
    			if (boardArr[x][y].getWeight() > 0) {
    				pacWeight(x,y,(boardArr[x][y].getWeight()));
    			}
    			y--;
    		}
    		x--;
    	}
    	if (checkZero() > 0) {
    		pacWeightRunner(i,k);
    	}
    }
    
    public void pacWeight(int i, int k, int val) {
    	boardArr[i][k].setWeight(val);
    	//UP
    	pacWeightK(i,k,val,true);
    	
    	//down
    	pacWeightK(i,k,val,false);
    	
    	//left
    	pacWeightI(i,k,val,true);
    	
    	//right
    	pacWeightI(i,k,val,false);
    	
    }
    public void pacWeightK(int i, int k, int val, boolean up) {
    	if (up == true) {
    		k = k-1;
    	} else {
    		k = k+1;
    	}
    	if((k) < getHeight() && (k) >= 0) {
    		if(isWall(i, k) != true) {
    			if(boardArr[i][k].getWeight() == 0) {
    				setWeight(i,k,val+1);
    					//pacWeight(i,k,val+1);
    			}
    		}
    	} else if (k >= getHeight()) {
    		k=0;
    		if(isWall(i, k) != true) {
    			if(boardArr[i][k].getWeight() == 0) {
    				setWeight(i,k,val+1);
    			}
    		}
    	} else if (k < 0) {
    		k=getHeight()-1;
    		if(isWall(i, k) != true) {
    			if(boardArr[i][k].getWeight() == 0) {
    				setWeight(i,k,val+1);
    			}
    		}
    	}
    }
   
    public void pacWeightI(int i, int k, int val, boolean left) {
    	if (left == true) {
    		i = i-1;
    	} else {
    		i = i+1;
    	}
    	if((i) < getWidth() && (i) >= 0) {
    		if(isWall(i, k) != true) {
    			if(boardArr[i][k].getWeight() == 0) {
    				setWeight(i,k,val+1);
    					//pacWeight(i,k,val+1);
    			}
    		}
    	} else if (i >= getWidth()) {
    		i=0;
    		if(isWall(i, k) != true) {
    			if(boardArr[i][k].getWeight() == 0) {
    				setWeight(i,k,val+1);
    			}
    		}
    	} else if (i < 0) {
    		i=getWidth()-1;
    		if(isWall(i, k) != true) {
    			if(boardArr[i][k].getWeight() == 0) {
    				setWeight(i,k,val+1);
    			}
    		}
    	}
    }
    
    public void addPoint(int i, int k) {
    	boardArr[i][k].setPoint(true);
    }
    public void addPointL(int i, int k) {
    	boardArr[i][k].setPointL(true);
    }
    public void removePointL(int i, int k) {
    	boardArr[i][k].setPointL(false);
    }
    public void removePoint(int i, int k) {
    	boardArr[i][k].setPoint(false);
    }
    public void createWall(int i, int k) {
    	boardArr[i][k].createWall();
    }
    public void removeWall(int i, int k) {
    	boardArr[i][k].removeWall();
    }
    public boolean isWall(int i, int k) {
        return boardArr[i][k].getFilled();
    }
    public boolean isPoint(int i, int k) {
        return boardArr[i][k].containsPoint();
    }
    public boolean isPointL(int i, int k) {
        return boardArr[i][k].containsPointL();
    }
    public int getWeight(int i, int k) {
    	return boardArr[i][k].getWeight();
    }
    public void setWeight(int i, int k, int val) {
    	boardArr[i][k].setWeight(val);
    	newWeight.add(new WeightOb(i,k,val));
    	zeroCheck--;
    }
    public int getWidth() {
        return boardArr.length;
    }
    public int getHeight() {
        return boardArr[0].length;
    }
    public String toString() {
        int i = 0;
        String toString = "";
        String newLine = System.getProperty("line.separator");
        while (i < boardArr.length) {
            int k = 0;
            while (k < boardArr[i].length) {
                toString = toString + (boardArr[i][k].getFilled() + " ");
                k+=1;
            }
            toString += newLine;
            i+=1;
        }
        return toString;
    }
    public String toStringWeight() {
        int i = 0;
        String toString = "";
        String newLine = System.getProperty("line.separator");
        while (i < boardArr.length) {
            int k = 0;
            while (k < boardArr[i].length) {
                toString = toString + (boardArr[i][k].getWeight() + " ");
                k+=1;
            }
            toString += newLine;
            i+=1;
        }
        return toString;
    }
}
