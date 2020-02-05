package com.mangotele.generate.types;

public class FileMessage {

    private Long fileId; // уникальный идентификатор файла
    private String fileName; // название файла
    private String fileDesc; // описание файла
    private Long filePathId; // id, из которого получаем путь к файлу
    private StringBuilder logBuilder = new StringBuilder();
    private long savedFileSize = 0;
    private String appIpAddr = "";
    private Long systemRecordId;
    private Long systemTableId;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDesc() {
        return fileDesc;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    public Long getFilePathId() {
        return filePathId;
    }

    public void setFilePathId(Long filePathId) {
        this.filePathId = filePathId;
    }

    public StringBuilder getLogBuilder() {
        return logBuilder;
    }

    public void setLogBuilder(StringBuilder logBuilder) {
        this.logBuilder = logBuilder;
    }

    public long getSavedFileSize() {
        return savedFileSize;
    }

    public void setSavedFileSize(long savedFileSize) {
        this.savedFileSize = savedFileSize;
    }

    public String getAppIpAddr() {
        return appIpAddr;
    }

    public void setAppIpAddr(String appIpAddr) {
        this.appIpAddr = appIpAddr;
    }

    public Long getSystemRecordId() {
        return systemRecordId;
    }

    public void setSystemRecordId(Long systemRecordId) {
        this.systemRecordId = systemRecordId;
    }

    public Long getSystemTableId() {
        return systemTableId;
    }

    public void setSystemTableId(Long systemTableId) {
        this.systemTableId = systemTableId;
    }

    @Override
    public String toString() {
        return "FileMessage{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", fileDesc='" + fileDesc + '\'' +
                ", filePathId=" + filePathId +
                ", logBuilder=" + logBuilder +
                ", savedFileSize=" + savedFileSize +
                ", appIpAddr='" + appIpAddr + '\'' +
                ", systemRecordId=" + systemRecordId +
                ", systemTableId=" + systemTableId +
                '}';
    }
}
