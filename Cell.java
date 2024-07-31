import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.awt.Color;
public class Cell {
    private boolean shown=false;
    private boolean hasBomb;
    private JButton button;
    private int x;
    private int y;
    public Board board;
    public Cell cellf = this;
    public Color blue = new Color(0,0,100);
    public Color red = new Color(150,30,20);
    HashMap<Color, Color> accents = new HashMap<>();
    
    public Cell(JButton button, boolean hasBomb, Board board, int x, int y){
        this.x=x;
        this.y=y;
        this.board=board;
        this.button=button;
        accents.put(red,new Color(0,0,0));
        accents.put(blue,new Color(255,255,255));
        paint(button, red);
        this.hasBomb=hasBomb;
        button.setBounds(x*50,y*50,50,50);
        board.display().add(button);
        button.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                if(!shown()){
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        flag();
                    }
                    else if(e.getButton() == MouseEvent.BUTTON1){
                        
                        cellf.reveal();
                    }
                }
                
            }
        });
        
    }
    public void paint(JButton b, Color c){
        button.setBackground(c);
        button.setForeground(accents.get(c));
    }
    public JButton button(){
        return button;
    }
    public boolean shown(){
        return shown;
    }
    public boolean hasBomb(){
        return hasBomb;
    }
    public void hasBomb(boolean hasBomb){
        this.hasBomb=hasBomb;
    }
    public void flag(){
        paint(button, blue);
    }
    public void reveal(){
        shown=true;
        paint(button, red);
        if(hasBomb){
            button.setText("💣");
            board.explode();
        }
        else if(board.getSurroundingNonBombs(x, y)==0){
            button.setText("0");
            revealSurroundings();
        }
        else{
            button.setText(""+board.getSurroundingNonBombs(x,y));
            Minesweeper.attempts++;
        }
    }
    public void revealSurroundings(){
        Cell[][] surroundingCells = board.getSurroundingCells(x, y);
        for(int i=0;i<surroundingCells.length;i++){
            for(int j=0;j<surroundingCells[0].length;j++){
                if(surroundingCells[i][j]!=null&&surroundingCells[i][j].shown()==false){
                    surroundingCells[i][j].reveal();
                }
            }
        }
    }
}
