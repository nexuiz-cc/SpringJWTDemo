package com.macro.mall.tiny.controller;

import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.mbg.model.Seafood;
import com.macro.mall.tiny.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/product")
@Api(tags = "ProductController")
@Tag(name = "ProductController", description = "商品管理")
@Controller
public class ProductController {
  @Autowired
  private ProductService productService;


  @ApiOperation("获取Seafood菜单")
  @ResponseBody
  @RequestMapping(value = "get/listAll", method = RequestMethod.GET)
  @PreAuthorize("hasAuthority('product')")
  public CommonResult<List<Seafood>> getSeafoodList() {
    return CommonResult.success(productService.selectAllSeafood());
  }

  @ApiOperation("获取单个Seafood")
  @RequestMapping(value = "get/{name}", method = RequestMethod.GET)
  @PreAuthorize("hasAuthority('product')")
  @ResponseBody
  public CommonResult<List<Seafood>> getOneSeafood(@PathVariable("name") String name) {
    return CommonResult.success(productService.selectOneSeafood(name));
  }

  @ApiOperation("更新单个Seafood")
  @RequestMapping(value = {"/update/{id}"}, method = RequestMethod.POST)
  @PreAuthorize("hasAuthority('product')")
  @ResponseBody
  public void UpdateOneSeafood(
    @PathVariable("id") String id,
    @Validated @RequestBody Seafood seafood) {
    productService.updateOneSeafood(id, seafood);
  }
}
