package Exceptions;

import Game.Move;

public class InvalidMoveException extends RuntimeException {
    public InvalidMoveException() {
        super("The move you places is invalid");
    }
}
