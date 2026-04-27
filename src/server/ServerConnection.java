package server;

import networking.SocketConnection;
import protocol.Protocol;

import java.io.IOException;
import java.net.Socket;

public class ServerConnection extends SocketConnection {
    ClientHandler clientHandler;
    public ServerConnection(Socket socket, Server server) throws IOException {
       super(socket);
       this.clientHandler = new ClientHandler(this, server);
    }

    @Override
    protected void handleMessage(String message) {
        String[] listOfWords = message.split(" ");
        switch (listOfWords[0])
        {
            case Protocol.MOVE :
                clientHandler.handleMove(listOfWords);
                break;
            case Protocol.REMOVE:
                clientHandler.handleRemove(listOfWords);
                break;
            case Protocol.NEWGAME:
                clientHandler.handleNewGame(listOfWords);
                break;
            default:
                sendMessageToClient(Protocol.ERROR + "Just error bro");
                break;
        }

    }

    public ClientHandler getClientHandler() {
        return clientHandler;
    }

    public void sendMessageToClient(String message) {
        sendMessage(message);
    }

    @Override
    protected void handleDisconnect() {
       close();
    }
}
