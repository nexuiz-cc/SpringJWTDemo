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
@Tag(name = "ProductController", description = "商品の管理")
@Controller
public class ProductController {
  private final ProductService service;

  @Autowired
  public ProductController(ProductService service) {
    this.service = service;
  }


  @ApiOperation("シーフードのメニューを取得")
  @ResponseBody
  @RequestMapping(value = "get/listAll", method = RequestMethod.GET)
  @PreAuthorize("hasAuthority('product')")
  public CommonResult<List<Seafood>> getSeafoodList() {
    return CommonResult.success(service.selectAllSeafood());
  }

  @ApiOperation("メニュー項目を検索")
  @RequestMapping(value = "get/{name}", method = RequestMethod.GET)
  @PreAuthorize("hasAuthority('product')")
  @ResponseBody
  public CommonResult<List<Seafood>> getOneSeafood(@PathVariable("name") String name) {
    return CommonResult.success(service.selectOneSeafood(name));
  }

  @ApiOperation("指定idのメニュー項目数量を取得")
  @GetMapping("get/count/{id}")
  public ResponseEntity<Object> getCountById(@PathVariable int id) {
    int count = service.getCountById(id);
    if (count == -1) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seafood with ID " + id + " not found.");
    }
    return ResponseEntity.ok(count);
  }

  @ApiOperation("指定idのメニュー項目数量+1")
  @RequestMapping(value = "update/increase/{id}/", method = RequestMethod.POST)
  @PreAuthorize("hasAuthority('product')")
  public ResponseEntity<Object> increase(@PathVariable int id) {
    int count = service.getCountById(id);
    int price = service.getPriceById(id);
    if (count == -1) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seafood with ID " + id + " not found.");
    }
    service.increase(id);
    String countMsg = "Count has increase successfully to 「" + count + "」,";
    String priceMsg = "price has changed to「" + price + "」";
    String idMsg = "seafood with ID 「" + id + "」.";
    return ResponseEntity.ok(countMsg + priceMsg + idMsg);
  }

  @ApiOperation("指定idのメニュー項目数量-1")
  @RequestMapping(value = "update/decrease/{id}/", method = RequestMethod.POST)
  @PreAuthorize("hasAuthority('product')")
  public ResponseEntity<Object> decrease(@PathVariable int id) {
    int count = service.getCountById(id);
    int price = service.getPriceById(id);
    if (count == -1) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("Seafood with ID " + id + " not found.");
    }
    service.decrease(id);
    String countMsg = "Count has decreased successfully to 「" + count + "」,";
    String priceMsg = "price has changed to「" + price + "」";
    String idMsg = "seafood with ID 「" + id + "」.";
    return ResponseEntity.ok(countMsg + priceMsg + idMsg);
  }

  @ApiOperation("指定idのシーフードを削除")
  @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
  @PreAuthorize("hasAuthority('product')")
  public ResponseEntity<Object> deleteSeafood(@PathVariable int id) {
    service.deleteSeafoodById(id);
    return ResponseEntity.ok("Seafood with ID " + id + " has been deleted.");
  }
}
