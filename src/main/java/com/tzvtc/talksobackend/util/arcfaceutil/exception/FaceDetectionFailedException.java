package com.tzvtc.talksobackend.util.arcfaceutil.exception;

public class FaceDetectionFailedException extends RuntimeException {

  public FaceDetectionFailedException(String message) {
    super(message);
  }

  public FaceDetectionFailedException() {
    this("Face detection failed!");
  }
}
