package Game;
import Game.SudokoBoard;
import Exceptions.InvalidMoveException;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private SudokoBoard board;
    private List<Integer> validMoves = new ArrayList<>();
    //initialized board on start of a game
    public Game(){
        board = new SudokoBoard();
        board.setField();
        board.setAllIndexes();

    }
    //just so i get the commit streak

    //TODO doMove method
    public void doMove(Move move)
    {
        int row = move.getRow();
        int col = move.getCol();
        int number = move.getNumber();

        if (isValidMove(move)){
            int place = row * SudokoBoard.ROWSIZE + col +1;
            this.board.placeNumber(number, place);
            this.validMoves.remove(place);
        }
        else{
            throw new InvalidMoveException();
        }


    }

    //TODO list with all valid move Place
    public List<Integer> validMovePlace()
    {
        for(int row = 0; row < SudokoBoard.ROWSIZE; row++)
        {
            for(int col = 0; col < SudokoBoard.COLUMNSIZE; col++)
            {
                if(this.board.getField(row, col) == 0){
                    this.validMoves.add(row * SudokoBoard.ROWSIZE + col + 1);
                }
            }
        }
        return validMoves;
    }

    //TODO check if a move is valid

    public boolean isValidMove(Move move)
    {
        //gameOver check
        if(move == null){
            return false;
        }

        if(isGameOver())
        {
            return false;
        }

        else{
            int row = move.getRow();
            int col = move.getCol();
            int index = row * SudokoBoard.ROWSIZE + col + 1;
            int number = move.getNumber();
            return validMoves.contains(index) && number >= 1 && number <= 9;
        }

    }
    //TODO method to get a number from a place



    //TODO Remove number
    public void removeMove(int place) {
        List<Integer> places = getBoard().getPrimitiveIndexes(place);
        int row = places.get(0);
        int col = places.get(1);
        int number = getBoard().getField(row,col);
        List<Integer> startingPlaces= getBoard().getDefaultValues();

        if(number != 0 && !startingPlaces.contains(place))
        {
            getBoard().addEmptyNumber(place);
        }
        validMovePlace().add(place);

    }

    public SudokoBoard getBoard() {
        return board;
    }

    //TODO GameOver check
    public boolean isGameOver()
    {
        return board.isWinner();
    }
}
