package com.macro.mall.tiny.service.impl;

import com.macro.mall.tiny.mbg.mapper.ProductMapper;
import com.macro.mall.tiny.mbg.model.Seafood;
import com.macro.mall.tiny.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
  @Autowired
  private ProductMapper mapper;

  @Override
  public List<Seafood> selectAllSeafood() {
    return mapper.selectAll(new Seafood());
  }

  @Override
  public List<Seafood> selectOneSeafood(String name) {
    return mapper.selectByName(name);
  }
  @Override
  public int updateOneSeafood(String id,Seafood seafood) {
    Seafood newSeafood= new Seafood();
    int newCount = seafood.getCount() + 1;
    newSeafood.setId(id);
    newSeafood.setCount(newCount);
    return  mapper.updateOneSeafood(newSeafood);
  }
}
