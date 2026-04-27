package Exceptions;

public class InvalidIndex extends RuntimeException {
    public InvalidIndex() {
        super("The number you chose is out of range. Pick another!");
    }

}
