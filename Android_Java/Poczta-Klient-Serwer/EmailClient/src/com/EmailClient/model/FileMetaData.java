package com.EmailClient.model;

import java.io.Serializable;


public class FileMetaData implements Serializable {

    private String fileName;
    private String creationDate;
    private long size;

    public FileMetaData() {
    }

    public String getFileName() {
        return fileName;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public long getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "FileMetaData{" +
                "fileName='" + fileName + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", size=" + size +
                '}';
    }
}
