package com.macro.mall.tiny.service;

import com.macro.mall.tiny.mbg.model.Seafood;

import java.util.List;

public interface ProductService {
  List<Seafood> selectAllSeafood();

  List<Seafood> selectOneSeafood(String name);

  /**
   * 指定idのシーフードの数量を取得
   * @param id
   * @return  int 数量
   */
  int getCountById(int id);
  /**
   * 指定idのシーフードの数量+1
   * @param id
   */
  void increase(int id);
  /**
   * 指定idのシーフードの数量-1
   * @param id
   */
  void decrease(int id);
  /**
   * 指定idのシーフードの値段を取得
   * @param id
   * @return int 値段を返す
   */
  int getPriceById(int id);

  /**
   * 指定idのシーフードを削除
   * @param id
   */
  void deleteSeafoodById(int id);
}
