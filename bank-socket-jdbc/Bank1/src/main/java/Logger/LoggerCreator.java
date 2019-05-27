package Logger;

import IU.FileTransmitter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerCreator {
    private FileTransmitter fileTransmitter;
    private String message;
    private String loggerName;
    private String level;
    public synchronized void createLog(String loggerName, String level, FileTransmitter fileTransmitter){
        this.fileTransmitter = fileTransmitter;
        this.loggerName =loggerName;
        this.level= level;
        if(level == "REQUEST"){
            message=String.format("client : %s with account number :%d , transactin : %d, amount: %d", fileTransmitter.getClientName(), fileTransmitter.getAccountId(), fileTransmitter.getId(), fileTransmitter.getAmount());
        }else if(level == "RESULT"){
            message=String.format("client : %s with account number :%d , transactin : %d, amount: %d condition id: %s", fileTransmitter.getClientName(), fileTransmitter.getAccountId(), fileTransmitter.getId(), fileTransmitter.getAmount(), fileTransmitter.getTransaction());
        }
         Logger logger=LogManager.getLogger(loggerName);
         logger.log(Level.getLevel(level),message);
    }
}
