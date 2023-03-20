package com.tzvtc.talksobackend.util.arcfaceutil.exception;

public class FaceComparisonFailedExeption extends FaceDetectionFailedException {


  public FaceComparisonFailedExeption(String message) {
    super(message);
  }

  public FaceComparisonFailedExeption() {
    this("Face comparison failed!");
  }
}
