package sample;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;


public class Connection implements Runnable {
    private Socket socket;
    public Connection(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            while (Server.clients != 1) {
                dataOutputStream.writeUTF(Integer.toString(Server.clients));
            }

            dataOutputStream.write(Server.clients);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
