package com.macro.mall.tiny.service;

import com.macro.mall.tiny.mbg.model.Seafood;

import java.util.List;

public interface ProductService {
  List<Seafood> selectAllSeafood();

  List<Seafood> selectOneSeafood(String name);


  int getCountById(int id);

  void increase(int id);
  void decrease(int id);
  int getPriceById(int id);
}
