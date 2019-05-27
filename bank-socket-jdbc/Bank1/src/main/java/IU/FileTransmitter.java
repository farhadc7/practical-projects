package IU;

import java.io.Serializable;

public class FileTransmitter implements Serializable {
    private int id;
    private Long amount;
    private int accountId;
    private Long serverToken;
    private Boolean transaction;
    private String clientName;
    private int operationType;
    private int distId;
    public int getOperationType() {
        return operationType;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }

    public int getDistId() {
        return distId;
    }

    public void setDistId(int distId) {
        this.distId = distId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setTransaction(Boolean transaction) {
        this.transaction = transaction;
    }

    public Boolean getTransaction() {
        return transaction;
    }

    public Long getServerToken() {
        return serverToken;
    }

    public void setServerToken(Long serverToken) {
        this.serverToken = serverToken;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
