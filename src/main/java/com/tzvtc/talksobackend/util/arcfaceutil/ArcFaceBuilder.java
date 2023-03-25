package com.tzvtc.talksobackend.util.arcfaceutil;

import com.arcsoft.face.EngineConfiguration;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.FunctionConfiguration;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


public class ArcFaceBuilder {

  public static String appId = "";
  public static String sdkKey = "";
  public static String faceEnginePath = "";

  public void setAppId(String appId) {
    ArcFaceBuilder.appId = appId;
  }

  public void setSdkKey(String sdkKey) {
    ArcFaceBuilder.sdkKey = sdkKey;
  }

  public void setFaceEnginePath(String faceEnginePath) {
    ArcFaceBuilder.faceEnginePath = faceEnginePath;
  }

  public static FaceEngine activeEngine() {
    // dll路径
    FaceEngine faceEngine = new FaceEngine(faceEnginePath);
    // active
    int activeCode = faceEngine.activeOnline(appId, sdkKey);
    if (activeCode != ErrorInfo.MOK.getValue() && activeCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
      throw new ExceptionInInitializerError("failed to active arcsoft-face engine");
    }

    // 引擎配置
    EngineConfiguration engineConfiguration = new EngineConfiguration();
    engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
    engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_0_ONLY);

    // 功能配置
    engineConfiguration.setFunctionConfiguration(FunctionSupportBuilder.all().build());

    int initCode = faceEngine.init(engineConfiguration);

    if (initCode != ErrorInfo.MOK.getValue()) {
      throw new ExceptionInInitializerError("failed to active arcsoft-face engine");
    }

    return faceEngine;
  }
}
