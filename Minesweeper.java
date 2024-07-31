import javax.swing.SwingUtilities;

public class Minesweeper{
    //i don't feel like using the class because theres too many comments and i dont want to read it really thats a lot of effort
    public static int attempts=-1;
    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{
            Board b = new Board();
        });
    }
}
