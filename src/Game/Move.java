package Game;

import Exceptions.InvalidIndex;
import Exceptions.InvalidPlace;

public class Move {
    private int row;
    private int col;
    private int number;

    public Move(int row, int col, int number)
    {
        if(row >= 0 && row <= 8 && col >=0 && col <= 8 )
        {
            this.row = row;
            this.col = col;

        }
        else{
            throw new InvalidPlace();
        }

        if(number >= 1 && number <= 9)
        {
            this.number = number;
        }
        else
        {
            throw new InvalidIndex();
        }

    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }
}





