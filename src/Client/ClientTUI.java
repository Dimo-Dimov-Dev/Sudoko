package Client;

import protocol.Protocol;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Struct;
import java.util.Scanner;

public class ClientTUI {
    private boolean running;
    public static void main(String[] args) {
        ClientTUI tui = new ClientTUI();
        tui.run();
    }

    public void run()
    {
        Scanner scanner = new Scanner(System.in);
        running = true;
        try
        {
        System.out.println("Enter server address");
        String serverAddress = scanner.nextLine();
        InetAddress address = InetAddress.getByName(serverAddress);
        System.out.println("Enter port");
        Scanner port = new Scanner(System.in);
        int portTo = port.nextInt();
        ClientConnection connection = new ClientConnection(address, portTo, this);
        connection.start();
        while(running)
        {
            System.out.println(" Command: ");
            String command = scanner.nextLine().trim().toUpperCase();

            switch (command) {
                case Protocol.NEWGAME -> {
                    connection.sendMessageToServer(Protocol.NEWGAME);
                }
                case Protocol.MOVE -> {
                    System.out.print("Enter cell index (1-81): ");
                    String place = scanner.nextLine().trim();

                    System.out.print("Enter number (1-9): ");
                    String number = scanner.nextLine().trim();

                    connection.sendMessageToServer(Protocol.MOVE + " " + place + " " + number);
                }
                case Protocol.REMOVE -> {
                    System.out.print("Enter cell index to remove number (1-81): ");
                    String place = scanner.nextLine().trim();
                    connection.sendMessageToServer(Protocol.REMOVE + " " + place);
                }
            }
        }


        } catch (IllegalArgumentException | IOException e) {
            System.err.println("Invalid server address format. Please enter a valid IP address or hostname.");
        }


    }

    public void processMessage(String message) {

        String[] parts = message.split(" ",2);
        switch (parts[0])
        {
            case Protocol.BOARD :

                System.out.println(parts[1]);
                break;
            case Protocol.WIN:
                System.out.println(parts[1]);
                System.out.println("Type NEWGAME to start a new game");
                break;
            case Protocol.ERROR:
                System.out.println(parts[1]);
                break;
        }


    }

    public void handleDisconnect() {
    }
}
