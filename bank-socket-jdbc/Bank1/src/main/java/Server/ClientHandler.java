package Server;
import IU.FileTransmitter;
import Logger.LoggerCreator;
import Model.DataModel;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private FileTransmitter fileTransmitter;
    private Boolean result;
    private Socket socket;
    private Long serverToken=112233l;
    private Boolean socketIsActive=true;
    private ObjectOutputStream out;
    private ObjectInputStream input;
    private Object inputObj;
    private LoggerCreator loggerCreator=new LoggerCreator();
    public ClientHandler( Socket socket){
        this.socket= socket;
    }
    public void run() {
        try {
            out=new ObjectOutputStream(socket.getOutputStream());
            input= new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("error in server side");
        }
        while(socketIsActive){
            try {
                fileTransmitter =(FileTransmitter)input.readObject();
                if(fileTransmitter.getServerToken().equals(serverToken)){
                    Thread.sleep(1000);
                    loggerCreator.createLog("requestLogger","REQUEST", fileTransmitter);
                    if(fileTransmitter.getOperationType() == 0){
                        result=DataModel.dbWithdraw(fileTransmitter.getAccountId(), fileTransmitter.getAmount());
                    }else if(fileTransmitter.getOperationType() == 1){
                        result=DataModel.dbDeposit(fileTransmitter.getAccountId(), fileTransmitter.getAmount());
                    }else if(fileTransmitter.getOperationType() == 3){
                        result=DataModel.cart2cart(fileTransmitter.getAccountId(),fileTransmitter.getDistId(), fileTransmitter.getAmount());
                    }


                    fileTransmitter.setTransaction(result);
                    loggerCreator.createLog("resultLogger","RESULT", fileTransmitter);
                    out.writeObject(fileTransmitter);
                    out.flush();
                }else{
                    break;
                }
            } catch (IOException e) {
                socketIsActive=false;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
