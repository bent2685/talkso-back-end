package com.tzvtc.talksobackend.util.arcfaceutil;

import com.arcsoft.face.*;
import com.arcsoft.face.enums.CompareModel;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.toolkit.ImageFactory;
import com.arcsoft.face.toolkit.ImageInfo;
import com.tzvtc.talksobackend.util.arcfaceutil.anno.FaceFeatureData;
import com.tzvtc.talksobackend.util.arcfaceutil.exception.*;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class ArcFaceUtil {


  /**
   * 人脸检测
   *
   * @param file
   * @return
   */
  public static List<FaceInfo> detectFaces(File file) {
    // 错误码
    Integer errorCode = 0;
    ImageInfo imageInfo = ImageFactory.getRGBData(file);
    FaceEngine faceEngine = ArcFaceBuilder.activeEngine();

    List<FaceInfo> faceInfoList = new ArrayList<>();
    // 人脸检测
    errorCode = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);

    // 如果人脸检测不通过，则抛出异常
    if (errorCode != ErrorInfo.MOK.getValue() || faceInfoList.isEmpty()) {
      faceEngine.unInit();
      throw new FaceDetectionFailedException();
    }

    faceEngine.unInit();
    return faceInfoList;
  }


  /**
   * 获得性别
   *
   * @param file
   * @return
   */
  public static Boolean getGender(File file) {
    // 错误码
    Integer errorCode = 0;
    ImageInfo imageInfo = ImageFactory.getRGBData(file);
    FaceEngine faceEngine = ArcFaceBuilder.activeEngine();

    List<FaceInfo> faceInfoList = detectFaces(file);

    // 传入分离的图像信息数据
    faceEngine.process(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList, FunctionSupportBuilder.init().setGender(true).build());

    List<GenderInfo> genderInfoList = new ArrayList<GenderInfo>();
    errorCode = faceEngine.getGender(genderInfoList);
    // 如果性别检测异常，则抛出异常
    if (errorCode != ErrorInfo.MOK.getValue() || genderInfoList.isEmpty()) {
      throw new GenderDetectionFailedException();
    }

    // 0为男，1为女
    Integer genderInt = genderInfoList.get(0).getGender();

    faceEngine.unInit();
    // true为男，false为女
    return genderInt == 0;
  }


  /**
   * 获得年龄
   *
   * @param file
   * @return
   */
  public static Integer getAge(File file) {
    // 错误码
    Integer errorCode = 0;
    ImageInfo imageInfo = ImageFactory.getRGBData(file);
    FaceEngine faceEngine = ArcFaceBuilder.activeEngine();

    List<FaceInfo> faceInfoList = detectFaces(file);

    // 传入分离的图像信息数据
    faceEngine.process(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList, FunctionSupportBuilder.init().setAge(true).build());

    List<AgeInfo> ageInfoList = new ArrayList<AgeInfo>();
    errorCode = faceEngine.getAge(ageInfoList);
    // 如果性别检测异常，则抛出异常
    if (errorCode != ErrorInfo.MOK.getValue() || ageInfoList.isEmpty()) {
      throw new AgeDetectionFailedException();
    }
    Integer age = ageInfoList.get(0).getAge();

    faceEngine.unInit();
    return age;
  }

  /**
   * 人脸比对
   *
   * @param sourceFaceFeature 原特征值
   * @param targetFaceFeature 目标特征值
   * @param compareModel      比对模型
   * @return
   */
  public static float faceComparison(FaceFeature sourceFaceFeature, FaceFeature targetFaceFeature, CompareModel compareModel) {
    // 错误码
    Integer errorCode = 0;
    FaceEngine faceEngine = ArcFaceBuilder.activeEngine();

    FaceSimilar faceSimilar = new FaceSimilar();
    errorCode = faceEngine.compareFaceFeature(sourceFaceFeature, targetFaceFeature, compareModel, faceSimilar);
    // 如果人脸比对检测异常，则抛出异常
    if (errorCode != ErrorInfo.MOK.getValue()) {
      throw new FaceComparisonFailedExeption();
    }

    faceEngine.unInit();
    return faceSimilar.getScore();

  }


  /**
   * 人脸比对
   *
   * @param sourceFile   源人脸
   * @param targetFile   目标人脸
   * @param compareModel 比对模型
   * @return
   */
  public static float faceComparison(File sourceFile, File targetFile, CompareModel compareModel) {
    FaceFeature sourceFaceFeature = file2FaceFeature(sourceFile);
    FaceFeature targetFaceFeature = file2FaceFeature(targetFile);
    return faceComparison(sourceFaceFeature, targetFaceFeature, compareModel);
  }

  /**
   * 人脸比对
   *
   * @param sourceFile 源人脸
   * @param targetFile 目标人脸
   * @return
   */
  public static float faceComparison(File sourceFile, File targetFile) {
    // 默认的对比模型为CompareModel.LIFE_PHOTO
    return faceComparison(sourceFile, targetFile, CompareModel.LIFE_PHOTO);
  }

  /**
   * 人脸比对
   *
   * @param sourceFaceFeature 源特征值
   * @param targetFaceFeature 目标特征值
   * @return
   */
  public static float faceComparison(FaceFeature sourceFaceFeature, FaceFeature targetFaceFeature) {
    // 默认的对比模型为CompareModel.LIFE_PHOTO
    return faceComparison(sourceFaceFeature, targetFaceFeature, CompareModel.LIFE_PHOTO);
  }


  /**
   * 人脸搜索
   *
   * @param sourceFaceFeature 源特征值
   * @param targetElList      包含特征值的目标列表
   * @param compareModel      比对模型
   * @param <T>               包含特征值的目标
   * @return
   */
  public static <T> T faceSearch(FaceFeature sourceFaceFeature, List<T> targetElList, CompareModel compareModel) {
    T mostSimilarEl = null;
    float mostSimilarValue = 0;
    for (T targetEl : targetElList) {
      Field[] fields = targetEl.getClass().getDeclaredFields();
      // 二进制特征值
      byte[] faceFeatureData = null;

      // 遍历字段，获取被注解修饰了的属性
      for (Field field : fields) {
        boolean isPresent = field.isAnnotationPresent(FaceFeatureData.class);
        // 如果该字段未被注解修饰则下一个
        if (!isPresent) continue;

        try {
          faceFeatureData = (byte[]) field.get(targetEl);
          break;
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      }

      // 如果没有给字段设置对应注解注解
      if (faceFeatureData == null) {
        throw new ElementHasNoFaceFeatureException();
      }

      // 设置特征值
      FaceFeature targetFaceFeature = new FaceFeature();
      targetFaceFeature.setFeatureData(faceFeatureData);

      // 如果相似度大于上一个，则覆盖
      float similarValue = faceComparison(sourceFaceFeature, targetFaceFeature, compareModel);
      if (similarValue > mostSimilarValue) {
        mostSimilarValue = similarValue;
        mostSimilarEl = targetEl;
      }
    }

    return mostSimilarEl;
  }

  /**
   * 人脸搜索
   *
   * @param sourceFaceFeature 源特征值
   * @param targetElList      包含特征值的目标列表
   * @param <T>               包含特征值的目标
   * @return
   */
  public static <T> T faceSearch(FaceFeature sourceFaceFeature, List<T> targetElList) {
    return faceSearch(sourceFaceFeature, targetElList, CompareModel.LIFE_PHOTO);
  }


  /**
   * 提取特征值
   *
   * @param file 人脸
   * @return
   */
  public static FaceFeature file2FaceFeature(File file) {
    // 错误码
    Integer errorCode = 0;
    ImageInfo imageInfo = ImageFactory.getRGBData(file);
    FaceEngine faceEngine = ArcFaceBuilder.activeEngine();

    List<FaceInfo> faceInfoList = detectFaces(file);
    FaceFeature faceFeature = new FaceFeature();
    faceEngine.extractFaceFeature(
        imageInfo.getImageData(),
        imageInfo.getWidth(),
        imageInfo.getHeight(),
        imageInfo.getImageFormat(),
        faceInfoList.get(0),
        faceFeature
    );
    faceEngine.unInit();
    return faceFeature;
  }
}