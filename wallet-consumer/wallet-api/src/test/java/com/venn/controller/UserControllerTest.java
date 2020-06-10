package com.venn.controller;


import com.power.doc.builder.ApiDocBuilder;
import com.power.doc.model.ApiConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/11/6 16:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {


  @Test
  public void getUserInfo() {
    ApiConfig config = new ApiConfig();
    //服务地址
    config.setServerUrl("http://localhost:8089");//接口地址:
    //生成到一个文档
    config.setAllInOne(true);
    //采用严格模式
    config.isStrict();
    //文档输出路径
    config.setOutPath("C:\\venn\\programe\\md");//这里的话 需要自己定义下文件的地址哈
    ApiDocBuilder.builderControllersApi(config);
    //将生成的文档输出到/Users/dujf/Downloads/md目录下，严格模式下api-doc会检测Controller的接口注释
  }
}