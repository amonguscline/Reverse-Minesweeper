import javax.swing.*;
public class Board{
    public Cell[][] cells;
    public JFrame display = new JFrame("board");
    public Board() {
        display.setLayout(null);
        display.setVisible(true);
        display.setSize(1800,950);
        this.cells = initializeCells();;
    }
    //it should initialize cells from the first cell that is clicked.
    public Cell[][] initializeCells(){
        Cell[][] cells = new Cell[36][18];
        for(int row=0;row<cells.length;row++){
            for(int col=0;col<cells[0].length;col++){
                cells[row][col]=new Cell(new JButton(),(Math.random()*5>4),this,row,col);
            }
        }
        return cells;
    }
    public void explode(){
        Cell cell = null;
        int i=2;
        while(i>0&&!isComplete(cells)){
            
            cell=cells[(int)(Math.random()*cells.length)][(int)(Math.random()*cells[0].length)];
            if(!cell.shown()){
                System.out.println(cell.hasBomb());
                cell.reveal();
                i--;
            }
        }
    }
    public JFrame display(){
        return display;
    }
    public int getSurroundingNonBombs(int x, int y){
        int surrounding=0;
        for(int i=x-1;i<=x+1;i++){
            for(int j=y-1;j<=y+1;j++){
                if(!(i>cells.length-1||j>cells[0].length-1)&&!(i<0||j<0)){if (cells[i][j].hasBomb()){surrounding++;}}
            }
        }
        return surrounding;
    }

    public Cell[][] getSurroundingCells(int x, int y){
        Cell[][] c = new Cell[3][3];
        for(int i=-1;i<=1;i++){
            for(int j=-1;j<=1;j++){
                if(!(x+i>cells.length-1||y+j>cells[0].length-1)&&!(x+i<0||y+j<0)){
                    c[i+1][j+1]=cells[x+i][y+j];
                }
            }
        }
        return c;
    }

    private boolean isComplete(Cell[][] cells){
        for(int i=0;i<cells.length;i++){
            for(int j=0;j<cells[0].length;j++){
                if(!cells[i][j].shown()) return false;
            }
        }
        return true;
    }
}
