package boardMaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import boardPac.Board;
import game.Getter;

public class bPanel extends JPanel {
	private int h;
    private int w;
    private int xMove = 50;
    private int yMove = 70;
    private Board gB;
    public pacObject p;
    public ArrayList<pacObject> enemyArr;
    public bPanel(Board gB, pacObject p, ArrayList<pacObject> enemyArr) {
    	this.gB = gB;
    	this.p = p;
        this.enemyArr = enemyArr;
        setPreferredSize(new Dimension (800,800));
        h = 800/gB.getHeight();
        w = 800/gB.getWidth();
        
        /*xMove = 50;
        yMove = 70;*/
    }
    public int getH() {
    	return h;
    }
    public int getW() {
    	return w;
    }
    public int xMove() {
    	return xMove;
    }
    public int yMove() {
    	return yMove;
    }
    @Override
	public void update(Graphics g) {
        super.update(g);
    }

    public void repaint(Graphics g) {
        super.repaint();
        paintComponent(g);

    }

    @Override
	public void paint(Graphics g) {
        super.paint(g);
        paintBoard(g);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        paintBoard(g);
        //paintSquare(g, 200,200);
        //g.drawString("test", 20, 20);
        //g.drawRect(200,200,200,200);
    }
    /**
     * paints the board
     */
    public void paintBoard(Graphics g) {
    	
    	//paintPoint(g,6*w,5*h);
        int i = 0;
        while (i < gB.getWidth()) {
            int k = 0;
            while (k < gB.getHeight()) {
                if (gB.isWall(i,k) == false) {
                	//System.out.println(gBo.isWall(i,k));
                	//System.out.println(gBo.isPoint(i,k));
                    paintSquare(g, i*w, k*h);
                    if (gB.isPoint(i,k) == true) {
                    	//System.out.println("painted point at" + i + ", " + k);
                        paintPoint(g,i*w,k*h);
                    } else if (gB.isPointL(i,k)==true) {
                    	paintPointL(g,i*w,k*h);
                    }
                } else if (gB.isWall(i,k) == true) {
                    paintFilledSquare(g, i*w, k*h);
                }

                k++;
            }
            i++;
        }
        paintPac(g);
        for (int icounter = 0; icounter<enemyArr.size(); icounter++) {
			paintEnemy(g,icounter);
		}
        
    }
    /**
     * paints an empty square
     */
    public void paintSquare(Graphics g, int x, int y) {
        g.setColor(Color.white);
        g.drawRect(x+xMove,y+yMove,w,h);
        //paintPacWeight(g,x,y);
    }
    public void paintPacWeight(Graphics g, int x, int y) {
    	g.setColor(Color.white);
        float f=20.0f; // font size.
        g.setFont(g.getFont().deriveFont(f));
        g.drawString(("" + gB.getWeight(x/w,y/h)), x+xMove+20, y+yMove+20);
    }
    public void paintFilledSquare(Graphics g, int x, int y) {
        g.setColor(Color.BLUE);
        g.fillRect(x+xMove,y+yMove,w,h);
        //paintPacWeight(g,x,y);
    }

    public void paintPac(Graphics g) {
    	g.setColor(Color.yellow);
    	//System.out.println(p.getX());
    	g.fillOval((p.getX()*w+xMove+w/4), (p.getY()*h+yMove+h/4), (w/2), (h/2));
    }

    public void paintEnemy(Graphics g, int index) {
    	
    	g.setColor(Color.red);
    	
    	
    	g.fillOval((enemyArr.get(index).getX()*w+xMove+w/4), (enemyArr.get(index).getY()*h+yMove+h/4), (w/2), (h/2));
    }

    public void paintPoint(Graphics g, int x, int y) {
    	g.setColor(Color.orange);
    	g.fillOval(x+w/4+w/8+xMove,y+h/4+h/8+yMove,w/4,h/4);
    }
    public void paintPointL(Graphics g, int x, int y) {
    	g.setColor(Color.cyan);
    	g.fillOval(x+w/4+xMove,y+h/4+yMove,w/2,h/2);
    }
}
