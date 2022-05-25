package blockPac;

public abstract class BlockFunc {
	private int x;
    private int y;
    public boolean alive;
    private int timespan;
    
    public void initVal(int x, int y) {
    	this.x = x;
    	this.y = y;
    	alive = true;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void moveX(int x) {
    	this.x = x;
    }
    
    public void moveY(int y) {
    	this.y = y;
    }
    
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void timespan(boolean set) {
    	if (set == true) {
    		timespan = 10;
    		kill();
    	} else {
    		timespan--;
    	}
    	if (timespan <= 0) {
    		res();
    	}
    }
    public void timespan(boolean set, int time) {
    	if (set == true) {
    		timespan = time;
    		kill();
    	} else {
    		timespan--;
    	}
    	if (timespan <= 0) {
    		res();
    	}
    }
    public void kill() {
    	alive = false;
    }
    public void res() {
    	alive = true;
    }
    
    public boolean getAlive() {
    	return alive;
    }
}
