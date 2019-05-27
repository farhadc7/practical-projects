package Client;

import IU.FileTransmitter;
import IU.XmlConfig;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Client1 {
    private String ip;
    private int port;
    private String clientName;
    private Long token;
    private List<FileTransmitter> list;
    private Socket socket;
    private Boolean socketIsActive = false;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public Client1(List<FileTransmitter> list, XmlConfig xmlConfig) {
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

