package blockPac;

public class Block extends BlockFunc {
	
	private boolean filled;
    private boolean containsPoint;
    private int pacWeight;
    private boolean largePoint;
    /**
     * Constructor for objects of class Block
     */
    public Block(int x, int y)
    {
        initVal(x,y);
        filled = false;
        containsPoint = false;
        pacWeight = 0;
    }
    public int getWeight() {
    	return pacWeight;
    }
    public void setWeight(int weight) {
    	pacWeight = weight;
    }
    public void createWall() {
        filled = true;
    }
    
    public void removeWall() {
    	filled = false;
    }
    public boolean getFilled() {
        return filled;
    }
    
    public void setPoint(boolean point) {
        containsPoint = point;
    }
    
    public boolean containsPoint() {
        return containsPoint;
    }
    public void setPointL(boolean point) {
        largePoint = point;
    }
    
    public boolean containsPointL() {
        return largePoint;
    }
}
