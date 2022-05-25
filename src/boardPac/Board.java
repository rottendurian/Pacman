package boardPac;

public class Board extends BoardRules {
	public Board(int xLen, int yLen) {
        createBoard(xLen,yLen);
    }
    
    public static void main(String[] args) {
        Board r = new Board(8,8);
        r.pacWeightRunner(0,0);
        System.out.println(r.toString());
        System.out.println(r.toStringWeight());
    }
}
