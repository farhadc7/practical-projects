package Client;

import IU.FileTransmitter;
import IU.XmlConfig;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Client2 {
    public String ip;
    public int port;
    public String clientName;
    public Long token;
    private List<FileTransmitter> list;
    Socket socket;
    private Boolean socketIsActive = false;
    private Boolean verification;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    public Client2(List<FileTransmitter> list, XmlConfig xmlConfig) {
        this.token = xmlConfig.getServerToken();
        this.clientName = xmlConfig.getName();
        this.ip = xmlConfig.getServerIp();
        this.port = xmlConfig.getServerPort();
        this.list = list;
    }
    public void connection() {
        int counter = 1;
        while (true) {
            try {
                if (socketIsActive) {
                    if (counter < list.size()){
                        list.get(counter).setClientName(this.clientName);
                        list.get(counter).setServerToken(this.token);
                        objectOutputStream.writeObject(list.get(counter));
                        counter++;
                        Thread.sleep(2000);
                        FileTransmitter result = (FileTransmitter) objectInputStream.readObject();
                        System.out.println(result.getAccountId());
                    }
                } else {
                    socket = new Socket(ip, port);
                    socket.setKeepAlive(true);
                    socketIsActive = socket.getKeepAlive();
                    if(socketIsActive) counter--;
                    objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectInputStream = new ObjectInputStream(socket.getInputStream());
                }
            } catch (IOException e) {
                socketIsActive = false;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

