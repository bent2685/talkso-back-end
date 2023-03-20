package com.tzvtc.talksobackend.util.arcfaceutil.exception;

public class AgeDetectionFailedException extends FaceDetectionFailedException {


  public AgeDetectionFailedException(String message) {
    super(message);
  }

  public AgeDetectionFailedException() {
    this("Age detection failed!");
  }
}
