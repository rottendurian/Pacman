package boardMaker;

public class pacObject {
	private int x;
	private int y;
	public pacObject(int x, int y) {
		this.x = x;
		this.y = y;
		
	}
	public void setInit(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void setInitX(int x) {
		this.x =x;
	}
	public void setInitY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
