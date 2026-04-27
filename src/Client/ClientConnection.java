package Client;

import networking.SocketConnection;

import java.io.IOException;
import java.net.InetAddress;

public class ClientConnection extends SocketConnection {
    private final ClientTUI client;
    protected ClientConnection(InetAddress host, int port, ClientTUI client) throws IOException {
        super(host, port);
        this.client = client;
    }

    @Override
    protected void handleMessage(String message) {
        if(client!= null)
        {
            client.processMessage(message);
        }
        else
        {
            System.out.println("No message bruv");
        }
    }

    @Override
    protected void handleDisconnect() {
        if(client != null)
        {
            client.handleDisconnect();
        }
        else
        {
            System.out.println("No Client bruv");
        }

    }
    public void sendMessageToServer(String message)
    {
        sendMessage(message);
    }
}
