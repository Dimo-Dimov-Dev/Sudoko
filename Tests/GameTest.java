import Game.Game;
import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import Game.SudokoBoard;
import Game.Move;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {


private Game game;
@BeforeEach
public void setUp() {
    game = new Game();
}
@Test
public void testValidMoves()
{

    assertEquals( 64,   game.validMovePlace().size());
}
@Test
public void testDoMove()
{
    Move move = new Move(3,4,5);
    game.validMovePlace();
    game.doMove(move);
}
//@Test
//public void testRemoveMove()
//{
//    Move move = new Move(3, 3, 4);
//    game.removeMove(31);
//    game.removeMove(81);
//}




}
