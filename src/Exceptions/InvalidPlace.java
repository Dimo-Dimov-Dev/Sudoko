package Exceptions;

public class InvalidPlace extends RuntimeException {
    public InvalidPlace() {
        super("You chose invalid place. Pick another one!");
    }
}
