package Game;

import Exceptions.InvalidIndex;
import Exceptions.InvalidPlace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class SudokoBoard {


    public static final int ROWSIZE = 9;
    public static final int COLUMNSIZE = 9;
    private final int numOfSquaresPerRow = ROWSIZE / 3;
    private final int numOfSquaresPerCol = COLUMNSIZE / 3;
    private final int[][] board = new int[ROWSIZE][COLUMNSIZE];
    public int[][] copyBoard = board;
    private HashMap<Integer, Integer> minRowMaxRow = new HashMap<>();
    private HashMap<Integer, Integer> minColMaxCol = new HashMap<>();
    private final List<Integer> allIndexes = new ArrayList<>();
    private List<Integer> defaultPlaces;

    public SudokoBoard()
    {
        this.defaultPlaces = new ArrayList<>();
    }
    public void setField() {
        // We need 17 random positions to pick
        // we need to do that 17 times
        int minimumCounter = 17;
        for (int counter = 0; counter < minimumCounter; counter++) {
            int col = (int) Math.floor(Math.random() * 9); //chooses random column
            int row = (int) Math.floor(Math.random() * 9); // chooses random row
            int place = row * 9 + col+1;

            if (board[row][col] == 0) {
                defaultPlaces.add(place);
                int randomNumber = (int) Math.floor(Math.random() * 9) + 1;
                board[row][col] = randomNumber;
            } else {
                minimumCounter++;
            }
        }

    }

    //gets all indexes from 0 to 8 comfortably
    public void setAllIndexes() {
        for (int i = 0; i < ROWSIZE; i++) {
            allIndexes.add(i);
        }
    }
    public void addEmptyNumber(int place)
    {
        int row = (place - 1) / ROWSIZE;
        int col = (place - 1) % COLUMNSIZE;
        board[row][col] = 0;
    }

    public void placeNumber(int number, int place) {
        if (number > 9 || number < 1) {
            throw new InvalidIndex();
        } else if (place > 81 || place < 1) {
            throw new InvalidPlace();
        } else {
            int row = (place - 1) / ROWSIZE;
            int col = (place - 1) % COLUMNSIZE;

            if (board[row][col] == 0) {
                board[row][col] = number;
            } else {
                throw new InvalidPlace();
            }


        }

    }

    public boolean hasRow(int row) {
        // We need to check if each element in the row are unique - 1 -9

        HashSet<Integer> rowNumbers = new HashSet<>(); //Creating a HashSet will guarantee uniqueness
        for (int col = 0; col < COLUMNSIZE; col++) {
            rowNumbers.add(board[row][col]);

        }
        return rowNumbers.size() == 9;


    }

    public boolean hasColumn(int col) {
        HashSet<Integer> columnNumbers = new HashSet<>();
        for (int row = 0; row < ROWSIZE; row++) {
            columnNumbers.add(board[row][col]);
        }
        return columnNumbers.size() == 9;

    }

    public boolean hasAllRows() {
        for (int row = 0; row < ROWSIZE; row++) {
            if (!hasRow(row)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasAllCols() {
        for (int col = 0; col < COLUMNSIZE; col++) {
            if (!hasColumn(col)) {
                return false;
            }
        }
        return true;
    }

    //Get field to check for each row/ col
    public int getField(int row, int col) {
        return board[row][col];
    }


    //Sudoko is a square - that means col = row; or number of squares to check is ROWSIZE/3 * COLSIZE/3
    public boolean hasSquare(int beginRow, int beginCol, int rowMax, int colMax) {

        HashSet<Integer> setOfAllNumbersInASquare = new HashSet<>();
        //Add each square to a HashSet/ God if

        for (int row = beginRow; row <= rowMax; row++) {
            for (int col = beginCol; col <= colMax; col++) {

                setOfAllNumbersInASquare.add(getField(row, col));
            }
        }
        return setOfAllNumbersInASquare.size() == 9;

    }

    //We need a function to determine the indexes that we need to check

    public int getNumOfSquaresPerRow() {
        return numOfSquaresPerRow;
    }

    public int getNumOfSquaresPerCol() {
        return numOfSquaresPerCol;
    }

    //Get the Min Max values to check a square
    public void rowMinRowMax() {
        minRowMaxRow = new HashMap<>();
        int beginRow = 0;
        int initialMaxRow = 2;
        int minCount = 1;
        int maxCount = getNumOfSquaresPerRow();
        minMaxDetermineIndex(beginRow, initialMaxRow, minCount, maxCount, minRowMaxRow);

    }

    public void colMinRowMax() {
        minColMaxCol = new HashMap<>();
        int beginCol = 0;
        int initialMaxCol = 2;
        int minCount = 1;
        int maxCount = getNumOfSquaresPerCol();
        minMaxDetermineIndex(beginCol, initialMaxCol, minCount, maxCount, minColMaxCol);

    }

    private void minMaxDetermineIndex(int beginCol, int initialMaxCol, int minCount, int maxCount, HashMap<Integer, Integer> minColMaxCol) {
        minColMaxCol.put(beginCol, initialMaxCol);
        while (minCount != maxCount) {
            for (int i = 0; i < 3; i++) {
                beginCol++;
                initialMaxCol++;

            }
            minColMaxCol.put(beginCol, initialMaxCol);
            minCount++;
        }
    }


    //Now for the two HashMaps
    //I need a way to go through all indexes from the HashMap
    public boolean hasAllSquares() {
        rowMinRowMax();
        colMinRowMax();
        setAllIndexes();//reference we can use to check indexes from the map
        for (Integer min : minRowMaxRow.keySet()) {
            for (Integer minCol : minColMaxCol.keySet()) {
                if (!hasSquare(min, minCol, minRowMaxRow.get(min), minColMaxCol.get(minCol))) {
                    return false;
                }
            }
        }
        return true;
        //imagine it like this:
        // 0 1 2
        // 3 6 5
        // 6 7 8
        //keys are the first number

    }

    public boolean isWinner() {
        return hasAllSquares() && hasAllRows() && hasAllCols();
    }

    public List<Integer> getRow(int row)
    {
        List<Integer> list =  new ArrayList<>();
        for(int col = 0; col < COLUMNSIZE; col++)
        {
            list.add(board[row][col]);
        }
        return list;
    }

    public List<Integer> getPrimitiveIndexes(int place)
    {
        List<Integer> fields = new ArrayList<>();
        int row = (place - 1) / ROWSIZE;
        int col = (place - 1) % COLUMNSIZE;
        fields.add(row);
        fields.add(col);
        return fields;


    }

    public List<Integer> getDefaultValues() {
        return defaultPlaces;
    }

    public void setBoard() {
        for(int row = 0; row < ROWSIZE; row++)
        {
            System.out.println(getRow(row));
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String horizontalLine = "+-------+-------+-------+\n";

        for (int row = 0; row < ROWSIZE; row++) {
            if (row % 3 == 0) sb.append(horizontalLine);
            for (int col = 0; col < COLUMNSIZE; col++) {
                if (col % 3 == 0) sb.append("| ");
                int value = board[row][col];
                sb.append(value == 0 ? ". " : value + " ");
            }
            sb.append("|\n");
        }
        sb.append(horizontalLine);
        return sb.toString();
    }


}
