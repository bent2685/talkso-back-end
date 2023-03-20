package com.tzvtc.talksobackend.controller;


import com.tzvtc.talksobackend.util.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Api(tags = "测试接口")
@RestController
@RequestMapping("/demo")
public class TestController {

  @ApiOperation("demo接口")
  @GetMapping("/demo01")
  public Resp<Void> demo() {
    return Resp.ok().msg("Hello World");
  }
}
