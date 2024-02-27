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
  public List<Seafood>selectAllSeafood(){
  return mapper.selectAll(new Seafood());}
}