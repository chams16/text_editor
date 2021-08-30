import java.util.Date;

public class LogEntry {
    private String ipAdress;
    private Date accessTime;
    private String request;
    private int statusCode;
    private int bytereturn;

    public LogEntry(String ipAdress, Date accessTime, String request, int statusCode, int bytereturn) {
        this.ipAdress = ipAdress;
        this.accessTime = accessTime;
        this.request = request;
        this.statusCode = statusCode;
        this.bytereturn = bytereturn;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public String getRequest() {
        return request;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getBytereturn() {
        return bytereturn;
    }

    public String toString(){
        return ipAdress+" "+accessTime+" "+request+" "+statusCode+" "+bytereturn;
    }
}
