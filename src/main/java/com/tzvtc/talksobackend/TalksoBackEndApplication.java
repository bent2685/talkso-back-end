package com.tzvtc.talksobackend;

import com.arcsoft.face.*;
import com.arcsoft.face.enums.CompareModel;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.toolkit.ImageFactory;
import com.arcsoft.face.toolkit.ImageInfo;
import com.arcsoft.face.toolkit.ImageInfoEx;
import com.tzvtc.talksobackend.entity.User;
import com.tzvtc.talksobackend.util.arcfaceutil.ArcFaceBuilder;
import com.tzvtc.talksobackend.util.arcfaceutil.ArcFaceUtil;
import com.tzvtc.talksobackend.util.uploadutil.UploadUtil;
import javafx.scene.shape.Arc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
@MapperScan("com.talksobackend.mapper")
public class TalksoBackEndApplication {
  public static void main(String[] args) {
    SpringApplication.run(TalksoBackEndApplication.class, args);
    testArcFace();
  }

  public static void testArcFace() {

//    File file1 = new File("F:\\Project\\talkso\\assets\\zjz1.png");
    File file2 = new File("F:" + File.separator + "Project" + File.separator + "talkso" + File.separator + "assets" + File.separator + "ne.jpg");
//    File file2 = new File("F:\\Project\\talkso\\assets\\zjz3.png");
//    System.out.println(ArcFaceUtil.faceComparison(file1, file2));

    System.out.println(file2.exists());
//    System.out.println(ArcFaceUtil.getGender(file2));
  }
}