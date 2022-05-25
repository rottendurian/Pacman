package boardMaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import blockPac.Enemy;
import blockPac.Pacman;
import boardPac.Board;
import dispSwing.DisplaySwing;
import game.GameController;
import game.Getter;

public class SwingDisplay extends JFrame {
	public int Width = 1000;
    public int Height = 1000;
    public JFrame f;
    public bPanel panel;
    public Board b;
    public pacObject p;
    public ArrayList<pacObject> enemyArr;
    private int type;
    public Getter g;
    //1 = wall, 2 = point, 3 lpoint, 4 = pacman, 5 = enemy1, 6 = enemy2, 7 = enemy 3, 8 = enemy 4
    
    public SwingDisplay(Board b, Getter g)
    {
        this.b = b;
        this.g = g;
        p = new pacObject(0,0);
        enemyArr = new ArrayList<pacObject>();
        for (int i = 0; i < 4; i++) {
        	enemyArr.add(new pacObject(0,0));
        }
        initComponents(b,p,enemyArr);
    }
    
    
    /**
     * This class gives GameController access to repaint the panel
     */
    public void repaint() {
        super.repaint();
        panel.revalidate();
        panel.repaint();
    }
    
    /**
     * This method sets all of the conditions for the JFrame, JPanel, JButtons, and JLabels as well as attaching listeners to the buttons
     */
    private void initComponents(Board gB, pacObject p, ArrayList<pacObject> enemyArr) {
        f = new JFrame("Pacman");
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setPreferredSize(new Dimension (900,950));
        panel = new bPanel(gB, p, enemyArr);
        
        panel.setBackground(Color.black/*new java.awt.Color(255,255,255)*/);
        //panel.addKeyListener(new KeyListenClass(gC));
        //f.addKeyListener(new KeyListenClass(gC));
        JButton Remove = new JButton("Remove");
        JButton Wall = new JButton("Wall");
        JButton Point = new JButton("Point");
        JButton LPoint = new JButton("LPoint");
        JButton Pacman = new JButton("Pacman");
        JButton Enemy = new JButton("Enemy 1");
        JButton Enemy2 = new JButton("Enemy 2");
        JButton Enemy3 = new JButton("Enemy 3");
        JButton Enemy4 = new JButton("Enemy 4");
        JButton Export = new JButton("Export");
        panel.addMouseListener(new MouseEventListener(null,this));
        panel.add(Remove);
        panel.add(Wall);
        panel.add(Point);
        panel.add(LPoint);
        panel.add(Pacman);
        panel.add(Enemy);
        panel.add(Enemy2);
        panel.add(Enemy3);
        panel.add(Enemy4);
        panel.add(Export);
        Remove.addActionListener(new removeListener(this));
        Wall.addActionListener(new wallListener(this));
        Point.addActionListener(new pointListener(this));
        LPoint.addActionListener(new lpointListener(this));
        Pacman.addActionListener(new pacListener(this));
        Enemy.addActionListener(new enemyListener(this,1));
        Enemy2.addActionListener(new enemyListener(this,2));
        Enemy3.addActionListener(new enemyListener(this,3));
        Enemy4.addActionListener(new enemyListener(this,4));
        Export.addActionListener(new exportListener(this,true));
        ///panel.add(new JLabel("Controls: wasd, Clear: c, Undo: z"));
        //panel.add(score);
        JMenuBar dropmenu = new JMenuBar();
        JMenu aMenu = new JMenu("Edit");
        JMenuItem importMap = new JMenuItem("Import Map");
        JMenuItem exportMapString = new JMenuItem("Export Map String");
        JMenuItem clearBoard = new JMenuItem("Clear Board");
        JMenuItem addPoints = new JMenuItem("Add points");
        aMenu.add(importMap);
        aMenu.add(exportMapString);
        aMenu.add(clearBoard);
        aMenu.add(addPoints);
        dropmenu.add(aMenu);
        importMap.addActionListener(new importListener(this));
        exportMapString.addActionListener(new exportListener(this,false));
        clearBoard.addActionListener(new clearListener(this));
        addPoints.addActionListener(new addPointsListener(this));
        f.setJMenuBar(dropmenu);
        f.add(panel);
        f.setContentPane(panel);
        f.pack();
        f.setVisible(true);
        
        
    }
    public void type(int type) {
    	this.type = type;
    	System.out.println(type);
    }
    public void mouseClicked(int x, int y) {
    	int xVal = (int) (x-panel.xMove())/panel.getW();
    	int yVal = (int) (y-panel.yMove())/panel.getH();
    	//System.out.println(xVal);
    	//System.out.println(yVal);
    	if (type == 0) {
    		b.removeWall(xVal,yVal);
    		b.removePoint(xVal,yVal);
    		b.removePointL(xVal,yVal);
    	} else if (type == 1) {
    		b.removePoint(xVal,yVal);
    		b.removePointL(xVal,yVal);
    		b.createWall(xVal,yVal);
    	} else if (type == 2) {
    		b.removeWall(xVal,yVal);
    		b.removePointL(xVal,yVal);
    		b.addPoint(xVal,yVal);
    	} else if (type == 3) {
    		b.removePoint(xVal,yVal);
    		b.removeWall(xVal,yVal);
    		b.addPointL(xVal,yVal);
    	} else if (type == 4) {
    		p.setInit(xVal,yVal);
    	} else if (type == 5) {
    		enemyArr.get(0).setInit(xVal,yVal);
    	} else if (type == 6) {
    		enemyArr.get(1).setInit(xVal,yVal);
    	} else if (type == 7) {
    		enemyArr.get(2).setInit(xVal,yVal);
    	} else if (type == 8) {
    		enemyArr.get(3).setInit(xVal,yVal);
    	}
    	repaint();
    }
    public void export(boolean auto) {
    	String export = new String();
    	int i = 0;
    	while (i < b.getWidth()) {
    		int k = 0;
    		while (k < b.getHeight()) {
    			if (b.isWall(i,k) == true) {
    				export = export + "1";
    			} else if (b.isPoint(i,k) == true) {
    				export = export + "2";
    			} else if (b.isPointL(i,k)== true) {
    				export = export+"3";
    			} else {
    				export = export + "0";
    			}
    			k++;
    		}
    		i++;
    	}
    	if (p.getX() < 10) {
    		//System.out.println("0" + Integer.toString(p.getX()));
    		export = export + "0" + Integer.toString(p.getX());
    	} else {
    		//System.out.println(Integer.toString(p.getX()));
    		export = export + Integer.toString(p.getX());
    	}
    	if (p.getY() < 10) {
    		export = export + "0" + Integer.toString(p.getY());
    	} else {
    		export = export + Integer.toString(p.getY());
    	}
    	
    	for (int icounter = 0; icounter<enemyArr.size(); icounter++) {
    		if (enemyArr.get(icounter).getX() < 10) {
    			export = export + "0" + Integer.toString(enemyArr.get(icounter).getX());
        	} else {
        		export = export + Integer.toString(enemyArr.get(icounter).getX());
        	}
    		if (enemyArr.get(icounter).getY() < 10) {
    			export = export + "0" + Integer.toString(enemyArr.get(icounter).getY());
        	} else {
        		export = export + Integer.toString(enemyArr.get(icounter).getY());
        	}
		}
    	if (auto == false) {
    	StringSelection selection = new StringSelection(export);
    	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    	clipboard.setContents(selection, selection);
    	//JOptionPane.showMessageDialog(this, export);
    	System.out.println(export);
    	} else {
    		g.importBoard(export);
    	}
    }
    public void addPoints() {
    	int i = 0;
        while (i < b.getHeight()) {
            int k = 0;
            while (k < b.getWidth()) {
                if (b.isWall(i,k) == false && b.isPoint(i,k) == false && b.isPointL(i,k) == false) {
                	//System.out.println("added point to " + i + ", " + k);
                	b.addPoint(i,k);
                	//System.out.println(b.isPoint(i,k));
                }
                k+=1;
            }
            i+=1;
        }
        repaint();
    }
    public void clearBoard() {
    	int i = 0;
    	while (i < b.getWidth()) {
    		int k = 0;
    		while (k < b.getHeight()) {
    			
    			b.removeWall(i,k);
    			b.removePoint(i,k);
    			b.removePointL(i,k);
    			
    			k++;
    		}
    		
    		i++;
    	}
    	p.setInitX(0);
    	p.setInitY(0);
    	for (int counter = 0; counter<enemyArr.size(); counter++) {
			enemyArr.get(counter).setInitX(0);
			enemyArr.get(counter).setInitY(0);
		}
    	repaint();
    	
    }
    public void importBoard(String board) {
		//currentBoard = board;
		if (board.length() < 21*21) {
			//board = defaultBoard;
		}
		int boardCount = 0;
		int i = 0;
    	while (i < b.getWidth()) {
    		int k = 0;
    		while (k < b.getHeight()) {
    			if (board.charAt(boardCount) == '1') {
    				b.createWall(i,k);
    				b.removePoint(i,k);
    				b.removePointL(i,k);
    			} else if (board.charAt(boardCount) == '2') {
    				b.addPoint(i,k);
    				b.removePointL(i,k);
    				b.removeWall(i,k);
    			} else if (board.charAt(boardCount) == '3') {
    				b.addPointL(i,k);
    				b.removeWall(i,k);
    				b.removePoint(i,k);
    			} else {
    				b.removeWall(i,k);
    				b.removePoint(i,k);
    				b.removePointL(i,k);
    			}
    			k++;
    			boardCount++;
    		}
    		i++;
    	}
    	/*System.out.println("X");
    	System.out.println(boardCount + " charAt " + board.charAt(boardCount) + " " + board.charAt(boardCount+1));
    	System.out.println((board.charAt(boardCount+2) + "" + board.charAt(boardCount+3)));
    	System.out.println("Y");
    	System.out.println(board.charAt(boardCount+2) + "" + board.charAt(boardCount+3));*/
    	p.setInitX(Integer.valueOf(board.charAt(boardCount) + "" + board.charAt(boardCount+1)) );
    	p.setInitY(Integer.valueOf(board.charAt(boardCount+2) + "" + board.charAt(boardCount+3)) );
    	boardCount += 4;
    	//p.moveX(p.getInitX());
    	//p.moveY(p.getInitY());
    	
    	
    	
    	for (int counter = 0; counter<enemyArr.size(); counter++) {
    		
    		
			enemyArr.get(counter).setInitX(Integer.valueOf(board.charAt(boardCount)+ "" +board.charAt(boardCount+1)));
			//System.out.println("e x" + board.charAt(boardCount)+ "" +board.charAt(boardCount+1));
			enemyArr.get(counter).setInitY(Integer.valueOf(board.charAt(boardCount+2)+ "" +board.charAt(boardCount+3)));
			//System.out.println("e y" + board.charAt(boardCount+2)+ "" +board.charAt(boardCount+3));
			//enemyArr.get(counter).moveX(enemyArr.get(counter).getX());
			//enemyArr.get(counter).moveY(enemyArr.get(counter).getY());
			boardCount+=4;
		}
    	
    	repaint();
	}
    
    
    
}
class importListener implements ActionListener {
    private SwingDisplay s;
    //private String Field;
    public importListener(SwingDisplay s) {
        
        this.s = s;
        //this.Field = Field;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	String field = JOptionPane.showInputDialog("Import Map String Here");
        s.importBoard(field);
    }
    
}
class exportListener implements ActionListener {
    private SwingDisplay s;
    private boolean auto;
    public exportListener(SwingDisplay s, boolean auto) {
        this.s = s;
        this.auto = auto;
        
    }
    @Override
    public void actionPerformed(ActionEvent evt) {
    	s.export(auto);
    }
    
}
class addPointsListener implements ActionListener {
    private SwingDisplay s;
    public addPointsListener(SwingDisplay s) {
        this.s = s;
    }
    @Override
    public void actionPerformed(ActionEvent evt) {
        s.addPoints();
    }
    
}
class clearListener implements ActionListener {
    private SwingDisplay s;
    public clearListener(SwingDisplay s) {
        this.s = s;
    }
    @Override
    public void actionPerformed(ActionEvent evt) {
        s.clearBoard();
    }
    
}
class removeListener implements ActionListener {
    private SwingDisplay s;
    public removeListener(SwingDisplay s) {
        this.s = s;
    }
    @Override
    public void actionPerformed(ActionEvent evt) {
        s.type(0);
    }
    
}
class wallListener implements ActionListener {
    private SwingDisplay s;
    public wallListener(SwingDisplay s) {
        this.s = s;
    }
    @Override
    public void actionPerformed(ActionEvent evt) {
        s.type(1);
    }
    
}
class pointListener implements ActionListener {
    private SwingDisplay s;
    public pointListener(SwingDisplay s) {
        this.s = s;
    }
    @Override
    public void actionPerformed(ActionEvent evt) {
        s.type(2);
    }
    
}
class lpointListener implements ActionListener {
    private SwingDisplay s;
    public lpointListener(SwingDisplay s) {
        this.s = s;
    }
    @Override
    public void actionPerformed(ActionEvent evt) {
        s.type(3);
    }
    
}
class pacListener implements ActionListener {
    private SwingDisplay s;
    public pacListener(SwingDisplay s) {
        this.s = s;
    }
    @Override
    public void actionPerformed(ActionEvent evt) {
        s.type(4);
    }
    
}
class enemyListener implements ActionListener {
    private SwingDisplay s;
    private int type;
    public enemyListener(SwingDisplay s, int type) {
        this.s = s;
        this.type = type;
    }
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (type == 1) {
        	s.type(5);
        } else if (type == 2) {
        	s.type(6);
        } else if (type == 3) {
        	s.type(7);
        } else if (type == 4) {
        	s.type(8);
        }
    	
    }
    
}
class MouseEventListener implements MouseListener {
	private SwingDisplay s;
	public MouseEventListener(MouseEvent e, SwingDisplay s) {
		this.s = s;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("mouse pressed");
		//System.out.println("x:" + e.getX() + "y:" + e.getY() );
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		s.mouseClicked(e.getX(),e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
