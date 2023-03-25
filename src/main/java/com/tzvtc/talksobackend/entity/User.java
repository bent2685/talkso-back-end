package com.tzvtc.talksobackend.entity;

import com.tzvtc.talksobackend.util.arcfaceutil.anno.FaceFeatureData;
import lombok.Data;

@Data
public class User {
  private Integer id;

  private String uname;

  @FaceFeatureData
  private byte[] faceFeature;
}
