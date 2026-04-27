package server;

import Game.Game;
import protocol.Protocol;
import Game.Move;
import Game.SudokoBoard;
public class ClientHandler {

    ServerConnection connection;
    Server server;
    public ClientHandler(ServerConnection connection, Server server) {
        this.connection = connection;
        this.server = server;
    }

    public void handleMove(String message[])
    {
        if (message.length != 3) {
            sendErrorToClient("MOVE message must include a valid move.");
            return;
        }
        try {
            int moveIndex = Integer.parseInt(message[1]);
            int number = Integer.parseInt(message[2]);
            Game game = server.getGameForClient(this);
            if (game == null) {
                sendErrorToClient("Start a game first");
            }
            else {
                int row = (moveIndex - 1) / SudokoBoard.ROWSIZE;
                int col = (moveIndex - 1) % SudokoBoard.COLUMNSIZE;
                Move move = new Move(row, col, number);
                if (game.isValidMove(move) && !game.isGameOver()) {
                    server.updateGame(this, move);
                }
                else {
                    System.out.println("Invalid move or game is over");
                }
            }
        }
        catch (NumberFormatException e) {
            sendErrorToClient("Invalid move format.");
        }
    }
    public void handleRemove(String message[])
    {
        if (message.length != 2) {
            sendErrorToClient("Remove message must include a valid move.");
            return;
        }
        try
        {
             int place = Integer.parseInt(message[1]);
             Game game = server.getGameForClient(this);
             if(game == null)
             {
                 sendErrorToClient("Game's gone");
             }
             else
             {
                 server.removeNumber(this, place);
             }
        }
        catch (NumberFormatException e) {
            sendErrorToClient("Invalid Remove place format.");
        }
    }
    public void handleNewGame(String message[])
    {
        if (message.length != 1) {
            sendErrorToClient("Error broski");
            return;
        }
        try
        {
            Game game = server.getGameForClient(this);
            if(game != null)
            {
                sendErrorToClient("Game is going");
            }
            else{
            server.startGame(this);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }


    }


    private void sendErrorToClient(String error) {
        connection.sendMessageToClient(Protocol.ERROR+" " + error);

    }

}
