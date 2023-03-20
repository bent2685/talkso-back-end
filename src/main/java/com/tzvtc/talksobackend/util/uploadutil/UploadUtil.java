package com.tzvtc.talksobackend.util.uploadutil;


import com.tzvtc.talksobackend.common.enums.ResultCodeEnum;
import com.tzvtc.talksobackend.common.exception.ServiceException;
import io.netty.util.internal.StringUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class UploadUtil {

  // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
  public static List<String> uploadTarget = Arrays.asList("src", "main", "resources", "static", "public");
  public static List<String> resourcesTarget = Arrays.asList("src", "main", "resources");
  public static List<String> imageSuffix = Arrays.asList("jpg", "jpeg", "png", "bmp", "tiff", "gif", "svg", "webp");
  private static List<String> videoSuffix = Arrays.asList("avi", "mov", "mp4", "m4v", "asf", "wmv", "flv", "mkv");

  // 头像上传目录
  public static String avatarSubdir = "avatar";
  // 头像支持的文件类型
  public static List<String> avatarSupport = Arrays.asList("jpg", "png");

  public static File getBasePathFile(List pathList) {
    String bathPath = null;
    try {
      bathPath = new File("").getCanonicalPath() + File.separator;
      if (pathList != null) {
        String path = bathPath + String.join(File.separator, pathList) + File.separator;
        return new File(path);
      }
      return new File(bathPath);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static File getResourcesPathFile(List pathList) {
    List<String> path = new ArrayList<>();
    path.addAll(resourcesTarget);
    path.addAll(pathList);
    return getBasePathFile(path);
  }

  public static File getPublicPath() {
    String basePath = null;
    // 获取项目在系统里的绝对路径
    // 构建上传文件的存放 "文件夹" 路径
    File fileDir = getBasePathFile(uploadTarget);
    if (!fileDir.exists()) {
      // 递归生成文件夹
      fileDir.mkdirs();
    }
    return fileDir;
  }

  public static String getTypePath(String suffix) {
    return File.separator + getType(suffix) + File.separator;
  }

  public static String getType(String suffix) {
    String path = "";
    if (imageSuffix.contains(suffix)) path = "images";
    if (videoSuffix.contains(suffix)) path = "videos";
    if (path.equals("")) path = "others";
    return path;
  }

  // 获得文件后缀名
  public static String getSuffix(String fileName) {
    if (StringUtil.isNullOrEmpty(fileName)) return "";
    return fileName.substring(fileName.lastIndexOf(".") + 1);
  }


  // 判断文件后缀是否支持
  public static Boolean isSupportSuffix(List<String> supportSuffixList, String suffix) {
    if (!supportSuffixList.contains(suffix)) {
      throw new ServiceException(ResultCodeEnum.DATA_IS_NOT_SUPPORT, "文件仅支持: " + String.join("、", supportSuffixList) + "类型");
    }
    return true;
  }

  // 判断文件名是否支持
  public static Boolean isSupport(List<String> supportSuffixList, String fileName) {
    String suffix = getSuffix(fileName);
    return isSupportSuffix(supportSuffixList, suffix);
  }

  // 判断文件是否支持
  public static Boolean isSupport(List<String> supportSuffixList, File file) {
    String fileName = file.getName();
    String suffix = getSuffix(fileName);
    return isSupportSuffix(supportSuffixList, suffix);
  }

  // 判断文件是否支持
  public static Boolean isSupport(List<String> supportSuffixList, MultipartFile file) {
    String fileName = file.getOriginalFilename();
    String suffix = getSuffix(fileName);
    return isSupportSuffix(supportSuffixList, suffix);
  }

  public static File upload(MultipartFile file, FileInfoForUpload target, Consumer<UploadedInfo> callback) {
    if (file.isEmpty()) throw new ServiceException(ResultCodeEnum.FILE_UPLOAD_ERROR, "请选择文件");

    //* 获得FileInfo对象
    FileInfoForUpload fileInfoForUpload = target;

    //* 获得目录
    // 获得public目录
    String basePath = null;
    try {
      basePath = getPublicPath().getCanonicalPath();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    //* 获得文件名
    String fileName = fileInfoForUpload.getFileName();
    // 文件后缀
    String suffix = getSuffix(fileName);

    // 如果文件名为空则自动创建个文件名
    if (StringUtil.isNullOrEmpty(fileName)) {
      String fileNameTemp = file.getOriginalFilename();
      suffix = getSuffix(fileNameTemp);
      if (StringUtil.isNullOrEmpty(suffix)) suffix = getSuffix(fileNameTemp);
      String uuid = UUID.randomUUID().toString().replace("-", "");
      fileName = uuid + "." + suffix;
    }

    //! 如果用户传入了自定义格式则再次修改文件名
    String format = fileInfoForUpload.getFormat();
    if (!StringUtil.isNullOrEmpty(format)) {
      fileName = fileName.split("\\.")[0] + "." + format;
    }

    //* 获得文件待上传的子目录
    String type = fileInfoForUpload.getSubDir();
    if (StringUtil.isNullOrEmpty(type)) type = getType(getSuffix(fileName));
    String typePath = File.separator + type + File.separator;

    //* 获得文件传输的目的目录
    String newFilePath = basePath + typePath;
    File newFileDir = new File(newFilePath);
    // 不存在则创建
    if (!newFileDir.exists()) newFileDir.mkdirs();

    //* 创建待上传文件
    File newFile = new File(newFilePath + fileName);

    // 上传文件
    Boolean isCompressed = false;
    try {
      file.transferTo(newFile);
      //! 如果需要对图片进行压缩则进行压缩
      if (fileInfoForUpload.getCompress() && imageSuffix.contains(suffix)) {
        isCompressed = true;
        // 压缩
        Thumbnails.of(newFile)
            .outputQuality(fileInfoForUpload.getQuality()).
            scale(fileInfoForUpload.getScale()).
            outputFormat(fileInfoForUpload.getFormat())
            .toFile(newFile);
      }

      // 设置回调参数的值
      UploadedInfo fileUploaded = new UploadedInfo();
      fileUploaded.setFile(newFile);
      fileUploaded.setSize(newFile.length());
      fileUploaded.setType(type);
      fileUploaded.setCompressed(isCompressed);
      fileUploaded.setFileName(fileName);
      fileUploaded.setSuffixBeforeCompress(suffix);
      fileUploaded.setSuffixAfterCompress(format);
      callback.accept(fileUploaded);

      return newFile;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  // 方法重载
  public static File upload(MultipartFile file, FileInfoForUpload target) {
    return upload(file, target, null);
  }

  // 方法重载
  public static File upload(MultipartFile file, Consumer<UploadedInfo> callback) {
    return upload(file, new FileInfoForUpload(), callback);
  }

  // 方法重载
  public static File upload(MultipartFile file) {
    return upload(file, new FileInfoForUpload(), null);
  }
}
