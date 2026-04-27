import Game.SudokoBoard;
import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoardTest {
    private SudokoBoard board;
    @BeforeEach
    public void sedTable()
    {
         board = new SudokoBoard();
    }

    @Test
    public void testSetField()
    {
        int count = 0;
        board.setField();
        for(int row = 0; row < SudokoBoard.ROWSIZE; row++)
        {
            for(int col = 0; col < SudokoBoard.COLUMNSIZE; col++)
            {
                if(board.getField(row, col) != 0)
                {
                    count++;
                }
            }
        }
       Assertions.assertEquals(17, count);
    }

    @Test
    public void testPlaceNumber()
    {
        board.placeNumber(2,10);
        Assertions.assertEquals(2, board.getField(1,0));
        Assertions.assertNotEquals(2, board.getField(2,8));
    }

    @Test
    public void testHasCol()
    {
        board.placeNumber(1, 1);
        board.placeNumber(2, 10);
        board.placeNumber(3, 19);
        board.placeNumber(4, 28);
        board.placeNumber(5, 37);
        board.placeNumber(6, 46);
        board.placeNumber(7, 55);
        board.placeNumber(8, 64);
        board.placeNumber(8, 73);
        Assertions.assertFalse(board.hasColumn(0));
    }
    @Test
    public void testHasCol2()
    {
        board.placeNumber(1, 1);
        board.placeNumber(2, 10);
        board.placeNumber(3, 19);
        board.placeNumber(4, 28);
        board.placeNumber(5, 37);
        board.placeNumber(6, 46);
        board.placeNumber(7, 55);
        board.placeNumber(8, 64);
        board.placeNumber(9, 73);
        Assertions.assertTrue(board.hasColumn(0));
    }
@Test
    public void testHasRow()
{
    board.placeNumber(1, 1);
    board.placeNumber(2, 2);
    board.placeNumber(3, 3);
    board.placeNumber(4, 4);
    board.placeNumber(5, 5);
    board.placeNumber(6, 6);
    board.placeNumber(7, 7);
    board.placeNumber(8, 8);
    board.placeNumber(9, 9);
    Assertions.assertTrue(board.hasRow(0));
}
    @Test
    public void testHasRow2()
    {
        board.placeNumber(1, 1);
        board.placeNumber(2, 2);
        board.placeNumber(3, 3);
        board.placeNumber(4, 4);
        board.placeNumber(5, 5);
        board.placeNumber(6, 6);
        board.placeNumber(7, 7);
        board.placeNumber(8, 8);
        board.placeNumber(8, 9);
        Assertions.assertFalse(board.hasRow(0));
    }
@Test
    public void hasSquareTest()
{
    board.placeNumber(1,1);
    board.placeNumber(2, 2);
    board.placeNumber(3, 3);
    board.placeNumber(4,10);
    board.placeNumber(5, 11);
    board.placeNumber(6,12);
    board.placeNumber(7, 19);
    board.placeNumber(8,20);
    board.placeNumber(8,21);
    Assertions.assertFalse(board.hasSquare(0,0,2,2));
}

    @Test
    public void hasSquareTest2()
    {
        board.placeNumber(5,1);
        board.placeNumber(3, 2);
        board.placeNumber(8, 3);
        board.placeNumber(9,10);
        board.placeNumber(2, 11);
        board.placeNumber(1,12);
        board.placeNumber(6, 19);
        board.placeNumber(7,20);
        board.placeNumber(4,21);
        Assertions.assertTrue(board.hasSquare(0,0,2,2));
    }

    @Test
    public void hasSquareTest3()
    {
        board.placeNumber(5,1);
        board.placeNumber(3, 2);
        board.placeNumber(8, 3);
        board.placeNumber(9,10);
        board.placeNumber(5, 11);
        board.placeNumber(1,12);
        board.placeNumber(6, 19);
        board.placeNumber(7,20);
        board.placeNumber(4,21);
        Assertions.assertFalse(board.hasSquare(0,0,2,2));
    }
    @Test
    public void hasWinnerTest(){
        board.placeNumber(4,1);
        board.placeNumber(3, 2);
        board.placeNumber(5, 3);
        board.placeNumber(2,4);
        board.placeNumber(6, 5);
        board.placeNumber(9,6);
        board.placeNumber(7, 7);
        board.placeNumber(8,8);
        board.placeNumber(1,9);

        board.placeNumber(6,10);
        board.placeNumber(8, 11);
        board.placeNumber(2, 12);
        board.placeNumber(5,13);
        board.placeNumber(7, 14);
        board.placeNumber(1,15);
        board.placeNumber(4, 16);
        board.placeNumber(9,17);
        board.placeNumber(3,18);

        board.placeNumber(1,19);
        board.placeNumber(9, 20);
        board.placeNumber(7, 21);
        board.placeNumber(8,22);
        board.placeNumber(3, 23);
        board.placeNumber(4,24);
        board.placeNumber(5, 25);
        board.placeNumber(6,26);
        board.placeNumber(2,27);

        board.placeNumber(8,28);
        board.placeNumber(2, 29);
        board.placeNumber(6, 30);
        board.placeNumber(1,31);
        board.placeNumber(9, 32);
        board.placeNumber(5,33);
        board.placeNumber(3, 34);
        board.placeNumber(4,35);
        board.placeNumber(7,36);

        board.placeNumber(3,37);
        board.placeNumber(7, 38);
        board.placeNumber(4, 39);
        board.placeNumber(6,40);
        board.placeNumber(8, 41);
        board.placeNumber(2,42);
        board.placeNumber(9, 43);
        board.placeNumber(1,44);
        board.placeNumber(5,45);

        board.placeNumber(9,46);
        board.placeNumber(5, 47);
        board.placeNumber(1, 48);
        board.placeNumber(7,49);
        board.placeNumber(4, 50);
        board.placeNumber(3,51);
        board.placeNumber(6, 52);
        board.placeNumber(2,53);
        board.placeNumber(8,54);

        board.placeNumber(5,55);
        board.placeNumber(1, 56);
        board.placeNumber(9, 57);
        board.placeNumber(3,58);
        board.placeNumber(2, 59);
        board.placeNumber(6,60);
        board.placeNumber(8, 61);
        board.placeNumber(7,62);
        board.placeNumber(4,63);

        board.placeNumber(2,64);
        board.placeNumber(4, 65);
        board.placeNumber(8, 66);
        board.placeNumber(9,67);
        board.placeNumber(5, 68);
        board.placeNumber(7,69);
        board.placeNumber(1, 70);
        board.placeNumber(3,71);
        board.placeNumber(6,72);

        board.placeNumber(7,73);
        board.placeNumber(6, 74);
        board.placeNumber(3, 75);
        board.placeNumber(4,76);
        board.placeNumber(1, 77);
        board.placeNumber(8,78);
        board.placeNumber(2, 79);
        board.placeNumber(5,80);
        board.placeNumber(9,81);
        Assertions.assertTrue(board.isWinner());
        Assertions.assertTrue(board.hasAllSquares());



    }

}
