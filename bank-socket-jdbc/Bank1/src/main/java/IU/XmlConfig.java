package IU;

public class XmlConfig {
    private String serverIp;
    private Integer serverPort;
    private String name;
    private long serverToken;

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getServerToken() {
        return serverToken;
    }

    public void setServerToken(long serverToken) {
        this.serverToken = serverToken;
    }
}
