package Game;

import java.util.Arrays;
import java.util.Scanner;

public class TUI {
    public static void main(String[] args) {
        TUI tui = new TUI();
        tui.run();
    }
    private void run()
    {

        Game game = new Game();
        game.validMovePlace();
        while (!game.isGameOver())
        {
            game.getBoard().setBoard();
            Scanner input = new Scanner(System.in);
            System.out.println("Enter a position");
            int position = input.nextInt();
            int row = (position - 1) / SudokoBoard.ROWSIZE;
            int col = (position - 1) % SudokoBoard.COLUMNSIZE;
            System.out.println("Enter a number");
            Scanner newInput = new Scanner(System.in);
            int number = newInput.nextInt();
            Move move = new Move(row, col,number);
            game.doMove(move);
            System.out.println("Enter a place to remove");
            Scanner placeInput = new Scanner(System.in);
            int place  = placeInput.nextInt();
            game.removeMove(place);


        }



    }



}
