package server;

import Game.Game;
import networking.SocketServer;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

import Game.Move;
import protocol.Protocol;

public class Server extends SocketServer {
    /**
     * Creates a new Server that listens for connections on the given port.
     * Use port 0 to let the system pick a free port.
     *
     * @param port the port on which this server listens for connections
     * @throws IOException if an I/O error occurs when opening the socket
     */
    ServerConnection connection;
    HashMap<ClientHandler, Game> gameWithClient = new HashMap<>();
    protected Server(int port) throws IOException {
        super(port);
    }

    @Override
    protected void handleConnection(Socket socket) {
        try {
            connection = new ServerConnection(socket, this);
            gameWithClient.put(connection.getClientHandler(), null);
            connection.start();
        }catch (IOException e)
        {
           e.printStackTrace();
        }

    }

    public void startGame(ClientHandler client)
    {
        Game game = new Game();
        game.validMovePlace();
        gameWithClient.put(client, game);
        for(String line: game.getBoard().toString().split("\n"))
        {
            connection.sendMessageToClient(Protocol.BOARD+" "+line);
        }

    }

    public void updateGame(ClientHandler client, Move move)
    {
        Game game = getGameForClient(client);
        game.doMove(move);
        for(String line: game.getBoard().toString().split("\n"))
        {
            connection.sendMessageToClient(Protocol.BOARD+" "+line);
        }
        if(game.isGameOver())
        {
            gameWithClient.remove(client);
            connection.sendMessageToClient(Protocol.WIN+ " " + "Congrats on solving the puzzle");
        }
    }
    public void removeNumber(ClientHandler client, int place)
    {
        Game game = getGameForClient(client);
        game.removeMove(place);
        for(String line: game.getBoard().toString().split("\n"))
        {
            connection.sendMessageToClient(Protocol.BOARD+" "+line);
        }
    }


    public Game getGameForClient(ClientHandler client)
    {
        return gameWithClient.get(client);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter port:");
            int port = scanner.nextInt();
            Server server = new Server(port);
            server.acceptConnections();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
