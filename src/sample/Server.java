package sample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server {

    public static ServerSocket serverSocket;
    public static int clients;
    public static List<Socket> clientsSockets = new ArrayList<>();
    public static List<Connection> connections = new ArrayList<>();
    public static List<Thread> threads = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        final int PORT = 1234;
        serverSocket = new ServerSocket(PORT);

        waitingClients();

    }


        public static void waitingClients() throws IOException {

            while (true) {
                System.out.println("ожидаем");

                Socket s = serverSocket.accept();
                clientsSockets.add(s);
                clients = clientsSockets.size();

                System.out.println("New client connected." + " Clients: " + clients);
                Connection connection = new Connection(s, clients);
                connections.add(connection);

                Thread thread = (new Thread(connection));
                threads.add(thread);
                thread.start();
            }
    }
}

