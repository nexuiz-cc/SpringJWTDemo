package com.macro.mall.tiny.controller;

import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.mbg.model.Seafood;
import com.macro.mall.tiny.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@RequestMapping("/product")
@Api(tags = "ProductController")
@Tag(name = "ProductController", description = "商品管理")
@Controller
public class ProductController {
  @Autowired
  private ProductService productService;

  @ApiOperation("获取所有Seafood菜单")
  @ResponseBody
  @RequestMapping(value = "listAll", method = RequestMethod.GET)
  public CommonResult<List<Seafood>> getSeafoodList(){
    return CommonResult.success(productService.selectAllSeafood());
  }

  @ApiOperation("获取单个Seafood菜单")
  @RequestMapping(value = "/{name}", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<List<Seafood>> getOneSeafood(@PathVariable("name") String name){
    return CommonResult.success(productService.selectOneSeafood(name));
  }
}
