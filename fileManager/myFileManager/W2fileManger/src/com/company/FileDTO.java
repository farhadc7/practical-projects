package com.company;
public class FileDTO {
    private String fileName;
    private long lastModified;
    private long createTime;
    private long fileSize;
    private String fileAddress;
    private boolean isDirectory;

    public FileDTO(){
        isDirectory=false;
        lastModified= -1;
        createTime = -1;
        fileSize = -1;
    }

    public String getFileName (){
        return this.fileName;
    }

    public long getCreateTime(){
        return createTime;
    }

    public void setCreateTime(long createTime){
        this.createTime=createTime;
    }

    public long getLastModified (){
        return lastModified;
    }

    public long getFileSize (){
        return fileSize;
    }

    public String getAddress (){
        return fileAddress;
    }

    public boolean isDirectory (){

        return isDirectory;
    }

    public void setFileName(String fileName){
        this.fileName=fileName;
    }

    public void setLastModified (long lastModified){
        this.lastModified=lastModified;
    }

    public void setFileSize (long fileSize){
        this.fileSize=fileSize;
    }

    public void setAddress (String fileAddress){
        this.fileAddress=fileAddress;
    }

    public void isADirectory(){
        this.isDirectory=true;
    }

}
