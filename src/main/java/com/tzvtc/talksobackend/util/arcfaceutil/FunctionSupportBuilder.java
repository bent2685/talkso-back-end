package com.tzvtc.talksobackend.util.arcfaceutil;

import com.arcsoft.face.FunctionConfiguration;

public class FunctionSupportBuilder {


  private Boolean faceDetect;
  private Boolean faceRecognition;
  private Boolean age;

  private Boolean gender;
  private Boolean face3dAngle;
  private Boolean liveness;
  private Boolean irLiveness;


  private FunctionSupportBuilder(Boolean faceDetect, Boolean faceRecognition, Boolean age, Boolean gender, Boolean face3dAngle, Boolean liveness, Boolean irLiveness) {
    this.faceDetect = faceDetect;
    this.faceRecognition = faceRecognition;
    this.age = age;
    this.gender = gender;
    this.face3dAngle = face3dAngle;
    this.liveness = liveness;
    this.irLiveness = irLiveness;
  }

  public static FunctionSupportBuilder all() {
    return new FunctionSupportBuilder(true, true, true, true, true, true, true);
  }

  public static FunctionSupportBuilder init() {
    return new FunctionSupportBuilder(false, false, false, false, false, false, false);
  }

  // 支持所有的人脸属性识别
  public static FunctionSupportBuilder allFaceProp() {
    return init()
        .setAge(true)
        .setGender(true)
        .setFace3dAngle(true)
        .setLiveness(true);
  }

  public FunctionConfiguration build() {
    FunctionConfiguration functionConfiguration = new FunctionConfiguration();
    functionConfiguration.setSupportFaceDetect(this.faceDetect);
    functionConfiguration.setSupportFaceRecognition(this.faceRecognition);
    functionConfiguration.setSupportAge(this.age);
    functionConfiguration.setSupportGender(this.gender);
    functionConfiguration.setSupportFace3dAngle(this.face3dAngle);
    functionConfiguration.setSupportLiveness(this.liveness);
    functionConfiguration.setSupportIRLiveness(this.irLiveness);
    return functionConfiguration;
  }

  public Boolean getFaceDetect() {
    return faceDetect;
  }

  public FunctionSupportBuilder setFaceDetect(Boolean faceDetect) {
    this.faceDetect = faceDetect;
    return this;
  }

  public Boolean getFaceRecognition() {
    return faceRecognition;
  }

  public FunctionSupportBuilder setFaceRecognition(Boolean faceRecognition) {
    this.faceRecognition = faceRecognition;
    return this;
  }

  public Boolean getAge() {
    return age;
  }

  public FunctionSupportBuilder setAge(Boolean age) {
    this.age = age;
    return this;
  }

  public Boolean getGender() {
    return gender;
  }

  public FunctionSupportBuilder setGender(Boolean gender) {
    this.gender = gender;
    return this;
  }

  public Boolean getFace3dAngle() {
    return face3dAngle;
  }

  public FunctionSupportBuilder setFace3dAngle(Boolean face3dAngle) {
    this.face3dAngle = face3dAngle;
    return this;
  }

  public Boolean getLiveness() {
    return liveness;
  }

  public FunctionSupportBuilder setLiveness(Boolean liveness) {
    this.liveness = liveness;
    return this;
  }

  public Boolean getIrLiveness() {
    return irLiveness;
  }

  public FunctionSupportBuilder setIrLiveness(Boolean irLiveness) {
    this.irLiveness = irLiveness;
    return this;
  }

  @Override
  public String toString() {
    return "FunctionSupportBuilder{" +
        "faceDetect=" + faceDetect +
        ", faceRecognition=" + faceRecognition +
        ", age=" + age +
        ", gender=" + gender +
        ", face3dAngle=" + face3dAngle +
        ", liveness=" + liveness +
        ", irLiveness=" + irLiveness +
        '}';
  }
}
