package com.tzvtc.talksobackend.entity;

import com.tzvtc.talksobackend.util.arcfaceutil.anno.FaceFeatureData;

public class User {
  private Integer id;

  private String uname;

  @FaceFeatureData
  private byte[] aaa;
}
