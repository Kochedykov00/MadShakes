package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Client {

    private int id;
    String address;



    public Socket connectingWithServer() throws IOException {



        String host = address;
        final int PORT = 1234;
        Socket socket = new Socket(host, PORT);
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        String message = dataInputStream.readUTF();
        this.id = Integer.parseInt(message);

        while (!message.equals("1")) {
            message = dataInputStream.readUTF();
        }

        //dataOutputStream.writeUTF(getNameOfPlayer());
        //nameOFSecondPlayer = dataInputStream.readUTF();
        return socket;
    }



    public Client(String host) {
        this.address = host;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
