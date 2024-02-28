package com.macro.mall.tiny.service;

import com.macro.mall.tiny.mbg.model.Seafood;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {
  List<Seafood> selectAllSeafood();

  List<Seafood> selectOneSeafood(String name);

  @Transactional
  int updateOneSeafood(String id, Seafood seafood);
}
