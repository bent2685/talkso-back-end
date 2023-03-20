package com.tzvtc.talksobackend.util.uploadutil;

import java.io.File;

public class UploadedInfo {
  private String type;
  private File file;
  private String fileName;
  private String suffixBeforeCompress;
  private long size;
  private Boolean isCompressed;

  private String suffixAfterCompress;

  public UploadedInfo() {
  }

  public UploadedInfo(String type, File file, String fileName, String suffixBeforeCompress, long size, Boolean isCompressed, String suffixAfterCompress) {
    this.type = type;
    this.file = file;
    this.fileName = fileName;
    this.suffixBeforeCompress = suffixBeforeCompress;
    this.size = size;
    this.isCompressed = isCompressed;
    this.suffixAfterCompress = suffixAfterCompress;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getSuffixBeforeCompress() {
    return suffixBeforeCompress;
  }

  public void setSuffixBeforeCompress(String suffixBeforeCompress) {
    this.suffixBeforeCompress = suffixBeforeCompress;
  }

  public String getSuffixAfterCompress() {
    return suffixAfterCompress;
  }

  public void setSuffixAfterCompress(String suffixAfterCompress) {
    this.suffixAfterCompress = suffixAfterCompress;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public Boolean getCompressed() {
    return isCompressed;
  }

  public void setCompressed(Boolean compressed) {
    isCompressed = compressed;
  }

  @Override
  public String toString() {
    return "UploadedInfo{" +
        "type='" + type + '\'' +
        ", file=" + file +
        ", fileName='" + fileName + '\'' +
        ", suffixBeforeCompress='" + suffixBeforeCompress + '\'' +
        ", size=" + size +
        ", isCompressed=" + isCompressed +
        ", suffixAfterCompress='" + suffixAfterCompress + '\'' +
        '}';
  }
}
