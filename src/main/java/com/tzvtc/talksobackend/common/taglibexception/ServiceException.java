package com.tzvtc.talksobackend.common.taglibexception;

import com.tzvtc.talksobackend.common.resultcode.ResultCodeEnum;

public class ServiceException extends RuntimeException{

  private ResultCodeEnum resultCodeEnum;

  private String message;

  private Object data;

 
}
