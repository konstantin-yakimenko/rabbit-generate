package com.mangotele.generate.types;

public class Command extends AbstractCommand {

    private CommandType commandType;
    private FileMessage fileMessage;
    private String urlMaster;
    private String fileServerDir;
    private String requestId;
    private String dateFrom;
    private String dateTo;

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public FileMessage getFileMessage() {
        return fileMessage;
    }

    public void setFileMessage(FileMessage fileMessage) {
        this.fileMessage = fileMessage;
    }

    public String getUrlMaster() {
        return urlMaster;
    }

    public void setUrlMaster(String urlMaster) {
        this.urlMaster = urlMaster;
    }

    public String getFileServerDir() {
        return fileServerDir;
    }

    public void setFileServerDir(String fileServerDir) {
        this.fileServerDir = fileServerDir;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        return "Command{" +
                "commandType=" + commandType +
                ", fileMessage=" + fileMessage +
                ", urlMaster='" + urlMaster + '\'' +
                ", fileServerDir='" + fileServerDir + '\'' +
                ", requestId='" + requestId + '\'' +
                ", country='" + country + '\'' +
                ", dateFrom='" + dateFrom + '\'' +
                ", dateTo='" + dateTo + '\'' +
                '}';
    }
}
