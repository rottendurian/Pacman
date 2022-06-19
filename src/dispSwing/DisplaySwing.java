package dispSwing;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

import boardMaker.BoardMaker;
import boardMaker.SwingDisplay;
import boardPac.Board;
import game.GameController;
import game.Getter;

import java.awt.Graphics;
import javax.swing.Action;
import javax.swing.JScrollPane;
import java.awt.event.*;
public class DisplaySwing extends Panel {
	public int Width = 1000;
    public int Height = 1000;
    public JFrame f;
    public PacPanel panel;
    
    
    public DisplaySwing(Getter g, GameController gC)
    {
        
        initComponents(g, gC);
        
    }
    public DisplaySwing() {
    	
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
    private void initComponents(Getter g, GameController gC) {
        f = new JFrame("Pacman");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setPreferredSize(new Dimension (900,950));
        panel = new PacPanel(g);
        
        JMenuBar dropmenu = new JMenuBar();
        JMenu aMenu = new JMenu("Edit");
        JMenuItem restartGame = new JMenuItem("Restart Game");
        JMenuItem importMap = new JMenuItem("Import Map");
        JMenuItem createMap = new JMenuItem("New Map");
        JMenuItem exportMap = new JMenuItem("Export Current Map");
        JMenuItem defaultBoard = new JMenuItem("Reset to Default Board");
        aMenu.add(createMap);
        aMenu.add(importMap);
        aMenu.add(exportMap);
        aMenu.add(restartGame);
        aMenu.add(defaultBoard);
        dropmenu.add(aMenu);
        f.setJMenuBar(dropmenu);
        
        
        //JTextField field = new JTextField(20);
        JButton imp = new JButton("Import");
        panel.setBackground(Color.black/*new java.awt.Color(255,255,255)*/);
        //panel.add(field);
        //panel.add(imp);
        //imp.addKeyListener(new KeyListenClass(gC));
        importMap.addActionListener(new importListener(g,this));
        createMap.addActionListener(new newListener(g,this));
        restartGame.addActionListener(new restartListener(gC));
        exportMap.addActionListener(new exportListener(g,false));
        defaultBoard.addActionListener(new defaultListener(g));
        panel.addKeyListener(new KeyListenClass(gC));
        f.addKeyListener(new KeyListenClass(gC));
        /*JButton clear = new JButton("Clear");
        JButton undo = new JButton("Undo");
        
        panel.add(undo);
        panel.add(clear);
        panel.add(new JLabel("Controls: wasd, Clear: c, Undo: z"));*/
        //panel.add(score);
        
        f.add(panel);
        f.setContentPane(panel);
        f.pack();
        f.setVisible(true);
        
        
    }
    /**
     * returns the local variable Height
     */
    public int getHeight() {
        return Height;
    }
    /**
     * returns the local variable Width
     */
    public int getWidth() {
        return Width;
    }
    public static void main(String[] args) {
       
    }
}
class defaultListener implements ActionListener {
    private Getter g;
    
    public defaultListener(Getter g) {
        this.g = g;
       
        
    }
    @Override
    public void actionPerformed(ActionEvent evt) {
    	g.importBoard(g.getDefaultBoard());
    }
    
}
class exportListener implements ActionListener {
    private Getter g;
    private boolean auto;
    public exportListener(Getter g, boolean auto) {
        this.g = g;
        this.auto = auto;
        
    }
    @Override
    public void actionPerformed(ActionEvent evt) {
    	g.export(auto);
    }
    
}
class importListener implements ActionListener {
    private Getter g;
    private DisplaySwing s;
    //private String Field;
    public importListener(Getter g, DisplaySwing s) {
        this.g = g;
        this.s = s;
        //this.Field = Field;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    	//System.out.println();
    	String field = JOptionPane.showInputDialog("Import Map String Here");
        g.importBoard(field);
    }
    
}
class newListener implements ActionListener {
    private Getter g;
    private DisplaySwing s;
    //private String Field;
    public newListener(Getter g, DisplaySwing s) {
        this.g = g;
        this.s = s;
        //this.Field = Field;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    	BoardMaker createBoard = new BoardMaker(17,17,g);
    	
        
    }
    
}
class restartListener implements ActionListener {
    private GameController g;
    
    //private String Field;
    public restartListener(GameController g) {
        this.g = g;
        
        //this.Field = Field;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    	g.togglePause(true);
    	g.resetGame();
    	
        
    }
    
}
class KeyListenClass implements KeyListener {
    private GameController gCu;
    public KeyListenClass(GameController gC) {
        gCu = gC;
    }
    @Override
    public void keyPressed(KeyEvent w) { gCu.keyPress(w); }
    public void keyReleased(KeyEvent w) { gCu.keyReleased(w); }
    public void keyTyped(KeyEvent w) { /*keyPress(w);*/ }
    
}