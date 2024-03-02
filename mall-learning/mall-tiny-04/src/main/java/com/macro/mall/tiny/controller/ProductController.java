package com.macro.mall.tiny.controller;

import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.mbg.model.Seafood;
import com.macro.mall.tiny.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/product")
@Api(tags = "ProductController")
@Tag(name = "ProductController", description = "商品管理")
@Controller
public class ProductController {
  private final ProductService service;

  @Autowired
  public ProductController(ProductService service) {
    this.service = service;
  }


  @ApiOperation("获取Seafood菜单")
  @ResponseBody
  @RequestMapping(value = "get/listAll", method = RequestMethod.GET)
  @PreAuthorize("hasAuthority('product')")
  public CommonResult<List<Seafood>> getSeafoodList() {
    return CommonResult.success(service.selectAllSeafood());
  }

  @ApiOperation("获取单个Seafood")
  @RequestMapping(value = "get/{name}", method = RequestMethod.GET)
  @PreAuthorize("hasAuthority('product')")
  @ResponseBody
  public CommonResult<List<Seafood>> getOneSeafood(@PathVariable("name") String name) {
    return CommonResult.success(service.selectOneSeafood(name));
  }

  @ApiOperation("用id获取Seafood数量")
  @GetMapping("/{id}/count")
  public ResponseEntity<Object> getCountById(@PathVariable int id) {
    int count = service.getCountById(id);
    if (count == -1) {
      // Handle the case where the resource is not found
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seafood with ID " + id + " not found.");
    }
    return ResponseEntity.ok(count);
  }

  @ApiOperation("更新Seafood")
  @RequestMapping(value = "/{id}/increment", method = RequestMethod.POST)
  @PreAuthorize("hasAuthority('product')")
  public ResponseEntity<Object> incrementCount(@PathVariable int id) {
    int count = service.getCountById(id);
    if (count == -1) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seafood with ID " + id + " not found.");
    }
    service.updateCount(id);
    return ResponseEntity.ok("Count incremented successfully for seafood with ID " + id);
  }
}
