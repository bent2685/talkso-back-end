package com.tzvtc.talksobackend.common;


import com.tzvtc.talksobackend.util.arcfaceutil.ArcFaceBuilder;
import com.tzvtc.talksobackend.util.uploadutil.UploadUtil;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class InitStart {

  @PostConstruct
  public void run() {
    initArcFaceBuilder();
  }

  // 初始化ArcFaceBuilder
  public void initArcFaceBuilder() {
    List<String> enginePathList = Arrays.asList("sdks", "arcface", "WIN64");
    ArcFaceBuilder.appId = "62NBG96dw3B7mYet1NTk8jGbhndktT5LDq4oNkbSafCd";
    ArcFaceBuilder.sdkKey = "Ap9MUww7JkWzpC1F4tH7FnDkGa9nH4qaWyoBc42zDkfe";
    ArcFaceBuilder.faceEnginePath = UploadUtil.getResourcesPathFile(enginePathList).getAbsolutePath();
  }
}
