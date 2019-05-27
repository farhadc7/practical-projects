package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private int port=5000;
    private Socket socket;
    private ServerSocket serverSocket;

    public Server() throws IOException {
        serverSocket=new ServerSocket(port);
    }
    public void start() throws IOException {

        while(true){
            socket= serverSocket.accept();
                Thread t=new Thread(new ClientHandler(socket));
                t.start();
        }
    }
}
