package com.macro.mall.tiny.controller;

import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.mbg.model.Seafood;
import com.macro.mall.tiny.service.PmsBrandService;
import com.macro.mall.tiny.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "ProductController")
@Tag(name = "ProductController→商品管理")
@RequestMapping("/product")
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
}
