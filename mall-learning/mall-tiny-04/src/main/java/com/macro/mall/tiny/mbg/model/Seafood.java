package com.macro.mall.tiny.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class Seafood implements Serializable {
  @ApiModelProperty(value = "id")
  private Long id;
  @ApiModelProperty(value = "商品名称")
  private String name;
  @ApiModelProperty(value = "数量")
  private Integer count;

  @ApiModelProperty(value="商品描述")
  private  String description;
  @ApiModelProperty(value = "单价")
  private Integer unitPrice;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(Integer unitPrice) {
    this.unitPrice = unitPrice;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  @ApiModelProperty(value = "总价")
  private Integer price;

  @ApiModelProperty(value = "图像")
  private String image;
}
