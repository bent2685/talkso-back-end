package com.tzvtc.talksobackend.util.arcfaceutil.exception;

public class GenderDetectionFailedException extends FaceDetectionFailedException {


  public GenderDetectionFailedException(String message) {
    super(message);
  }

  public GenderDetectionFailedException() {
    this("Gender detection failed!");
  }
}
