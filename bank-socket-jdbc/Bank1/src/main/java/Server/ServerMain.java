package Server;

import java.io.IOException;

public class ServerMain {
    public static void main(String Args[]) throws IOException, ClassNotFoundException, InterruptedException {
        Server server=new Server();
        server.start();
    }
}
