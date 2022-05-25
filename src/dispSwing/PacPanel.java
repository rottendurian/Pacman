package dispSwing;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import boardPac.Board;
import game.Getter;
public class PacPanel extends JPanel {
	private int h;
    private int w;
    private int xMove;
    private int yMove;
    private Getter gBo;
    public PacPanel(Getter gB) {
        setPreferredSize(new Dimension (800,800));
        h = 800/gB.getHeight();
        w = 800/gB.getWidth();
        gBo = gB;
        xMove = 50;
        yMove = 70;
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
    	if (gBo.gameOver() == true) {
    		gameOver(g);
    	} else {
    	//paintPoint(g,6*w,5*h);
        int i = 0;
        while (i < gBo.getWidth()) {
            int k = 0;
            while (k < gBo.getHeight()) {
                if (gBo.isWall(i,k) == false) {
                	//System.out.println(gBo.isWall(i,k));
                	//System.out.println(gBo.isPoint(i,k));
                    //paintSquare(g, i*w, k*h);
                    if (gBo.isPoint(i,k) == true) {
                    	//System.out.println("painted point at" + i + ", " + k);
                        paintPoint(g,i*w,k*h);
                    } else if (gBo.isPointL(i,k)==true) {
                    	paintPointL(g,i*w,k*h);
                    }
                } else {
                    paintFilledSquare(g, i*w, k*h);
                }

                k++;
            }
            i++;
        }
        paintPac(g);
        for (int icounter = 0; icounter<gBo.egetSize(); icounter++) {
			paintEnemy(g,icounter);
		}
        //paintEnemy(g);
        paintScore(g);
    	}
    }
    public void gameOver(Graphics g) {
    	int x = 150;
    	int y = 100;
    	g.setColor(Color.white);
        float f=80.0f; // font size.
        g.setFont(g.getFont().deriveFont(f));
        g.drawString("Game Over", x+xMove+20, y+yMove+20);
        paintScore(g);
        if(gBo.getMaxScore() == gBo.getScore()) {
        	g.setColor(Color.white);
            f=80.0f; // font size.
            g.setFont(g.getFont().deriveFont(f));
            g.drawString("You Win", x+50+xMove+20, y+300+yMove+20);
        }
        
    }
    /**
     * paints an empty square
     */
    public void paintSquare(Graphics g, int x, int y) {
        g.setColor(Color.black);
        g.drawRect(x+xMove,y+yMove,w,h);
        paintPacWeight(g,x,y);
    }
    public void paintPacWeight(Graphics g, int x, int y) {
    	g.setColor(Color.white);
        float f=20.0f; // font size.
        g.setFont(g.getFont().deriveFont(f));
        g.drawString(("" + gBo.getWeight(x/w,y/h)), x+xMove+20, y+yMove+20);
    }
    public void paintFilledSquare(Graphics g, int x, int y) {
        g.setColor(Color.BLUE);
        g.fillRect(x+xMove,y+yMove,w,h);
        //paintPacWeight(g,x,y);
    }

    public void paintPac(Graphics g) {
    	g.setColor(Color.yellow);
    	g.fillOval((gBo.pgetX()*w+xMove+w/4), (gBo.pgetY()*h+yMove+h/4), (w/2), (h/2));
    }

    public void paintEnemy(Graphics g, int index) {
    	
    	if (gBo.getEdible(index) == true) {
    		g.setColor(Color.green);
    	} else {
    		g.setColor(Color.red);
    	}
    	
    	g.fillOval((gBo.egetX(index)*w+xMove+w/4), (gBo.egetY(index)*h+yMove+h/4), (w/2), (h/2));
    }

    public void paintPoint(Graphics g, int x, int y) {
    	g.setColor(Color.orange);
    	g.fillOval(x+w/4+w/8+xMove,y+h/4+h/8+yMove,w/4,h/4);
    }
    public void paintPointL(Graphics g, int x, int y) {
    	g.setColor(Color.cyan);
    	g.fillOval(x+w/4+xMove,y+h/4+yMove,w/2,h/2);
    }
    public void paintScore(Graphics g) {
    	g.setColor(Color.white);
        float f=20.0f; // font size.
        g.setFont(g.getFont().deriveFont(f));
        g.drawString(("Score: " + gBo.getScore()), 415, 50);
    }
}
