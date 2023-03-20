package com.tzvtc.talksobackend.util.uploadutil;

public class FileInfoForUpload {
  private String fileName;
  // 是否压缩
  private Boolean isCompress = false;
  // 压缩比例0-1
  private Double quality = 1.00;
  // 缩放比例0-1
  private Double scale = 1.00;

  private String subDir;

  private String format;

  public FileInfoForUpload() {
  }

  public FileInfoForUpload(String fileName, Boolean isCompress, Double quality, Double scale, String subDir, String format) {
    this.fileName = fileName;
    this.isCompress = isCompress;
    this.quality = quality;
    this.scale = scale;
    this.subDir = subDir;
    this.format = format;
  }

  public String getFileName() {
    return fileName;
  }

  public FileInfoForUpload setFileName(String fileName) {
    this.fileName = fileName;
    return this;
  }

  public Boolean getCompress() {
    return isCompress;
  }

  public FileInfoForUpload setCompress(Boolean compress) {
    isCompress = compress;
    return this;
  }

  public Double getQuality() {
    return quality;
  }

  public FileInfoForUpload setQuality(Double quality) {
    this.quality = quality;
    return this;
  }

  public Double getScale() {
    return scale;
  }

  public FileInfoForUpload setScale(Double scale) {
    this.scale = scale;
    return this;
  }

  public String getSubDir() {
    return subDir;
  }

  public FileInfoForUpload setSubDir(String subDir) {
    this.subDir = subDir;
    return this;
  }

  public String getFormat() {
    return format;
  }

  public FileInfoForUpload setFormat(String format) {
    this.format = format;
    return this;
  }

  @Override
  public String toString() {
    return "FileInfo{" +
        "fileName='" + fileName + '\'' +
        ", isCompress=" + isCompress +
        ", quality=" + quality +
        ", scale=" + scale +
        ", subDir='" + subDir + '\'' +
        ", format='" + format + '\'' +
        '}';
  }
}
