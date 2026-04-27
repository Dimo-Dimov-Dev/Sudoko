package Game;

import java.util.Arrays;

public class TestMainClass {

    public static void main(String[] args) {
        SudokoBoard board = new SudokoBoard();
        board.setField();
        for(int row = 0; row < SudokoBoard.ROWSIZE; row++)
        {
            for(int col = 0; col < SudokoBoard.COLUMNSIZE; col++)
            {
                if(board.copyBoard[row][col] == 0)
                {
                    int placement = (row * 9 + col)+1;
                    board.placeNumber(9,placement);
                    break;
                }
            }
        }
        System.out.println(Arrays.deepToString(board.copyBoard));


    }
}
