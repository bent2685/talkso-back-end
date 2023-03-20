package com.tzvtc.talksobackend.util.arcfaceutil.exception;

public class ElementHasNoFaceFeatureException extends FaceDetectionFailedException {


  public ElementHasNoFaceFeatureException(String message) {
    super(message);
  }

  public ElementHasNoFaceFeatureException() {
    this("Element has no 'FaceFeatureData' annotation!");
  }
}
